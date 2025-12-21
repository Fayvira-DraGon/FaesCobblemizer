package github.fayvira.fabric.cobblemizer.item

import com.cobblemon.mod.common.api.pokeball.PokeBalls.ANCIENT_AZURE_BALL
import com.cobblemon.mod.common.api.pokeball.PokeBalls.ANCIENT_CITRINE_BALL
import com.cobblemon.mod.common.api.pokeball.PokeBalls.ANCIENT_FEATHER_BALL
import com.cobblemon.mod.common.api.pokeball.PokeBalls.ANCIENT_GIGATON_BALL
import com.cobblemon.mod.common.api.pokeball.PokeBalls.ANCIENT_GREAT_BALL
import com.cobblemon.mod.common.api.pokeball.PokeBalls.ANCIENT_HEAVY_BALL
import com.cobblemon.mod.common.api.pokeball.PokeBalls.ANCIENT_IVORY_BALL
import com.cobblemon.mod.common.api.pokeball.PokeBalls.ANCIENT_JET_BALL
import com.cobblemon.mod.common.api.pokeball.PokeBalls.ANCIENT_LEADEN_BALL
import com.cobblemon.mod.common.api.pokeball.PokeBalls.ANCIENT_ORIGIN_BALL
import com.cobblemon.mod.common.api.pokeball.PokeBalls.ANCIENT_POKE_BALL
import com.cobblemon.mod.common.api.pokeball.PokeBalls.ANCIENT_ROSEATE_BALL
import com.cobblemon.mod.common.api.pokeball.PokeBalls.ANCIENT_SLATE_BALL
import com.cobblemon.mod.common.api.pokeball.PokeBalls.ANCIENT_ULTRA_BALL
import com.cobblemon.mod.common.api.pokeball.PokeBalls.ANCIENT_VERDANT_BALL
import com.cobblemon.mod.common.api.pokeball.PokeBalls.ANCIENT_WING_BALL
import com.cobblemon.mod.common.api.pokeball.PokeBalls.AZURE_BALL
import com.cobblemon.mod.common.api.pokeball.PokeBalls.BEAST_BALL
import com.cobblemon.mod.common.api.pokeball.PokeBalls.CHERISH_BALL
import com.cobblemon.mod.common.api.pokeball.PokeBalls.CITRINE_BALL
import com.cobblemon.mod.common.api.pokeball.PokeBalls.DIVE_BALL
import com.cobblemon.mod.common.api.pokeball.PokeBalls.DREAM_BALL
import com.cobblemon.mod.common.api.pokeball.PokeBalls.DUSK_BALL
import com.cobblemon.mod.common.api.pokeball.PokeBalls.FAST_BALL
import com.cobblemon.mod.common.api.pokeball.PokeBalls.FRIEND_BALL
import com.cobblemon.mod.common.api.pokeball.PokeBalls.GREAT_BALL
import com.cobblemon.mod.common.api.pokeball.PokeBalls.HEAL_BALL
import com.cobblemon.mod.common.api.pokeball.PokeBalls.HEAVY_BALL
import com.cobblemon.mod.common.api.pokeball.PokeBalls.LEVEL_BALL
import com.cobblemon.mod.common.api.pokeball.PokeBalls.LOVE_BALL
import com.cobblemon.mod.common.api.pokeball.PokeBalls.LURE_BALL
import com.cobblemon.mod.common.api.pokeball.PokeBalls.LUXURY_BALL
import com.cobblemon.mod.common.api.pokeball.PokeBalls.MASTER_BALL
import com.cobblemon.mod.common.api.pokeball.PokeBalls.MOON_BALL
import com.cobblemon.mod.common.api.pokeball.PokeBalls.NEST_BALL
import com.cobblemon.mod.common.api.pokeball.PokeBalls.NET_BALL
import com.cobblemon.mod.common.api.pokeball.PokeBalls.PARK_BALL
import com.cobblemon.mod.common.api.pokeball.PokeBalls.POKE_BALL
import com.cobblemon.mod.common.api.pokeball.PokeBalls.PREMIER_BALL
import com.cobblemon.mod.common.api.pokeball.PokeBalls.QUICK_BALL
import com.cobblemon.mod.common.api.pokeball.PokeBalls.REPEAT_BALL
import com.cobblemon.mod.common.api.pokeball.PokeBalls.ROSEATE_BALL
import com.cobblemon.mod.common.api.pokeball.PokeBalls.SAFARI_BALL
import com.cobblemon.mod.common.api.pokeball.PokeBalls.SLATE_BALL
import com.cobblemon.mod.common.api.pokeball.PokeBalls.SPORT_BALL
import com.cobblemon.mod.common.api.pokeball.PokeBalls.TIMER_BALL
import com.cobblemon.mod.common.api.pokeball.PokeBalls.ULTRA_BALL
import com.cobblemon.mod.common.api.pokeball.PokeBalls.VERDANT_BALL
import com.cobblemon.mod.common.api.pokemon.stats.Stats.*
import github.fayvira.fabric.cobblemizer.Cobblemizer.Companion.LOGGER
import github.fayvira.fabric.cobblemizer.Cobblemizer.Companion.MOD_ID
import github.fayvira.fabric.cobblemizer.Cobblemizer.Companion.MOD_NAME
import github.fayvira.fabric.cobblemizer.item.custom.*
import net.minecraft.item.Item
import net.minecraft.item.Item.Settings
import net.minecraft.item.ItemGroup.*
import net.minecraft.item.ItemStack
import net.minecraft.registry.Registries.ITEM
import net.minecraft.registry.Registries.ITEM_GROUP
import net.minecraft.registry.Registry.register
import net.minecraft.text.Text.translatable
import net.minecraft.util.Identifier

