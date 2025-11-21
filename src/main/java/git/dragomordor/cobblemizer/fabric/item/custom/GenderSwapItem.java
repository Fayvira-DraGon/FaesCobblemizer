package git.dragomordor.cobblemizer.fabric.item.custom;

import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import com.cobblemon.mod.common.pokemon.Gender;
import com.cobblemon.mod.common.pokemon.Pokemon;
import net.minecraft.item.Item;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class GenderSwapItem extends PokemonUseItem {
  public GenderSwapItem() { super(new Item.Settings().maxCount(1)); }

  @Override
  public ActionResult processInteraction(ItemStack itemStack, PlayerEntity player, PokemonEntity target, Pokemon pokemon) {
    Gender gender = pokemon.getGender();

    // if gender is genderless, it cannot swap
    if (gender == Gender.GENDERLESS) {
      player.sendMessage(Text.of("Pokémon Gender cannot be Gender-Unknown"));
      return ActionResult.FAIL;
    }

    // swap male -> female and female -> male
    Gender newGender = (gender == Gender.MALE ? Gender.FEMALE : Gender.MALE);
    pokemon.setGender(newGender);
    player.sendMessage(Text.of("The Pokémon's gender has been changed to " + StringUtils.capitalize(newGender.name().toLowerCase())));

    itemStack.decrement(1); // remove item after use
    return ActionResult.SUCCESS;
  }

  @Override
  public void appendTooltip(ItemStack itemStack, TooltipContext tooltipContext, List<Text> list, TooltipType tooltipType) {
    list.add(Text.of("Swap Pokémon's Gender"));

    super.appendTooltip(itemStack, tooltipContext, list, tooltipType);
  }
}
