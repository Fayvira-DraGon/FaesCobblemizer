package git.dragomordor.cobblemizer.fabric.item;

import git.dragomordor.cobblemizer.fabric.CobblemizerMod;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;

import static git.dragomordor.cobblemizer.fabric.CobblemizerMod.LOGGER;
import static git.dragomordor.cobblemizer.fabric.CobblemizerMod.MOD_NAME;
import static git.dragomordor.cobblemizer.fabric.item.CobblemizerItems.*;


public class CobblemizerItemGroups {

  @SuppressWarnings("unused")
  public static final ItemGroup COBBLEMIZER_GROUP = Registry.register(Registries.ITEM_GROUP,
    CobblemizerMod.of("gender_swapper"),
    new ItemGroup.Builder(null, -1).displayName(Text.translatable("itemgroup.cobblemizer")).icon(() -> new ItemStack(GENDER_SWAPPER)).entries((displayContext, entries) -> {
      // CaughtBallChangerItem
      entries.add(AZURE_BALL_CB_CHANGER);
      entries.add(BEAST_BALL_CB_CHANGER);
      entries.add(CHERISH_BALL_CB_CHANGER);
      entries.add(CITRINE_BALL_CB_CHANGER);
      entries.add(DIVE_BALL_CB_CHANGER);
      entries.add(DREAM_BALL_CB_CHANGER);
      entries.add(DUSK_BALL_CB_CHANGER);
      entries.add(FAST_BALL_CB_CHANGER);
      entries.add(FRIEND_BALL_CB_CHANGER);
      entries.add(GREAT_BALL_CB_CHANGER);
      entries.add(HEAL_BALL_CB_CHANGER);
      entries.add(HEAVY_BALL_CB_CHANGER);
      entries.add(LEVEL_BALL_CB_CHANGER);
      entries.add(LOVE_BALL_CB_CHANGER);
      entries.add(LURE_BALL_CB_CHANGER);
      entries.add(LUXURY_BALL_CB_CHANGER);
      entries.add(MASTER_BALL_CB_CHANGER);
      entries.add(MOON_BALL_CB_CHANGER);
      entries.add(NEST_BALL_CB_CHANGER);
      entries.add(NET_BALL_CB_CHANGER);
      entries.add(PARK_BALL_CB_CHANGER);
      entries.add(POKE_BALL_CB_CHANGER);
      entries.add(PREMIER_BALL_CB_CHANGER);
      entries.add(REPEAT_BALL_CB_CHANGER);
      entries.add(ROSEATE_BALL_CB_CHANGER);
      entries.add(SAFARI_BALL_CB_CHANGER);
      entries.add(SLATE_BALL_CB_CHANGER);
      entries.add(SPORT_BALL_CB_CHANGER);
      entries.add(TIMER_BALL_CB_CHANGER);
      entries.add(ULTRA_BALL_CB_CHANGER);
      entries.add(VERDANT_BALL_CB_CHANGER);
      //FriendshipAddItem
      entries.add(FRIENDSHIP_ADD_COMMON);
      entries.add(FRIENDSHIP_ADD_UNCOMMON);
      entries.add(FRIENDSHIP_ADD_RARE);
      entries.add(FRIENDSHIP_ADD_EPIC);
      entries.add(FRIENDSHIP_ADD_LEGENDARY);
      entries.add(FRIENDSHIP_ADD_MAX);
      // EVRandom
      entries.add(EV_RANDOM);
      // EVAddItem
      entries.add(EV_ADD_SP_DEF_COMMON);
      entries.add(EV_ADD_SP_DEF_UNCOMMON);
      entries.add(EV_ADD_SP_DEF_RARE);
      entries.add(EV_ADD_SP_DEF_EPIC);
      entries.add(EV_ADD_SP_DEF_LEGENDARY);
      entries.add(EV_ADD_SP_DEF_MAX);
      entries.add(EV_ADD_SP_ATK_COMMON);
      entries.add(EV_ADD_SP_ATK_UNCOMMON);
      entries.add(EV_ADD_SP_ATK_RARE);
      entries.add(EV_ADD_SP_ATK_EPIC);
      entries.add(EV_ADD_SP_ATK_LEGENDARY);
      entries.add(EV_ADD_SP_ATK_MAX);
      entries.add(EV_ADD_SPEED_COMMON);
      entries.add(EV_ADD_SPEED_UNCOMMON);
      entries.add(EV_ADD_SPEED_RARE);
      entries.add(EV_ADD_SPEED_EPIC);
      entries.add(EV_ADD_SPEED_LEGENDARY);
      entries.add(EV_ADD_SPEED_MAX);
      entries.add(EV_ADD_ATK_COMMON);
      entries.add(EV_ADD_ATK_UNCOMMON);
      entries.add(EV_ADD_ATK_RARE);
      entries.add(EV_ADD_ATK_EPIC);
      entries.add(EV_ADD_ATK_LEGENDARY);
      entries.add(EV_ADD_ATK_MAX);
      entries.add(EV_ADD_DEF_COMMON);
      entries.add(EV_ADD_DEF_UNCOMMON);
      entries.add(EV_ADD_DEF_RARE);
      entries.add(EV_ADD_DEF_EPIC);
      entries.add(EV_ADD_DEF_LEGENDARY);
      entries.add(EV_ADD_DEF_MAX);
      entries.add(EV_ADD_HP_COMMON);
      entries.add(EV_ADD_HP_UNCOMMON);
      entries.add(EV_ADD_HP_RARE);
      entries.add(EV_ADD_HP_EPIC);
      entries.add(EV_ADD_HP_LEGENDARY);
      entries.add(EV_ADD_HP_MAX);
      // IVMaxer
      entries.add(ALL_IV_MAXER);
      // IVRandom
      entries.add(IV_RANDOM);
      // IVAddItem
      entries.add(IV_ADD_SP_DEF_COMMON);
      entries.add(IV_ADD_SP_DEF_UNCOMMON);
      entries.add(IV_ADD_SP_DEF_RARE);
      entries.add(IV_ADD_SP_DEF_EPIC);
      entries.add(IV_ADD_SP_DEF_LEGENDARY);
      entries.add(IV_ADD_SP_DEF_MAX);
      entries.add(IV_ADD_SP_ATK_COMMON);
      entries.add(IV_ADD_SP_ATK_UNCOMMON);
      entries.add(IV_ADD_SP_ATK_RARE);
      entries.add(IV_ADD_SP_ATK_EPIC);
      entries.add(IV_ADD_SP_ATK_LEGENDARY);
      entries.add(IV_ADD_SP_ATK_MAX);
      entries.add(IV_ADD_SPEED_COMMON);
      entries.add(IV_ADD_SPEED_UNCOMMON);
      entries.add(IV_ADD_SPEED_RARE);
      entries.add(IV_ADD_SPEED_EPIC);
      entries.add(IV_ADD_SPEED_LEGENDARY);
      entries.add(IV_ADD_SPEED_MAX);
      entries.add(IV_ADD_ATK_COMMON);
      entries.add(IV_ADD_ATK_UNCOMMON);
      entries.add(IV_ADD_ATK_RARE);
      entries.add(IV_ADD_ATK_EPIC);
      entries.add(IV_ADD_ATK_LEGENDARY);
      entries.add(IV_ADD_ATK_MAX);
      entries.add(IV_ADD_DEF_COMMON);
      entries.add(IV_ADD_DEF_UNCOMMON);
      entries.add(IV_ADD_DEF_RARE);
      entries.add(IV_ADD_DEF_EPIC);
      entries.add(IV_ADD_DEF_LEGENDARY);
      entries.add(IV_ADD_DEF_MAX);
      entries.add(IV_ADD_HP_COMMON);
      entries.add(IV_ADD_HP_UNCOMMON);
      entries.add(IV_ADD_HP_RARE);
      entries.add(IV_ADD_HP_EPIC);
      entries.add(IV_ADD_HP_LEGENDARY);
      entries.add(IV_ADD_HP_MAX);
      // LVLAddItem
      entries.add(LVL_ADD_COMMON);
      entries.add(LVL_ADD_UNCOMMON);
      entries.add(LVL_ADD_RARE);
      entries.add(LVL_ADD_EPIC);
      entries.add(LVL_ADD_LEGENDARY);
      entries.add(LVL_ADD_MAX);
      // LVLRandom
      entries.add(LVL_RANDOM);
      // NatureChangerItems
      entries.add(NATURE_CHANGER_ADAMANT);
      entries.add(NATURE_CHANGER_BASHFUL);
      entries.add(NATURE_CHANGER_BOLD);
      entries.add(NATURE_CHANGER_BRAVE);
      entries.add(NATURE_CHANGER_CALM);
      entries.add(NATURE_CHANGER_CAREFUL);
      entries.add(NATURE_CHANGER_DOCILE);
      entries.add(NATURE_CHANGER_GENTLE);
      entries.add(NATURE_CHANGER_HARDY);
      entries.add(NATURE_CHANGER_HASTY);
      entries.add(NATURE_CHANGER_IMPISH);
      entries.add(NATURE_CHANGER_JOLLY);
      entries.add(NATURE_CHANGER_LAX);
      entries.add(NATURE_CHANGER_LONELY);
      entries.add(NATURE_CHANGER_MILD);
      entries.add(NATURE_CHANGER_MODEST);
      entries.add(NATURE_CHANGER_NAIVE);
      entries.add(NATURE_CHANGER_NAUGHTY);
      entries.add(NATURE_CHANGER_QUIET);
      entries.add(NATURE_CHANGER_QUIRKY);
      entries.add(NATURE_CHANGER_RASH);
      entries.add(NATURE_CHANGER_RELAXED);
      entries.add(NATURE_CHANGER_SASSY);
      entries.add(NATURE_CHANGER_SERIOUS);
      entries.add(NATURE_CHANGER_TIMID);
      // GenderSwapItems
      entries.add(GENDER_SWAPPER);
      // ShinySwapItems
      entries.add(SHINY_SWAPPER);
      entries.add(SHINY_CAPSULE);
    }).build());


  public static void registerItemGroups() {
    LOGGER.info("Registering Item Groups for " + MOD_NAME);
  }
}
