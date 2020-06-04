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
package ro.deiutzblaxo.Purgatory.Spigot.Commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import ro.deiutzblaxo.Purgatory.Spigot.MainSpigot;
import ro.deiutzblaxo.Purgatory.Spigot.Titles.TitleManager;

public class PurgeCommand extends Command {
	private MainSpigot plugin;
	private OfflinePlayer player ;
	private TitleManager TitleManager;
	public PurgeCommand(String name, MainSpigot mainSpigot) {
		super(name);
		plugin = mainSpigot;
		TitleManager = new TitleManager(mainSpigot);
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean execute(CommandSender sender, String arg1, String[] args) {
		if(!sender.hasPermission("purgatory.purge")) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getString
					(plugin.getConfigManager().getMessages(),"NoPermission")));
			return false ;
		}
		if(args.length < 1) {
			ArrayList<BaseComponent[]> texts = new ArrayList<BaseComponent[]>();
			BaseComponent[] test = null;
			test = new ComponentBuilder(ChatColor.translateAlternateColorCodes('&',
					plugin.getConfigManager().getMessages().getString("InvalidCommand.Usage")+" :"))
					.event(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/" + plugin.getConfig().getString("Command.Purge") + " "
							+plugin.getConfigManager().getMessages().getString("InvalidCommand.Player.player"))).create();
			texts.add(test);
			test = new ComponentBuilder("/" + plugin.getConfig().getString("Command.Purge")).event(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
					new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessages().getString("InvalidCommand.Command"))).create())).create();
			texts.add(test);
			test = new ComponentBuilder(plugin.getConfigManager().getMessages().getString("InvalidCommand.Player.player")).event(new HoverEvent(HoverEvent.Action.SHOW_TEXT ,
					new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessages().getString("InvalidCommand.Player.hover"))).create())).create();
			texts.add(test);
			ComponentBuilder proprozitie = new ComponentBuilder("");
			for(int fraze = 0 ; fraze < texts.size() ; fraze++) {
				proprozitie.append(texts.get(fraze));
				proprozitie.append(" ");

			}
			sender.spigot().sendMessage(proprozitie.create());

			return false;
		}
		if(plugin.getServer().getPlayer(args[0]) != null || plugin.getServer().getOfflinePlayer(args[0]) != null) {
			if(plugin.getServer().getPlayer(args[0]) != null) {
				player = plugin.getServer().getPlayer(args[0]);
			}
			else if(plugin.getServer().getOfflinePlayer(args[0]) != null) {
				player = plugin.getServer().getOfflinePlayer(args[0]);
			}
		}else {
			sender.sendMessage("this player don`t exists!");
			return false;
		}

		if(!plugin.getBanFactory().isBan(player.getUniqueId())) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getString
					(plugin.getConfigManager().getMessages(),"Purge.notBanned")
					.replaceAll("%player%", player.getName())));
			return false;
		}
		plugin.getBanFactory().removeBan(player.getUniqueId());
		plugin.getTaskFactory().removeTasks(player.getUniqueId());
		Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getString
				(plugin.getConfigManager().getMessages(),"Purge.broadcast")
				.replaceAll("%player%", player.getName()).replaceAll("%admin%", sender.getName())));
		if(player.isOnline()) {
			if(plugin.getConfig().getBoolean("Force-Kick")) {
				player.getPlayer().kickPlayer(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getString
						(plugin.getConfigManager().getMessages(),"Purge.Format")
						.replaceAll("%admin%", sender.getName())));
			}else {
				TitleManager.Title(player.getPlayer(), ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getString
						(plugin.getConfigManager().getMessages(),"Purge.Format")
						.replaceAll("%admin%", sender.getName())));

			}
			player.getPlayer().teleport(plugin.getWorldManager().getDefault().getSpawnLocation());
		}


		return false;
	}

}
