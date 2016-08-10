package me.camdenorrb.kpermissions.rank;

import me.camdenorrb.kpermissions.utils.ChatUtils;

/**
 * Created by camdenorrb on 8/9/16.
 */
public class RankInfo {

    private String name, prefix, tabPrefix;

    public RankInfo(String name, String prefix, String tabPrefix) {
        this.name = name;
        this.prefix = ChatUtils.format(prefix);
        this.tabPrefix = ChatUtils.format(tabPrefix);
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

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public void setTabPrefix(String tabPrefix) {
        this.tabPrefix = tabPrefix;
    }

}
