package me.camdenorrb.kpermissions.database;

import com.google.gson.Gson;
import me.camdenorrb.kpermissions.rank.RankInfo;
import me.camdenorrb.kpermissions.utils.Call;

import java.util.Set;
import java.util.UUID;

/**
 * Created by camdenorrb on 8/8/16.
 */
public abstract class Database<T extends AutoCloseable> {

    protected static final Gson gson = new Gson();

    public abstract T getResource();

    public abstract void remRank(String rankName);

    public abstract void setRank(RankInfo rankInfo);

    public abstract void getRanks(Call<Set<RankInfo>> call);

    public String getName() { return getClass().getSimpleName(); }

    public abstract void setPlayerRank(UUID uuid, String rankName);

    public abstract void getPlayerRank(UUID uuid, Call<RankInfo> call);

    public abstract void getRank(String rankName, Call<RankInfo> call);

}
