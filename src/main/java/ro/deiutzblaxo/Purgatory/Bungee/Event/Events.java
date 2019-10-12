package ro.deiutzblaxo.Purgatory.Bungee.Event;

import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import ro.deiutzblaxo.Purgatory.Bungee.MainBungee;

public class Events implements Listener {
	private MainBungee plugin;
	public Events(MainBungee main) {
		plugin = main;

	}

	@EventHandler
	public void onJoin(PostLoginEvent e) {
		if(plugin.getBanFactory().isBan(e.getPlayer().getUniqueId())) {
			if(e.getPlayer().getServer().getInfo() != plugin.getServerManager().getPurgatoryServer()) {
				e.getPlayer().connect(plugin.getServerManager().getPurgatoryServer());
			}
		}
	}
	@EventHandler
	public void onConnect(ServerConnectEvent e) {
		if(plugin.getBanFactory().isBan(e.getPlayer().getUniqueId())) {
			if(e.getTarget() != plugin.getServerManager().getPurgatoryServer()) {
				e.setCancelled(true);
			}
		}
	}

}