object Items {
  // BottleCapItem
  // base bottle caps
  val BOTTLE_CAP: Item = register(ITEM, Identifier.of(MOD_ID, "bottle_cap/bottle_cap"), Item(Settings().maxCount(16)))
  val BOTTLE_CAP_BLACK: BottleCapItem = register(ITEM, Identifier.of(MOD_ID, "bottle_cap/bottle_cap_black"), BottleCapItem(stat = ATTACK))
  val BOTTLE_CAP_BLUE: BottleCapItem = register(ITEM, Identifier.of(MOD_ID, "bottle_cap/bottle_cap_blue"), BottleCapItem(stat = SPECIAL_ATTACK))
  val BOTTLE_CAP_GOLD: BottleCapItem = register(ITEM, Identifier.of(MOD_ID, "bottle_cap/bottle_cap_gold"), BottleCapItem())
  val BOTTLE_CAP_GREEN: BottleCapItem = register(ITEM, Identifier.of(MOD_ID, "bottle_cap/bottle_cap_green"), BottleCapItem(stat = SPECIAL_DEFENCE))
  val BOTTLE_CAP_PINK: BottleCapItem = register(ITEM, Identifier.of(MOD_ID, "bottle_cap/bottle_cap_pink"), BottleCapItem(stat = SPEED))
  val BOTTLE_CAP_RED: BottleCapItem = register(ITEM, Identifier.of(MOD_ID, "bottle_cap/bottle_cap_red"), BottleCapItem(stat = HP))
  val BOTTLE_CAP_YELLOW: BottleCapItem = register(ITEM, Identifier.of(MOD_ID, "bottle_cap/bottle_cap_yellow"), BottleCapItem(stat = DEFENCE))

  // void bottle caps
  val VOID_BOTTLE_CAP: Item = register(ITEM, Identifier.of(MOD_ID, "bottle_cap/void_bottle_cap"), Item(Settings().maxCount(16)))
  val VOID_BOTTLE_CAP_BLACK: BottleCapItem = register(ITEM, Identifier.of(MOD_ID, "bottle_cap/void_bottle_cap_black"), BottleCapItem(stat = ATTACK, iv = 0))
  val VOID_BOTTLE_CAP_BLUE: BottleCapItem = register(ITEM, Identifier.of(MOD_ID, "bottle_cap/void_bottle_cap_blue"), BottleCapItem(stat = SPECIAL_ATTACK, iv = 0))
  val VOID_BOTTLE_CAP_GREEN: BottleCapItem = register(ITEM, Identifier.of(MOD_ID, "bottle_cap/void_bottle_cap_green"), BottleCapItem(stat = SPECIAL_DEFENCE, iv = 0))
  val VOID_BOTTLE_CAP_PINK: BottleCapItem = register(ITEM, Identifier.of(MOD_ID, "bottle_cap/void_bottle_cap_pink"), BottleCapItem(stat = SPEED, iv = 0))
  val VOID_BOTTLE_CAP_RED: BottleCapItem = register(ITEM, Identifier.of(MOD_ID, "bottle_cap/void_bottle_cap_red"), BottleCapItem(stat = HP, iv = 0))
  val VOID_BOTTLE_CAP_YELLOW: BottleCapItem = register(ITEM, Identifier.of(MOD_ID, "bottle_cap/void_bottle_cap_yellow"), BottleCapItem(stat = DEFENCE, iv = 0))

