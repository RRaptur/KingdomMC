package me.rapturr.wmc.items;

import me.rapturr.wmc.helpers.Utilities;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ShapedRecipe;

public class ItemRecipes {

    public ItemRecipes() {
       /*
        tomahawk();
        bonemerang();
        anduril();
        sparkWand();
        scythe();
        bookOfSkulls();
        perun();
        artemis();
        crimsonWand();
        wardensGrace();
        */
        brick();
    }

    private void tomahawk() {
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("tomahawk"), WesternItem.getItemStack(WesternItems.TOMAHAWK));
        recipe.shape("NNN", "NBF", " B ");
        recipe.setIngredient('B', Material.BLAZE_ROD);
        recipe.setIngredient('N', Material.NETHERITE_SCRAP);
        recipe.setIngredient('F', Material.FEATHER);
        Bukkit.getServer().addRecipe(recipe);
    }
    private void bonemerang() {
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("bonemerang"), WesternItem.getItemStack(WesternItems.BONEMERANG));
        recipe.shape(" D ", "BBB", " P ");
        recipe.setIngredient('B', Material.BONE_BLOCK);
        recipe.setIngredient('D', Material.DIAMOND);
        recipe.setIngredient('P', Material.PHANTOM_MEMBRANE);
        Bukkit.getServer().addRecipe(recipe);
    }
    private void anduril() {
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("anduril"), WesternItem.getItemStack(WesternItems.ANDURIL));
        recipe.shape("AIA", "AIA", "FBF");
        recipe.setIngredient('I', Material.IRON_BLOCK);
        recipe.setIngredient('B', Material.BLAZE_ROD);
        recipe.setIngredient('F', Material.FEATHER);
        recipe.setIngredient('A', Material.AMETHYST_SHARD);
        Bukkit.getServer().addRecipe(recipe);
    }
    private void sparkWand() {
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("spark-wand"), WesternItem.getItemStack(WesternItems.SPARK_WAND));
        recipe.shape(" GE", " AB", "S  ");
        recipe.setIngredient('S', Material.STICK);
        recipe.setIngredient('B', Material.BLAZE_POWDER);
        recipe.setIngredient('E', Material.EXPERIENCE_BOTTLE);
        recipe.setIngredient('G', Material.GOLD_NUGGET);
        recipe.setIngredient('A', Material.AMETHYST_SHARD);
        Bukkit.getServer().addRecipe(recipe);
    }
    private void scythe() {
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("scythe"), WesternItem.getItemStack(WesternItems.SCYTHE));
        recipe.shape(" FD", " BM", "B  ");
        recipe.setIngredient('F', Material.FERMENTED_SPIDER_EYE);
        recipe.setIngredient('B', Material.BONE);
        recipe.setIngredient('D', Material.DEAD_BUSH);
        recipe.setIngredient('M', Material.MAGMA_CREAM);
        Bukkit.getServer().addRecipe(recipe);
    }
    private void bookOfSkulls() {
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("book-of-skulls"), WesternItem.getItemStack(WesternItems.BOOK_OF_SKULLS));
        recipe.shape("GHI", "FED", "CBA");
        recipe.setIngredient('A', Material.BONE);
        recipe.setIngredient('B', Material.RABBIT_FOOT);
        recipe.setIngredient('C', Material.BLAZE_POWDER);
        recipe.setIngredient('D', Material.GLISTERING_MELON_SLICE);
        recipe.setIngredient('E', Material.WRITABLE_BOOK);
        recipe.setIngredient('F', Material.PUFFERFISH);
        recipe.setIngredient('G', Material.EMERALD);
        recipe.setIngredient('H', Material.COCOA_BEANS);
        recipe.setIngredient('I', Material.FERMENTED_SPIDER_EYE);

        Bukkit.getServer().addRecipe(recipe);
    }
    private void perun() {
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("perun"), WesternItem.getItemStack(WesternItems.PERUN));
        recipe.shape("GGN", "GLU", " N ");
        recipe.setIngredient('L', Material.LIGHTNING_ROD);
        recipe.setIngredient('G', Material.GOLD_BLOCK);
        recipe.setIngredient('N', Material.NETHERITE_SCRAP);
        recipe.setIngredient('U', Material.GUNPOWDER);
        Bukkit.getServer().addRecipe(recipe);
    }
    private void artemis() {
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("artemis"), WesternItem.getItemStack(WesternItems.ARTEMIS));
        recipe.shape("EBS", "BLS", "PBS");
        recipe.setIngredient('E', Material.ENDER_PEARL);
        recipe.setIngredient('L', Material.LAPIS_LAZULI);
        recipe.setIngredient('P', Material.BLAZE_POWDER);
        recipe.setIngredient('B', Material.BLAZE_ROD);
        recipe.setIngredient('S', Material.STRING);
        Bukkit.getServer().addRecipe(recipe);
    }
    private void crimsonWand() {
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("crimson-wand"), WesternItem.getItemStack(WesternItems.CRIMSON_WAND));
        recipe.shape("FCI", "GBH", " B ");
        recipe.setIngredient('F', Material.FIREWORK_ROCKET);
        recipe.setIngredient('C', Material.CRIMSON_FUNGUS);
        recipe.setIngredient('I', Material.FIRE_CHARGE);
        recipe.setIngredient('H', Material.HONEY_BOTTLE);
        recipe.setIngredient('B', Material.BLAZE_ROD);
        recipe.setIngredient('G', Material.GUNPOWDER);
        Bukkit.getServer().addRecipe(recipe);
    }
    private void brick() {
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("throwable-brick"), WesternItem.getItemStack(WesternItems.BRICK));
        recipe.shape("CCC", "CBC", "CCC");
        recipe.setIngredient('C', Material.COPPER_BLOCK);
        recipe.setIngredient('B', Material.BRICK);
        Bukkit.getServer().addRecipe(recipe);
    }
    private void wardensGrace() {
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("wardens-grace"), WesternItem.getItemStack(WesternItems.WARDENS_GRACE));
        recipe.shape("ABC", "DEF", "GHI");
        recipe.setIngredient('A', Material.NETHER_WART);
        recipe.setIngredient('B', Material.NETHER_STAR);
        recipe.setIngredient('C', Material.WITHER_ROSE);
        recipe.setIngredient('D', Material.NAME_TAG);
        recipe.setIngredient('E', Material.WRITABLE_BOOK);
        recipe.setIngredient('F', Material.CLOCK);
        recipe.setIngredient('G', Material.SUNFLOWER);
        recipe.setIngredient('H', Material.DIAMOND_BLOCK);
        recipe.setIngredient('I', Material.NETHERITE_SCRAP);

        Bukkit.getServer().addRecipe(recipe);
    }
}
