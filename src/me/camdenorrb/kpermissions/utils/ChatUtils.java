package me.camdenorrb.kpermissions.utils;

import org.bukkit.ChatColor;

/**
 * Created by camdenorrb on 8/9/16.
 */
public class ChatUtils {

    public static String format(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

}
