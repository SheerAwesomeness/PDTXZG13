package me.sheer.LoreListener;

import java.util.Random;


import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.enchantments.EnchantmentWrapper;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;
//import org.bukkit.util.Vector;

import me.sheer.LoreListener.ChatEffects;
import me.sheer.LoreListener.LPlayer;
import me.sheer.LoreListener.StrengthListener;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.DefaultFlag;
import com.sk89q.worldguard.protection.managers.RegionManager;

public class HealthManager {
	public static int constructHealthBar(LPlayer mp, int length) {
		int barLength = length;
		//String healthLeft = "", lostHealth = "";
		int h = Math.min(barLength,
				(int) (1.0 * mp.getHealth() / mp.getMaxHealth() * barLength+1));
		//int l = barLength - h;
		//for (int i = h; i > 0; i--) {
		//	healthLeft += "|";
		//}
		//for (int i = l; i > 0; i--) {
		//	lostHealth += "|";
		//}
		// String m = "(" + mp.getHealth() + "/" + mp.maxHealth + ")";
		//return (ChatColor.BLUE + "{[" + ChatColor.GREEN + healthLeft
		//		+ ChatColor.RED + lostHealth + ChatColor.BLUE + "]} " + ChatColor.GRAY);
		return h;
	}
	
	public static int absoluteHealth(LPlayer mp, int damage, boolean invulnerable,
			DamageCause cause) {
		int health = mp.getHealth();
		Player p = Bukkit.getPlayer(mp.getName());
		// CraftPlayer c = (CraftPlayer)p;
		// if (invulnerable || c.getHandle().isInvulnerable())
		// return health;
		Random r = new Random();
		Double actualDamage = damage * (1.0 - getDamageReduced(p, cause));
		Double leftOver = actualDamage - Math.floor(actualDamage);
		damage = (int) ((r.nextDouble() <= leftOver) ? actualDamage + 1
				: actualDamage);
		return health - damage;
	}
	
