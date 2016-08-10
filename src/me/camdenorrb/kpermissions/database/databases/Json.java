package me.camdenorrb.kpermissions.database.databases;

import me.camdenorrb.kpermissions.database.Database;
import me.camdenorrb.kpermissions.rank.RankInfo;
import me.camdenorrb.kpermissions.utils.Call;

import java.util.UUID;

/**
 * Created by camdenorrb on 8/10/16.
 */
public class Json implements Database {

    @Override
    public String getName() {
        return "Json";
    }

    @Override
    public AutoCloseable getResource() {
        return null;
    }

    @Override
    public void remRank(String rankName) {

    }

    @Override
    public void setRank(RankInfo rankInfo) {

    }

    @Override
    public void getRanks(Call call) {

    }

    @Override
    public void setPlayerRank(UUID uuid, String rankName) {

    }

    @Override
    public void getRank(String rankName, Call call) {

    }

    @Override
    public void getPlayerRank(UUID uuid, Call call) {

    }
}
