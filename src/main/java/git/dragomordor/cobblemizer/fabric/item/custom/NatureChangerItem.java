package git.dragomordor.cobblemizer.fabric.item.custom;

import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import com.cobblemon.mod.common.pokemon.Nature;
import com.cobblemon.mod.common.pokemon.Pokemon;
import net.minecraft.item.Item;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class NatureChangerItem extends PokemonUseItem {
  private final Nature nature;

  public NatureChangerItem(Nature nature) {
    super(new Item.Settings().maxCount(1));
    this.nature = nature;
  }

  @Override
  public ActionResult processInteraction(ItemStack itemStack, PlayerEntity player, PokemonEntity target, Pokemon pokemon) {
    Nature currentNature = pokemon.getNature(); // get current Pokémon nature

    // same nature is failed
    if (currentNature == nature) {
      player.sendMessage(Text.of("Pokémon is already " + StringUtils.capitalize(nature.getName().getPath().toLowerCase())));
      return ActionResult.FAIL;
    }

    // change nature
    pokemon.setNature(nature);
    player.sendMessage(Text.of("Pokémon is now " + StringUtils.capitalize(nature.getName().getPath().toLowerCase())));
    itemStack.decrement(1); // remove item after use
    return ActionResult.SUCCESS;
  }

  @Override
  public void appendTooltip(ItemStack itemStack, TooltipContext tooltipContext, List<Text> list, TooltipType tooltipType) {
    list.add(Text.of("Change a Pokémon's Nature to " + StringUtils.capitalize(nature.getName().getPath().toLowerCase())));

    super.appendTooltip(itemStack, tooltipContext, list, tooltipType);
  }
}