	public static double getDamageReduced(Player player, DamageCause cause) {
		PlayerInventory inv = player.getInventory();
		ItemStack boots = inv.getBoots();
		ItemStack helmet = inv.getHelmet();
		ItemStack chest = inv.getChestplate();
		ItemStack pants = inv.getLeggings();
		double red = 0.0;
		int epfProt = 0;
		int epfFire = 0;
		int epfExpl = 0;
		int epfProj = 0;
		int epfFall = 0;
		Integer bootsProt, helmetProt, chestProt, pantsProt, bootsProtf, helmetProtf, chestProtf, pantsProtf, fallprot, bootsProte, helmetProte, chestProte, pantsProte, bootsProtp, helmetProtp, chestProtp, pantsProtp;
		try {
			bootsProt = boots.getEnchantments().get(new EnchantmentWrapper(0));
			if (bootsProt != null)
				bootsProt = (bootsProt == 0) ? 0 : (int) Math.floor((6 + Math
						.pow(bootsProt, 2)) * 0.75 / 3);
			else
				bootsProt = 0;
		} catch (Exception e) {
			bootsProt = 0;
		}
		try {
			helmetProt = helmet.getEnchantments()
					.get(new EnchantmentWrapper(0));
			if (helmetProt != null)
				helmetProt = (helmetProt == 0) ? 0 : (int) Math.floor((6 + Math
						.pow(helmetProt, 2)) * 0.75 / 3);
			else
				helmetProt = 0;
		} catch (Exception e) {
			helmetProt = 0;
		}
		try {
			chestProt = chest.getEnchantments().get(new EnchantmentWrapper(0));
			if (chestProt != null)
				chestProt = (chestProt == 0) ? 0 : (int) Math.floor((6 + Math
						.pow(chestProt, 2)) * 0.75 / 3);
			else
				chestProt = 0;
		} catch (Exception e) {
			chestProt = 0;
		}
		try {
			pantsProt = pants.getEnchantments().get(new EnchantmentWrapper(0));
			if (pantsProt != null)
				pantsProt = (pantsProt == 0) ? 0 : (int) Math.floor((6 + Math
						.pow(pantsProt, 2)) * 0.75 / 3);
			else
				pantsProt = 0;
		} catch (Exception e) {
			pantsProt = 0;
		}
		epfProt = bootsProt + helmetProt + chestProt + pantsProt;
		try {
			bootsProtf = boots.getEnchantments().get(new EnchantmentWrapper(1));
			if (bootsProtf != null)
				bootsProtf = (bootsProtf == 0) ? 0 : (int) Math.floor((6 + Math
						.pow(bootsProtf, 2)) * 1.25 / 3);
			else
				bootsProtf = 0;
		} catch (Exception e) {
			bootsProtf = 0;
		}
		try {
			helmetProtf = helmet.getEnchantments().get(
					new EnchantmentWrapper(1));
			if (helmetProtf != null)
				helmetProtf = (helmetProtf == 0) ? 0 : (int) Math
						.floor((6 + Math.pow(helmetProtf, 2)) * 1.25 / 3);
			else
				helmetProtf = 0;
		} catch (Exception e) {
			helmetProtf = 0;
		}
		try {
			chestProtf = chest.getEnchantments().get(new EnchantmentWrapper(1));
			if (chestProtf != null)
				chestProtf = (chestProtf == 0) ? 0 : (int) Math.floor((6 + Math
						.pow(chestProtf, 2)) * 1.25 / 3);
			else
				chestProtf = 0;
		} catch (Exception e) {
			chestProtf = 0;
		}
		try {
			pantsProtf = pants.getEnchantments().get(new EnchantmentWrapper(1));
			if (pantsProtf != null)
				pantsProtf = (pantsProtf == 0) ? 0 : (int) Math.floor((6 + Math
						.pow(pantsProtf, 2)) * 1.25 / 3);
			else
				pantsProtf = 0;
		} catch (Exception e) {
			pantsProtf = 0;
		}
		epfFire = bootsProtf + helmetProtf + chestProtf + pantsProtf;
		try {
			fallprot = boots.getEnchantments().get(new EnchantmentWrapper(2));
		} catch (Exception e) {
			fallprot = 0;
		}
		if (fallprot != null && fallprot != 0)
			epfFall = (int) Math.floor((6 + Math.pow(fallprot, 2)) * 2.5 / 3);
		else
			epfFall = 0;
		try {
			bootsProte = boots.getEnchantments().get(new EnchantmentWrapper(3));
			if (bootsProte != null) {
				bootsProte = (bootsProte == 0) ? 0 : (int) Math.floor((6 + Math
						.pow(bootsProte, 2)) * 1.5 / 3);
			} else {
				bootsProte = 0;
			}
		} catch (Exception e) {
			bootsProte = 0;
		}
		try {
			helmetProte = helmet.getEnchantments().get(
					new EnchantmentWrapper(3));
			if (helmetProte != null)
				helmetProte = (helmetProte == 0) ? 0 : (int) Math
						.floor((6 + Math.pow(helmetProte, 2)) * 1.5 / 3);
			else
				helmetProte = 0;
		} catch (Exception e) {
			helmetProte = 0;
		}
		try {
			chestProte = chest.getEnchantments().get(new EnchantmentWrapper(3));
			if (chestProte != null)
				chestProte = (chestProte == 0) ? 0 : (int) Math.floor((6 + Math
						.pow(chestProte, 2)) * 1.5 / 3);
			else
				chestProte = 0;
		} catch (Exception e) {
			chestProte = 0;
		}
		try {
			pantsProte = pants.getEnchantments().get(new EnchantmentWrapper(3));
			if (pantsProte != null)
				pantsProte = (pantsProte == 0) ? 0 : (int) Math.floor((6 + Math
						.pow(pantsProte, 2)) * 1.5 / 3);
			else
				pantsProte = 0;
		} catch (Exception e) {
			pantsProte = 0;
		}
		epfExpl = bootsProte + helmetProte + chestProte + pantsProte;
		try {
			bootsProtp = boots.getEnchantments().get(new EnchantmentWrapper(4));
			if (bootsProtp != null)
				bootsProtp = (bootsProtp == 0) ? 0 : (int) Math.floor((6 + Math
						.pow(bootsProtp, 2)) * 1.5 / 3);
			else
				bootsProtp = 0;
		} catch (Exception e) {
			bootsProtp = 0;
		}
		try {
			helmetProtp = helmet.getEnchantments().get(
					new EnchantmentWrapper(4));
			if (helmetProtp != null)
				helmetProtp = (helmetProtp == 0) ? 0 : (int) Math
						.floor((6 + Math.pow(helmetProtp, 2)) * 1.5 / 3);
			else
				helmetProtp = 0;
		} catch (Exception e) {
			helmetProtp = 0;
		}
		try {
			chestProtp = chest.getEnchantments().get(new EnchantmentWrapper(4));
			if (chestProtp != null)
				chestProtp = (chestProtp == 0) ? 0 : (int) Math.floor((6 + Math
						.pow(chestProtp, 2)) * 1.5 / 3);
			else
				chestProtp = 0;
		} catch (Exception e) {
			chestProtp = 0;
		}
		try {
			pantsProtp = pants.getEnchantments().get(new EnchantmentWrapper(4));
			if (pantsProtp != null)
				pantsProtp = (pantsProtp == 0) ? 0 : (int) Math.floor((6 + Math
						.pow(pantsProtp, 2)) * 1.5 / 3);
			else
				pantsProtp = 0;
		} catch (Exception e) {
			pantsProtp = 0;
		}
		epfProj = bootsProtp + helmetProtp + chestProtp + pantsProtp;
		if (cause == DamageCause.CONTACT || cause == DamageCause.ENTITY_ATTACK
				|| cause == DamageCause.LAVA || cause == DamageCause.FIRE
				|| cause == DamageCause.PROJECTILE
				|| cause == DamageCause.ENTITY_EXPLOSION
				|| cause == DamageCause.BLOCK_EXPLOSION) {
			if (helmet != null) {
				if (helmet.getType() == Material.LEATHER_HELMET)
					red = red + 0.04;
				else if (helmet.getType() == Material.GOLD_HELMET)
					red = red + 0.08;
				else if (helmet.getType() == Material.CHAINMAIL_HELMET)
					red = red + 0.08;
				else if (helmet.getType() == Material.IRON_HELMET)
					red = red + 0.08;
				else if (helmet.getType() == Material.DIAMOND_HELMET)
					red = red + 0.12;
			}
			if (boots != null) {
				if (boots.getType() == Material.LEATHER_BOOTS)
					red = red + 0.04;
				else if (boots.getType() == Material.GOLD_BOOTS)
					red = red + 0.04;
				else if (boots.getType() == Material.CHAINMAIL_BOOTS)
					red = red + 0.04;
				else if (boots.getType() == Material.IRON_BOOTS)
					red = red + 0.08;
				else if (boots.getType() == Material.DIAMOND_BOOTS)
					red = red + 0.12;
			}
			if (pants != null) {
				if (pants.getType() == Material.LEATHER_LEGGINGS)
					red = red + 0.08;
				else if (pants.getType() == Material.GOLD_LEGGINGS)
					red = red + 0.12;
				else if (pants.getType() == Material.CHAINMAIL_LEGGINGS)
					red = red + 0.16;
				else if (pants.getType() == Material.IRON_LEGGINGS)
					red = red + 0.20;
				else if (pants.getType() == Material.DIAMOND_LEGGINGS)
					red = red + 0.24;
			}
			if (chest != null) {
				if (chest.getType() == Material.LEATHER_CHESTPLATE)
					red = red + 0.12;
				else if (chest.getType() == Material.GOLD_CHESTPLATE)
					red = red + 0.20;
				else if (chest.getType() == Material.CHAINMAIL_CHESTPLATE)
					red = red + 0.20;
				else if (chest.getType() == Material.IRON_CHESTPLATE)
					red = red + 0.24;
				else if (chest.getType() == Material.DIAMOND_CHESTPLATE)
					red = red + 0.32;
			}
		}
		double multiplier = 1.0 - red;
		if (cause == DamageCause.FALL) {
			multiplier *= 0.04 * Math.min(
					20,
					Math.floor(Math.min(25, epfProt + epfFall)
							* (new Random().nextDouble() * 0.5 + 0.5)));
		} else if (cause == DamageCause.FIRE || cause == DamageCause.FIRE_TICK
				|| cause == DamageCause.LAVA) {
			multiplier *= 0.04 * Math.min(
					20,
					Math.floor(Math.min(25, epfProt + epfFire)
							* (new Random().nextDouble() * 0.5 + 0.5)));
		} else if (cause == DamageCause.ENTITY_EXPLOSION
				|| cause == DamageCause.BLOCK_EXPLOSION) {
			multiplier *= 0.04 * Math.min(
					20,
					Math.floor(Math.min(25, epfProt + epfExpl)
							* (new Random().nextDouble() * 0.5 + 0.5)));
		} else if (cause == DamageCause.PROJECTILE) {
			multiplier *= 0.04 * Math.min(
					20,
					Math.floor(Math.min(25, epfProt + epfProj)
							* (new Random().nextDouble() * 0.5 + 0.5)));
		} else {
			multiplier *= 0.04 * Math.min(
					20,
					Math.floor(Math.min(25, epfProt)
							* (new Random().nextDouble() * 0.5 + 0.5)));
		}
		// if (LoreListener.damageReductionPlayers.contains(player)) {
		// player.sendMessage(ChatEffects.addColor("&9Damage reduced by "
		// + (red + multiplier) * 100 + "%"));
		// }
		return red + multiplier;
	}
	
