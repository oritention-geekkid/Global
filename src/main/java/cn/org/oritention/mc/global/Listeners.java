package cn.org.oritention.mc.global;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

public class Listeners implements Listener {

    // BlockBreakEvent
    @EventHandler
    public void BlockBreakEvent(BlockBreakEvent e) {
        if(Config.getConfigB("NoBreak")) {
            if(!e.getPlayer().hasPermission(Config.getConfigS("BreakPermission"))){
                e.setCancelled(true);
            }
        }
    }

    // BlockPlaceEvent
    @EventHandler
    public void BlockPlaceEvent(BlockPlaceEvent e) {
        if(Config.getConfigB("NoPlace")) {
            if(!e.getPlayer().hasPermission(Config.getConfigS("PlacePermission"))){
                e.setCancelled(true);
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
    @EventHandler
    public void EntityDamageEvent(EntityDamageEvent e) {
        if(Config.getConfigB("NoDamage")){
            if(e.getEntity() instanceof Player) {
                e.setCancelled(true);
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
    @EventHandler
    public void PlayerInteractEvent(PlayerInteractEvent e){
        if(Config.getConfigB("NoInteractionWithBlocks")) {
            if(!e.getPlayer().hasPermission(Config.getConfigS("InteractPermission"))) {
                if(e.hasBlock()) {
                    e.setCancelled(true);
                }
            }
        }
    }
}
