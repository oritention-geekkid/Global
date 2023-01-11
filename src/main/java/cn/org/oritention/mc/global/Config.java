package cn.org.oritention.mc.global;

import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.Plugin;

public class Config {
    // Class Definition for Config
    static Plugin plugin = cn.org.oritention.mc.global.Global.getProvidingPlugin(cn.org.oritention.mc.global.Global.class);
    // To get boolean value from config
    public static boolean getConfigB(String key){
        return plugin.getConfig().getBoolean(key);
    }
    // To get String value from config
    public static String getConfigS(String key){
        return plugin.getConfig().getString(key);
    }
    // To get int value from config
    public static int getConfigI(String key){
        return plugin.getConfig().getInt(key);
    }
    // To get DamageCause from config
    public static EntityDamageEvent.DamageCause stringToDamageCause(String s){
        switch(s){
            case "CONTACT":
                return EntityDamageEvent.DamageCause.CONTACT;
            case "ENTITY_ATTACK":
                return EntityDamageEvent.DamageCause.ENTITY_ATTACK;
            case "ENTITY_SWEEP_ATTACK":
                return EntityDamageEvent.DamageCause.ENTITY_SWEEP_ATTACK;
            case "PROJECTILE":
                return EntityDamageEvent.DamageCause.PROJECTILE;
            case "SUFFOCATION":
                return EntityDamageEvent.DamageCause.SUFFOCATION;
            case "FALL":
                return EntityDamageEvent.DamageCause.FALL;
            case "FIRE":
                return EntityDamageEvent.DamageCause.FIRE;
            case "FIRE_TICK":
                return EntityDamageEvent.DamageCause.FIRE_TICK;
            case "MELTING":
                return EntityDamageEvent.DamageCause.MELTING;
            case "LAVA":
                return EntityDamageEvent.DamageCause.LAVA;
            case "DROWNING":
                return EntityDamageEvent.DamageCause.DROWNING;
            case "BLOCK_EXPLOSION":
                return EntityDamageEvent.DamageCause.BLOCK_EXPLOSION;
            case "ENTITY_EXPLOSION":
                return EntityDamageEvent.DamageCause.ENTITY_EXPLOSION;
            case "VOID":
                return EntityDamageEvent.DamageCause.VOID;
            case "LIGHTNING":
                return EntityDamageEvent.DamageCause.LIGHTNING;
            case "STARVATION":
                return EntityDamageEvent.DamageCause.STARVATION;
            case "POISON":
                return EntityDamageEvent.DamageCause.POISON;
            case "MAGIC":
                return EntityDamageEvent.DamageCause.MAGIC;
            case "WITHER":
                return EntityDamageEvent.DamageCause.WITHER;
            case "FALLING_BLOCK":
                return EntityDamageEvent.DamageCause.FALLING_BLOCK;
            case "THORNS":
                return EntityDamageEvent.DamageCause.THORNS;
            case "DRAGON_BREATH":
                return EntityDamageEvent.DamageCause.DRAGON_BREATH;
            case "CUSTOM":
                return EntityDamageEvent.DamageCause.CUSTOM;
            case "FLY_INTO_WALL":
                return EntityDamageEvent.DamageCause.FLY_INTO_WALL;
            case "HOT_FLOOR":
                return EntityDamageEvent.DamageCause.HOT_FLOOR;
            case "CRAMMING":
                return EntityDamageEvent.DamageCause.CRAMMING;
            case "DRYOUT":
                return EntityDamageEvent.DamageCause.DRYOUT;
        }
        return null;
    }

}
