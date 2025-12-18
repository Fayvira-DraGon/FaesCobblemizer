package github.fayvira.fabric.cobblemizer.item.custom

import com.cobblemon.mod.common.Cobblemon.config
import com.cobblemon.mod.common.CobblemonSounds.ITEM_USE
import com.cobblemon.mod.common.CobblemonSounds.PC_CLICK
import com.cobblemon.mod.common.api.item.PokemonSelectingItem
import com.cobblemon.mod.common.item.CobblemonItem
import com.cobblemon.mod.common.item.battle.BagItem
import com.cobblemon.mod.common.pokemon.Pokemon
import github.fayvira.fabric.cobblemizer.Cobblemizer.Companion.maxFriendship
import github.fayvira.fabric.cobblemizer.Cobblemizer.Companion.LOGGER
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

class FriendshipCubeItem(
  val friendship: Int = (maxFriendship ?: 255),
  settings: Settings = Settings().maxCount(16)
) : CobblemonItem(settings), PokemonSelectingItem {

  override val bagItem: BagItem? = null
  val success: SoundEvent = ITEM_USE
  val failure: SoundEvent = PC_CLICK

  override fun appendTooltip(stack: ItemStack, context: TooltipContext, tooltip: MutableList<Text>, type: TooltipType) {
    try {
      maxFriendship = config.maxPokemonFriendship
    } catch (e: Exception) {
      LOGGER.info("appendTooltip - config.maxPokemonFriendship: ${e.message ?: "null"}")
    }

    tooltip.add(Text.of((if (friendship == 0) "Randomize Pokémon's Friendship" else "Set Pokémon's Friendship to ${friendship.coerceIn(1..(maxFriendship ?: 255))}")))
    super.appendTooltip(stack, context, tooltip, type)
  }

  override fun applyToPokemon(
    player: ServerPlayerEntity,
    stack: ItemStack,
    pokemon: Pokemon
  ): TypedActionResult<ItemStack> {
    try {
      maxFriendship = config.maxPokemonFriendship
    } catch (e: Exception) {
      LOGGER.info("applyToPokemon - config.maxPokemonFriendship: ${e.message ?: "null"}")
    }

    val newFriendship: Int = if (friendship == 0) {
      val random: Int = (1..<(maxFriendship ?: 255)).random()
      if (random >= pokemon.friendship) random + 1  else random
    } else friendship.coerceIn(1..(maxFriendship ?: 255))
    return if (newFriendship == pokemon.friendship) {
      player.sendMessage(Text.of("Pokémon's Friendeship is already $newFriendship"))
      pokemon.entity?.playSound(failure, 1F, 1F)
      fail(stack)
    } else {
      pokemon.setFriendship(newFriendship)
      player.sendMessage(Text.of("Pokémon's Friendeship is now $newFriendship"))
      pokemon.entity?.playSound(success, 1F, 1F)
      stack.decrementUnlessCreative(1, player)
      success(stack)
    }
  }

  override fun use(world: World, user: PlayerEntity, hand: Hand): TypedActionResult<ItemStack> {
    if (user is ServerPlayerEntity) {
      return use(user, user.getStackInHand(hand))
    }
    return success(user.getStackInHand(hand))
  }
}
