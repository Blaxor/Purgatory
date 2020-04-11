package ro.deiutzblaxo.Purgatory.Spigot.Titles;

import org.bukkit.entity.Player;

import ro.deiutzblaxo.Purgatory.Spigot.MainSpigot;

public class TitleManager {
	private MainSpigot plugin;
	public TitleManager(MainSpigot main) {
		plugin = main;
	}
	public void Title(Player player , String message) {
		if (plugin.getServer().getVersion().contains("1.14")) {
			Spigot1_14 test = new Spigot1_14();
			test.Packet1_14(player.getPlayer(), message);

		} else if (plugin.getServer().getVersion().contains("1.15")) {
			Spigot1_15 test = new Spigot1_15();
			test.Packet1_15(player.getPlayer(), message);
		}
	}

}
