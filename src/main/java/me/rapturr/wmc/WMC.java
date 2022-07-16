package me.rapturr.wmc;

import me.rapturr.wmc.commands.WMCCommand;
import me.rapturr.wmc.helpers.*;
import me.rapturr.wmc.items.ItemRecipes;
import me.rapturr.wmc.items.WesternItem;
import me.rapturr.wmc.items.kingdom.*;
import me.rapturr.wmc.items.unfinished.*;
import me.rapturr.wmc.items.weapons.*;
import me.rapturr.wmc.listeners.*;
import me.rapturr.wmc.mobs.WesternMob;
import me.rapturr.wmc.projectiles.arrows.GrapplingHookArrow;
import me.rapturr.wmc.projectiles.arrows.ShortbowArrow;
import me.rapturr.wmc.projectiles.arrows.WesternArrow;
import me.rapturr.wmc.projectiles.snowballs.*;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

import static org.bukkit.Bukkit.getPluginManager;

public class WMC extends JavaPlugin {

    private static WMC instance;
    public static WMC getInstance() {
        return instance;
    }

    public static Map<String, WesternItem> westernItems = new HashMap<>();
    public static Map<String, WesternMob> westernMobs = new HashMap<>();
    public static Map<String, WesternArrow> westernArrows = new HashMap<>();
    public static Map<String, WesternSnowball> westernSnowballs = new HashMap<>();




    @Override
    public void onEnable() {
        //Plugin Startup logic
        instance = this;
        //
        this.registerEvents();
        this.registerItems();
        this.registerCommands();
        this.registerMobs();
        this.saveDefaultConfig();
        this.registerProjectiles();
        new ItemRecipes();
        new RemoveRecipes(Arrays.asList(Material.NETHERITE_INGOT, Material.ENDER_EYE));

    }

    @Override
    public void onDisable() {
        //Plugin disable logic
    }



    private void registerEvents() {
        registerEvent(new OnPlayerInteract());
        registerEvent(new WesternExplosion(this));
        registerEvent(new InventoryListeners());
        registerEvent(new MobListeners());
        registerEvent(new WingSuitListener());
        OnPlayerInteract.activeListener();
        //MineCar.minecartEvent(WMC.getInstance());
    }

