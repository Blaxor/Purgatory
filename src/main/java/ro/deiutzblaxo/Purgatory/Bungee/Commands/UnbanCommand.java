package ro.deiutzblaxo.Purgatory.Bungee.Commands;

import java.util.ArrayList;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;
import ro.deiutzblaxo.Purgatory.Bungee.MainBungee;

public class UnbanCommand extends Command {

	private MainBungee plugin;
	public UnbanCommand(String name , MainBungee main) {
		super(name);
		plugin = main;
	}

	@Override
	public void execute(CommandSender sender, String[] args) {

		if(!sender.hasPermission("purgatory.purge")) {
			sender.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&',
					plugin.getConfigManager().getString(plugin.getConfigManager().getMessages(), "NoPermission"))));
			return;
		}
		if(args.length != 1) {

			ArrayList<BaseComponent[]> raspuns = new ArrayList<BaseComponent[]>();
			BaseComponent[] word_usage = new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessages().getString("InvalidCommand.Usage")))
					.event(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND , "/" + plugin.getConfigManager().getConfig().getString("Command.UnBan").toLowerCase() + " <player> <seconds> <reason>")).create();
			raspuns.add(word_usage);
			BaseComponent[] command = new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', "/" + plugin.getConfigManager().getConfig().getString("Command.UnBan").toLowerCase()))
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
		if(plugin.getBanFactory().isBan(args[0])) {
			plugin.getBanFactory().removeBan(args[0]);
			plugin.getProxy().broadcast(new TextComponent(ChatColor.translateAlternateColorCodes('&',
					plugin.getConfigManager().getString(plugin.getConfigManager().getMessages(), "Purge.Broadcast")
					.replaceAll("%player%", args[0])).replaceAll("%admin%", sender.getName())));
			if(plugin.getConfigManager().getConfig().getBoolean("UnBan-Disconnect")) {
				if(plugin.getProxy().getPlayer(args[0]) != null) {
					plugin.getProxy().getPlayer(args[0]).setReconnectServer(plugin.getServerManager().getHubServer());
					plugin.getProxy().getPlayer(args[0]).disconnect(new TextComponent(ChatColor.translateAlternateColorCodes('&'
							, plugin.getConfigManager().getString(plugin.getConfigManager().getMessages(), "UnBanFormat"))
							.replaceAll("%admin%", sender.getName())));
				}
			}else {
				plugin.getProxy().getPlayer(args[0]).connect(plugin.getServerManager().getHubServer());
				plugin.getProxy().getPlayer(args[0]).sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&'
						, plugin.getConfigManager().getString(plugin.getConfigManager().getMessages(), "UnBanFormat"))
						.replaceAll("%admin%", sender.getName())));
			}
		}else {
			sender.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&',
					plugin.getConfigManager().getString(plugin.getConfigManager().getMessages(), "Purge.notBanned").replaceAll("%player%", args[0]))));

		}

	}

}
