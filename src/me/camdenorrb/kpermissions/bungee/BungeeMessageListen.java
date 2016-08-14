package me.camdenorrb.kpermissions.bungee;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import com.google.gson.Gson;
import me.camdenorrb.kpermissions.KPermissions;
import me.camdenorrb.kpermissions.rank.RankInfo;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.util.UUID;

/**
 * Created by camdenorrb on 8/13/16.
 */
public class BungeeMessageListen implements PluginMessageListener {

    private final static Gson gson = new Gson();

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        if (!channel.equals("BungeeCord")) return;
        ByteArrayDataInput in = ByteStreams.newDataInput(message);
        if (in.readUTF().equals("RankUpdate")) {
            UUID uuid = UUID.fromString(in.readUTF());
            if (Bukkit.getPlayer(uuid) != null) KPermissions.uuidRankMap.put(uuid, gson.fromJson(in.readUTF(), RankInfo.class));
        }
    }
}