    private void registerItems() {
        putItem("TEST_ITEM", new TestItem("TEST_ITEM", "Test Item", false, true, ItemRarity.EPIC, Material.BEDROCK, "Test item actions!", ItemType.NONE, false));
        putItem("WHIP", new Whip("WHIP", "Horse Whip", false, true, ItemRarity.UNCOMMON, Material.FISHING_ROD, "basic lore prefix", ItemType.NONE, false));
        putItem("DUMMY_EGG", new DummyEgg("DUMMY_EGG", "Dummy Spawnegg", true, false, ItemRarity.COMMON, Material.ZOMBIE_VILLAGER_SPAWN_EGG, "", ItemType.NONE, false));
        putItem("SHORT_BOW", new Shortbow("SHORT_BOW", "Shortbow", false, true, ItemRarity.RARE, Material.BOW, "Shoots arrows at fast pace!", ItemType.BOW, false));
        putItem("CRACKED_BOTTLE", new CrackedBottle("CRACKED_BOTTLE", "Cracked Bottle", false, false, ItemRarity.COMMON, Material.GLASS_BOTTLE, "A quick weapon for fighting\noff drunk guys!", ItemType.MISC, false));
        putItem("GRAPPLING_HOOK", new GrapplingHook("GRAPPLING_HOOK", "Grappling Hook", false, false, ItemRarity.UNCOMMON, Material.NETHERITE_PICKAXE, "Im like spuderman!", ItemType.MISC, false));
        putItem("AK47", new Ak47("AK47", "Ak-47", false, true, ItemRarity.SPECIAL, Material.WOODEN_HOE, "Boom boom", ItemType.RIFLE, false));
        putItem("INDIAN_MASK", new IndianMask("INDIAN_MASK", "Indian Mask", false, false, ItemRarity.RARE, Material.PLAYER_HEAD, "", ItemType.NONE, false));
        putItem("SPACE_HELMET", new SpaceHelmet("SPACE_HELMET", "Rapturr's Space Helmet", false, true, ItemRarity.SPECIAL, Material.GLASS, "", ItemType.NONE, false));
        putItem("PICKLE_JAR", new PickeJar("PICKLE_JAR", "Pickle Jar", false, false, ItemRarity.SPECIAL, Material.POTION, "", ItemType.NONE, false));
        putItem("SILK_LASSO", new SilkLasso("SILK_LASSO", "Silk Lasso", false, false, ItemRarity.UNCOMMON, Material.LEAD, "", ItemType.MISC, false));
        putItem("REVOLVER", new Revolver("REVOLVER", "Silver Revolver", false, true, ItemRarity.RARE, Material.IRON_HORSE_ARMOR, "", ItemType.PISTOL, false));
        putItem("MUSKET", new Musket("MUSKET", "Musket", false, true, ItemRarity.RARE, Material.STONE_HOE, "", ItemType.RIFLE, false));
        putItem("HORN", new Horn("HORN", "Horn", false, true, ItemRarity.RARE, Material.HOPPER, "", ItemType.NONE, true));
        putItem("WATER_SKIN", new WaterSkin("WATER_SKIN", "Water Skin", false, false, ItemRarity.UNCOMMON, Material.RABBIT_FOOT, "Stores up to 64 Water", ItemType.NONE, true));
        putItem("SPEAR", new Spear("SPEAR", "Spear", false, false, ItemRarity.UNCOMMON, Material.STICK, "", ItemType.NONE, false));
        putItem("DYNAMITE", new Dynamite("DYNAMITE", "Dynamite", true, false, ItemRarity.COMMON, Material.RED_CANDLE, "Boom boom!", ItemType.NONE, false));
        putItem("RAY_GUN", new RayGun("RAY_GUN", "Ray gun", false, true, ItemRarity.EPIC, Material.DIAMOND_HOE, "DAMN!", ItemType.NONE, false));

        putItem("ARMORSTAND_EDITOR", new ArmorstandPreviewer("ARMORSTAND_EDITOR", "Armorstand Editor", false, true, ItemRarity.SPECIAL, Material.STICK, "", ItemType.MISC, true));

        //Kingdom
        putItem("BONEMERANG", new Bonemerang("BONEMERANG", "Bonemerang", false, false, ItemRarity.RARE, Material.BONE, "A scary boomerang that\ndeals 6hp of damage.", ItemType.BOW, false));
        putItem("TOMAHAWK", new Tomahawk("TOMAHAWK", "Tomahawk", false, false, ItemRarity.RARE, Material.STONE_AXE, "A throwable axe that deals\n4hp of damage and ignores armor!", ItemType.AXE, false));
        putItem("ANDURIL", new Anduril("ANDURIL", "Anduril", false, true, ItemRarity.RARE, Material.IRON_SWORD, "An iron sword that gives speed!", ItemType.SWORD, true));
        putItem("SCYTHE", new Scythe("SCYTHE", "Death Scythe", false, true, ItemRarity.EPIC, Material.NETHERITE_HOE, "This scythe will deal 20% damage\nof the remaining players health.\nCooldown 6s", ItemType.MISC, false));
        putItem("PERUN", new Perun("PERUN", "Axe of Perun", false, false, ItemRarity.RARE, Material.GOLDEN_AXE, "An Axe that will\nstrike thunder on impact.\nCooldown 4s", ItemType.AXE, false));
        putItem("BOOK_OF_SKULLS", new BookOfSkulls("BOOK_OF_SKULLS", "Book of Skulls", false, true, ItemRarity.EPIC, Material.BOOK, "A spell that casts\nangry wither skulls.\nCooldown 2s", ItemType.BOOK, false));
        putItem("ARTEMIS", new Artemis("ARTEMIS", "Artemis", false, true, ItemRarity.RARE, Material.BOW, "A rapid shooting bow that\ndeals the damage of\na regular bow.", ItemType.BOW, false));
        putItem("BRICK", new Brick("BRICK", "Brick", true, false, ItemRarity.COMMON, Material.BRICK, "A brick you found in a\nsketchy allyway you should\nbreak a window with\nthis!", ItemType.MISC, false));
        putItem("CRIMSON_WAND", new CrimsonWand("CRIMSON_WAND", "Crimson Wand", false, true, ItemRarity.EPIC, Material.CRIMSON_FUNGUS, "A spell that casts fireballs.\nCooldown 1s", ItemType.WAND, false));
        putItem("WARDENS_GRACE", new WardensGrace("WARDENS_GRACE", "Wardens Grace", false, true, ItemRarity.SPECIAL, Material.KNOWLEDGE_BOOK, "A spell that casts the\nwarden's sonic boom attack.\nCooldown 8s", ItemType.BOOK, false));
        putItem("SPARK_WAND", new SparkWand("SPARK_WAND", "Spark Wand", false, true, ItemRarity.UNCOMMON, Material.STICK, "You're a wizzard harry!", ItemType.WAND, false));
        putItem("RHYDIA_WAND", new RhydiaWand("RHYDIA_WAND", "Rhydia Wand", false, true, ItemRarity.SPECIAL, Material.BLAZE_ROD, ChatColor.ITALIC + "All magical powers found\ntrough the kingdom\ninside one wand...", ItemType.WAND, true));
        putItem("DICE", new Dice("DICE", "Dice", false, false, ItemRarity.SPECIAL, Material.PLAYER_HEAD, "Gamble with your friends!", ItemType.MISC, false));
    }




    private void putItem(String itemID, WesternItem westernItem) {
        westernItems.put(itemID, westernItem);
    }

    private void putProjectiles(String itemID, WesternSnowball westernSnowball) {
       westernSnowballs.put(itemID, westernSnowball);
    }

    private void putProjectiles(String itemID, WesternArrow westernArrow) {
        westernArrows.put(itemID, westernArrow);
    }


    private void registerProjectiles() {
        putProjectiles("SHORTBOW_ARROW", new ShortbowArrow("SHORTBOW_ARROW"));
        putProjectiles("WHIP_PROJECTILE", new WhipProjectile("WHIP_PROJECTILE"));
        putProjectiles("GRAPPLING_HOOK_ARROW", new GrapplingHookArrow("GRAPPLING_HOOK_ARROW"));
        putProjectiles("GRAPPLING_HOOK_SNOWBALL", new GrapplingHookSnowball("GRAPPLING_HOOK_SNOWBALL"));
        putProjectiles("AK_BULLET", new Ak47Bullet("AK_BULLET"));
        putProjectiles("BRICK_SNOWBALL", new BrickSnowball("BRICK_SNOWBALL"));

    }

    private void registerMobs() {



    }

    private void registerCommands() {
        Objects.requireNonNull(getCommand("wmc")).setExecutor(new WMCCommand(this));
    }


    private void registerEvent(Listener listener) {
        getPluginManager().registerEvents(listener, this);
    }
}
