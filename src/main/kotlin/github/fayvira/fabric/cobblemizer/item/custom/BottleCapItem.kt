package github.fayvira.fabric.cobblemizer.item.custom

import com.cobblemon.mod.common.CobblemonSounds.ITEM_USE
import com.cobblemon.mod.common.CobblemonSounds.PC_CLICK
import com.cobblemon.mod.common.api.item.PokemonSelectingItem
import com.cobblemon.mod.common.api.pokemon.stats.Stat
import com.cobblemon.mod.common.api.pokemon.stats.Stat.Type.PERMANENT
import com.cobblemon.mod.common.item.CobblemonItem
import com.cobblemon.mod.common.item.battle.BagItem
import com.cobblemon.mod.common.pokemon.IVs
import com.cobblemon.mod.common.pokemon.IVs.Companion.MAX_VALUE
import com.cobblemon.mod.common.pokemon.Pokemon
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.item.tooltip.TooltipType
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.sound.SoundEvent
import net.minecraft.text.Text
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import net.minecraft.util.TypedActionResult.pass
import net.minecraft.util.TypedActionResult.success
import net.minecraft.world.World

class BottleCapItem(
  val stat: Stat? = null,
  val iv: Int = MAX_VALUE,
  settings: Settings = Settings().maxCount(16)
) : CobblemonItem(settings), PokemonSelectingItem {

  override val bagItem: BagItem? = null
  val success: SoundEvent = ITEM_USE
  val failure: SoundEvent = PC_CLICK
  val acceptableRange = 0.. MAX_VALUE

  override fun appendTooltip(stack: ItemStack, context: TooltipContext, tooltip: MutableList<Text>, type: TooltipType) {
    tooltip.add(Text.of(if (iv < 0) "Randomize all of Pokémon's IVs" else (if (stat == null) "Set all of Pokémon's IVs to ${iv.coerceIn(acceptableRange)}" else "Set Pokémon's ${stat.displayName.string} IV to ${iv.coerceIn(acceptableRange)}")))
    super.appendTooltip(stack, context, tooltip, type)
  }

  override fun applyToPokemon(
    player: ServerPlayerEntity,
    stack: ItemStack,
    pokemon: Pokemon
  ): TypedActionResult<ItemStack> {
    if (stat == null) {
      if (iv < 0) {
        var count = 6
        do {
          val ivs: IVs = IVs.createRandomIVs()
          pokemon.ivs.filter { it.key.type == PERMANENT }.forEach {
            if (pokemon.ivs[it.key] != (ivs[it.key]!!)) count -= 1
            pokemon.ivs[it.key] = (ivs[it.key]!!)
          }
        } while (count == 6)
        player.sendMessage(Text.of("Randomized Pokémon's IVs!"))
        player.sendMessage(Text.of("======================================"))
        pokemon.ivs.filter { it.key.type == PERMANENT }.forEach { player.sendMessage(Text.of("Pokémon's ${it.key.displayName.string} IV is now ${pokemon.ivs[it.key]}")) }
      } else {
        if (pokemon.ivs.none { it.value != iv.coerceIn(acceptableRange) }) {
          player.sendMessage(Text.of("Pokémon's IVs are each already $iv"))
          pokemon.entity?.playSound(failure, 1F, 1F)
          return pass(stack)
        }
        pokemon.ivs.filter { it.key.type == PERMANENT && it.value != iv.coerceIn(acceptableRange) }.forEach { pokemon.ivs[it.key] = iv.coerceIn(acceptableRange) }
        player.sendMessage(Text.of("Pokémon's IVs are each now $iv"))
      }
    } else {
      val newIV = if (iv < 0) {
        val random: Int = acceptableRange.minus(acceptableRange.last).random()
        if (random == pokemon.ivs[stat]) random + 1 else random
      } else iv.coerceIn(acceptableRange)
      if (newIV == pokemon.ivs[stat]) {
        player.sendMessage(Text.of("Pokémon's ${stat.displayName.string} IV is already $iv"))
        pokemon.entity?.playSound(failure, 1F, 1F)
        return pass(stack)
      } else {
        pokemon.ivs[stat] = newIV
        player.sendMessage(Text.of("Pokémon's ${stat.displayName.string} IV is now $newIV"))
      }
    }
    pokemon.entity?.playSound(success, 1F, 1F)
    stack.decrementUnlessCreative(1, player)
    return success(stack)
  }

  override fun canUseOnPokemon(stack: ItemStack, pokemon: Pokemon): Boolean = pokemon.isPlayerOwned()

  override fun use(world: World, user: PlayerEntity, hand: Hand): TypedActionResult<ItemStack> = if (user is ServerPlayerEntity) use(user, user.getStackInHand(hand)) else success(user.getStackInHand(hand))
}
