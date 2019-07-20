package ro.deiutzblaxo.Purgatory.Spigot.Titles;

import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_13_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_13_R2.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_13_R2.PacketPlayOutTitle;
import net.minecraft.server.v1_13_R2.PacketPlayOutTitle.EnumTitleAction;

public class Spigot1_13 {
	public void Packet1_13(Player player, String titleString){
		String titlestring = ChatColor.translateAlternateColorCodes('&', titleString);
		PacketPlayOutTitle title = new PacketPlayOutTitle(EnumTitleAction.TITLE,
				ChatSerializer.a("{\"text\":\"" + titlestring + "\"}"), 1, 20, 20);
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(title);
	}

}
