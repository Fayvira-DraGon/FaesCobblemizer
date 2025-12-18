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
import net.minecraft.util.TypedActionResult.fail
import net.minecraft.util.TypedActionResult.success
import net.minecraft.world.World

class BottleCapItem(
  val stat: Stat? = null,
  val iv: Int = MAX_VALUE,
  settings: Settings = Settings().maxCount(16)
) : CobblemonItem(settings), PokemonSelectingItem {
  constructor() : this(null, MAX_VALUE)
  constructor(stat: Stat? = null) : this(stat, MAX_VALUE)
  constructor(iv: Int = MAX_VALUE) : this(null, iv)

  override val bagItem: BagItem? = null
  val success: SoundEvent = ITEM_USE
  val failure: SoundEvent = PC_CLICK
  val acceptableRange = 0.. MAX_VALUE

  override fun appendTooltip(stack: ItemStack, context: TooltipContext, tooltip: MutableList<Text>, type: TooltipType) {
    tooltip.add(Text.of((if (iv < 0) "Randomize " else "Set ") + (if (stat == null) "All of a Pokémon's IVs" else "a Pokémon's ${stat.displayName.string} IV") + (if (iv < 0) "" else " to ${iv.coerceIn(acceptableRange)}")))
    super.appendTooltip(stack, context, tooltip, type)
  }

  override fun applyToPokemon(
    player: ServerPlayerEntity,
    stack: ItemStack,
    pokemon: Pokemon
  ): TypedActionResult<ItemStack> {
    if (stat == null) {
      if (iv < 0) {
        val ivs: IVs = pokemon.ivs
        while (true) {
          pokemon.ivs.filter { it.key.type == PERMANENT }.forEach { pokemon.ivs[it.key] = acceptableRange.random() }
          if (pokemon.ivs.filter { it.key.type == PERMANENT } != ivs.filter { it.key.type == PERMANENT }) {
            pokemon.ivs.filter { it.key.type == PERMANENT }.forEach { player.sendMessage(Text.of("Pokémon's ${it.key.displayName.string} IV is now ${pokemon.ivs[it.key]}")) }
            break
          }
        }
      } else {
        if (pokemon.ivs.none { it.value != iv.coerceIn(acceptableRange) }) {
          player.sendMessage(Text.of("Pokémon's IVs are each already $iv"))
          pokemon.entity?.playSound(failure, 1F, 1F)
          return fail(stack)
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
        return fail(stack)
      } else {
        pokemon.ivs[stat] = newIV
        player.sendMessage(Text.of("Pokémon's ${stat.displayName.string} IV is now $newIV"))
      }
    }
    pokemon.entity?.playSound(success, 1F, 1F)
    stack.decrementUnlessCreative(1, player)
    return success(stack)
  }

  override fun use(world: World, user: PlayerEntity, hand: Hand): TypedActionResult<ItemStack> {
    if (user is ServerPlayerEntity) {
      return use(user, user.getStackInHand(hand))
    }
    return success(user.getStackInHand(hand))
  }
}
