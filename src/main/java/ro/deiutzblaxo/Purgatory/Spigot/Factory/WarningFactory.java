package ro.deiutzblaxo.Purgatory.Spigot.Factory;

import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;

import ro.deiutzblaxo.Purgatory.Spigot.MainSpigot;
import ro.deiutzblaxo.Purgatory.Spigot.Titles.TitleManager;

public class WarningFactory {
	private MainSpigot plugin = MainSpigot.getInstance();

	public void setWarning(OfflinePlayer player , String reason) {
		String uuid = player.getUniqueId().toString();

		plugin.getConfigManager().loadWarningDataBase();
		if(!isWarning(player)) {
			plugin.getConfigManager().getWarningDataBase().set(uuid + ".Value", 1);
			plugin.getConfigManager().getWarningDataBase().set(uuid + ".Reason", reason);
			plugin.getConfigManager().saveWarningDataBase();
		}else {
			int MaxWarning = plugin.getConfig().getInt("MaxWarnings");
			int Warning = this.getWarningNumber(player) + 1;
			if(MaxWarning > Warning) {
				plugin.getConfigManager().getWarningDataBase().set(uuid + ".Value" , Warning);
				plugin.getConfigManager().getWarningDataBase().set(uuid + ".Reason", reason);
				plugin.getConfigManager().saveWarningDataBase();
			}else if(MaxWarning <= Warning) {
				plugin.getBanFactory().setBan(player.getUniqueId() , player.getName() ,reason);
				if(player.isOnline()) {
					player.getPlayer().teleport(plugin.getWorldManager().getPurgatory().getSpawnLocation());
					if(plugin.getConfig().getBoolean("Force-Kick")) {

						player.getPlayer().kickPlayer(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getString
								(plugin.getConfigManager().getMessages(),"Ban.Format").replaceAll("%reason%", reason)).replaceAll("/n", "\n"));

					}else {
						TitleManager titlemanager;
						titlemanager = new TitleManager(plugin);
						titlemanager.Title(player.getPlayer(), ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getString
								(plugin.getConfigManager().getMessages(),"Ban.Format").replaceAll("%reason%", reason)).replaceAll("/n", "\n"));

					}
				}
			}


		}

	}
	public void removeWarning(UUID uuid) {


		plugin.getConfigManager().loadWarningDataBase();
		plugin.getConfigManager().getWarningDataBase().set(uuid.toString(), null);
		plugin.getConfigManager().saveWarningDataBase();


	}
	public int getMaxWarning() {
		return plugin.getConfig().getInt("MaxWarnings");

	}
	public boolean isWarning(OfflinePlayer player) {
		plugin.getConfigManager().loadWarningDataBase();
		String uuid = player.getUniqueId().toString();
		if(plugin.getConfigManager().getWarningDataBase().contains(uuid)) {
			return true;
		}
		return false;
	}
	public int getWarningNumber(OfflinePlayer player) {
		String uuid = player.getUniqueId().toString();
		if(isWarning(player)) {
			return plugin.getConfigManager().getWarningDataBase().getInt(uuid + ".Value");
		}else {
			return 0;
		}
	}
	public String getReason(OfflinePlayer player) {
		String uuid = player.getUniqueId().toString();
		return plugin.getConfigManager().getWarningDataBase().getString(uuid + ".Reason");
	}

}
