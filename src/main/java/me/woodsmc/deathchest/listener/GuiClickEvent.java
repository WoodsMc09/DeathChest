package me.woodsmc.deathchest.listener;

import me.woodsmc.deathchest.DeathChest;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class GuiClickEvent implements Listener {

    private Plugin plugin = DeathChest.getPlugin(DeathChest.class);

    @EventHandler
    public void onClick(InventoryClickEvent event){
        Player p = (Player) event.getWhoClicked();
        if(event.getClickedInventory().getSize() == 45){
            if(event.getCurrentItem() != null) {
                if (event.getCurrentItem().getItemMeta() != null) {
                    if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&',
                            plugin.getConfig().getString("chest-collect-item-name")))){
                        event.getClickedInventory().remove(event.getCurrentItem());
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("chest-collected-message")));
                        Bukkit.getScheduler().runTaskLater(plugin, () -> {
                        for(ItemStack item : event.getClickedInventory().getContents()){
                            if(item != null) {
                                p.getWorld().dropItem(p.getLocation(), item);
                                p.closeInventory();
                                DeathEvent.location.add(0, 1, 0).getBlock().setType(Material.AIR);
                                DeathEvent.location.getBlock().setType(Material.AIR);
                            }
                        }
                        }, 5);
                    }
                }
            }
        }
    }
}
