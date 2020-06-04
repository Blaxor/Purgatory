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

import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_14_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_14_R1.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_14_R1.PacketPlayOutTitle;
import net.minecraft.server.v1_14_R1.PacketPlayOutTitle.EnumTitleAction;

public class Spigot1_14 {
	public void Packet1_14(Player player, String titleString) {
		String titlestring = ChatColor.translateAlternateColorCodes('&', titleString);
		PacketPlayOutTitle title = new PacketPlayOutTitle(EnumTitleAction.TITLE,
				ChatSerializer.a("{\"text\":\"" + titlestring + "\"}"), 1, 20, 20);
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(title);
	}
}
