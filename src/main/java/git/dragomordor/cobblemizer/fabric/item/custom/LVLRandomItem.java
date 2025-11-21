package git.dragomordor.cobblemizer.fabric.item.custom;

import com.cobblemon.mod.common.Cobblemon;
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import com.cobblemon.mod.common.pokemon.Pokemon;
import net.minecraft.item.Item;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;

import java.util.List;
import java.util.Random;

public class LVLRandomItem extends PokemonUseItem {

  public LVLRandomItem() {
    super(new Item.Settings().maxCount(1));
  }

  @Override
  public ActionResult processInteraction(ItemStack itemStack, PlayerEntity player, PokemonEntity target, Pokemon pokemon) {
    int maxLevel = Cobblemon.config.getMaxPokemonLevel(); // maximum level of Pokémon allowed by config
    int minLevel = 1;
    int randomLevel = new Random().nextInt(maxLevel - minLevel + 1) + minLevel;
    pokemon.setLevel(randomLevel);

    itemStack.decrement(1); // remove item after use
    player.sendMessage(Text.of("Pokémon level randomized"));
    return ActionResult.SUCCESS;
  }

  @Override
  public void appendTooltip(ItemStack itemStack, TooltipContext tooltipContext, List<Text> list, TooltipType tooltipType) {
    list.add(Text.of("Randomize Pokémon's Level"));

    super.appendTooltip(itemStack, tooltipContext, list, tooltipType);
  }
}
