package ro.deiutzblaxo.Purgatory.Spigot.Tasks;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import ro.deiutzblaxo.Purgatory.Spigot.MainSpigot;

public class BreakTask implements Listener {
	private MainSpigot plugin;
	public BreakTask(MainSpigot main){
		plugin = main;
	}
	private Integer Progress, newProgress;

	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		Player player = e.getPlayer();

		if(plugin.getBanFactory().isBan(player.getUniqueId())) {

			for(String task : plugin.getTaskFactory().getTasks()) {

				if(plugin.getTaskFactory().getType(task).equalsIgnoreCase("break")) {

					if(plugin.getTaskFactory().isMaterial(task, e.getBlock().getType())) {

						Progress = plugin.getTaskFactory().getProgress(player.getUniqueId().toString(), task);
						newProgress = Progress + 1;

						if(newProgress >= plugin.getTaskFactory().getCount(task)) {

							plugin.getBanFactory().removeBan(player);
							plugin.getTaskFactory().removeTasks(player.getUniqueId().toString());
							plugin.getScoreBoardAPI().removeScoreBroad(player);

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
