//    Purgatory , a ban system for servers of Minecraft
//    Copyright (C) 2020  Deiutz
//
//    This program is free software: you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation, either version 3 of the License, or
//    (at your option) any later version.
//
//    This program is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License
//    along with this program.  If not, see <https://www.gnu.org/licenses/>.
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
