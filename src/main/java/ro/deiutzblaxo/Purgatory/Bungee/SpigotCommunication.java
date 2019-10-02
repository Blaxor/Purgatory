package ro.deiutzblaxo.Purgatory.Bungee;

import java.util.UUID;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class SpigotCommunication implements Listener {
	protected MainBungee plugin;
	protected SpigotCommunication(MainBungee main){
		plugin = main;


	}

	@EventHandler
	public void onPluginMessage(PluginMessageEvent ev) {

		byte[] data = ev.getData();
		ByteArrayDataInput in = ByteStreams.newDataInput(data);
		if(ev.getTag().contentEquals("purgatory:main")) {
			String type = in.readUTF();
			if(type.equals("unban")) {
				UUID uuid = UUID.fromString(in.readUTF());
				plugin.getBanFactory().removeBan(uuid);
			}

		}
	}

	public void send(UUID uuid , String[] str) {
		ByteArrayDataOutput output = ByteStreams.newDataOutput();
		output.writeUTF(str[0]);//TYPE
		if(str[0].equals("ban")) {
			output.writeUTF(uuid.toString());//PLAYER'S UUID
			output.writeUTF(str[1]);//REASON
			output.writeUTF(str[2]);//Player's name
		}else if(str[0].equals("unban")){
			output.writeUTF(uuid.toString());//PLAYER'S UUID
		}else {
			plugin.getProxy().getConsole().sendMessage(new TextComponent("UNAVALABILE TYPE AT ro.deiutzblaxo.Purgatory.Bungee.SpigotComunication.class AT send method"));
		}

		plugin.getServerManager().getPurgatoryServer().sendData("purgatory:main", output.toByteArray());

	}
}
