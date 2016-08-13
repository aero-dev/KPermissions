package me.camdenorrb.kpermissions.rank;

import me.camdenorrb.kpermissions.KPermissions;
import me.camdenorrb.kpermissions.utils.ChatUtils;

/**
 * Created by camdenorrb on 8/9/16.
 */
public class RankInfo {

    private int level;
    private String name, prefix, tabPrefix;

    public RankInfo(String name, int level, String prefix) {
        this.name = name;
        this.level = level;
        this.prefix = ChatUtils.format(prefix);
    }

    public int getLevel() {
        return level;
    }

    public String getName() {
        return name;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getBoardName() {
        int position = KPermissions.RANKS.size() - level;
        char p = 'A';
        if (position >= 10) while ((position -= 10) >= 10) p++;
        return Character.toString(p) + position;
    }
}
