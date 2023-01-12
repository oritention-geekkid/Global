package cn.org.oritention.mc.global;

import org.bukkit.plugin.Plugin;

import java.util.List;

public class Config {
    // Class Definition for Config
    static Plugin plugin = cn.org.oritention.mc.global.Global.getProvidingPlugin(cn.org.oritention.mc.global.Global.class);

    // To get boolean value from config
    public static boolean getConfigB(String key) {
        return plugin.getConfig().getBoolean(key);
    }

    // To get String value from config
    public static String getConfigS(String key) {
        return plugin.getConfig().getString(key);
    }

    // To get int value from config
    public static int getConfigI(String key) {
        return plugin.getConfig().getInt(key);
    }

    // To get DamageCause from config
    public static List<String> getConfigStringLst(String key) {
        return plugin.getConfig().getStringList(key);
    }
}