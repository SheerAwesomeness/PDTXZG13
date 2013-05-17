package me.sheer.LoreListener;

import java.util.List;
import java.util.Random;

import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;



public class StrengthListener {
	public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent damage) {
		Entity damager = damage.getDamager();
	
		if (damager instanceof Player) {
			Player player = ((Player) damager);
			List<String> getlore = null;
			List<String> getweplore = null;
			
			

			
			int total = 0;
			String wepdmg;
			int wepdmg2;
			String wepdmg3;
			String wepdmg4;
			int wepdmglower;
			String wepdmg6;
			String wepdmg7;
			int wepdmghigher;
			int randomdamage = 0;
			int str = 0;
			int str2 = 0;
			int str3 = 0;
			int str4 = 0;
			try {
				ItemStack billy = player.getItemInHand();
				getweplore = billy.getItemMeta().getLore();
				

				if (getweplore.get(0).contains("§eWeapon Damage ")) {
					wepdmg = getweplore.get(0).substring(15);
					wepdmg2 = getweplore.get(0).indexOf("-");
					wepdmg3 = wepdmg.substring(0, wepdmg2-1);
					wepdmg4 = wepdmg.substring(wepdmg2+1);
					wepdmglower = Integer.parseInt(wepdmg3);
					wepdmghigher = Integer.parseInt(wepdmg4);
					Random range = new Random();
					randomdamage = wepdmglower+range.nextInt(wepdmghigher-wepdmglower+1);
					
					

				} else {

				}
			} catch (Exception e) {

			}
			try {
				ItemStack bob = player.getInventory().getHelmet();
				getlore = bob.getItemMeta().getLore();

				if (getlore.get(0).contains("§cStrength +")) {
					str = Integer.parseInt(getlore.get(0).substring(12));

				} else {

				}
			} catch (Exception e) {

			}
			try {
				ItemStack bob = player.getInventory().getLeggings();
				getlore = bob.getItemMeta().getLore();

				if (getlore.get(0).contains("§cStrength +")) {
					str2 = Integer.parseInt(getlore.get(0).substring(12));

				} else {

				}
			} catch (Exception e) {

			}
			try {
				ItemStack bob = player.getInventory().getBoots();
				getlore = bob.getItemMeta().getLore();

				if (getlore.get(0).contains("§cStrength +")) {
					str3 = Integer.parseInt(getlore.get(0).substring(12));

				} else {

				}
			} catch (Exception e) {

			}
			try {
				ItemStack bob = player.getInventory().getChestplate();
				getlore = bob.getItemMeta().getLore();

				if (getlore.get(0).contains("§cStrength +")) {
					str4 = Integer.parseInt(getlore.get(0).substring(12));

				} else {

				}
			} catch (Exception e) {

			}
			total = str + str2 + str3 + str4;
			int real = (total / 2)+randomdamage;
			
			damage.setDamage(real);
			long time2 = player.getWorld().getTime();
			if (damage.getEntity() instanceof Player) {
				Entity entity = damage.getEntity();
				Player player2 = ((Player) entity);
				int blahh = 0;
				blahh = LPlayer.getPlayer(player2.getName()).getHealth();
				LPlayer.getPlayer(player2.getName()).setHealth(blahh-real, true);
				
			}
			
		}

	}
}



