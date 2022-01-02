package me.woodsmc.deathchest.command;

import me.woodsmc.deathchest.DeathChest;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class DeathChestCommand implements CommandExecutor {

    private Plugin plugin = DeathChest.getPlugin(DeathChest.class);
    String string = plugin.getConfig().getString("reload-message");
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            if(p.hasPermission("deathchest.reload")){
                if(args.length == 0){
                    p.sendMessage("§cUsage: /dc reload");
                }if(args.length == 1){
                    if(args[0].equalsIgnoreCase("reload")){
                        this.string = this.plugin.getConfig().getString("reload-message");
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', string));
                        plugin.reloadConfig();

                    }
                }

            }else{
                p.sendMessage("§cYou do not have the permission to execute this command!");
            }

        }else{
            sender.sendMessage("§cOnly players may execute this command!");
        }

        return true;
    }
}