  // wood bottle cap
  val WOOD_BOTTLE_CAP: BottleCapItem = register(ITEM, Identifier.of(MOD_ID, "bottle_cap/wood_bottle_cap"), BottleCapItem(iv = -1))

  // FriendshipCubeItem
  val FRIENDSHIP_CORRUPTED_CUBE: FriendshipCubeItem = register(ITEM, Identifier.of(MOD_ID, "friendship_cube/friendship_corrupted_cube"), FriendshipCubeItem(friendship = 1))
  val FRIENDSHIP_PERFECT_CUBE: FriendshipCubeItem = register(ITEM, Identifier.of(MOD_ID, "friendship_cube/friendship_perfect_cube"), FriendshipCubeItem())
  val FRIENDSHIP_UNIDENTIFIED_CUBE: FriendshipCubeItem = register(ITEM, Identifier.of(MOD_ID, "friendship_cube/friendship_unidentified_cube"), FriendshipCubeItem(friendship = 0))

  // GenderItem
  val GENDER_CRYSTAL: GenderItem = register(ITEM, Identifier.of(MOD_ID, "gender/gender_crystal"), GenderItem())
  val GENDER_FLUID: GenderItem = register(ITEM, Identifier.of(MOD_ID, "gender/gender_fluid"), GenderItem(fluid = true))

  // LevelCandyItem
  val LEVEL_CANDY_CHAOS: LevelCandyItem = register(ITEM, Identifier.of(MOD_ID, "level_candy/level_candy_chaos"), LevelCandyItem(level = 0))
  val LEVEL_CANDY_SPOILED: LevelCandyItem = register(ITEM, Identifier.of(MOD_ID, "level_candy/level_candy_spoiled"), LevelCandyItem(level = 1))
  val LEVEL_CANDY_ULTIMATE: LevelCandyItem = register(ITEM, Identifier.of(MOD_ID, "level_candy/level_candy_ultimate"), LevelCandyItem())

  // ShiftBallItem
  // tier 1 balls
  val SHIFT_BALL_ANCIENT_AZURE: ShiftBallItem = register(ITEM, Identifier.of(MOD_ID, "shift_ball/tier_1/shift_ball_ancient_azure"), ShiftBallItem(selectedBall = ANCIENT_AZURE_BALL))
  val SHIFT_BALL_ANCIENT_CITRINE: ShiftBallItem = register(ITEM, Identifier.of(MOD_ID, "shift_ball/tier_1/shift_ball_ancient_citrine"), ShiftBallItem(selectedBall = ANCIENT_CITRINE_BALL))
  val SHIFT_BALL_ANCIENT_FEATHER: ShiftBallItem = register(ITEM, Identifier.of(MOD_ID, "shift_ball/tier_1/shift_ball_ancient_feather"), ShiftBallItem(selectedBall = ANCIENT_FEATHER_BALL))
  val SHIFT_BALL_ANCIENT_HEAVY: ShiftBallItem = register(ITEM, Identifier.of(MOD_ID, "shift_ball/tier_1/shift_ball_ancient_heavy"), ShiftBallItem(selectedBall = ANCIENT_HEAVY_BALL))
  val SHIFT_BALL_ANCIENT_IVORY: ShiftBallItem = register(ITEM, Identifier.of(MOD_ID, "shift_ball/tier_1/shift_ball_ancient_ivory"), ShiftBallItem(selectedBall = ANCIENT_IVORY_BALL))
  val SHIFT_BALL_ANCIENT_POKE: ShiftBallItem = register(ITEM, Identifier.of(MOD_ID, "shift_ball/tier_1/shift_ball_ancient_poke"), ShiftBallItem(selectedBall = ANCIENT_POKE_BALL))
  val SHIFT_BALL_ANCIENT_ROSEATE: ShiftBallItem = register(ITEM, Identifier.of(MOD_ID, "shift_ball/tier_1/shift_ball_ancient_roseate"), ShiftBallItem(selectedBall = ANCIENT_ROSEATE_BALL))
  val SHIFT_BALL_ANCIENT_SLATE: ShiftBallItem = register(ITEM, Identifier.of(MOD_ID, "shift_ball/tier_1/shift_ball_ancient_slate"), ShiftBallItem(selectedBall = ANCIENT_SLATE_BALL))
  val SHIFT_BALL_ANCIENT_VERDANT: ShiftBallItem = register(ITEM, Identifier.of(MOD_ID, "shift_ball/tier_1/shift_ball_ancient_verdant"), ShiftBallItem(selectedBall = ANCIENT_VERDANT_BALL))

