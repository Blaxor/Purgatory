package ro.deiutzblaxo.Purgatory.Spigot.Factory;

import org.bukkit.entity.Player;

import ro.deiutzblaxo.Purgatory.Spigot.MainSpigot;

public class WarningFactory {
	private MainSpigot plugin = MainSpigot.getInstance();

	public void setWarning(Player player , String reason) {
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
				plugin.getBanFactory().setBan(player , reason);
			}


		}

	}
	public void removeWarning(Player player) {
		String uuid = player.getUniqueId().toString();

		plugin.getConfigManager().loadWarningDataBase();
		plugin.getConfigManager().getWarningDataBase().set(uuid, null);
		plugin.getConfigManager().saveWarningDataBase();


	}
	public boolean isWarning(Player player) {
		plugin.getConfigManager().loadWarningDataBase();
		String uuid = player.getUniqueId().toString();
		if(plugin.getConfigManager().getWarningDataBase().contains(uuid)) {
			return true;
		}
		return false;
	}
	public int getWarningNumber(Player player) {
		String uuid = player.getUniqueId().toString();
		if(isWarning(player)) {
			return plugin.getConfigManager().getWarningDataBase().getInt(uuid + ".Value");
		}else {
			return 0;
		}
	}

}
