package me.sheer.LoreListener;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import me.sheer.LoreListener.LPlayer;
import me.sheer.LoreListener.LoreListener.SneakListener;
import me.sheer.skills.Skill;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import me.sheer.skills.Test;
import me.sheer.LoreListener.CommandHandler;
import me.sheer.LoreListener.HealthManager;
import me.sheer.LoreListener.HealthManager.DamageListener;
import me.sheer.LoreListener.HealthManager.EntityDamageListener;
import me.sheer.LoreListener.HealthManager.RegainListener;
import me.sheer.LoreListener.HealthManager.RespawnListener;
import me.sheer.LoreListener.LPlayer.PlayerJoinListener;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

public class LoreListener extends JavaPlugin {
	public static LoreListener plugin;
	public static Server server;
	

	public static final Logger logger = Logger.getLogger("Minecraft");

	public static List<LPlayer> players = new ArrayList<LPlayer>();
	public static HashMap<Player, Entity> vehicles = new HashMap<Player, Entity>();

	public static List<Skill> skills = new ArrayList<Skill>();

	public void onEnable() {
		plugin = this;
		server = getServer();

		boolean error = false;

		for (Player p : Bukkit.getOnlinePlayers()) {
			LPlayer.load(p.getName());
		}
	
			

		try {
			new File(getDataFolder() + File.separator + "players").mkdirs();
		} catch (Exception e) {
			e.printStackTrace();
			error = true;
		}

		registerEvents();

		if (!error)
			logger.info(getName() + " has been enabled.");
		else
			logger.warning(getName() + " has been enabled, but with errors.");
	}

	public void onDisable() {
		logger.info(getName() + " has been disabled.");
	}

	public void registerEvents() {
		server.getPluginManager().registerEvents(
				new HealthManager.DamageListener(), plugin);
		server.getPluginManager().registerEvents(
				new HealthManager.EntityDamageListener(), plugin);
		server.getPluginManager().registerEvents(
				new HealthManager.RegainListener(), plugin);
		server.getPluginManager().registerEvents(
				new HealthManager.RespawnListener(), plugin);
		server.getPluginManager().registerEvents(
				new LPlayer.PlayerJoinListener(), plugin);
		server.getPluginManager().registerEvents(new SneakListener(), plugin);
	}

	public void load() {
		File f = new File(plugin.getDataFolder() + File.separator + "players");
		String p = f.getAbsolutePath() + File.separator;
		String[] dir = f.list();

		for (String n : dir) {
			File s = new File(p + n);
			LPlayer.load(YamlConfiguration.loadConfiguration(s));
		}
	}

	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		return CommandHandler.execute(sender, command, label, args);
	}

	public static WorldGuardPlugin getWorldGuard() {
		Plugin plugin = LoreListener.plugin.getServer().getPluginManager()
				.getPlugin("WorldGuard");

		// WorldGuard may not be loaded
		if (plugin == null || !(plugin instanceof WorldGuardPlugin)) {
			return null;
		}

		return (WorldGuardPlugin) plugin;
	}

	class SneakListener implements Listener {
		@EventHandler
		public void onSneak(PlayerToggleSneakEvent e) {
			// if (e.getPlayer().isSneaking()) {
			try {
				vehicles.get(e.getPlayer()).setVelocity(
						e.getPlayer().getLocation().getDirection().normalize()
								.add(new Vector(0, 0.5, 0)));
			} catch (Exception e1) {
			}
		}

		@EventHandler
		public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent damage) {
			
			Entity damager = damage.getDamager();
			if (damager instanceof Player) {
				Player player = ((Player) damager);
				String name = player.getName();
				YamlConfiguration timer = new YamlConfiguration();
				YamlConfiguration yaml = YamlConfiguration
						.loadConfiguration(new File(getDataFolder()
								+ File.separator + "timez" + File.separator
								+ name + ".yml"));
				timer.set("name", name);
				timer.set("time", player.getWorld().getTime());
				
				if (yaml.getInt("time") + 40 < player.getWorld().getTime() || yaml.getInt("time") - 40 > player.getWorld().getTime()) {
					
					StrengthListener stre = new StrengthListener();
					stre.onEntityDamageByEntityEvent(damage);
					try {
						timer.save(new File(getDataFolder() + File.separator
								+ "timez" + File.separator + name + ".yml"));
					} catch (IOException e) {
						e.printStackTrace();
					}

				} else {
					damage.setCancelled(true);
					

				}
				

				if (damage.getEntity() instanceof Player) {
					Entity entity = damage.getEntity();
					Player player2 = ((Player) entity);
					int blahh = 0;
					blahh = LPlayer.getPlayer(player2.getName()).getHealth();
					

				}
			}else if (damager instanceof Projectile){
				PrecisionListener prec = new PrecisionListener();
				prec.onEntityDamageByEntityEvent(damage);
			}

		}

		@EventHandler
		public void onPlayerJoinEvent(PlayerJoinEvent health) {
			HealthListener hp = new HealthListener();
			hp.onPlayerJoinEvent(health);

		}

		@EventHandler
		public void onInventoryCloseEvent(InventoryCloseEvent health) {
			HealthListener hp = new HealthListener();
			hp.onInventoryCloseEvent(health);

		}
	}
}
