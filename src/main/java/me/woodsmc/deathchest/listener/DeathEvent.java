package me.woodsmc.deathchest.listener;

import jdk.internal.org.jline.terminal.Terminal;
import me.woodsmc.deathchest.DeathChest;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;


public class DeathEvent implements Listener {

    private final Plugin plugin = DeathChest.getPlugin(DeathChest.class);
    public static ItemStack[] inventory;
    public static Location location;
    public static String pName;
    boolean despawnEnabled = plugin.getConfig().getBoolean("chest-despawn.enabled");
    int time = plugin.getConfig().getInt("chest-despawn.time");
    String despawnMessage = plugin.getConfig().getString("chest-despawn.message");
    String signType = plugin.getConfig().getString("sign-type");
    String signLineOne = plugin.getConfig().getString("sign-line-1");
    String signLineTwo = plugin.getConfig().getString("sign-line-2");
    String signLineThree = plugin.getConfig().getString("sign-line-3");
    String signLineFour = plugin.getConfig().getString("sign-line-4");

    @EventHandler
    public void onDeath(PlayerDeathEvent event){
        Player p = event.getEntity();
        p.getLocation().getBlock().setType(Material.CHEST);
        this.signType = this.plugin.getConfig().getString("sign-type");
        p.getLocation().add(0, 1, 0).getBlock().setType(Material.valueOf(signType));
        event.getDrops().clear();
        String name = p.getName();
        Location pLoc = p.getLocation();
        ItemStack[] items = p.getInventory().getContents();
        Sign sign = (Sign) p.getLocation().add(0, 1, 0).getBlock().getState();
        this.signLineOne = this.plugin.getConfig().getString("sign-line-1");
        this.signLineTwo = this.plugin.getConfig().getString("sign-line-2");
        this.signLineThree = this.plugin.getConfig().getString("sign-line-3");
        this.signLineFour = this.plugin.getConfig().getString("sign-line-4");
        sign.setLine(0, ChatColor.translateAlternateColorCodes('&', signLineOne.replace("{PLAYER}", p.getName())));
        sign.setLine(1, ChatColor.translateAlternateColorCodes('&', signLineTwo.replace("{PLAYER}", p.getName())));
        sign.setLine(2, ChatColor.translateAlternateColorCodes('&', signLineThree.replace("{PLAYER}", p.getName())));
        sign.setLine(3, ChatColor.translateAlternateColorCodes('&', signLineFour.replace("{PLAYER}", p.getName())));
        sign.update();
        sign.getLocation().setYaw(sign.getLocation().add(0, -1, 0).getBlock().getLocation().getYaw());
        this.despawnEnabled = this.plugin.getConfig().getBoolean("chest-despawn.enabled");
        if (despawnEnabled) {
            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                if(pLoc.getBlock() instanceof Chest) {
                    pLoc.getBlock().setType(Material.AIR);
                    this.despawnMessage = this.plugin.getConfig().getString("chest-despawn.message");
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', despawnMessage));
                    this.time = this.plugin.getConfig().getInt("chest-despawn.time");
                }else{

                }
                }, time * 20L);
        } else {

        }
        inventory = items;
        location = pLoc;
        pName = name;
    }

}
