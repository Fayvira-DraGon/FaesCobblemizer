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

class GenderItem(
  val fluid: Boolean = false,
  settings: Settings = Settings().maxCount(16)
) : CobblemonItem(settings), PokemonSelectingItem {

  override val bagItem: BagItem? = null
  val success: SoundEvent = ITEM_USE
  val failure: SoundEvent = PC_CLICK

  override fun appendTooltip(stack: ItemStack, context: TooltipContext, tooltip: MutableList<Text>, type: TooltipType) {
    tooltip.add(Text.of(if (fluid) "Swap Pokémon's Gender Appearance" else "Swap Pokémon's Gender"))
    super.appendTooltip(stack, context, tooltip, type)
  }

  override fun applyToPokemon(
    player: ServerPlayerEntity,
    stack: ItemStack,
    pokemon: Pokemon
  ): TypedActionResult<ItemStack> {
    return if (fluid) {
      player.sendMessage(Text.of("Gender Fluid is not yet ready for use!"))
      // player.sendMessage(Text.of("Swapped Pokémon's Gender Appearance!"))
      pokemon.entity?.playSound(failure, 1F, 1F)
      // pokemon.entity?.playSound(success, 1F, 1F)
      pass(stack)
      // success(stack)
    } else {
      if (pokemon.gender == Gender.GENDERLESS) {
        player.sendMessage(Text.of("Pokémon must not be ${pokemon.gender.name}!"))
        pokemon.entity?.playSound(failure, 1F, 1F)
        pass(stack)
      } else {
        pokemon.gender = if (pokemon.gender == Gender.MALE) Gender.FEMALE else Gender.MALE
        player.sendMessage(Text.of("Pokémon's Gender is now ${pokemon.gender.name}!"))
        pokemon.entity?.playSound(success, 1F, 1F)
        stack.decrementUnlessCreative(1, player)
        success(stack)
      }
    }
  }

  override fun canUseOnPokemon(stack: ItemStack, pokemon: Pokemon): Boolean = pokemon.isPlayerOwned()

  override fun use(world: World, user: PlayerEntity, hand: Hand): TypedActionResult<ItemStack> = if (user is ServerPlayerEntity) use(user, user.getStackInHand(hand)) else success(user.getStackInHand(hand))
}
