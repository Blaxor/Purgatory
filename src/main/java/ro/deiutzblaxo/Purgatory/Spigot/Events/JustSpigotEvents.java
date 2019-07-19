package ro.deiutzblaxo.Purgatory.Spigot.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import ro.deiutzblaxo.Purgatory.Spigot.MainSpigot;

public class JustSpigotEvents implements Listener{
	MainSpigot plugin;
	public JustSpigotEvents(MainSpigot main){
		plugin = main;
	}
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();

		if(plugin.getBanFactory().isBan(player)) {
			if(!player.getLocation().getWorld().getName().equalsIgnoreCase(plugin.getWorldManager().getPurgatory().getName())) {
				player.teleport(plugin.getWorldManager().getPurgatory().getSpawnLocation());
			}else {
				if(plugin.getConfig().getBoolean("Force-Spawn-Purgatory-World")) {
					player.teleport(plugin.getWorldManager().getPurgatory().getSpawnLocation());
				}
			}
		}
	}


}
