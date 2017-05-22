package me.fudged.freeze;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Freeze extends JavaPlugin implements Listener{
	
	@Override
	public void onEnable(){
		getLogger().info("Freeze has been enabled!");
		getCommand("freeze").setExecutor(new Commands(this));
	}
	
	@Override
	public void onDisable(){
		for(UUID u : frozen){
			unFreeze(Bukkit.getServer().getPlayer(u));
		}
		frozen.clear();
	}
	
	public static Plugin getPlugin(){
		return Bukkit.getServer().getPluginManager().getPlugin("Freeze");
	}
	
	private List<UUID> frozen = new ArrayList<>();
	
	public List<UUID> getFrozenPlayers(){
		return frozen;
	}
	
	public void freeze(Player p){
		frozen.add(p.getUniqueId());
		p.setWalkSpeed(0.0F);
		p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 100000, 128));
	}
	
	public void unFreeze(Player p){
		frozen.remove(p.getUniqueId());
		p.setWalkSpeed(0.2F); //normal walking speed
		p.removePotionEffect(PotionEffectType.JUMP);
	}
	
}
