package me.sheer.LoreListener;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.v1_5_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class LPlayer implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String name = "";
	private int currentHealth = 20;
	private int maxHealth = 20;
	public boolean global = true;
	
	public LPlayer(String n, int maxhealth) {
		name = n;
		maxHealth = maxhealth;
		LoreListener.players.add(this);
		Bukkit.broadcastMessage(""+maxHealth);
	}
	
	public LPlayer(String n, int maxhealth, int currenthealth) {
		name = n;
		maxHealth = maxhealth;
		currentHealth = currenthealth;
		LoreListener.players.add(this);
		LPlayer.save(this);
		
	}
	
	//public void refreshHealth() {
	//	Player p = Bukkit.getPlayer(name);
	//	if (p != null)
	//		currentHealth = p.getHealth();
	//	LPlayer.save(this);
	//}
	
	public int getHealth() {
		return currentHealth;
	}
	
	public int getMaxHealth() {
		return maxHealth;
	}
	
	public void setMaxHealth(int h) {
		if (h < getHealth()) {
			setHealth(h, false);
			
		}
		maxHealth = Math.max(0, Math.min(2000000000, h));
		LPlayer.save(this);
		
	}
	
	public void setHealth(int h, boolean disableRegen) {
		Player p = Bukkit.getPlayer(name);
		if (h <= 0 && p != null) { // death
			//Random r = new Random();
			//World w = p.getWorld(); //DeathSplatter and such.
			for (ItemStack i : p.getInventory()) {
				if (i == null || i.getType().equals(Material.AIR))
					continue;
				// set item durability down 20%
			}
			for (ItemStack i : p.getInventory().getArmorContents()) {
				if (i == null || i.getType().equals(Material.AIR)) {
					continue;
				}
				// set item durability down 20%
			}
			CraftPlayer c = (CraftPlayer)p;
			c.getHandle().setHealth(0);
			currentHealth = 0;
			return;
		}
		currentHealth = Math.max(h,0);
		//if (p != null && currentHealth < maxHealth) {
		//	p.setHealth(Math.max(0, Math.min(19, h)));
		//} else if ((p != null && currentHealth >= maxHealth) | disableRegen) {
		//	p.setHealth(Math.max(0, Math.min(20, h)));
		//}
		if (p != null) {
			p.setHealth(HealthManager.constructHealthBar(this, 20));
		}
		save(this);
	}
	
	public static LPlayer getPlayer(String p) {
		for (LPlayer pp : LoreListener.players) {
			if (pp.getName().equalsIgnoreCase(p)) {
				return pp;
			}
		}
		return null;
	}
	
	public static void save(LPlayer p) {
		YamlConfiguration s = new YamlConfiguration();
		s.set("Name", p.getName());
		s.set("Current health", p.getHealth());
		s.set("Max health", p.getMaxHealth());
		
		
		try {
			s.save(new File(LoreListener.plugin.getDataFolder() + File.separator + "players" + File.separator + p.getName() + ".yml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getValue(String s) {
		YamlConfiguration yaml = YamlConfiguration.loadConfiguration(new File(LoreListener.plugin.getDataFolder() + File.separator + "players" + File.separator + getName() + ".yml"));
		return yaml.getString(s);
	}
	
	public void setValue(String p, Object v) {
		YamlConfiguration yaml = YamlConfiguration.loadConfiguration(new File(LoreListener.plugin.getDataFolder() + File.separator + "players" + File.separator + getName() + ".yml"));
		yaml.set(p, v);
	}
	
	public static void load(YamlConfiguration c) {
		if (c != null && c.getInt("Max health") != 0)
			new LPlayer(c.getString("Name"), c.getInt("Max health"), c.getInt("Current health"));
	}
	
	public static void load(String n) {
		YamlConfiguration c = YamlConfiguration.loadConfiguration(new File(LoreListener.plugin.getDataFolder() + File.separator + "players" + File.separator + n + ".yml"));
		Bukkit.broadcastMessage("loading " + n);
		
		if (c != null && c.getInt("Max health") != 0)
			new LPlayer(c.getString("Name"), c.getInt("Max health"), c.getInt("Current health"));
		else
			new LPlayer(n, 20, 20);
	}
	
	//public void loadInitialized(YamlConfiguration c) {
	//	name = c.getString("Name");
	//	setMaxHealth(c.getInt("Max health"));
	//	setHealth(c.getInt("Current health"), true);
	//}
	
	public String getName() {
		return name;
	}
	
	public static class PlayerJoinListener implements Listener {
		@EventHandler
		public void onPlayerJoin(PlayerJoinEvent event) {
			if (LPlayer.getPlayer(event.getPlayer().getName()) == null)
				load(event.getPlayer().getName());
		
		}
	}
}

