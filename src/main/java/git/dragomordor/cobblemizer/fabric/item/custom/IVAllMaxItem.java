package git.dragomordor.cobblemizer.fabric.item.custom;

import com.cobblemon.mod.common.api.pokemon.stats.Stat;
import com.cobblemon.mod.common.api.pokemon.stats.Stats;
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import com.cobblemon.mod.common.pokemon.IVs;
import com.cobblemon.mod.common.pokemon.Pokemon;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;

import java.util.List;

public class IVAllMaxItem extends PokemonUseItem {

  public IVAllMaxItem() {
    super(new Item.Settings().maxCount(1));
  }

  @Override
  public ActionResult processInteraction(ItemStack itemStack, PlayerEntity player, PokemonEntity target, Pokemon pokemon) {
    IVs ivs = pokemon.getIvs(); // Access the IVs of the Pokémon

    for (Stat stat : Stats.values()) { // Loop through the Stats
      if (ivs.get(stat) instanceof Integer iv && iv < IVs.MAX_VALUE)
        ivs.set(stat, IVs.MAX_VALUE); // if Stat IV is NotNull set it to IV Max Value
    }

    itemStack.decrement(1); // remove item after use
    player.sendMessage(Text.of("Maximized All IVs of Pokémon"));
    return ActionResult.SUCCESS;
  }

  @Override
  public void appendTooltip(ItemStack itemStack, TooltipContext tooltipContext, List<Text> list, TooltipType tooltipType) {
    list.add(Text.of("Maximize All IVs of Pokémon"));

    super.appendTooltip(itemStack, tooltipContext, list, tooltipType);
  }
}
