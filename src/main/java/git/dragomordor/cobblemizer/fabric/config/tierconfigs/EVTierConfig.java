package git.dragomordor.cobblemizer.fabric.config.tierconfigs;

import git.dragomordor.cobblemizer.fabric.misc.TierRarityClass;

import java.util.ArrayList;
import java.util.List;

public class EVTierConfig {
  private final List<TierRarityClass> EVTiers = new ArrayList<>();

  // Private constructor to prevent direct instantiation
  private EVTierConfig() {
    // Add default tiers and values
    EVTiers.add(new TierRarityClass("COMMON", 10));
    EVTiers.add(new TierRarityClass("UNCOMMON", 20));
    EVTiers.add(new TierRarityClass("RARE", 40));
    EVTiers.add(new TierRarityClass("EPIC", 70));
    EVTiers.add(new TierRarityClass("LEGENDARY", 120));
    EVTiers.add(new TierRarityClass("MAX", 252));
    // Add more tiers and values as needed
  }

  public static EVTierConfig getDefaultConfig() {
    return new EVTierConfig();
  }

  public List<TierRarityClass> getEVTiers() {
    return EVTiers;
  }
}
