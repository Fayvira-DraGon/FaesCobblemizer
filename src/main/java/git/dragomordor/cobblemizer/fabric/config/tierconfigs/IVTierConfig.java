package git.dragomordor.cobblemizer.fabric.config.tierconfigs;

import git.dragomordor.cobblemizer.fabric.config.TierRarityClass;

import java.util.ArrayList;
import java.util.List;

public class IVTierConfig {
  private final List<TierRarityClass> IVTiers = new ArrayList<>();

  // Private constructor to prevent direct instantiation
  private IVTierConfig() {
    // Add default tiers and values
    IVTiers.add(new TierRarityClass("COMMON", 1));
    IVTiers.add(new TierRarityClass("UNCOMMON", 2));
    IVTiers.add(new TierRarityClass("RARE", 4));
    IVTiers.add(new TierRarityClass("EPIC", 6));
    IVTiers.add(new TierRarityClass("LEGENDARY", 10));
    IVTiers.add(new TierRarityClass("MAX", 31));
    // Add more tiers and values as needed
  }

  public static IVTierConfig getDefaultConfig() {
    return new IVTierConfig();
  }

  public List<TierRarityClass> getIVTiers() {
    return IVTiers;
  }
}
