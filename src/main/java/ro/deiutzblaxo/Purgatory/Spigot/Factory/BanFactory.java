package ro.deiutzblaxo.Purgatory.Spigot.Factory;

import java.util.UUID;

import org.bukkit.entity.Player;

import ro.deiutzblaxo.Purgatory.Spigot.MainSpigot;

public class BanFactory {
	private MainSpigot plugin = MainSpigot.getInstance();

	public void setBan(UUID uuid, String name, String reason) {
		if(!plugin.isBungeeEnabled()) {
			if(!isBan(uuid)) {
				plugin.getConfigManager().loadBanDataBase();
				plugin.getConfigManager().getBanDataBase().set(uuid + ".Name", name);
				plugin.getConfigManager().getBanDataBase().set(uuid + ".Reason", reason);

				plugin.getConfigManager().saveBanDataBase();
				plugin.getWarningFactory().removeWarning(uuid);
				plugin.getTaskFactory().setTasks(uuid);
			}
		}

	}

	public void removeBan(Player player) {
		if(!plugin.isBungeeEnabled()) {

			plugin.getConfigManager().loadBanDataBase();
			String uuid = player.getUniqueId().toString();
			plugin.getConfigManager().getBanDataBase().set(uuid , null);
			plugin.getConfigManager().saveBanDataBase();

		}

	}
	public boolean isBan(UUID uuid) {

		plugin.getConfigManager().loadBanDataBase();
		if(plugin.getConfigManager().getBanDataBase().contains(uuid.toString())) {
			return true;
		}
		return false;
	}
	public void setTempBan(){

	}
	public void removeTempBan() {

	}
}
