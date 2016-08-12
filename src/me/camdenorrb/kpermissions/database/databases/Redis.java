package me.camdenorrb.kpermissions.database.databases;

import me.camdenorrb.kpermissions.KPermissions;
import me.camdenorrb.kpermissions.database.Database;
import me.camdenorrb.kpermissions.rank.RankInfo;
import me.camdenorrb.kpermissions.utils.Call;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Created by camdenorrb on 8/9/16.
 */
public class Redis extends Database<Jedis> {

    private final JedisPool jedisPool;

    public Redis(String host, String password, int timeout, int port) {
        jedisPool = new JedisPool(new JedisPoolConfig(), host, port, timeout, password);
    }

    @Override
    public Jedis getResource() {
        return jedisPool.getResource();
    }

    @Override
    public void remRank(String rankName) {
        KPermissions.runAsync(() -> {
            try (Jedis jedis = jedisPool.getResource()) {
                jedis.hdel("set:ranks", rankName);
            }
        });
    }

    @Override
    public void setPlayerRank(UUID uuid, String rankName) {
        KPermissions.runAsync(() -> {
            try (Jedis jedis = jedisPool.getResource()) {
                jedis.hset("player:" + uuid, "Rank", rankName);
            }
        });
    }

    @Override
    public void getRank(String rankName, Call<RankInfo> call) {
        KPermissions.runAsync(() -> {
            try (Jedis jedis = jedisPool.getResource()) {
                call.call(gson.fromJson(jedis.hget("set:ranks", rankName), RankInfo.class));
            }
        });
    }

    @Override
    public void setRank(RankInfo rankInfo) {
        KPermissions.runAsync(() -> {
            try (Jedis jedis = jedisPool.getResource()) {
                jedis.hset("set:ranks", rankInfo.getName(), gson.toJson(rankInfo));
            }
        });
    }

    @Override
    public void getRanks(Call<Set<RankInfo>> call) {
        KPermissions.runAsync(() -> {
            try (Jedis jedis = jedisPool.getResource()) {
                Set<RankInfo> rankInfoSet = new HashSet<>();
                for (String string : jedis.hkeys("set:ranks")) rankInfoSet.add(gson.fromJson(string, RankInfo.class));
                call.call(rankInfoSet);
            }
        });
    }

    @Override
    public void getPlayerRank(UUID uuid, Call<RankInfo> call) {
        KPermissions.runAsync(() -> {
            try (Jedis jedis = jedisPool.getResource()) {

                String rankName = jedis.hget("player:" + uuid, "Rank");
                if (rankName == null) { call.onFail(); return; }

                RankInfo rankInfo = KPermissions.RANKS.get(rankName);
                if (rankInfo == null) call.onFail();
                else call.call(rankInfo);
            }
        });
    }
}