  val SHIFT_BALL_AZURE: ShiftBallItem = register(ITEM, Identifier.of(MOD_ID, "shift_ball/tier_1/shift_ball_azure"), ShiftBallItem(selectedBall = AZURE_BALL))
  val SHIFT_BALL_CITRINE: ShiftBallItem = register(ITEM, Identifier.of(MOD_ID, "shift_ball/tier_1/shift_ball_citrine"), ShiftBallItem(selectedBall = CITRINE_BALL))
  val SHIFT_BALL_HEAL: ShiftBallItem = register(ITEM, Identifier.of(MOD_ID, "shift_ball/tier_1/shift_ball_heal"), ShiftBallItem(selectedBall = HEAL_BALL))
  val SHIFT_BALL_POKE: ShiftBallItem = register(ITEM, Identifier.of(MOD_ID, "shift_ball/tier_1/shift_ball_poke"), ShiftBallItem(selectedBall = POKE_BALL))
  val SHIFT_BALL_PREMIER: ShiftBallItem = register(ITEM, Identifier.of(MOD_ID, "shift_ball/tier_1/shift_ball_premier"), ShiftBallItem(selectedBall = PREMIER_BALL))
  val SHIFT_BALL_ROSEATE: ShiftBallItem = register(ITEM, Identifier.of(MOD_ID, "shift_ball/tier_1/shift_ball_roseate"), ShiftBallItem(selectedBall = ROSEATE_BALL))
  val SHIFT_BALL_SAFARI: ShiftBallItem = register(ITEM, Identifier.of(MOD_ID, "shift_ball/tier_1/shift_ball_safari"), ShiftBallItem(selectedBall = SAFARI_BALL))
  val SHIFT_BALL_SLATE: ShiftBallItem = register(ITEM, Identifier.of(MOD_ID, "shift_ball/tier_1/shift_ball_slate"), ShiftBallItem(selectedBall = SLATE_BALL))
  val SHIFT_BALL_VERDANT: ShiftBallItem = register(ITEM, Identifier.of(MOD_ID, "shift_ball/tier_1/shift_ball_verdant"), ShiftBallItem(selectedBall = VERDANT_BALL))

