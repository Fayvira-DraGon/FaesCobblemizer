package github.fayvira.fabric.cobblemizer.item.custom

import com.cobblemon.mod.common.CobblemonSounds.ITEM_USE
import com.cobblemon.mod.common.CobblemonSounds.PC_CLICK
import com.cobblemon.mod.common.api.item.PokemonSelectingItem
import com.cobblemon.mod.common.item.CobblemonItem
import com.cobblemon.mod.common.item.battle.BagItem
import com.cobblemon.mod.common.pokeball.PokeBall
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

class ShiftBallItem(
  val selectedBall: PokeBall,
  settings: Settings = Settings().maxCount(16)
) : CobblemonItem(settings), PokemonSelectingItem {

  override val bagItem: BagItem? = null
  val success: SoundEvent = ITEM_USE
  val failure: SoundEvent = PC_CLICK

  override fun appendTooltip(stack: ItemStack, context: TooltipContext, tooltip: MutableList<Text>, type: TooltipType) {
    tooltip.add(Text.of("Shift Pokémon into the ${selectedBall.item().name.string}"))
    super.appendTooltip(stack, context, tooltip, type)
  }

  override fun applyToPokemon(
    player: ServerPlayerEntity,
    stack: ItemStack,
    pokemon: Pokemon
  ): TypedActionResult<ItemStack> {
    return if (selectedBall == pokemon.caughtBall) {
      player.sendMessage(Text.of("Pokémon is already in the $selectedBall"))
      pokemon.entity?.playSound(failure, 1F, 1F)
      fail(stack)
    } else {
      pokemon.caughtBall = selectedBall
      player.sendMessage(Text.of("Pokémon is now in the $selectedBall"))
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
