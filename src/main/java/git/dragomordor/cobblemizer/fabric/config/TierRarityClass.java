package git.dragomordor.cobblemizer.fabric.config;

import java.util.List;

public class TierRarityClass {
  public String name;
  public int tierAmount;

  public TierRarityClass(String name, int tierAmount) {
    this.name = name;
    this.tierAmount = tierAmount;
  }

  // Method to get the tierAmount from the config based on the provided tier
  public static int getTierAmount(List<TierRarityClass> tierList, String tierName) {
    for (TierRarityClass tier : tierList) {
      if (tier.name.equalsIgnoreCase(tierName)) {
        return tier.tierAmount;
      }
    }
    return 0; // Default value if tierName not found in config
  }
}
