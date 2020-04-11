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
