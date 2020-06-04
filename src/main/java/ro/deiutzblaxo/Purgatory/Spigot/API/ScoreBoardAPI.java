//    Purgatory , a ban system for servers of Minecraft
//    Copyright (C) 2020  Deiutz
//
//    This program is free software: you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation, either version 3 of the License, or
//    (at your option) any later version.
//
//    This program is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License
//    along with this program.  If not, see <https://www.gnu.org/licenses/>.
package ro.deiutzblaxo.Purgatory.Spigot.API;

import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import net.md_5.bungee.api.ChatColor;
import ro.deiutzblaxo.Purgatory.Spigot.MainSpigot;

public class ScoreBoardAPI {
	private MainSpigot plugin = MainSpigot.getInstance();

	/**
	 * @param player  The Player
	 *
	 * @param Title   The title of scoreboard
	 *
	 * @param HashMap String : The name of obiective <br>
	 *
	 *                Integer: The Score on obiective
	 *
	 * @return Return a scoreboard
	 *
	 * @author Deiutz
	 */

	public void createScoreboard(Player player,  Set<String> Tasks) {
		if(plugin.getConfig().getBoolean("Scoreboard")) {
			ScoreboardManager manager = Bukkit.getScoreboardManager();
			Scoreboard board = manager.getNewScoreboard();
			Objective objective = board.registerNewObjective("Stats", "dummy", ChatColor.translateAlternateColorCodes('&',
					plugin.getConfigManager().getMessages().getString("Scoreboard.Title")));
			objective.setDisplaySlot(DisplaySlot.SIDEBAR);
			for (String str : Tasks) {
				Score score = objective.getScore(ChatColor.translateAlternateColorCodes('&',
						plugin.getConfigManager().getMessages().getString("Scoreboard.TasksColor") +str));
				if(!plugin.getConfig().getBoolean("ScoreBoard-CountDown-Tasks")) {
					score.setScore(plugin.getTaskFactory().getProgress(player.getUniqueId().toString(), str));
				}else {
					score.setScore(plugin.getTaskFactory().getCount(str) - plugin.getTaskFactory().getProgress(player.getUniqueId().toString(), str));
				}

			}
			player.setScoreboard(board);
		}
	}

	/**
	 * @param player  The Player
	 *
	 * @param HashMap String : The name of obiective <br>
	 *
	 *                Integer: The Score on obiective
	 *
	 * @return Update the scoreboard with the new score.
	 *
	 * @author Deiutz
	 */
	public void updateScoreboard1(Player player, String Task , Integer Value) {
		if(plugin.getConfig().getBoolean("Scoreboard")) {
			Score score = player.getScoreboard().getObjective(DisplaySlot.SIDEBAR).getScore(ChatColor.translateAlternateColorCodes('&',
					plugin.getConfigManager().getMessages().getString("Scoreboard.TasksColor") +Task));
			if(plugin.getConfig().getBoolean("ScoreBoard-CountDown-Tasks")) {
				int count = plugin.getTaskFactory().getCount(Task);
				score.setScore(count - Value);
			}else {

				score.setScore(Value);
			}
		}
	}
	/**
	 * @param player  The Player
	 *
	 * @return Delete the scoreboard
	 *
	 * @author Deiutz
	 */
	public void removeScoreBroad(Player player) {
		player.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
	}
}
