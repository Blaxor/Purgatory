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
package ro.deiutzblaxo.Purgatory.Bungee.Commands;

import java.util.ArrayList;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import ro.deiutzblaxo.Purgatory.Bungee.MainBungee;

public class WarningCommand extends Command {
	private MainBungee plugin;
	private String reason;
	public WarningCommand(String name, MainBungee mainBungee) {
		super(name);
		plugin = mainBungee;
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		plugin.getConfigManager().loadWarnings();
		plugin.getConfigManager().loadBans();


		if(!sender.hasPermission("purgatory.warning")) {
			sender.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getString
					(plugin.getConfigManager().getMessages(),"NoPermission"))));
			return;
		}
		if(args.length < 1) {
			// /warning <player> <reason>

			ArrayList<BaseComponent[]> raspuns = new ArrayList<BaseComponent[]>();
			BaseComponent[] word_usage = new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessages().getString("InvalidCommand.Usage")))
					.event(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND , "/" + plugin.getConfigManager().getConfig().getString("Command.Warning").toLowerCase() + " <player> <reason>")).create();
			raspuns.add(word_usage);
			BaseComponent[] command = new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', "/" + plugin.getConfigManager().getConfig().getString("Command.Warning").toLowerCase()))
					.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessages().getString("InvalidCommand.Command"))).create())).create();
			raspuns.add(command);
			BaseComponent[] player = new ComponentBuilder(ChatColor.translateAlternateColorCodes('&',"<player>"))
					.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT , new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessages().getString("InvalidCommand.Player"))).create())).create();
			raspuns.add(player);
			BaseComponent[] reason = new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', "<reason>"))
					.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT , new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessages().getString("InvalidCommand.Reason"))).create())).create();
			raspuns.add(reason);

			ComponentBuilder raspunsFinalizat = new ComponentBuilder("");
			for(int parti = 0 ; parti < raspuns.size() ; parti++) {
				raspunsFinalizat.append(raspuns.get(parti));
				raspunsFinalizat.append(" ");

			}
			sender.sendMessage(raspunsFinalizat.create());

			return;
		}
		if(plugin.getProxy().getPlayer(args[0]) == null) {
			sender.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&',
					plugin.getConfigManager().getString(plugin.getConfigManager().getMessages(), "PlayerOffline"))));
			return;
		}
		ProxiedPlayer player = plugin.getProxy().getPlayer(args[0]);

		if(plugin.getBanFactory().isBan(player.getUniqueId())) {
			sender.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getString
					(plugin.getConfigManager().getMessages(),"Warning.isBanned").replaceAll("%player%", player.getName()))));
			return ;
		}
		if(args.length == 1) {
			reason = plugin.getConfigManager().getString
					(plugin.getConfigManager().getMessages(),"Warning.DefaultReason");
		}else {
			args[0] = "";
			StringBuilder stringBuilder = new StringBuilder();
			for (String arg : args) {
				stringBuilder.append(arg).append(" ");
			}
			reason = stringBuilder.toString();
		}

		plugin.getWarningFactory().setWarning(player, sender ,reason);
		int WarningMax, Warning;
		WarningMax = plugin.getWarningFactory().getMaxWarning();
		if(plugin.getWarningFactory().getWarningNumber(player) == 0) {
			Warning = WarningMax;
		}else {
			Warning = plugin.getWarningFactory().getWarningNumber(player);
		}

		player.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getString
				(plugin.getConfigManager().getMessages(),"Warning.Receive"))
				.replaceAll("%warning%", Warning + "")
				.replaceAll("%warning_max%", WarningMax+ "")
				.replaceAll("%player%", player.getName())
				.replaceAll("%admin%", sender.getName())
				.replaceAll("%reason%", reason)));

		sender.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getString
				(plugin.getConfigManager().getMessages(),"Warning.Send"))
				.replaceAll("%warning%", Warning + "")
				.replaceAll("%warning_max%", WarningMax+ "")
				.replaceAll("%player%", player.getName())
				.replaceAll("%admin%", sender.getName())
				.replaceAll("%reason%", reason)));
		plugin.getProxy().broadcast(new TextComponent(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getString
				(plugin.getConfigManager().getMessages(),"Warning.broadcast"))
				.replaceAll("%warning%", Warning + "")
				.replaceAll("%warning_max%", WarningMax+ "")
				.replaceAll("%player%", player.getName())
				.replaceAll("%admin%", sender.getName())
				.replaceAll("%reason%", reason)));

	}

}
