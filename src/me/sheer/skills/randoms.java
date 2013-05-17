package me.sheer.skills;

import me.sheer.LoreListener.LPlayer;
import me.sheer.LoreListener.PrecisionListener;
import me.sheer.LoreListener.StrengthListener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class randoms extends JavaPlugin implements Listener{
	public void onEnable() {
		Bukkit.getPluginManager().registerEvents(this, this);
	
	}

	
	@EventHandler
	public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent damage) {
		StrengthListener stre = new StrengthListener();
		stre.onEntityDamageByEntityEvent(damage);
		PrecisionListener prec = new PrecisionListener();
		prec.onEntityDamageByEntityEvent(damage);
		if (damage.getEntity() instanceof Player){
			Entity entity = damage.getEntity();
			Player player = ((Player) damage.getEntity());
			int blahh = 0;
			blahh = LPlayer.getPlayer(player.getName()).getHealth();
			player.sendMessage(blahh+"hm");
			
		}
		
	}}
