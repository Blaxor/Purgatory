package ro.deiutzblaxo.Purgatory.Spigot.API;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class ScoreBoardAPI {

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

	public void createScoreboard(Player player, String Title, HashMap<String, Integer> HashMap) {

		ArrayList<String> Str = new ArrayList<String>();
		Str.addAll(HashMap.keySet());
		ScoreboardManager manager = Bukkit.getScoreboardManager();
		Scoreboard board = manager.getNewScoreboard();
		Objective objective = board.registerNewObjective("Stats", "dummy", Title);
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		for (String str : Str) {
			Score score = objective.getScore(str);
			score.setScore(HashMap.get(str));

		}
		player.setScoreboard(board);

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
	public void updateScoreboard1(Player player, HashMap<String, Integer> HashMap) {
		ArrayList<String> Str = new ArrayList<String>();
		Str.addAll(HashMap.keySet());
		for (String str : Str) {
			Score score = player.getScoreboard().getObjective(DisplaySlot.SIDEBAR).getScore(str);
			score.setScore(HashMap.get(str));

		}

	}
}
