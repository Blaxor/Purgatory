package ro.deiutzblaxo.Purgatory.Bungee.Event;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.event.TabCompleteEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import ro.deiutzblaxo.Purgatory.Bungee.MainBungee;

public class Events implements Listener {
	private MainBungee plugin;
	public Events(MainBungee main) {
		plugin = main;

	}

	@EventHandler
	public void onJoin(ServerConnectEvent e) {
		if(plugin.getBanFactory().isBan(e.getPlayer().getUniqueId())) {
			e.setTarget(plugin.getServerManager().getPurgatoryServer());
		}
	}
	//	@EventHandler
	//	public void onConnect(ServerConnectEvent e) {
	//		if(plugin.getBanFactory().isBan(e.getPlayer().getUniqueId())) {
	//			if(e.getTarget() != plugin.getServerManager().getPurgatoryServer()) {
	//				e.setCancelled(true);
	//			}
	//		}
	//	}
	@EventHandler
	public void TabCompleter(TabCompleteEvent e ) {
		String[] args= e.getCursor().toLowerCase().split(" ");
		if(args.length >= 1) {

			if(args[0].startsWith("/")) { // command
				if(args[0].equalsIgnoreCase("/" + plugin.getConfigManager().getConfig().getString("Command.Ban").toLowerCase())) {
					if(args.length== 1) {
						for(ProxiedPlayer player : plugin.getProxy().getPlayers()) {
							e.getSuggestions().add(player.getName());

						}
					}
				}else if(args[0].equalsIgnoreCase("/" + plugin.getConfigManager().getConfig().getString("Command.Warning").toLowerCase())) {

					if(args.length== 1) {
						for(ProxiedPlayer player : plugin.getProxy().getPlayers()) {
							e.getSuggestions().add(player.getName());
						}
					}
				}else if(args[0].equalsIgnoreCase("/" + plugin.getConfigManager().getConfig().getString("Command.TempBan").toLowerCase())) {

					if(args.length== 1) {
						for(ProxiedPlayer player : plugin.getProxy().getPlayers()) {
							e.getSuggestions().add(player.getName());
						}

					}
					if(args.length == 2) {
						e.getSuggestions().add("60");
						e.getSuggestions().add("300");
						e.getSuggestions().add("600");
						e.getSuggestions().add("900");
						e.getSuggestions().add("1800");
						e.getSuggestions().add("3600");
						e.getSuggestions().add("21600");
						e.getSuggestions().add("43200");
						e.getSuggestions().add("86400");
						e.getSuggestions().add("259200");
						e.getSuggestions().add("604800");

					}
				}else if(args[0].equalsIgnoreCase("/" + plugin.getConfigManager().getConfig().getString("Command.Info").toLowerCase())) {
					if(args.length == 1) {
						for(ProxiedPlayer player : plugin.getProxy().getPlayers()) {
							e.getSuggestions().add(player.getName());
						}
					}

				}else if(args[0].equalsIgnoreCase("/" + plugin.getConfigManager().getConfig().getString("Command.UnBan").toLowerCase())) {
					if(args.length == 1) {
						for(ProxiedPlayer player : plugin.getProxy().getPlayers()) {
							e.getSuggestions().add(player.getName());
						}
					}
				}
			}
		}

	}
}