	public static class DamageListener implements Listener {
		@EventHandler
		public void onEntityDamageEvent(EntityDamageEvent e) {
			if (e.getEventName().equals("EntityDamageByEntityEvent")
					&& e.getEntity() instanceof Player) {
				e.setCancelled(true);
			} else if (e.getEntityType().equals(EntityType.PLAYER)) {
				Player playerAffected = null;
				int id = e.getEntity().getEntityId();
				int i = 0;
				for (Player player : Bukkit.getOnlinePlayers()) {
					if (player.getEntityId() == id) {
						playerAffected = Bukkit.getOnlinePlayers()[i];
					}
					i++;
				}
				if (playerAffected != null
						&& !playerAffected.getGameMode().equals(
								GameMode.CREATIVE)) {
					RegionManager regionManager = LoreListener.getWorldGuard()
							.getRegionManager(playerAffected.getWorld());
					ApplicableRegionSet set = regionManager
							.getApplicableRegions(playerAffected.getLocation());
					if (set.allows(DefaultFlag.INVINCIBILITY)) {
						return;
					}
					LPlayer mp = LPlayer.getPlayer(playerAffected.getName());
					//if (mp.getHealth() < 20) {
					//	mp.refreshHealth();
					//}
					if (playerAffected.getHealth() > 0
							&& (playerAffected.getNoDamageTicks() < playerAffected
									.getMaximumNoDamageTicks() / 2.0F || ((e
									.getCause().equals(DamageCause.FIRE)
									|| e.getCause().equals(DamageCause.LAVA) || e
									.getCause().equals(DamageCause.CONTACT)) && playerAffected
									.getNoDamageTicks() < playerAffected
									.getMaximumNoDamageTicks() / 1.8F))) {
						playerAffected.damage(0);
						if (e.getCause().equals(DamageCause.LAVA)) {
							e.setDamage(2);
						} else if (e.getCause().equals(DamageCause.FALL)) {
							e.setDamage((int)(mp.getMaxHealth() * 1/20.0 * e.getDamage()));
						}
						mp.setHealth(
								absoluteHealth(
										mp,
										e.getDamage(),
										(playerAffected.getNoDamageTicks() > 0),
										e.getCause()), false);
						if (mp.getHealth() <= 0) {
							if (!e.getCause().equals(DamageCause.ENTITY_ATTACK))
								sendDeathMessage(playerAffected, e.getCause());
						}
						//if (mp.getHealth() > 20) {
						//	playerAffected.sendMessage(ChatEffects
						//			.addColor("&dYou: "
						//					+ constructHealthBar(mp, 60)
						//					+ " &7(" + mp.getHealth() + "/"
						//					+ mp.getMaxHealth() + ")"));
						//}
					}
					e.setDamage(0);
				}
			}
		}
	}
	
