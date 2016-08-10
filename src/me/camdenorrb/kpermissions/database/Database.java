package me.camdenorrb.kpermissions.database;

import me.camdenorrb.kpermissions.rank.RankInfo;
import me.camdenorrb.kpermissions.utils.Call;

import java.util.Set;
import java.util.UUID;

/**
 * Created by camdenorrb on 8/8/16.
 */
public abstract class Database<T extends AutoCloseable> {

    abstract T getResource();

    abstract String getName();

    abstract void remRank(String rankName);

    abstract void setRank(RankInfo rankInfo);

    abstract void getRanks(Call<Set<RankInfo>> call);

    abstract  void setPlayerRank(UUID uuid, String rankName);

    abstract void getPlayerRank(UUID uuid, Call<RankInfo> call);

    abstract  void getRank(String rankName, Call<RankInfo> call);

}
