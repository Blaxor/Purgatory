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
package ro.deiutzblaxo.Purgatory.Spigot.Hoockers;

import org.bukkit.entity.Player;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import ro.deiutzblaxo.Purgatory.Spigot.MainSpigot;



public class PlaceHolderHooker extends PlaceholderExpansion {
	private final MainSpigot plugin;

	public PlaceHolderHooker(MainSpigot plugin) {this.plugin = plugin;}

	@Override
	public String onPlaceholderRequest(Player p, String params) {
		for(String task : plugin.getTaskFactory().getTasks()) {
			if(params.equalsIgnoreCase(task + "_progress"))
				return String.valueOf(plugin.getTaskFactory().getProgress(p.getUniqueId().toString(), task));
			if(params.equalsIgnoreCase(task + "_count")) {
				return String.valueOf(plugin.getTaskFactory().getCount(task));
			}
		}
		if(params.equalsIgnoreCase("warnings")) {
			return String.valueOf(plugin.getWarningFactory().getWarningNumber(p));
		}
		if(params.equalsIgnoreCase("warnings_max")) {
			return String.valueOf(plugin.getConfig().getInt("MaxWarnings"));
		}
		if(params.equalsIgnoreCase("reason_warning")) {
			return String.valueOf(plugin.getWarningFactory().getReason(p));
		}
		if(params.equalsIgnoreCase("reason_ban")) {
			return String.valueOf(plugin.getBanFactory().reasonBan(p.getUniqueId()));
		}

		return null;
	}
	@Override
	public String getAuthor() {
		return "Deiutz";
	}

	@Override
	public String getIdentifier() {
		return "purgatory";
	}

	@Override
	public String getVersion() {

		return this.plugin.getDescription().getVersion();
	}

}