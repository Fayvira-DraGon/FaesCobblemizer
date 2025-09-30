package git.dragomordor.cobblemizer.fabric.config.tierconfigs;

import git.dragomordor.cobblemizer.fabric.misc.TierRarityClass;

import java.util.ArrayList;
import java.util.List;

public class FriendshipTierConfig {
  private final List<TierRarityClass> friendshipTiers = new ArrayList<>();

  // Private constructor to prevent direct instantiation
  private FriendshipTierConfig() {
    // Add default tiers and values
    friendshipTiers.add(new TierRarityClass("COMMON", 5));
    friendshipTiers.add(new TierRarityClass("UNCOMMON", 10));
    friendshipTiers.add(new TierRarityClass("RARE", 20));
    friendshipTiers.add(new TierRarityClass("EPIC", 40));
    friendshipTiers.add(new TierRarityClass("LEGENDARY", 80));
    friendshipTiers.add(new TierRarityClass("MAX", 255));
    // Add more tiers and values as needed
  }

  public static FriendshipTierConfig getDefaultConfig() {
    return new FriendshipTierConfig();
  }

  public List<TierRarityClass> getFriendshipTiers() {
    return friendshipTiers;
  }
}
