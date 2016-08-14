package me.camdenorrb.kpermissions.listener;

import me.camdenorrb.kpermissions.KPermissions;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

/**
 * Created by camdenorrb on 8/12/16.
 */
public class PlayerListen implements Listener {

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        KPermissions.uuidRankMap.remove(player.getUniqueId());
        KPermissions.scoreManager.remove(player.getDisplayName());
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        KPermissions.database.getPlayerRank(uuid, rankInfo -> {
            KPermissions.uuidRankMap.put(uuid, rankInfo);
            KPermissions.scoreManager.setEntry(player.getDisplayName(), rankInfo);
        });
    }
}
