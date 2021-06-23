package me.blume.controlplayer.listeners;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import me.blume.controlplayer.Main;
import me.blume.controlplayer.methods.ChangeExperience;
import me.blume.controlplayer.methods.ChangeLocations;
import me.blume.controlplayer.methods.ChangeName;
import me.blume.controlplayer.methods.HealthAndFood;
import me.blume.controlplayer.methods.HideAndShowPlayer;
import me.blume.controlplayer.methods.InventoryChange;
import me.blume.controlplayer.methods.StartControlling;

public class DeathEvent implements Listener{
	@SuppressWarnings("unused")
	private Main plugin;
	public DeathEvent(Main plugin) {
		this.plugin=plugin;
	}
	ChangeLocations cl = new ChangeLocations();
	HideAndShowPlayer hasp = new HideAndShowPlayer();
	HealthAndFood haf = new HealthAndFood();
	InventoryChange ic = new InventoryChange();
	ChangeExperience ce = new ChangeExperience();
	ChangeName cn = new ChangeName();
	StartControlling sc = new StartControlling();
	@SuppressWarnings("static-access")
	@EventHandler
	public void death(PlayerDeathEvent event){
		Player controller = event.getEntity();
		if(plugin.inControl.containsKey(controller.getUniqueId())) {
			UUID controllingUUID = plugin.inControl.get(controller.getUniqueId());
			Player controlling = Bukkit.getPlayer(controllingUUID);
			cn.changeName(plugin.controllerName, controller);
			hasp.showPlayer(controlling);
			controller.getInventory().clear();
			controlling.setHealth(0);
			controller.setHealth(Main.healthLevetlController);
			controller.setFoodLevel(Main.foodLevelController);
			controller.setExp(Main.experienceController);
			controller.setLevel(Main.levelController);
			controller.teleport(Main.getBackToLocationController);
			
			
		}
	}
	@EventHandler
	public void reborn(Player controller) {
		if(plugin.inControl.containsKey(controller.getUniqueId())) {
			controller.getInventory().clear();
			controller.getInventory().setContents(Main.inventoryController);
			plugin.inControl.remove(controller.getUniqueId());
		}
		
		
		
	}
}
