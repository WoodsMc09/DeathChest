package me.woodsmc.deathchest.tool;

import me.woodsmc.deathchest.DeathChest;
import me.woodsmc.deathchest.listener.DeathEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class ToolManager {

    public static Inventory inventory(Player p) {
        Plugin plugin = DeathChest.getPlugin(DeathChest.class);
        String chestName = plugin.getConfig().getString("chest-name");
        chestName = plugin.getConfig().getString("chest-name");
        assert chestName != null;
        assert DeathEvent.pName != null;
        ItemStack itemStack = new ItemStack(Material.valueOf(plugin.getConfig().getString("chest-collect-item")));
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("chest-collect-item-name")));
        itemStack.setItemMeta(meta);
        Inventory inventory = Bukkit.createInventory(p, 45, ChatColor.translateAlternateColorCodes('&', chestName.replace("{PLAYER}", DeathEvent.pName)));
        if (DeathEvent.inventory != null)
            inventory.setContents(DeathEvent.inventory);
        inventory.setItem(44, itemStack);
        return inventory;
    }
}