  // tier 2 balls
  val SHIFT_BALL_ANCIENT_GREAT: ShiftBallItem = register(ITEM, Identifier.of(MOD_ID, "shift_ball/tier_2/shift_ball_ancient_great"), ShiftBallItem(selectedBall = ANCIENT_GREAT_BALL))
  val SHIFT_BALL_ANCIENT_LEADEN: ShiftBallItem = register(ITEM, Identifier.of(MOD_ID, "shift_ball/tier_2/shift_ball_ancient_leaden"), ShiftBallItem(selectedBall = ANCIENT_LEADEN_BALL))
  val SHIFT_BALL_ANCIENT_WING: ShiftBallItem = register(ITEM, Identifier.of(MOD_ID, "shift_ball/tier_2/shift_ball_ancient_wing"), ShiftBallItem(selectedBall = ANCIENT_WING_BALL))
  val SHIFT_BALL_DIVE: ShiftBallItem = register(ITEM, Identifier.of(MOD_ID, "shift_ball/tier_2/shift_ball_dive"), ShiftBallItem(selectedBall = DIVE_BALL))
  val SHIFT_BALL_FAST: ShiftBallItem = register(ITEM, Identifier.of(MOD_ID, "shift_ball/tier_2/shift_ball_fast"), ShiftBallItem(selectedBall = FAST_BALL))
  val SHIFT_BALL_FRIEND: ShiftBallItem = register(ITEM, Identifier.of(MOD_ID, "shift_ball/tier_2/shift_ball_friend"), ShiftBallItem(selectedBall = FRIEND_BALL))
  val SHIFT_BALL_GREAT: ShiftBallItem = register(ITEM, Identifier.of(MOD_ID, "shift_ball/tier_2/shift_ball_great"), ShiftBallItem(selectedBall = GREAT_BALL))
  val SHIFT_BALL_HEAVY: ShiftBallItem = register(ITEM, Identifier.of(MOD_ID, "shift_ball/tier_2/shift_ball_heavy"), ShiftBallItem(selectedBall = HEAVY_BALL))
  val SHIFT_BALL_LEVEL: ShiftBallItem = register(ITEM, Identifier.of(MOD_ID, "shift_ball/tier_2/shift_ball_level"), ShiftBallItem(selectedBall = LEVEL_BALL))
  val SHIFT_BALL_LURE: ShiftBallItem = register(ITEM, Identifier.of(MOD_ID, "shift_ball/tier_2/shift_ball_lure"), ShiftBallItem(selectedBall = LURE_BALL))
  val SHIFT_BALL_MOON: ShiftBallItem = register(ITEM, Identifier.of(MOD_ID, "shift_ball/tier_2/shift_ball_moon"), ShiftBallItem(selectedBall = MOON_BALL))
  val SHIFT_BALL_NEST: ShiftBallItem = register(ITEM, Identifier.of(MOD_ID, "shift_ball/tier_2/shift_ball_nest"), ShiftBallItem(selectedBall = NEST_BALL))
  val SHIFT_BALL_NET: ShiftBallItem = register(ITEM, Identifier.of(MOD_ID, "shift_ball/tier_2/shift_ball_net"), ShiftBallItem(selectedBall = NET_BALL))
  val SHIFT_BALL_PARK: ShiftBallItem = register(ITEM, Identifier.of(MOD_ID, "shift_ball/tier_2/shift_ball_park"), ShiftBallItem(selectedBall = PARK_BALL))
  val SHIFT_BALL_SPORT: ShiftBallItem = register(ITEM, Identifier.of(MOD_ID, "shift_ball/tier_2/shift_ball_sport"), ShiftBallItem(selectedBall = SPORT_BALL))

  // tier 3 balls
  val SHIFT_BALL_ANCIENT_GIGATON: ShiftBallItem = register(ITEM, Identifier.of(MOD_ID, "shift_ball/tier_3/shift_ball_ancient_gigaton"), ShiftBallItem(selectedBall = ANCIENT_GIGATON_BALL))
  val SHIFT_BALL_ANCIENT_JET: ShiftBallItem = register(ITEM, Identifier.of(MOD_ID, "shift_ball/tier_3/shift_ball_ancient_jet"), ShiftBallItem(selectedBall = ANCIENT_JET_BALL))
  val SHIFT_BALL_ANCIENT_ULTRA: ShiftBallItem = register(ITEM, Identifier.of(MOD_ID, "shift_ball/tier_3/shift_ball_ancient_ultra"), ShiftBallItem(selectedBall = ANCIENT_ULTRA_BALL))
  val SHIFT_BALL_DUSK: ShiftBallItem = register(ITEM, Identifier.of(MOD_ID, "shift_ball/tier_3/shift_ball_dusk"), ShiftBallItem(selectedBall = DUSK_BALL))
  val SHIFT_BALL_LOVE: ShiftBallItem = register(ITEM, Identifier.of(MOD_ID, "shift_ball/tier_3/shift_ball_love"), ShiftBallItem(selectedBall = LOVE_BALL))
  val SHIFT_BALL_LUXURY: ShiftBallItem = register(ITEM, Identifier.of(MOD_ID, "shift_ball/tier_3/shift_ball_luxury"), ShiftBallItem(selectedBall = LUXURY_BALL))
  val SHIFT_BALL_QUICK: ShiftBallItem = register(ITEM, Identifier.of(MOD_ID, "shift_ball/tier_3/shift_ball_quick"), ShiftBallItem(selectedBall = QUICK_BALL))
  val SHIFT_BALL_REPEAT: ShiftBallItem = register(ITEM, Identifier.of(MOD_ID, "shift_ball/tier_3/shift_ball_repeat"), ShiftBallItem(selectedBall = REPEAT_BALL))
  val SHIFT_BALL_TIMER: ShiftBallItem = register(ITEM, Identifier.of(MOD_ID, "shift_ball/tier_3/shift_ball_timer"), ShiftBallItem(selectedBall = TIMER_BALL))
  val SHIFT_BALL_ULTRA: ShiftBallItem = register(ITEM, Identifier.of(MOD_ID, "shift_ball/tier_3/shift_ball_ultra"), ShiftBallItem(selectedBall = ULTRA_BALL))

