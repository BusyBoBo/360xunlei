package com.czg.xunlei.gen;

import android.content.Context;

/**
 * Created by czg on 2017/9/25.
 */

public class Dao {
    private static Dao instance;
    private final CarToonBeanDao mCarToonBeanDao;

    private Dao(Context context) {
        DbHelper dbHelper = new DbHelper(context, "xunlei.db", null);
        DaoMaster daoMaster = new DaoMaster(dbHelper.getWritableDb());
        DaoSession daoSession = daoMaster.newSession();
        mCarToonBeanDao = daoSession.getCarToonBeanDao();
    }

    public static synchronized Dao getInstance(Context context) {
        if (instance == null) {
            synchronized (Dao.class) {
                if (instance == null) {
                    instance = new Dao(context);
                }
            }
        }
        return instance;
    }

    public CarToonBeanDao getCarToonBeanDao() {
        return mCarToonBeanDao;
    }
}
