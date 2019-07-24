package ro.deiutzblaxo.Purgatory.Spigot.Titles;

import org.bukkit.entity.Player;

import ro.deiutzblaxo.Purgatory.Spigot.MainSpigot;

public class TitleManager {
	private MainSpigot plugin;
	public TitleManager(MainSpigot main) {
		plugin = main;
	}
	public void Title(Player player , String message) {
		if(plugin.getServer().getVersion().contains("1.12")) {
			Spigot1_12 test = new Spigot1_12();
			test.Packet1_12(player.getPlayer(), message);

		}else if(plugin.getServer().getVersion().contains("1.11")){
			Spigot1_11 test = new Spigot1_11();
			test.Packet1_11(player.getPlayer(), message);

		}else if(plugin.getServer().getVersion().contains("1.10")) {
			Spigot1_10 test = new Spigot1_10();
			test.Packet1_10(player.getPlayer(), message);

		}else if(plugin.getServer().getVersion().contains("1.9")) {
			Spigot1_9 test = new Spigot1_9();
			test.Packet1_9(player.getPlayer(), message);

		}else if(plugin.getServer().getVersion().contains("1.8")) {
			Spigot1_8 test = new Spigot1_8();
			test.Packet1_8(player.getPlayer(), message);

		}else if(plugin.getServer().getVersion().contains("1.14")) {
			Spigot1_14 test = new Spigot1_14();
			test.Packet1_14(player.getPlayer(), message);

		}else if(plugin.getServer().getVersion().contains("1.13")) {
			Spigot1_13 test = new Spigot1_13();
			test.Packet1_13(player.getPlayer(), message);
		}
	}

}
