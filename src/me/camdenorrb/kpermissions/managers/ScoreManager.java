package me.camdenorrb.kpermissions.managers;

import me.camdenorrb.kpermissions.rank.RankInfo;
import org.bukkit.Bukkit;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

/**
 * Created by camdenorrb on 8/13/16.
 */
public class ScoreManager {

    private final Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();

    public Scoreboard getScoreboard() {
        return scoreboard;
    }

    public void setEntry(String name, RankInfo rankInfo) { remove(name); add(name, rankInfo); }

    public void remove(String name) { scoreboard.getTeams().forEach(team -> team.removeEntry(name)); }

    public void add(String name, RankInfo rankInfo) { scoreboard.getTeam(rankInfo.getBoardName()).addEntry(name); }

    public void setTeam(String name, String prefix) {
        scoreboard.getTeams().stream().filter(team -> team.getName().equals(name)).findFirst().ifPresent(Team::unregister);
        scoreboard.registerNewTeam(name).setPrefix(prefix);
    }
}
