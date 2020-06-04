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

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.util.Location;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;

public class WorldGuardHooker {


	public static boolean isProtected(Player player) {

		LocalPlayer localPlayer = WorldGuardPlugin.inst().wrapPlayer(player);
		Location loc = new Location(BukkitAdapter.adapt(player.getLocation().getWorld()), player.getLocation().getX(),
				player.getLocation().getY(), player.getLocation().getZ());
		RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();

		RegionQuery query = container.createQuery();

		if(!query.testState(loc,localPlayer,Flags.BUILD))
		{

			return true;
		}

		return false;
	}

}


