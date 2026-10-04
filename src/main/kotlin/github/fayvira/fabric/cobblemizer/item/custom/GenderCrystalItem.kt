package github.fayvira.fabric.cobblemizer.item.custom

import com.cobblemon.mod.common.CobblemonSounds.ITEM_USE
import com.cobblemon.mod.common.CobblemonSounds.PC_CLICK
import com.cobblemon.mod.common.api.item.PokemonSelectingItem
import com.cobblemon.mod.common.item.CobblemonItem
import com.cobblemon.mod.common.item.battle.BagItem
import com.cobblemon.mod.common.pokemon.Gender
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

class GenderCrystalItem(
  settings: Settings = Settings().maxCount(16)
) : CobblemonItem(settings), PokemonSelectingItem {

  override val bagItem: BagItem? = null
  val success: SoundEvent = ITEM_USE
  val failure: SoundEvent = PC_CLICK

  override fun appendTooltip(stack: ItemStack, context: TooltipContext, tooltip: MutableList<Text>, type: TooltipType) {
    tooltip.add(
      Text.of("Swap Pokémon's Gender")
    )
    super.appendTooltip(stack, context, tooltip, type)
  }

  override fun applyToPokemon(
    player: ServerPlayerEntity,
    stack: ItemStack,
    pokemon: Pokemon
  ): TypedActionResult<ItemStack> {
    if (!player.world.isClient) {
      if (pokemon.gender == Gender.GENDERLESS) {
        player.sendMessage(Text.of("Pokémon must not be ${pokemon.gender.name}!"))
        pokemon.entity?.playSound(failure, 1F, 1F)
        return pass(stack)
      } else {
        pokemon.gender = if (pokemon.gender == Gender.MALE) Gender.FEMALE else Gender.MALE
        player.sendMessage(Text.of("Pokémon's Gender is now ${pokemon.gender.name}!"))
        pokemon.entity?.playSound(success, 1F, 1F)
        stack.decrementUnlessCreative(1, player)
        return success(stack)
      }
    }
    return pass(stack)
  }

  override fun canUseOnPokemon(stack: ItemStack, pokemon: Pokemon): Boolean = pokemon.isPlayerOwned()

  override fun use(world: World, user: PlayerEntity, hand: Hand): TypedActionResult<ItemStack> = if (user is ServerPlayerEntity) use(user, user.getStackInHand(hand)) else success(user.getStackInHand(hand))
}
