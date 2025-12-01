package git.dragomordor.cobblemizer.fabric.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import git.dragomordor.cobblemizer.fabric.Cobblemizer;
import git.dragomordor.cobblemizer.fabric.config.tierconfigs.EVTierConfig;
import git.dragomordor.cobblemizer.fabric.config.tierconfigs.FriendshipTierConfig;
import git.dragomordor.cobblemizer.fabric.config.tierconfigs.IVTierConfig;
import git.dragomordor.cobblemizer.fabric.config.tierconfigs.LVLTierConfig;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import static git.dragomordor.cobblemizer.fabric.Cobblemizer.*;

public class CobblemizerConfig {

  public static List<TierRarityClass> EV_TIERS = new ArrayList<>();
  public static List<TierRarityClass> FRIENDSHIP_TIERS = new ArrayList<>();
  public static List<TierRarityClass> IV_TIERS = new ArrayList<>();
  public static List<TierRarityClass> LVL_TIERS = new ArrayList<>();

  public List<TierRarityClass> FriendshipTiers = new ArrayList<>();
  public List<TierRarityClass> EVTiers = new ArrayList<>();
  public List<TierRarityClass> IVTiers = new ArrayList<>();
  public List<TierRarityClass> LVLTiers = new ArrayList<>();

  public static class Builder {
    @SuppressWarnings("UnusedReturnValue")
    public static CobblemizerConfig load() {
      Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();

      CobblemizerConfig config = new CobblemizerConfig();
      File configFile = new File("config/" + Cobblemizer.MOD_ID + "/" + Cobblemizer.MOD_ID + ".json");

      LOGGER.info("{} configuration directory {}.", MOD_NAME, configFile.getParentFile().mkdirs() ? "was created" : "already existed");

      // Load Default Tier Configs to get default tiers and values
      FriendshipTierConfig defaultFriendshipConfig = FriendshipTierConfig.getDefaultConfig(); // FriendshipTierConfig
      EVTierConfig defaultEVConfig = EVTierConfig.getDefaultConfig(); // EVTierConfig
      IVTierConfig defaultIVConfig = IVTierConfig.getDefaultConfig(); // IVTierConfig
      LVLTierConfig defaultLVLConfig = LVLTierConfig.getDefaultConfig(); // LVLTierConfig

      if (configFile.exists()) {
        LOGGER.info("{} configuration file exists.", MOD_NAME);
        try {
          FileReader fileReader = new FileReader(configFile);
          config = gson.fromJson(fileReader, CobblemizerConfig.class);
          fileReader.close();

          // add different tiers if empty
          // friendshipTiers
          if (config.FriendshipTiers.isEmpty()) {
            config.FriendshipTiers.addAll(defaultFriendshipConfig.getFriendshipTiers());
            FileWriter fileWriter = new FileWriter(configFile);
            gson.toJson(config, fileWriter);
            fileWriter.close();
          }

          // EVTiers
          if (config.EVTiers.isEmpty()) {
            config.EVTiers.addAll(defaultEVConfig.getEVTiers());
            FileWriter fileWriter = new FileWriter(configFile);
            gson.toJson(config, fileWriter);
            fileWriter.close();
          }// IVTiers

          if (config.IVTiers.isEmpty()) {
            config.IVTiers.addAll(defaultIVConfig.getIVTiers());
            FileWriter fileWriter = new FileWriter(configFile);
            gson.toJson(config, fileWriter);
            fileWriter.close();
          }// LVLTiers

          if (config.LVLTiers.isEmpty()) {
            config.LVLTiers.addAll(defaultLVLConfig.getLVLTiers());
            FileWriter fileWriter = new FileWriter(configFile);
            gson.toJson(config, fileWriter);
            fileWriter.close();
          }
        }
        catch (Exception e) {
          LOGGER.info("Error {} reading config file", e.getMessage());
        }
      }
      else {
        LOGGER.info("{} configuration file doesn't exist.", MOD_NAME);

        // If the file does not exist, create it with the default values
        try {
          // Defaults of all tiers
          config.FriendshipTiers.addAll(defaultFriendshipConfig.getFriendshipTiers()); // Friendship defaults
          config.EVTiers.addAll(defaultEVConfig.getEVTiers()); // EV defaults
          config.IVTiers.addAll(defaultIVConfig.getIVTiers()); // IV defaults
          config.LVLTiers.addAll(defaultLVLConfig.getLVLTiers()); // LVL defaults

          FileWriter fileWriter = new FileWriter(configFile);
          gson.toJson(config, fileWriter);
          fileWriter.close();
        }
        catch (Exception e) {
          LOGGER.info("Error {} reading default config values", e.getMessage());
        }
      }

      // Save the config values to global fields
      EV_TIERS = List.copyOf(config.EVTiers);
      FRIENDSHIP_TIERS = List.copyOf(config.FriendshipTiers);
      IV_TIERS = List.copyOf(config.IVTiers);
      LVL_TIERS = List.copyOf(config.LVLTiers);

      return config;
    }
  }
}
