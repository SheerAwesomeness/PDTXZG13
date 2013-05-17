package me.sheer.LoreListener;

import org.bukkit.ChatColor;

public abstract class ChatEffects {
	public static String addColor(String s) {
		s = s.replaceAll("&a", ChatColor.GREEN+"");
		s = s.replaceAll("&b", ChatColor.AQUA+"");
		s = s.replaceAll("&c", ChatColor.RED+"");
		s = s.replaceAll("&d", ChatColor.LIGHT_PURPLE+"");
		s = s.replaceAll("&e", ChatColor.YELLOW+"");
		s = s.replaceAll("&f", ChatColor.WHITE+"");
		s = s.replaceAll("&0", ChatColor.BLACK+"");
		s = s.replaceAll("&1", ChatColor.DARK_BLUE+"");
		s = s.replaceAll("&2", ChatColor.DARK_GREEN+"");
		s = s.replaceAll("&3", ChatColor.DARK_AQUA+"");
		s = s.replaceAll("&4", ChatColor.DARK_RED+"");
		s = s.replaceAll("&5", ChatColor.DARK_PURPLE+"");
		s = s.replaceAll("&6", ChatColor.GOLD+"");
		s = s.replaceAll("&7", ChatColor.GRAY+"");
		s = s.replaceAll("&8", ChatColor.DARK_GRAY+"");
		s = s.replaceAll("&9", ChatColor.BLUE+"");
		s = s.replaceAll("&k", ChatColor.MAGIC+"");
		s = s.replaceAll("&l", ChatColor.BOLD+"");
		s = s.replaceAll("&n", ChatColor.UNDERLINE+"");
		s = s.replaceAll("&o", ChatColor.ITALIC+"");
		s = s.replaceAll("&m", ChatColor.STRIKETHROUGH+"");
		s = s.replaceAll("&r", ChatColor.RESET+"");
		return s;
	}
}
