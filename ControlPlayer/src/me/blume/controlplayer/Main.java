package me.blume.controlplayer;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import me.blume.controlplayer.commands.ControlPlayerCommand;
import me.blume.controlplayer.commands.StopControlCommand;
import me.blume.controlplayer.listeners.CantMove;
import me.blume.controlplayer.listeners.DeathEvent;

public class Main extends JavaPlugin{
	public HashMap<UUID,UUID> inControl = new HashMap<UUID,UUID>();
	public String controllerName,controllingName;
	public static Location getBackToLocationController,getBackToLocationControlling;
	public static int foodLevelController,foodLevelControlling;
	public static double healthLevetlController,healthLevelControlling;
	public static ItemStack[] inventoryController,inventoryControlling;
	public static float experienceController,experienceControlling;
	public static int levelController,levelControlling;
	@Override
	public void onEnable() {
		getCommand("control").setExecutor(new ControlPlayerCommand(this));
		getCommand("stopcontrol").setExecutor(new StopControlCommand(this));
		getServer().getPluginManager().registerEvents(new CantMove(this), this);
		getServer().getPluginManager().registerEvents(new DeathEvent(this), this);
	}
}
