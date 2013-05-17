package me.sheer.LoreListener;
import java.util.List;


import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;


public class HealthListener {
	public void onPlayerJoinEvent(PlayerJoinEvent health) {
		Player player = health.getPlayer();
		
	
	List<String> getlore = null;
	
	


int total = 0;

int pre = 0;
int pre2 = 0;
int pre3 = 0;
int pre4 = 0;
	try {
		ItemStack bob = player.getInventory().getHelmet();
		getlore = bob.getItemMeta().getLore();

		if (getlore.get(0).contains("§bHealth +")) {
			pre = Integer.parseInt(getlore.get(0).substring(10));

		} else {

		}
	} catch (Exception e) {

	}
	try {
		ItemStack bob = player.getInventory().getLeggings();
		getlore = bob.getItemMeta().getLore();

		if (getlore.get(0).contains("§bHealth +")) {
			pre2 = Integer.parseInt(getlore.get(0).substring(10));

		} else {

		}
	} catch (Exception e) {

	}
	try {
		ItemStack bob = player.getInventory().getBoots();
		getlore = bob.getItemMeta().getLore();

		if (getlore.get(0).contains("§bHealth +")) {
			pre3 = Integer.parseInt(getlore.get(0).substring(10));

		} else {

		}
	} catch (Exception e) {

	}
	try {
		ItemStack bob = player.getInventory().getChestplate();
		getlore = bob.getItemMeta().getLore();

		if (getlore.get(0).contains("§bHealth +")) {
			pre4 = Integer.parseInt(getlore.get(0).substring(10));

		} else {

		}
	} catch (Exception e) {

	}
	total = pre + pre2 + pre3 + pre4;
	LPlayer.getPlayer(player.getName()).setMaxHealth(total+20);
	int shoo = LPlayer.getPlayer(player.getName()).getMaxHealth();
	if (shoo < LPlayer.getPlayer(player.getName()).getHealth()){
		LPlayer.getPlayer(player.getName()).setHealth(shoo, true);
	}
	
	
	
}
	public void onInventoryCloseEvent(InventoryCloseEvent health) {
		Player player = (Player) health.getPlayer();
		
	
	List<String> getlore = null;
	
	


int total = 0;

int pre = 0;
int pre2 = 0;
int pre3 = 0;
int pre4 = 0;
	try {
		ItemStack bob = player.getInventory().getHelmet();
		getlore = bob.getItemMeta().getLore();

		if (getlore.get(0).contains("§bHealth +")) {
			pre = Integer.parseInt(getlore.get(0).substring(10));

		} else {

		}
	} catch (Exception e) {

	}
	try {
		ItemStack bob = player.getInventory().getLeggings();
		getlore = bob.getItemMeta().getLore();

		if (getlore.get(0).contains("§bHealth +")) {
			pre2 = Integer.parseInt(getlore.get(0).substring(10));

		} else {

		}
	} catch (Exception e) {

	}
	try {
		ItemStack bob = player.getInventory().getBoots();
		getlore = bob.getItemMeta().getLore();

		if (getlore.get(0).contains("§bHealth +")) {
			pre3 = Integer.parseInt(getlore.get(0).substring(10));

		} else {

		}
	} catch (Exception e) {

	}
	try {
		ItemStack bob = player.getInventory().getChestplate();
		getlore = bob.getItemMeta().getLore();

		if (getlore.get(0).contains("§bHealth +")) {
			pre4 = Integer.parseInt(getlore.get(0).substring(10));

		} else {

		}
	} catch (Exception e) {

	}
	total = pre + pre2 + pre3 + pre4;
	LPlayer.getPlayer(player.getName()).setMaxHealth(total+20);
	int shoo = LPlayer.getPlayer(player.getName()).getMaxHealth();
	if (shoo < LPlayer.getPlayer(player.getName()).getHealth()){
		LPlayer.getPlayer(player.getName()).setHealth(shoo, true);
	}
	
	
	
}}	