  // tier 4 balls
  val SHIFT_BALL_BEAST: ShiftBallItem = register(ITEM, Identifier.of(MOD_ID, "shift_ball/tier_4/shift_ball_beast"), ShiftBallItem(selectedBall = BEAST_BALL))
  val SHIFT_BALL_DREAM: ShiftBallItem = register(ITEM, Identifier.of(MOD_ID, "shift_ball/tier_4/shift_ball_dream"), ShiftBallItem(selectedBall = DREAM_BALL))

  // tier 5 balls
  val SHIFT_BALL_ANCIENT_ORIGIN: ShiftBallItem = register(ITEM, Identifier.of(MOD_ID, "shift_ball/tier_5/shift_ball_ancient_origin"), ShiftBallItem(selectedBall = ANCIENT_ORIGIN_BALL))
  val SHIFT_BALL_CHERISH: ShiftBallItem = register(ITEM, Identifier.of(MOD_ID, "shift_ball/tier_5/shift_ball_cherish"), ShiftBallItem(selectedBall = CHERISH_BALL))
  val SHIFT_BALL_MASTER: ShiftBallItem = register(ITEM, Identifier.of(MOD_ID, "shift_ball/tier_5/shift_ball_master"), ShiftBallItem(selectedBall = MASTER_BALL))

  // ShinyItem
  val SHINY_CAPSULE: ShinyItem = register(ITEM, Identifier.of(MOD_ID, "shiny/shiny_capsule"), ShinyItem(capsule = true))
  val SHINY_CRYSTAL: ShinyItem = register(ITEM, Identifier.of(MOD_ID, "shiny/shiny_crystal"), ShinyItem())
  val SHINY_FLUID: ShinyItem = register(ITEM, Identifier.of(MOD_ID, "shiny/shiny_fluid"), ShinyItem(capsule = null))

