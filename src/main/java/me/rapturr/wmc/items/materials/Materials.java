package me.rapturr.wmc.items.materials;

import me.rapturr.wmc.helpers.ItemRarity;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public enum Materials {


    GOLD("Gold", "basic lore prefix", Material.GOLD_INGOT, ItemRarity.EPIC, true),
    RAW_GOLD("Raw Gold", "basic lore prefix", Material.RAW_GOLD, ItemRarity.EPIC, false),
    GOLD_NUGGET("Gold Nugget", "basic lore prefix", Material.GOLD_NUGGET, ItemRarity.EPIC, false),
    IRON("Iron", "basic lore prefix", Material.IRON_INGOT, ItemRarity.RARE, false),
    RAW_IRON("Raw Iron", "basic lore prefix", Material.RAW_IRON, ItemRarity.RARE, false),
    SILVER("Silver", "A precious metal, found from\nrefining various resources.", Material.IRON_NUGGET, ItemRarity.RARE, true),
    STRANGE_CRYSTAL("Stange Crystal", "basic lore prefix", Material.AMETHYST_SHARD, ItemRarity.SPECIAL, true),
    QUARTZ("Quartz", "Quartz is a hard, crystalline\n mineral composed of silica.", Material.QUARTZ, ItemRarity.EPIC, true),
    BRONZE("Bronze", "basic lore prefix", Material.COPPER_INGOT, ItemRarity.UNCOMMON, false),
    AGED_BRONZE_PLATING("Aged Bronze Plating", "basic lore prefix", Material.NETHERITE_SCRAP, ItemRarity.UNCOMMON, false),
    CACTUS("Cactus", "basic lore prefix", Material.CACTUS, ItemRarity.COMMON, false),
    FEATHERS("Feathers", "basic lore prefix", Material.FEATHER, ItemRarity.UNCOMMON, false),
    RARE_FEATHER("Shiny Feather", "basic lore prefix", Material.FEATHER, ItemRarity.RARE, true),
    ANIMAL_PAW("Animal Paw", "basic lore prefix", Material.RABBIT_FOOT, ItemRarity.RARE, true),
    COD_LIVER_OIL("Cod Liver Oil", "basic lore prefix", Material.GHAST_TEAR, ItemRarity.RARE, false),
    GUNPOWDER("Gunpowder", "basic lore prefix", Material.GUNPOWDER, ItemRarity.UNCOMMON, false),
    HONEY_COMB("Honeycomb", "basic lore prefix", Material.HONEYCOMB, ItemRarity.UNCOMMON, false),
    SUGAR("Sugar", "basic lore prefix", Material.SUGAR, ItemRarity.COMMON, false),
    SUGAR_CANE("Sugar Cane", "basic lore prefix", Material.SUGAR_CANE, ItemRarity.COMMON, false),
    WHEAT("Wheat", "basic lore prefix", Material.WHEAT, ItemRarity.COMMON, false),
    CARROT("Carrot", "basic lore prefix", Material.CARROT, ItemRarity.COMMON, false),
    EGG("Egg", "basic lore prefix", Material.EGG, ItemRarity.UNCOMMON, false),
    STRANGE_EGG("Strange Egg", ChatColor.ITALIC + "What are those spots!?", Material.TURTLE_EGG, ItemRarity.SPECIAL, true),
    BONE("Bone", ChatColor.ITALIC + "yuk!", Material.BONE, ItemRarity.COMMON, false),
    FOSSIL("Fossil", "basic lore prefix", Material.NAUTILUS_SHELL, ItemRarity.RARE, false),
    CANDLE("Candle", "basic lore prefix", Material.CANDLE, ItemRarity.UNCOMMON, false),
    PICKLE("Pickle", "basic lore prefix", Material.SEA_PICKLE, ItemRarity.UNCOMMON, false),
    ANIMAL_FAT("Animal Fat", "Harvested from dead animals,\ncan be used as fuel.", Material.BONE_MEAL, ItemRarity.UNCOMMON, false),
    STRING("String", "basic lore prefix", Material.STRING, ItemRarity.COMMON, false),
    LORE_BOOK("Book of lore", ChatColor.ITALIC + "Book from the natives of these\n deserted lands, and their\n devestating stories.", Material.KNOWLEDGE_BOOK, ItemRarity.SPECIAL, true),


    SILVER_BULLET("Silver Bullet", "A deadly bullet made from pure silver\ninflicting extra damage.", Material.GHAST_TEAR, ItemRarity.RARE, true),


    GARLIC("Garlic", "basic lore prefix", Material.PHANTOM_MEMBRANE, ItemRarity.UNCOMMON, false);


    private final String displayName;
    private final String lore;
    private final Material material;
    private final ItemRarity itemRarity;
    private final boolean isShiny;

    Materials(String displayName, String lore, Material material, ItemRarity rarity, boolean isShiny) {
        this.displayName = displayName;
        this.lore = lore;
        this.material = material;
        this.itemRarity = rarity;
        this.isShiny = isShiny;
    }

    public ItemStack getItem() {
        ItemStack itemStack = new ItemStack(material);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(itemRarity.getColor() + displayName);
        if (isShiny)
            meta.addEnchant(Enchantment.LUCK, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_DYE, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_POTION_EFFECTS);
        meta.setUnbreakable(true);
        meta.setLore(createLore());


        itemStack.setItemMeta(meta);
        return itemStack;

    }

    private List<String> createLore() {

        List<String> finalLore = new ArrayList<>();
        Collection<String> descCollection = Arrays.asList(lore.split("\n"));
        Iterator iterator = descCollection.iterator();
        finalLore.add(ChatColor.DARK_GRAY + "Material");
        finalLore.add("");
        while (iterator.hasNext()) {
            String item = (String) iterator.next();
            item = ChatColor.GRAY + item;
            finalLore.add(item);
        }

        return finalLore;

    }

}
