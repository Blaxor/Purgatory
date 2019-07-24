package ro.deiutzblaxo.Purgatory.Spigot.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import ro.deiutzblaxo.Purgatory.Spigot.MainSpigot;
import ro.deiutzblaxo.Purgatory.Spigot.Titles.TitleManager;

public class WarningCommand extends Command {
	private MainSpigot plugin ;
	private OfflinePlayer player;
	private String reason;
	private TitleManager titlemanager;
	public WarningCommand(String name, MainSpigot mainSpigot) {
		super(name);
		plugin = mainSpigot;
		titlemanager = new TitleManager(mainSpigot);
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean execute(CommandSender sender, String arg1, String[] args) {
		plugin.getConfigManager().loadWarningDataBase();
		plugin.getConfigManager().loadBanDataBase();
		plugin.getConfigManager().loadMessages();

		if(!sender.hasPermission("purgatory.warning")) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessages().getString("NoPermission")));
			return false;
		}
		if(args.length < 1) {

			sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
					plugin.getConfigManager().getMessages().getString("Warning.InvalidCommand")));
			return false;
		}

		if(plugin.getServer().getPlayer(args[0]) != null || plugin.getServer().getOfflinePlayer(args[0]) != null) {
			if(plugin.getServer().getPlayer(args[0]) != null) {
				player = plugin.getServer().getPlayer(args[0]);
			}
			else if(plugin.getServer().getOfflinePlayer(args[0]) != null) {
				player = plugin.getServer().getOfflinePlayer(args[0]);

			}

		}
		if(plugin.getBanFactory().isBan(player.getUniqueId())) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessages()
					.getString("Warning.isBanned").replaceAll("%player%", player.getName())));
			return false;
		}
		if(args.length == 1) {
			reason = plugin.getConfigManager().getMessages().getString("Warning.DefaultReason");
		}else {
			args[0] = "";
			StringBuilder stringBuilder = new StringBuilder();
			for (String arg : args) {
				stringBuilder.append(arg).append(" ");
			}
			reason = stringBuilder.toString();
		}

		plugin.getWarningFactory().setWarning(player, reason);
		int WarningMax, Warning;
		WarningMax = plugin.getWarningFactory().getMaxWarning();
		if(plugin.getWarningFactory().getWarningNumber(player) == 0) {
			Warning = WarningMax;
		}else {
			Warning = plugin.getWarningFactory().getWarningNumber(player);
		}
		if(player.isOnline()) {
			player.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessages().getString("Warning.Receive"))
					.replaceAll("%warning%", Warning + "")
					.replaceAll("%warning_max%", WarningMax+ "")
					.replaceAll("%player%", player.getName())
					.replaceAll("%admin%", sender.getName())
					.replaceAll("%reason%", reason));
			if(plugin.getConfig().getBoolean("Title-Warning")) {
				titlemanager.Title(player.getPlayer(), ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessages().getString("Warning.Receive"))
						.replaceAll("%warning%", Warning + "")
						.replaceAll("%warning_max%", WarningMax+ "")
						.replaceAll("%player%", player.getName())
						.replaceAll("%admin%", sender.getName())
						.replaceAll("%reason%", reason));
			}
		}
		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessages().getString("Warning.Send"))
				.replaceAll("%warning%", Warning + "")
				.replaceAll("%warning_max%", WarningMax+ "")
				.replaceAll("%player%", player.getName())
				.replaceAll("%admin%", sender.getName())
				.replaceAll("%reason%", reason));
		Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessages().getString("Warning.broadcast"))
				.replaceAll("%warning%", Warning + "")
				.replaceAll("%warning_max%", WarningMax+ "")
				.replaceAll("%player%", player.getName())
				.replaceAll("%admin%", sender.getName())
				.replaceAll("%reason%", reason));

		return true;

	}
}