  // register all mod items function
  fun init() {
    LOGGER.info("Register Mod Items for $MOD_NAME")

    // register item group
    register(
      ITEM_GROUP,
      Identifier.of(MOD_ID, "/gender/gender_fluid"),
      Builder(null, -1)
        .displayName(translatable("itemgroup.cobblemizer"))
        .icon { ItemStack(GENDER_FLUID) }
        .entries { _: DisplayContext, entries: Entries ->
          // BottleCapItem
          // bottle caps
          entries.add(BOTTLE_CAP)
          entries.add(BOTTLE_CAP_GOLD)
          entries.add(BOTTLE_CAP_BLACK)
          entries.add(BOTTLE_CAP_BLUE)
          entries.add(BOTTLE_CAP_GREEN)
          entries.add(BOTTLE_CAP_PINK)
          entries.add(BOTTLE_CAP_RED)
          entries.add(BOTTLE_CAP_YELLOW)

          // wood bottle cap
          entries.add(WOOD_BOTTLE_CAP)

          // void bottle caps
          entries.add(VOID_BOTTLE_CAP)
          entries.add(VOID_BOTTLE_CAP_BLACK)
          entries.add(VOID_BOTTLE_CAP_BLUE)
          entries.add(VOID_BOTTLE_CAP_GREEN)
          entries.add(VOID_BOTTLE_CAP_PINK)
          entries.add(VOID_BOTTLE_CAP_RED)
          entries.add(VOID_BOTTLE_CAP_YELLOW)

          // FriendshipCubeItem
          entries.add(FRIENDSHIP_CORRUPTED_CUBE)
          entries.add(FRIENDSHIP_PERFECT_CUBE)
          entries.add(FRIENDSHIP_UNIDENTIFIED_CUBE)

          // GenderItem
          entries.add(GENDER_CRYSTAL)
          entries.add(GENDER_FLUID)

          // LevelCandyItem
          entries.add(LEVEL_CANDY_CHAOS)
          entries.add(LEVEL_CANDY_SPOILED)
          entries.add(LEVEL_CANDY_ULTIMATE)

          // ShiftBallItem
          // tier 1 balls
          entries.add(SHIFT_BALL_ANCIENT_AZURE)
          entries.add(SHIFT_BALL_ANCIENT_CITRINE)
          entries.add(SHIFT_BALL_ANCIENT_FEATHER)
          entries.add(SHIFT_BALL_ANCIENT_HEAVY)
          entries.add(SHIFT_BALL_ANCIENT_IVORY)
          entries.add(SHIFT_BALL_ANCIENT_POKE)
          entries.add(SHIFT_BALL_ANCIENT_ROSEATE)
          entries.add(SHIFT_BALL_ANCIENT_SLATE)
          entries.add(SHIFT_BALL_ANCIENT_VERDANT)
          entries.add(SHIFT_BALL_AZURE)
          entries.add(SHIFT_BALL_CITRINE)
          entries.add(SHIFT_BALL_HEAL)
          entries.add(SHIFT_BALL_POKE)
          entries.add(SHIFT_BALL_PREMIER)
          entries.add(SHIFT_BALL_ROSEATE)
          entries.add(SHIFT_BALL_SAFARI)
          entries.add(SHIFT_BALL_SLATE)
          entries.add(SHIFT_BALL_VERDANT)

          // tier 2 balls
          entries.add(SHIFT_BALL_ANCIENT_GREAT)
          entries.add(SHIFT_BALL_ANCIENT_LEADEN)
          entries.add(SHIFT_BALL_ANCIENT_WING)
          entries.add(SHIFT_BALL_DIVE)
          entries.add(SHIFT_BALL_FAST)
          entries.add(SHIFT_BALL_FRIEND)
          entries.add(SHIFT_BALL_GREAT)
          entries.add(SHIFT_BALL_HEAVY)
          entries.add(SHIFT_BALL_LEVEL)
          entries.add(SHIFT_BALL_LURE)
          entries.add(SHIFT_BALL_MOON)
          entries.add(SHIFT_BALL_NEST)
          entries.add(SHIFT_BALL_NET)
          entries.add(SHIFT_BALL_PARK)
          entries.add(SHIFT_BALL_SPORT)

          // tier 3 balls
          entries.add(SHIFT_BALL_ANCIENT_GIGATON)
          entries.add(SHIFT_BALL_ANCIENT_JET)
          entries.add(SHIFT_BALL_ANCIENT_ULTRA)
          entries.add(SHIFT_BALL_DUSK)
          entries.add(SHIFT_BALL_LOVE)
          entries.add(SHIFT_BALL_LUXURY)
          entries.add(SHIFT_BALL_QUICK)
          entries.add(SHIFT_BALL_REPEAT)
          entries.add(SHIFT_BALL_TIMER)
          entries.add(SHIFT_BALL_ULTRA)

          // tier 4 balls
          entries.add(SHIFT_BALL_BEAST)
          entries.add(SHIFT_BALL_DREAM)

          // tier 5 balls
          entries.add(SHIFT_BALL_ANCIENT_ORIGIN)
          entries.add(SHIFT_BALL_CHERISH)
          entries.add(SHIFT_BALL_MASTER)

          // ShinyItem
          entries.add(SHINY_CAPSULE)
          entries.add(SHINY_CRYSTAL)
          entries.add(SHINY_FLUID)
        }
        .build()
    )
  }
}
