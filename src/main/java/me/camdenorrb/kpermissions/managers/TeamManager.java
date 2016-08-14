package me.camdenorrb.kpermissions.managers;

import me.camdenorrb.kpermissions.KPermissions;
import me.camdenorrb.kpermissions.rank.RankInfo;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;

/**
 * Created by camdenorrb on 8/13/16.
 */
public class TeamManager {

    private final Scoreboard rankBoard = Bukkit.getScoreboardManager().getNewScoreboard(), mainBoard = Bukkit.getScoreboardManager().getMainScoreboard();

    public TeamManager() {
        setupTeams(rankBoard);
    }

    public void remove(Player player) {
        rankBoard.getTeams().forEach(team -> team.removeEntry(player.getDisplayName()));
        Bukkit.getOnlinePlayers().forEach(player1 -> player1.getScoreboard().getTeams().forEach(team -> team.removeEntry(player.getDisplayName())));
    }

    private Scoreboard setupTeams(Scoreboard scoreboard) {

        for (RankInfo rankInfo : KPermissions.ranks.values()) {
            String boardName = rankInfo.getBoardName();
            if (scoreboard.getTeam(boardName) == null) continue;
            scoreboard.registerNewTeam(boardName).setPrefix(rankInfo.getPrefix());
        }

        return scoreboard;
    }

    public void setup(Player player, String boardName) {

        Scoreboard scoreboard = player.getScoreboard();
        mainBoard.getTeam(boardName).addEntry(player.getDisplayName());

        if (scoreboard == mainBoard) player.setScoreboard(rankBoard);
        else setupTeams(scoreboard).getTeam(boardName).addEntry(player.getDisplayName());

        for (Player player1 : Bukkit.getOnlinePlayers()) {
            Scoreboard scoreboard1 = player1.getScoreboard();
            if (scoreboard1 == mainBoard || scoreboard1 == rankBoard) continue;
            scoreboard1.getTeam(boardName).addEntry(player1.getDisplayName());
        }
    }
}
