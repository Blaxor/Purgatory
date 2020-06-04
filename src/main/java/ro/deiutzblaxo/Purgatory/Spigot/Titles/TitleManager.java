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
