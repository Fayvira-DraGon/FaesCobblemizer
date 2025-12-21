package github.fayvira.fabric.cobblemizer.item.custom

import com.cobblemon.mod.common.Cobblemon.config
import com.cobblemon.mod.common.CobblemonSounds.ITEM_USE
import com.cobblemon.mod.common.CobblemonSounds.PC_CLICK
import com.cobblemon.mod.common.api.item.PokemonSelectingItem
import com.cobblemon.mod.common.item.CobblemonItem
import com.cobblemon.mod.common.item.battle.BagItem
import com.cobblemon.mod.common.pokemon.Pokemon
import github.fayvira.fabric.cobblemizer.Cobblemizer.Companion.LOGGER
import github.fayvira.fabric.cobblemizer.Cobblemizer.Companion.maxLevel
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

class LevelCandyItem(
  val level: Int = (maxLevel ?: 100),
  settings: Settings = Settings().maxCount(16)
) : CobblemonItem(settings), PokemonSelectingItem {

  override val bagItem: BagItem? = null
  val success: SoundEvent = ITEM_USE
  val failure: SoundEvent = PC_CLICK

  override fun appendTooltip(stack: ItemStack, context: TooltipContext, tooltip: MutableList<Text>, type: TooltipType) {
    try {
      maxLevel = config.maxPokemonLevel
    } catch (e: Exception) {
      LOGGER.info("appendTooltip - config.maxPokemonLevel: ${e.message ?: "null"}")
    }

    tooltip.add(Text.of(if (level < 1) "Randomize Pokémon's Level" else "Set Pokémon's Level to ${level.coerceIn(1..(maxLevel ?: 100))}"))
    super.appendTooltip(stack, context, tooltip, type)
  }

  override fun applyToPokemon(
    player: ServerPlayerEntity,
    stack: ItemStack,
    pokemon: Pokemon
  ): TypedActionResult<ItemStack> {
    try {
      maxLevel = config.maxPokemonLevel
    } catch (e: Exception) {
      LOGGER.info("applyToPokemon - config.maxPokemonLevel: ${e.message ?: "null"}")
    }

    val newLevel: Int = if (level < 1) {
      val random: Int = (1..<(maxLevel ?: 100)).random()
      if (random >= pokemon.level) random + 1 else random
    } else level.coerceIn(1..(maxLevel ?: 100))
    return if (newLevel == pokemon.level) {
      player.sendMessage(Text.of("Pokémon's Level is already $newLevel"))
      pokemon.entity?.playSound(failure, 1F, 1F)
      pass(stack)
    } else {
      pokemon.level = newLevel
      pokemon.setFriendship(if (level == 1) pokemon.friendship / 2 else (pokemon.friendship + level ))
      player.sendMessage(Text.of("Pokémon's Level is now $newLevel"))
      pokemon.entity?.playSound(success, 1F, 1F)
      stack.decrementUnlessCreative(1, player)
      success(stack)
    }
  }

  override fun canUseOnPokemon(stack: ItemStack, pokemon: Pokemon): Boolean = pokemon.isPlayerOwned()

  override fun use(world: World, user: PlayerEntity, hand: Hand): TypedActionResult<ItemStack> = if (user is ServerPlayerEntity) use(user, user.getStackInHand(hand)) else success(user.getStackInHand(hand))
}
