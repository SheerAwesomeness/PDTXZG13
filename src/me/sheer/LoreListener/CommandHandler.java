package me.sheer.LoreListener;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import me.sheer.skills.Test;

public abstract class CommandHandler {
	public static boolean execute(CommandSender sender, Command command, String l, String[] args) {
		if (l.equalsIgnoreCase("test"))
			new Test(sender, args);	
		return true;
	}
}