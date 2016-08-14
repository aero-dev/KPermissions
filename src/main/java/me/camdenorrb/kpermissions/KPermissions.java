package me.camdenorrb.kpermissions;

import me.camdenorrb.kpermissions.bungee.BungeeMessageListen;
import me.camdenorrb.kpermissions.database.Database;
import me.camdenorrb.kpermissions.database.databases.Redis;
import me.camdenorrb.kpermissions.managers.TeamManager;
import me.camdenorrb.kpermissions.rank.RankInfo;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.Messenger;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by camdenorrb on 8/8/16.
 */
public class KPermissions extends JavaPlugin {

    public static Database<?> database;
    public static KPermissions instance;

    public static transient TeamManager teamManager;
    public static transient Map<String, RankInfo> ranks = new HashMap<>();
    public static transient Map<UUID, RankInfo> uuidRankMap = new HashMap<>();

    @Override
    public void onEnable() {
        instance = this;
        initBungee();
        initAllData();

        teamManager = new TeamManager();
    }

    @Override
    public void onDisable() {
        ranks = null;
        instance = null;
        uuidRankMap = null;
    }

    public static void runAsync(Runnable runnable) {
        Bukkit.getScheduler().runTaskAsynchronously(instance, runnable);
    }

    private void initBungee() {
        if (getConfig().getBoolean("bungeecord", false)) {
            Messenger messenger = getServer().getMessenger();
            messenger.registerOutgoingPluginChannel(instance, "BungeeCord");
            messenger.registerIncomingPluginChannel(instance, "BungeeCord", new BungeeMessageListen());
        }
    }

    private void initAllData() {
        saveDefaultConfig();
        int timeout = getConfig().getInt("timeout", 2000), port = getConfig().getInt("port", 6379);
        String host = getConfig().getString("host", "127.0.0.1"), password = getConfig().getString("password", "");
        database = new Redis(host, password, timeout, port);

        for (Player player : getServer().getOnlinePlayers()) {
            UUID uuid = player.getUniqueId();
            database.getPlayerRank(uuid, rankInfo -> {
                teamManager.setup(player, rankInfo.getBoardName());
                uuidRankMap.put(uuid, rankInfo);
            });
        }

        database.getRanks(rankInfos -> rankInfos.forEach(rankInfo -> {ranks.put(rankInfo.getName(), rankInfo);}));
    }

}
