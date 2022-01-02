package me.woodsmc.deathchest.listener;

import me.woodsmc.deathchest.DeathChest;
import me.woodsmc.deathchest.tool.ToolManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;

public class InteractEvent implements Listener {


    private final Plugin plugin = DeathChest.getPlugin(DeathChest.class);
    String signLineOne = plugin.getConfig().getString("sign-line-1");
    @EventHandler
    public void onInteract(PlayerInteractEvent event){
        Player p = event.getPlayer();
        if(event.getAction() == Action.RIGHT_CLICK_BLOCK){
            if(event.getClickedBlock().getType() == Material.CHEST){
                Chest chest = (Chest) event.getClickedBlock().getState();
                if(chest.getLocation().add(0, 1, 0).getBlock() != null) {
                    if (chest.getLocation().add(0, 1, 0).getBlock().getType() == Material.OAK_SIGN) {
                        Sign sign = (Sign) chest.getLocation().add(0, 1, 0).getBlock().getState();
                        this.signLineOne = this.plugin.getConfig().getString("sign-line-1");
                        if(sign.getLine(0).equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', signLineOne.replace("{PLAYER}", DeathEvent.pName)))) {
                            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                                for(Player players : Bukkit.getOnlinePlayers()) {
                                    p.openInventory(ToolManager.inventory(players));
                                }
                            }, 3);
                        }
                    }
                }
            }

        }
    }
}
