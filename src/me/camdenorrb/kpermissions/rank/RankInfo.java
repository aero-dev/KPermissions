package me.camdenorrb.kpermissions.rank;

import me.camdenorrb.kpermissions.utils.ChatUtils;

/**
 * Created by camdenorrb on 8/9/16.
 */
public class RankInfo {

    private int level;
    private String name, prefix, tabPrefix;

    public RankInfo(String name, int level, String prefix, String tabPrefix) {
        this.name = name;
        this.level = level;
        this.prefix = ChatUtils.format(prefix);
        this.tabPrefix = ChatUtils.format(tabPrefix);
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

    public String getTabPrefix() {
        return tabPrefix;
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

    public void setTabPrefix(String tabPrefix) {
        this.tabPrefix = tabPrefix;
    }

}
