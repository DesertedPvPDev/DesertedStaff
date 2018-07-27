package codes.matthewp.desertedstaff.util;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ItemFactory {

    private ItemStack stack;
    private ItemMeta meta;
    private List<String> lore;
    private String name;

    public ItemFactory(Material mat) {
        stack = new ItemStack(mat);
        meta = stack.getItemMeta();
    }

    public ItemFactory(Material mat, int count) {
        stack = new ItemStack(mat, count);
        meta = stack.getItemMeta();
    }

    public ItemFactory(Material mat, int count, int data) {
        stack = new ItemStack(mat, count, (byte) data);
        meta = stack.getItemMeta();
    }

    public ItemFactory setName(String str) {
        this.name = name;
        return this;
    }

    public ItemFactory addLore(String str) {
        if(lore == null) {
            lore= new ArrayList<>();
        }
        lore.add(str);
        return this;
    }

    private void updateMeta() {
        meta.setDisplayName(color(name));
        meta.setLore(colorList(lore));
        stack.setItemMeta(meta);
    }

    public ItemStack build() {
        updateMeta();
        return stack;
    }

    private String color(String str) {
        return ChatColor.translateAlternateColorCodes('&', str);
    }

    private List<String> colorList(List<String> list) {
        return list.stream().map(s -> color(s)).collect(Collectors.toList());
    }
}
