package ro.deiutzblaxo.Purgatory.Bungee.Commands;

import java.util.ArrayList;
import java.util.UUID;

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

public class BanCommand extends Command {
	private MainBungee plugin;
	private String name , reason;
	private UUID uuid;
	public BanCommand(String name , MainBungee main) {
		super(name);
		plugin = main;

	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if(!sender.hasPermission("purgatory.ban")) {
			sender.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&',
					plugin.getConfigManager().getString(plugin.getConfigManager().getMessages(), "NoPermission"))));
			return;
		}
		if(args.length < 1) {

			ArrayList<BaseComponent[]> raspuns = new ArrayList<BaseComponent[]>();

			BaseComponent[] word_usage = new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessages().getString("InvalidCommand.Usage")))
					.event(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND , "/" + plugin.getConfigManager().getConfig().getString("Command.Ban").toLowerCase() + " <player> <reason>")).create();
			raspuns.add(word_usage);
			BaseComponent[] command = new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', "/" + plugin.getConfigManager().getConfig().getString("Command.Ban").toLowerCase()))
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
				raspunsFinalizat.reset();
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
		uuid = player.getUniqueId();
		name = player.getName();
		if(plugin.getBanFactory().isBan(uuid)) {
			sender.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&',
					plugin.getConfigManager().getString(plugin.getConfigManager().getMessages(), "isBan").replaceAll("%player%", name))));
			return;
		}else {

			if(args.length >= 2) {
				args[0] = "";
				StringBuilder stringBuilder = new StringBuilder();
				for (String arg : args) {
					stringBuilder.append(arg).append(" ");
				}
				reason = stringBuilder.toString();
			}else {
				reason = plugin.getConfigManager().getString(plugin.getConfigManager().getMessages(), "Ban.DefaultReason");
			}
			plugin.getBanFactory().setBan(uuid, reason, name);
			if(plugin.getConfigManager().getConfig().getBoolean("Ban-Disconnect")) {

				plugin.getProxy().getPlayer(name).disconnect(new TextComponent(ChatColor.translateAlternateColorCodes('&',
						plugin.getConfigManager().getString(plugin.getConfigManager().getMessages(), "Ban.Format").replaceAll("%reason%", reason))));
			}else {
				plugin.getProxy().getPlayer(name).connect(plugin.getServerManager().getPurgatoryServer());
				plugin.getProxy().getPlayer(name).sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&',
						plugin.getConfigManager().getString(plugin.getConfigManager().getMessages(), "Ban.Format").replaceAll("%reason%", reason))));
			}

			plugin.getProxy().broadcast(new TextComponent(ChatColor.translateAlternateColorCodes('&',
					plugin.getConfigManager().getString(plugin.getConfigManager().getMessages(), "Ban.Broadcast")
					.replaceAll("%player%", name).replaceAll("%admin%", sender.getName()).replaceAll("%reason%", reason))));
		}

	}

}
