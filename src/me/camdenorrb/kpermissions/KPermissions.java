package me.camdenorrb.kpermissions;

import me.camdenorrb.kpermissions.rank.RankInfo;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by camdenorrb on 8/8/16.
 */
public class KPermissions extends JavaPlugin {

    public static KPermissions instance;

    public static final Map<String, RankInfo> RANKS = new ConcurrentHashMap<>();

    @Override
    public void onEnable() {
        instance = this;
    }

    public static void runAsync(Runnable runnable) {
        Bukkit.getScheduler().runTaskAsynchronously(instance, runnable);
    }

}
