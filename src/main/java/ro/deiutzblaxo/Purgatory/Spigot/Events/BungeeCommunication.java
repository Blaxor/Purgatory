package ro.deiutzblaxo.Purgatory.Spigot.Events;

import java.util.UUID;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import ro.deiutzblaxo.Purgatory.Spigot.MainSpigot;

public class BungeeCommunication implements PluginMessageListener{
	protected MainSpigot plugin;
	public BungeeCommunication(MainSpigot main) {
		plugin = main;

	}

	@Override
	public void onPluginMessageReceived(String channel, Player player, byte[] message) {
		if(channel.contentEquals("purgatory:main")) {
			ByteArrayDataInput in = ByteStreams.newDataInput(message);
			String type = in.readUTF();
			String uuidplayer = in.readUTF();
			UUID uuid = UUID.fromString(uuidplayer);
			if(type.equals("ban")) {
				String reason = in.readUTF();
				String name = in.readUTF();
				plugin.getBanFactory().setBan(uuid, name, reason);
				if(plugin.getServer().getPlayer(UUID.fromString(uuidplayer))!= null) {
					plugin.getScoreBoardAPI().createScoreboard(player,  plugin.getTaskFactory().getTasks());
					for(PotionEffect p : player.getActivePotionEffects()) {
						if(p.getType().equals(PotionEffectType.INVISIBILITY)) {
							player.removePotionEffect(p.getType());

						}
					}
					player.setAllowFlight(false);
					player.setCanPickupItems(true);
					player.setGameMode(GameMode.SURVIVAL);
				}
			}else if(type.equals("unban")) {
				plugin.getBanFactory().removeBan(uuid);



			}else if(type.equals("tempban")){
				String time = in.readUTF();
				String name = in.readUTF();
				String reason = in.readUTF();
				plugin.getBanFactory().setTempBan(uuid, name, reason, Integer.valueOf(time));

			}else {
				plugin.getServer().getConsoleSender().sendMessage("UNAVALABILE TYPE AT ro.deiutzblaxo.Purgatory.Spigot.Events.BungeeCommunication.class AT onPluginMessageRecived Event");
			}
		}
	}

	public void send(UUID uuid , String[] str) {

		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF(str[0]);//TYPE
		out.writeUTF(uuid.toString());//PLAYER'S UUID

		plugin.getServer().sendPluginMessage(plugin, "purgatory:main", out.toByteArray()); // schimbam din purgatory:unbans






	}
}
