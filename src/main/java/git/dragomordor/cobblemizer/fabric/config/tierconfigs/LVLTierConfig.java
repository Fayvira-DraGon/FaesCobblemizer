package git.dragomordor.cobblemizer.fabric.config.tierconfigs;

import git.dragomordor.cobblemizer.fabric.misc.TierRarityClass;

import java.util.ArrayList;
import java.util.List;

public class LVLTierConfig {
    private final List<TierRarityClass> LVLTiers = new ArrayList<>();

    // Private constructor to prevent direct instantiation
    private LVLTierConfig() {
        // Add default tiers and values
        LVLTiers.add(new TierRarityClass("COMMON", 1));
        LVLTiers.add(new TierRarityClass("UNCOMMON", 5));
        LVLTiers.add(new TierRarityClass("RARE", 10));
        LVLTiers.add(new TierRarityClass("EPIC", 15));
        LVLTiers.add(new TierRarityClass("LEGENDARY", 25));
        LVLTiers.add(new TierRarityClass("MAX", 100));
        // Add more tiers and values as needed
    }

    public static LVLTierConfig getDefaultConfig() {
        return new LVLTierConfig();
    }

    public List<TierRarityClass> getLVLTiers() {
        return LVLTiers;
    }
}
