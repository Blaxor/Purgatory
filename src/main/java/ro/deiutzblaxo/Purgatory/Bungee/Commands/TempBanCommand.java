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

public class TempBanCommand extends Command {
	private MainBungee plugin;
	public TempBanCommand(String name, MainBungee main) {
		super(name);
		plugin = main;
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if(!sender.hasPermission("purgatory.tempban")) {
			sender.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&',
					plugin.getConfigManager().getString(plugin.getConfigManager().getMessages(), "NoPermission"))));
			return;
		}
		if(args.length <=1) {

			ArrayList<BaseComponent[]> raspuns = new ArrayList<BaseComponent[]>();
			BaseComponent[] word_usage = new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessages().getString("InvalidCommand.Usage")))
					.event(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND , "/" + plugin.getConfigManager().getConfig().getString("Command.TempBan").toLowerCase() + " <player> <seconds> <reason>")).create();
			raspuns.add(word_usage);
			BaseComponent[] command = new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', "/" + plugin.getConfigManager().getConfig().getString("Command.TempBan").toLowerCase()))
					.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessages().getString("InvalidCommand.Command"))).create())).create();
			raspuns.add(command);
			BaseComponent[] player = new ComponentBuilder(ChatColor.translateAlternateColorCodes('&',"<player>"))
					.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT , new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessages().getString("InvalidCommand.Player"))).create())).create();
			raspuns.add(player);
			BaseComponent[] timp = new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', "<time>"))
					.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT , new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessages().getString("InvalidCommand.Time"))).create())).create();
			raspuns.add(timp);
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
			sender.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&',
					plugin.getConfigManager().getString(plugin.getConfigManager().getMessages(), "isBan").replaceAll("%player%", player.getName()))));
			return;
		}
		Integer time = 0;
		try {
			time = Integer.valueOf(args[1]);
		}catch(Exception e) {
			sender.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&',
					plugin.getConfigManager().getString(plugin.getConfigManager().getMessages(), "TempBan.NotNumber").replaceAll("%time%", args[1]))));
			return;
		}
		String reason;
		if(args.length >= 3) {
			args[0] = "";
			args[1] = "";
			StringBuilder stringBuilder = new StringBuilder();
			for (String arg : args) {
				stringBuilder.append(arg).append(" ");
			}
			reason = stringBuilder.toString();
		}else {
			reason = plugin.getConfigManager().getMessages().getString("TempBan.DefaultReason");
		}
		plugin.getProxy().broadcast(new TextComponent(plugin.getConfigManager().getString(plugin.getConfigManager().getMessages(), "TempBan.TempBanned")
				.replaceAll("%player%", player.getName()).replaceAll("%time%", time.toString()).replaceAll("%reason%", reason)));


		plugin.getBanFactory().setTempBan(
				player.getUniqueId(),
				reason,
				time,
				player.getName())
		;

	}

}
