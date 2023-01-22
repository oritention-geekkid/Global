package cn.org.oritention.mc.global;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

import java.util.ArrayList;
import java.util.List;

public class Listeners implements Listener {

    private Boolean isIgnoredBlock(Block block, List<String> ignoreLst){
        for (String s : ignoreLst) {
            if (block.getType().name().equals(s)){
                return true;
            }
        }
        return false;
    }

    // BlockBreakEvent
    @EventHandler
    public void BlockBreakEvent(BlockBreakEvent e) {
        if(Config.getConfigB("NoBreak")) {
            if(!e.getPlayer().hasPermission(Config.getConfigS("BreakPermission"))) {
                if(!isIgnoredBlock(e.getBlock(),Config.getConfigStringLst("NoBreakException"))) {
                    e.setCancelled(true);
                }
            }
        }
    }

    // BlockPlaceEvent
    @EventHandler
    public void BlockPlaceEvent(BlockPlaceEvent e) {
        if(Config.getConfigB("NoPlace")) {
            if(!e.getPlayer().hasPermission(Config.getConfigS("PlacePermission"))) {
                if (!isIgnoredBlock(e.getBlock(), Config.getConfigStringLst("NoPlaceException"))){
                    e.setCancelled(true);
                }
            }
        }
    }

    // WeatherChangeEvent
    @EventHandler
    public void WeatherChangeEvent(WeatherChangeEvent e) {
        if(Config.getConfigB("NoWeatherChange")) {
            e.setCancelled(true);
        }
    }

    // FoodLevelChangeEvent
    @EventHandler
    public void FoodLevelChangeEvent(FoodLevelChangeEvent e) {
        if(Config.getConfigB("NoHunger")) {
            if(e.getEntity() instanceof Player) {
                e.getEntity().setFoodLevel(20);
                e.setCancelled(true);
            }
        }
    }

    // EntityDamageEvent
    private boolean isIgnoredCause(EntityDamageEvent.DamageCause cause, List<String> ignoreLst) {
        for (String ignoreCause : ignoreLst) {
            if(cause.name().equals(ignoreCause)) {
                return false;
            }
        }
        return true;
    }
    @EventHandler
    public void EntityDamageEvent(EntityDamageEvent e) {
        if(Config.getConfigB("NoDamage")) {
            if(e.getEntity() instanceof Player) {
                e.setCancelled(isIgnoredCause(e.getCause(), Config.getConfigStringLst("NoDamageException")));
            }
        }
    }

    // PlayerDropItemEvent
    @EventHandler
    public void PlayerDropItemEvent(PlayerDropItemEvent e) {
        if(Config.getConfigB("NoDrop")) {
            if(!e.getPlayer().hasPermission(Config.getConfigS("DropPermission"))){
                e.setCancelled(true);
            }
        }
    }

    // PlayerInteractEvent
    private Boolean isNoInteractionBlock(Block block, List<String> Lst) {
        for (String s : Lst) {
            if(block.getType().name().equals(s)) {
                return true;
            }
        }
        return false;
    }
    @EventHandler
    public void PlayerInteractEvent(PlayerInteractEvent e) {
        if(Config.getConfigB("NoInteractionWithBlocks")) {
            if(!e.getPlayer().hasPermission(Config.getConfigS("InteractPermission"))) {
                if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                    if (isNoInteractionBlock(e.getClickedBlock(), Config.getConfigStringLst("NoInteractionBlocks"))) {
                        e.setCancelled(true);
                    }
                }
            }
        }
    }

    // AsyncPlayerChatEvent
    ArrayList<String> cooldownLst = new ArrayList<>();
    private String getFormatMessage (Player p, String m) {
        String tmp = Config.getConfigS("ChatFormat");
        if (p.hasPermission(Config.getConfigS("AdminPermission"))) {
            tmp = tmp.replaceAll("%prefix%",Config.getConfigS("AdminPrefix"));
        } else {
            tmp = tmp.replaceAll("%prefix%",Config.getConfigS("PlayerPrefix"));
        }
        tmp = tmp.replaceAll("%player%",p.getName());
        tmp = tmp.replaceAll("%message%",m);
        return tmp;
    }
    @EventHandler
    public void AsyncPlayerChatEvent(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        if (Config.getConfigB("ChatCooldownToggle")) {
            if (cooldownLst.contains(p.getName())) {
                p.sendMessage(ChatColor.RED+"Chat Cooldowning...");
                e.setCancelled(true);
            } else {
                cooldownLst.add(p.getName());
                e.getPlayer().getServer().getScheduler().scheduleAsyncDelayedTask(cn.org.oritention.mc.global.Global.getProvidingPlugin(cn.org.oritention.mc.global.Global.class), () -> cooldownLst.remove(p.getName()), 20L * (long)Config.getConfigI("CooldownTime"));
                if (Config.getConfigB("ChatFormatToggle")) {
                    String m = e.getMessage();
                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&',getFormatMessage(p,m)));
                    e.setCancelled(true);
                }
            }
        } else if (Config.getConfigB("ChatFormatToggle")) {
            String m = e.getMessage();
            Bukkit.broadcastMessage(getFormatMessage(p,m));
            e.setCancelled(true);
        }
    }
}
