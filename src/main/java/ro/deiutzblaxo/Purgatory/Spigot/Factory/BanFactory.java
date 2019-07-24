package ro.deiutzblaxo.Purgatory.Spigot.Factory;

import java.util.UUID;

import org.bukkit.Bukkit;

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
				if(Bukkit.getPlayer(uuid).isOnline()) {
					plugin.getScoreBoardAPI().createScoreboard(Bukkit.getPlayer(uuid), plugin.getTaskFactory().getTasks());
				}
			}
		}

	}

	public void removeBan(UUID uuid) {
		if(!plugin.isBungeeEnabled()) {

			plugin.getConfigManager().loadBanDataBase();
			plugin.getConfigManager().getBanDataBase().set(uuid.toString() , null);
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
