package me.rapturr.wmc.helpers;

import me.rapturr.wmc.WMC;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Recipe;

import java.util.Iterator;
import java.util.List;

public class RemoveRecipes {

    Iterator<Recipe> recipes = WMC.getInstance().getServer().recipeIterator();

    public RemoveRecipes(List<Material> bannedRecipes) {
        while (recipes.hasNext()) {
            Recipe recipe = recipes.next();
            if (recipe != null && bannedRecipes.contains(recipe.getResult().getType())) {
                Bukkit.getConsoleSender().sendMessage("[WMC] removed recipe for " + recipe.getResult().getType().name() + ".");
                recipes.remove();
            }
        }
    }


}
