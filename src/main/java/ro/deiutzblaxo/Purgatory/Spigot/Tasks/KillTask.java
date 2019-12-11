package ro.deiutzblaxo.Purgatory.Spigot.Tasks;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import net.md_5.bungee.api.ChatColor;
import ro.deiutzblaxo.Purgatory.Spigot.MainSpigot;

public class KillTask implements Listener {
	private MainSpigot plugin;
	private int Progress, newProgress;
	public KillTask(MainSpigot main){
		plugin = main;
	}


	@EventHandler(ignoreCancelled =  true , priority = EventPriority.HIGHEST)
	public void onKill(EntityDeathEvent e) {
		Player player = e.getEntity().getKiller();
		if(player instanceof Player) {
			if(plugin.getBanFactory().isBan(player.getUniqueId())) {
				for(String task : plugin.getTaskFactory().getTasks()) {
					if(plugin.getTaskFactory().getType(task).equalsIgnoreCase("kill")) {
						if(plugin.getTaskFactory().getEntityType(task).equals(e.getEntityType())) {

							Progress = plugin.getTaskFactory().getProgress(player.getUniqueId().toString(), task);
							newProgress = Progress + 1;

							if(newProgress >= plugin.getTaskFactory().getCount(task)) {
								player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getString(
										plugin.getConfigManager().getMessages(), "TaskComplete").replaceAll("%task%", task)));
								plugin.getBanFactory().removeBan(player.getUniqueId());
								plugin.getTaskFactory().removeTasks(player.getUniqueId());
								plugin.getScoreBoardAPI().removeScoreBroad(player);
								if(plugin.isBungeeEnabled()) {
									String[] send = "unban * test".split("*");
									plugin.getBungeeCommunication().send(player.getUniqueId(), send);
								}

							}else {

								plugin.getTaskFactory().setProgress(player.getUniqueId().toString(),task , newProgress);
								plugin.getScoreBoardAPI().updateScoreboard1(player, task, newProgress);

							}

						}
					}
				}
			}
		}
	}

}
