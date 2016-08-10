package me.camdenorrb.kpermissions.database.databases;

import com.google.gson.Gson;
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
public class Redis implements Database<Jedis> {

    private final JedisPool jedisPool;
    private final Gson gson = new Gson();

    public Redis(String host, String password, int timeout, int port) {
        jedisPool = new JedisPool(new JedisPoolConfig(), host, port, timeout, password);
    }

    @Override
    public String getName() {
        return "Redis";
    }

    @Override
    public Jedis getResource() {
        return jedisPool.getResource();
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
                jedis.select(1);
                call.call(gson.fromJson(jedis.hget("set:ranks", rankName), RankInfo.class));
            }
        });
    }

    @Override
    public void remRank(String rankName) {
        KPermissions.runAsync(() -> {
            try (Jedis jedis = jedisPool.getResource()) {
                jedis.select(1);
                jedis.hdel("set:ranks", rankName);
            }
        });
    }

    @Override
    public void setRank(RankInfo rankInfo) {
        KPermissions.runAsync(() -> {
            try (Jedis jedis = jedisPool.getResource()) {
                jedis.select(1);
                jedis.hset("set:ranks", rankInfo.getName(), gson.toJson(rankInfo));
            }
        });
    }

    @Override
    public void getRanks(Call<Set<RankInfo>> call) {
        KPermissions.runAsync(() -> {
            try (Jedis jedis = jedisPool.getResource()) {
                jedis.select(1);
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

