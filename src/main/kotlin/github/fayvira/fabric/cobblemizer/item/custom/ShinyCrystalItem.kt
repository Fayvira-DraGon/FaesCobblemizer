package github.fayvira.fabric.cobblemizer.item.custom

import com.cobblemon.mod.common.CobblemonSounds.ITEM_USE
import com.cobblemon.mod.common.api.item.PokemonSelectingItem
import com.cobblemon.mod.common.item.CobblemonItem
import com.cobblemon.mod.common.item.battle.BagItem
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

class ShinyCrystalItem(
  settings: Settings = Settings().maxCount(16)
) : CobblemonItem(settings), PokemonSelectingItem {

  override val bagItem: BagItem? = null
  val success: SoundEvent = ITEM_USE

  override fun appendTooltip(stack: ItemStack, context: TooltipContext, tooltip: MutableList<Text>, type: TooltipType) {
    tooltip.add(
      Text.of("Swap Pokémon's Shiny Status")
    )
    super.appendTooltip(stack, context, tooltip, type)
  }

  override fun applyToPokemon(
    player: ServerPlayerEntity,
    stack: ItemStack,
    pokemon: Pokemon
  ): TypedActionResult<ItemStack> {
    if (!player.world.isClient) {
      pokemon.shiny = !pokemon.shiny
      player.sendMessage(Text.of("Pokémon is no${if (pokemon.shiny) "w" else " longer"} shiny"))
      pokemon.entity?.playSound(success, 1F, 1F)
      stack.decrementUnlessCreative(1, player)
      return success(stack)
    }
    return pass(stack)
  }

  override fun canUseOnPokemon(stack: ItemStack, pokemon: Pokemon): Boolean = pokemon.isPlayerOwned()

  override fun use(world: World, user: PlayerEntity, hand: Hand): TypedActionResult<ItemStack> = if (user is ServerPlayerEntity) use(user, user.getStackInHand(hand)) else success(user.getStackInHand(hand))
}
