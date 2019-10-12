package ro.deiutzblaxo.Purgatory.Bungee.Commands;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
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
			sender.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&',
					plugin.getConfigManager().getString(plugin.getConfigManager().getMessages(), "TempBan.InvalidCommand"))));
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
