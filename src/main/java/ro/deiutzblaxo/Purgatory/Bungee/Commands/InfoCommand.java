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

public class InfoCommand extends Command {
	private MainBungee plugin;

	private String reason , isBanS;
	private Integer warnings ,seconds;
	public InfoCommand(String name , MainBungee main) {
		super(name);
		plugin = main;


	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if(!sender.hasPermission("purgatory.info")) {
			sender.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&',
					plugin.getConfigManager().getString(plugin.getConfigManager().getMessages(), "NoPermission"))));
			return;
		}
		if(args.length != 1) {
			ArrayList<BaseComponent[]> raspuns = new ArrayList<BaseComponent[]>();

			BaseComponent[] word_usage = new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessages().getString("InvalidCommand.Usage")))
					.event(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND , "/" + plugin.getConfigManager().getConfig().getString("Command.Info").toLowerCase() + " <player>")).create();
			raspuns.add(word_usage);
			BaseComponent[] command = new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', "/" + plugin.getConfigManager().getConfig().getString("Command.Info").toLowerCase()))
					.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessages().getString("InvalidCommand.Command"))).create())).create();
			raspuns.add(command);
			BaseComponent[] player = new ComponentBuilder(ChatColor.translateAlternateColorCodes('&',"<player>"))
					.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT , new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessages().getString("InvalidCommand.Player"))).create())).create();
			raspuns.add(player);

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
			reason = plugin.getBanFactory().getReason(player);
			warnings = plugin.getWarningFactory().getWarningNumber(player);
		}else if(!plugin.getBanFactory().isBan(player.getUniqueId())|| plugin.getWarningFactory().isWarning(player)) {

			warnings = plugin.getWarningFactory().getWarningNumber(player);
			reason = plugin.getWarningFactory().getReason(player);
		}

		if(reason == null) reason = " ";

		isBanS = plugin.getBanFactory().isBan(player.getUniqueId()) ? plugin.getConfigManager().getMessages().getString("Yes") : plugin.getConfigManager().getMessages().getString("No");
		seconds = plugin.getBanFactory().isTempBan(player.getUniqueId()) ? plugin.getBanFactory().getTime(player.getUniqueId()) : 0;


		sender.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&',
				plugin.getConfigManager().getString(plugin.getConfigManager().getMessages(), "Info.Format").replaceAll("%reason%", reason)
				.replaceAll("%warnings%", warnings+ "").replaceAll("%isban%", isBanS).replaceAll("%player%", player.getName()).replaceAll("%time%", seconds.toString()))));


	} // String gen = p.sex =='m' ? masculin : feminin;

}


