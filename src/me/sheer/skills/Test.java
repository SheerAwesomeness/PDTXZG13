package me.sheer.skills;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.server.v1_5_R3.Packet53BlockChange;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_5_R3.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import me.sheer.LoreListener.LPlayer;
import me.sheer.LoreListener.LoreListener;
import me.sheer.skills.Skill;

public class Test {
	static List<Integer> ids = new ArrayList<Integer>();
	
	public Test(CommandSender sender, String[] args) {
		boolean hasPermission = (sender instanceof Player)
				&& sender.hasPermission("sheerrpg.test");
		if (hasPermission) {
			Player p = (Player) sender;
			final Location l = p.getTargetBlock(null, 100).getLocation().add(0, 1, 0);
			if (args[0].equalsIgnoreCase("clear")) {
				for (int id : ids) {
					Bukkit.getScheduler().cancelTask(id);
				}
				ids = new ArrayList<Integer>();
				return;
			} else if (args[0].equalsIgnoreCase("blockpacket")) {
				int amt = Math.min(30, Integer.parseInt(args[2]));
				for (int x = l.getBlockX()-amt; x < l.getBlockX()+amt; x++) {
					for (int y = l.getBlockY()-amt; y < l.getBlockY()+amt; y++) {
						for (int z = l.getBlockZ()-amt; z < l.getBlockZ()+amt; z++) {
							if (p.getWorld().getBlockAt(new Location(p.getWorld(), x, y, z)).getTypeId() != 0) {
								Packet53BlockChange pk = new Packet53BlockChange();
								pk.a = x;
								pk.b = y;
								pk.c = z;
								pk.material = Material.GLASS.getId();
								pk.data = 0;
								((CraftPlayer)Bukkit.getPlayer(args[1])).getHandle().playerConnection.sendPacket(pk);
							}
						}
					}
				}
				return;
			} else if (args[0].equalsIgnoreCase("ride")) {
				try {
					Entity e = p.getWorld().spawnEntity(l, EntityType.fromName(args[1]));
					e.setPassenger(p);
					LoreListener.vehicles.put(p, e);
				} catch (Exception e) {
					String s = "";
					for (EntityType en : EntityType.values()) {
						s += en.getName() + ", ";
					}
					s = s.substring(0, s.length() - 2);
					p.sendMessage(s);
				}
				return;
			} else if (args[0].equalsIgnoreCase("create")) {
				new Skill(args[1]);
				return;
			} else if (args[0].equalsIgnoreCase("add")) {
				Skill.getSkill(args[1]).addExp(Integer.parseInt(args[2]));
				return;
			} else if (args[0].equalsIgnoreCase("level")) {
				p.sendMessage("Level: " + Skill.getSkill(args[1]).getLevel() + ", Experience: " + Skill.getSkill(args[1]).getExp() + " (" + (Skill.getExpOfLevel(Skill.getSkill(args[1]).getLevel() + 1) - Skill.getSkill(args[1]).getExp()) + " exp to next level, or " + ((int)((1 - (1.0*Skill.getExpOfLevel(Skill.getSkill(args[1]).getLevel() + 1) - Skill.getSkill(args[1]).getExp())/(Skill.getExpOfLevel(Skill.getSkill(args[1]).getLevel() + 1) - Skill.getExpOfLevel(Skill.getSkill(args[1]).getLevel())))*10000)/100.0) + "% there!)");
				return;
			}
			//CraftPlayer c = (CraftPlayer)p;
			//c.setCustomName(ChatColor.GREEN + "Wut");
			//p.sendBlockChange(p.getLocation(), Material.LAVA, (byte)0);
			final int a = Math.min(Math.max(0, Integer.parseInt(args[0])), 158);
			final float b = Float.parseFloat(args[1]);
			final float c;
			if (args.length > 3)
				c = Float.parseFloat(args[3]);
			else
				c = 1.0F;
			ids.add(Bukkit.getServer().getScheduler()
					.scheduleSyncRepeatingTask(LoreListener.plugin, new Runnable() {
						public void run() {
							//Random r = new Random();
							for (int i = 0; i < 1; i++) {
								for (Player p : Bukkit.getOnlinePlayers())
									p.playSound(l, Sound.values()[a], c, b);
							}
						}
					}, 1, Integer.parseInt(args[2])));
		}
	}
}
