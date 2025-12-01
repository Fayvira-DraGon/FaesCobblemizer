package git.dragomordor.cobblemizer.fabric.item.custom;

import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import com.cobblemon.mod.common.pokemon.Pokemon;
import net.minecraft.item.Item;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;

import java.util.List;

import static git.dragomordor.cobblemizer.fabric.config.CobblemizerConfig.LVL_TIERS;
import static git.dragomordor.cobblemizer.fabric.config.TierRarityClass.getTierAmount;

public class LVLAddItem extends PokemonUseItem {
  private final String tier;

  public LVLAddItem(String tier) {
    // super(new FabricItemSettings().maxCount(1));
    super(new Item.Settings().maxCount(1));
    this.tier = tier;
  }

  @Override
  public ActionResult processInteraction(ItemStack itemStack, PlayerEntity player, PokemonEntity target, Pokemon pokemon) {
    int level = pokemon.getLevel(); // Get Pokémon's Level
    int tierAmount = getTierAmount(LVL_TIERS, tier); // Get tierAmount from config

    pokemon.setLevel(level + tierAmount); // Increase level

    if (pokemon.getLevel() == level) { // If Level is already at max, return pass
      player.sendMessage(Text.of("Pokémon already has the maximum level"));
      return ActionResult.PASS;
    }

    player.sendMessage(Text.of("Increased Pokémon's Level by " + (pokemon.getLevel() - level) + " to " + pokemon.getLevel()));

    itemStack.decrement(1); // remove item after use
    return ActionResult.SUCCESS;
  }

  @Override
  public void appendTooltip(ItemStack itemStack, TooltipContext tooltipContext, List<Text> list, TooltipType tooltipType) {
    list.add(Text.of("Increase Pokémon's Level by up to " + getTierAmount(LVL_TIERS, tier)));

    super.appendTooltip(itemStack, tooltipContext, list, tooltipType);
  }
}
