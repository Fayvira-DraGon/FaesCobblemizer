package git.dragomordor.cobblemizer.fabric.item.custom;

import com.cobblemon.mod.common.api.pokemon.stats.Stat;
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

import static git.dragomordor.cobblemizer.fabric.config.CobblemizerConfig.IV_TIERS;
import static git.dragomordor.cobblemizer.fabric.config.TierRarityClass.getTierAmount;

public class IVAddItem extends PokemonUseItem {
  private final String tier;
  private final Stat statToBoost;

  public IVAddItem(String tier, Stat statToBoost) {
    super(new Item.Settings().maxCount(1));
    this.tier = tier;
    this.statToBoost = statToBoost;
  }

  @Override
  public ActionResult processInteraction(ItemStack itemStack, PlayerEntity player, PokemonEntity target, Pokemon pokemon) {
    IVs ivs = pokemon.getIvs(); // Get IV values
    int tierAmount = getTierAmount(IV_TIERS, tier); // Get tierAmount from config
    int ivAmount = ivs.get(this.statToBoost) instanceof Integer ivBoxed ? ivBoxed : 0; // Get unboxed IV amount
    ivs.set(statToBoost, ivAmount + tierAmount); // Increase IV value
    int ivNewAmount = (ivs.get(this.statToBoost) instanceof Integer ivBoxed ? ivBoxed : 0); // Get new unboxed IV amount

    if (ivNewAmount == ivAmount) { // If IV is already at max, return pass
      player.sendMessage(Text.of("Pokémon already has the maximum " + statToBoost.getDisplayName().getString() + " IV"));
      return ActionResult.PASS;
    }

    player.sendMessage(Text.of("Increased Pokémon's " + statToBoost.getDisplayName().getString() + " IV by " + (ivNewAmount - ivAmount)));
    itemStack.decrement(1); // remove item after use
    return ActionResult.SUCCESS;
  }

  @Override
  public void appendTooltip(ItemStack itemStack, TooltipContext tooltipContext, List<Text> list, TooltipType tooltipType) {
    list.add(Text.of("Increase Pokémon's " + statToBoost.getDisplayName().getString() + " IV by up to " + getTierAmount(IV_TIERS, tier)));

    super.appendTooltip(itemStack, tooltipContext, list, tooltipType);
  }
}
