package cn.org.oritention.mc.global;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

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
    private boolean isIgnoredCause(EntityDamageEvent.DamageCause cause, List<String> ignoreLst){
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
    public void PlayerDropItemEvent(PlayerDropItemEvent e){
        if(Config.getConfigB("NoDrop")) {
            if(!e.getPlayer().hasPermission(Config.getConfigS("DropPermission"))){
                e.setCancelled(true);
            }
        }
    }

    // PlayerInteractEvent
    private Boolean isNoInteractionBlock(Block block, List<String> Lst){
        for (String s : Lst) {
            if(block.getType().name().equals(s)) {
                return true;
            }
        }
        return false;
    }
    @EventHandler
    public void PlayerInteractEvent(PlayerInteractEvent e){
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
}
