package cn.org.oritention.mc.global;

import cn.org.oritention.mc.global.commands.Day;
import cn.org.oritention.mc.global.commands.Night;
import cn.org.oritention.mc.global.commands.Time;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public final class Global extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin Startup Notice
        noteOnConsole("[Global] Global-2.0 was loaded successfully！");
        // Event Register
        getServer().getPluginManager().registerEvents(new Listeners(), this);
        // Config Load
        saveDefaultConfig();
        // Command Load
        if(Config.getConfigB("TimeChangeCommandToggle")) {
            getCommand("day").setExecutor(new Day());
            getCommand("night").setExecutor(new Night());
            getCommand("settime").setExecutor(new Time());
        }
    }

    @Override
    public void onDisable() {
        // Plugin Shutdown Notice
        noteOnConsole("[Global] Global-2.0 was unloaded successfully！");
    }

    public void noteOnConsole(String s) {
        // Note something on the console
        CommandSender sender = Bukkit.getConsoleSender();
        sender.sendMessage(s);
    }

}
