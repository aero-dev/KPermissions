package me.camdenorrb.kpermissions.utils;

/**
 * Created by camdenorrb on 8/8/16.
 */
public interface Call<D> {

    void call(D paramD);

    default void onFail() {}

}
