package me.camdenorrb.kpermissions;

import me.camdenorrb.kpermissions.database.Database;
import me.camdenorrb.kpermissions.managers.ScoreManager;
import me.camdenorrb.kpermissions.rank.RankInfo;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by camdenorrb on 8/8/16.
 */
public class KPermissions extends JavaPlugin {

    public static Database<?> database;
    public static KPermissions instance;

    public static Map<String, RankInfo> ranks = new HashMap<>();
    public static ScoreManager scoreManager = new ScoreManager();
    public static Map<UUID, RankInfo> uuidRankMap = new HashMap<>();

    @Override
    public void onEnable() {
        instance = this;
        database.getRanks(rankInfos -> rankInfos.forEach(rankInfo -> {
            ranks.put(rankInfo.getName(), rankInfo);
            scoreManager.setTeam(rankInfo.getBoardName(), rankInfo.getPrefix());
        }));
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

}
