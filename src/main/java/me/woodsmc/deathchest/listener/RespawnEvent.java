package me.woodsmc.deathchest.listener;

import me.woodsmc.deathchest.DeathChest;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.Plugin;

public class RespawnEvent implements Listener {

    private final Plugin plugin = DeathChest.getPlugin(DeathChest.class);
    boolean despawnEnabled = plugin.getConfig().getBoolean("chest-despawn.enabled");
    int time = plugin.getConfig().getInt("chest-despawn.time");
    @EventHandler
    public void onRespawn(PlayerRespawnEvent event){
        Player p = event.getPlayer();
        for (String s : plugin.getConfig().getStringList("respawn-message")){
            if(DeathEvent.location != null) {
                int x = (int) DeathEvent.location.getX();
                int y = (int) DeathEvent.location.getY();
                int z = (int) DeathEvent.location.getZ();
                this.despawnEnabled = this.plugin.getConfig().getBoolean("chest-despawn.enabled");
                if(despawnEnabled){
                    String loc = x + ", " + y + ", " + z;
                    this.time = this.plugin.getConfig().getInt("chest-despawn.time");
                    String string = s.replace("{LOCATION}", loc).replace("{TIME}", String.valueOf(time));
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', string));
                }else {
                    String loc = x + ", " + y + ", " + z;
                    String string = s.replace("{LOCATION}", loc).replace("{TIME}", "infinite");
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', string));
                }

            }else{

            }
        }
    }
}
