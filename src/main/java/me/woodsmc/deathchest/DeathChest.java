package me.woodsmc.deathchest;

import me.woodsmc.deathchest.command.DeathChestCommand;
import me.woodsmc.deathchest.listener.DeathEvent;
import me.woodsmc.deathchest.listener.GuiClickEvent;
import me.woodsmc.deathchest.listener.InteractEvent;
import me.woodsmc.deathchest.listener.RespawnEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class DeathChest extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getConsoleSender().sendMessage("§8[§d§lDeathChest §7§l>>> §5Plugin has enabled §8]");
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        getCommand("deathchest").setExecutor(new DeathChestCommand());
        getServer().getPluginManager().registerEvents(new DeathEvent(), this);
        getServer().getPluginManager().registerEvents(new RespawnEvent(), this);
        getServer().getPluginManager().registerEvents(new InteractEvent(), this);
        getServer().getPluginManager().registerEvents(new GuiClickEvent(), this);
    }


}
