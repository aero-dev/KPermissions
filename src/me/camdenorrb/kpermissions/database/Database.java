package me.camdenorrb.kpermissions.database;

import me.camdenorrb.kpermissions.rank.RankInfo;
import me.camdenorrb.kpermissions.utils.Call;

import java.util.Set;
import java.util.UUID;

/**
 * Created by camdenorrb on 8/8/16.
 */
public interface Database<T extends AutoCloseable> {

    T getResource();

    void remRank(String rankName);

    void setRank(RankInfo rankInfo);

    void getRanks(Call<Set<RankInfo>> call);

    void setPlayerRank(UUID uuid, String rankName);

    void getRank(String name, Call<RankInfo> call);

    void getPlayerRank(UUID uuid, Call<RankInfo> call);

}
