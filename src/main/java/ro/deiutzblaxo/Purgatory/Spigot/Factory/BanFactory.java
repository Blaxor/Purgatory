package ro.deiutzblaxo.Purgatory.Spigot.Factory;

import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

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
				if(Bukkit.getPlayer(uuid) != null) {
					plugin.getScoreBoardAPI().createScoreboard(Bukkit.getPlayer(uuid), plugin.getTaskFactory().getTasks());
					Player player = Bukkit.getPlayer(uuid);
					for(PotionEffect p : player.getActivePotionEffects()) {
						if(p.getType().equals(PotionEffectType.INVISIBILITY)) {
							player.removePotionEffect(p.getType());
							player.setAllowFlight(false);
							player.setCanPickupItems(true);

						}
					}
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
	public String reasonBan(UUID uuid) {
		plugin.getConfigManager().loadBanDataBase();
		return plugin.getConfigManager().getBanDataBase().getString(uuid + ".Reason");
	}
	public void setTempBan(UUID uuid, String name, String reason, Integer seconds){
		setBan(uuid , name ,reason);
		if(isTempBan(uuid)) {
			plugin.TempBan.put(uuid, seconds + getTime(uuid));
		}else {
			plugin.TempBan.put(uuid, seconds);
		}


		plugin.getConfigManager().saveBanDataBase();
	}
	public void removeTempBan(UUID uuid) {
		removeBan(uuid);
		plugin.TempBan.remove(uuid);

	}
	public Boolean isTempBan(UUID uuid) {
		if(isBan(uuid)) {
			if(plugin.TempBan.containsKey(uuid)) {
				return true;
			}
		}
		return false;
	}
	public Integer getTime(UUID uuid) {
		return plugin.TempBan.get(uuid);
	}
	public void DisableTempBan() {
		System.out.print("Temp ban system shutdown...");
		plugin.getConfigManager().loadBanDataBase();
		for(UUID uuid : plugin.TempBan.keySet()) {
			plugin.getConfigManager().getBanDataBase().set(uuid + ".Seconds", plugin.TempBan.get(uuid));
		}
		plugin.getConfigManager().saveBanDataBase();
		System.out.print("Temp ban system has been shutdown...");
	}
	public void EnableTempBan() {
		System.out.print("Temp ban system starting...");
		plugin.getConfigManager().loadBanDataBase();

		Set<String> keys = plugin.getConfigManager().getBanDataBase().getKeys(false);
		ArrayList<UUID> uuids = new ArrayList<UUID>();
		for(String str: keys) {
			uuids.add(UUID.fromString(str));
		}

		for(UUID uuid : uuids) {
			if(plugin.getConfigManager().getBanDataBase().contains(uuid + ".Seconds")) {
				plugin.TempBan.put(uuid, plugin.getConfigManager().getBanDataBase().getInt(uuid + ".Seconds"));
			}
		}
		System.out.print("Temp ban system started!");
	}

}