	public static class EntityDamageListener implements Listener {
		@EventHandler
		public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent e) {
			if (e.getEntityType().equals(EntityType.PLAYER)) {
				Player playerAffected = null;
				int id = e.getEntity().getEntityId();
				int i = 0;
				for (Player player : Bukkit.getOnlinePlayers()) {
					if (player.getEntityId() == id) {
						playerAffected = Bukkit.getOnlinePlayers()[i];
					}
					i++;
				}
				if (playerAffected != null
						&& !playerAffected.getGameMode().equals(
								GameMode.CREATIVE)) {
					RegionManager regionManager = LoreListener.getWorldGuard()
							.getRegionManager(playerAffected.getWorld());
					ApplicableRegionSet set = regionManager
							.getApplicableRegions(playerAffected.getLocation());
					LPlayer mp = LPlayer
							.getPlayer(playerAffected.getName());
					//if (mp.getHealth() < 20) {
					//	mp.refreshHealth();
					//}
					Player damager = null;
					int id2 = e.getDamager().getEntityId();
					i = 0;
					for (Player player : Bukkit.getOnlinePlayers()) {
						if (player.getEntityId() == id2) {
							damager = Bukkit.getOnlinePlayers()[i];
						}
						i++;
					}
					if (damager == null) {
						if (e.getDamager() instanceof Arrow) {
							Arrow a = (Arrow) e.getDamager();
							if (a.getShooter() instanceof Player) {
								damager = (Player) a.getShooter();
							}
						}
					}
					if (!set.allows(DefaultFlag.PVP) && damager != null
							|| set.allows(DefaultFlag.INVINCIBILITY)) {
						e.setDamage(0);
						e.setCancelled(true);
						return;
					}
					if (damager != null
							&& playerAffected.getNoDamageTicks() < playerAffected
									.getMaximumNoDamageTicks() / 2.0F) {
						LPlayer mp2 = LPlayer.getPlayer(damager
								.getName());
						playerAffected.damage(0);
						mp.setHealth(
								absoluteHealth(
										mp,
										e.getDamage(),										
										(playerAffected.getNoDamageTicks() > 0),
										e.getCause()), false);
						if (mp.getHealth() <= 0) {
							entityDeathMessage(playerAffected, e);
						}
						playerAffected.setNoDamageTicks(playerAffected
								.getMaximumNoDamageTicks());
						damager.sendMessage(ChatEffects.addColor("&dYou: "
								+ constructHealthBar(mp2, 40) + " &d"
								+ mp.getName() + "&d: "
								+ constructHealthBar(mp, 40)));
					} else if (playerAffected.getNoDamageTicks() < playerAffected
							.getMaximumNoDamageTicks() / 2.0F) {
						playerAffected.damage(0);
						mp.setHealth(
								absoluteHealth(
										mp,
										e.getDamage(),
										(playerAffected.getNoDamageTicks() > 0),
										e.getCause()), false);
						if (mp.getHealth() > 20) {
							playerAffected.sendMessage(ChatEffects
									.addColor("&dYou: "
											+ constructHealthBar(mp, 60)
											+ " &7(" + mp.getHealth() + "/"
											+ mp.getMaxHealth() + ")"));
						} else if (mp.getHealth() <= 0) {
							entityDeathMessage(playerAffected, e);
						}
						playerAffected.setNoDamageTicks(playerAffected
								.getMaximumNoDamageTicks());
					}
				}
			}
			Vector v = e.getDamager().getLocation().getDirection().normalize();
			//if (e.getDamager().getType().equals(EntityType.ARROW)) {
				v.multiply(0.25);
			//} else {
			//	v.multiply(0.25);
			//}
			e.getEntity().setVelocity(v);
		}
	}
	
	public static class RegainListener implements Listener {
		@EventHandler
		public void onRegenEvent(EntityRegainHealthEvent e) {
			if (e.getEntityType().equals(EntityType.PLAYER)) {
				Player playerAffected = null;
				int id = e.getEntity().getEntityId();
				int i = 0;
				for (Player player : Bukkit.getOnlinePlayers()) {
					if (player.getEntityId() == id) {
						playerAffected = Bukkit.getOnlinePlayers()[i];
					}
					i++;
				}
				if (playerAffected != null
						&& !playerAffected.getGameMode().equals(
								GameMode.CREATIVE)) {
					LPlayer mp = LPlayer
							.getPlayer(playerAffected.getName());
					mp.setHealth(
							Math.min(Math.max(mp.getHealth(), mp.getMaxHealth()),
									mp.getHealth() + e.getAmount()), false);
					e.setCancelled(true);
					
				}
			}
		}
	}
	
	public static void entityDeathMessage(Player p, EntityDamageByEntityEvent e) {
		EntityType damager = e.getDamager().getType();
		if (damager.equals(EntityType.PLAYER)) {
			Bukkit.broadcastMessage(p.getName() + " was slain by "
					+ ((Player) e.getDamager()).getName());
			return;
		}
		if (damager.equals(EntityType.ARROW)) {
			Bukkit.broadcastMessage(p.getName() + " was shot by arrow.");
			return;
		}
		Bukkit.broadcastMessage(p.getName() + " was slain by "
				+ damager.getName());
	}
	
	public static void sendDeathMessage(Player p, DamageCause c) {
		switch (c) {
		case BLOCK_EXPLOSION:
			Bukkit.broadcastMessage(p.getName() + " has been scattered all over Xenos");
			break;
		case CONTACT:
			Bukkit.broadcastMessage(p.getName() + " was pricked to death");
			break;
		case DROWNING:
			Bukkit.broadcastMessage(p.getName() + " tried to breathe underwater");
			break;
		case FALL:
			Bukkit.broadcastMessage(p.getName() + " jumped into Death's welcoming arms...");
			break;
		case FALLING_BLOCK:
			Bukkit.broadcastMessage(p.getName() + " was crushed");
			break;
		case FIRE:
			Bukkit.broadcastMessage(p.getName() + " went up in flames");
			break;
		case FIRE_TICK:
			Bukkit.broadcastMessage(p.getName() + " burned to death");
			break;
		case LAVA:
			Bukkit.broadcastMessage(p.getName() + " tried to swim in lava");
			break;
		case LIGHTNING:
			Bukkit.broadcastMessage(p.getName() + " was struck by lightning");
			break;
		case MAGIC:
			Bukkit.broadcastMessage(p.getName() + " was killed by magic");
			break;
		case POISON:
			Bukkit.broadcastMessage(p.getName() + " was killed by poison");
			break;
		case PROJECTILE:
			Bukkit.broadcastMessage(p.getName() + " was shot to death");
			break;
		case STARVATION:
			Bukkit.broadcastMessage(p.getName() + " starved to death");
			break;
		case SUFFOCATION:
			Bukkit.broadcastMessage(p.getName() + " suffocated in a wall");
			break;
		case SUICIDE:
			Bukkit.broadcastMessage(p.getName() + " committed suicide");
			break;
		case VOID:
			Bukkit.broadcastMessage(p.getName() + " fell into the void");
			break;
		case WITHER:
			Bukkit.broadcastMessage(p.getName() + " withered away");
			break;
		default:
			Bukkit.broadcastMessage(p.getName() + " died");
		}
		
	}
	
	public static class RespawnListener implements Listener {
		@EventHandler
		public void onRespawnEvent(PlayerRespawnEvent e) {
			LPlayer mp = LPlayer.getPlayer(e.getPlayer()
					.getName());
			mp.setHealth(mp.getMaxHealth(), false);
			Bukkit.getScheduler().scheduleSyncDelayedTask(
					(Plugin) (LoreListener.plugin), new Runnable() {
						public void run() {
							for (Player p : Bukkit.getOnlinePlayers()) {
								LPlayer mp = LPlayer
										.getPlayer(p.getName());
								if (mp != null && !p.getGameMode().equals(GameMode.CREATIVE))
									mp.setHealth(mp.getHealth(), false);
							}
						}
					}, 1);
		}
	}
}
