package com.czg.xunlei.gen;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import org.greenrobot.greendao.database.Database;

/**
 * @author ：czg
 * @class ：DbHelper.class
 * @date ：2017/9/5.
 * @describe ：DbHelper
 */
public class DbHelper extends DaoMaster.OpenHelper {
    public DbHelper(Context context, String name) {
        super(new GreenDaoContext(context), name);
    }

    public DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(new GreenDaoContext(context), name, factory);
    }

    @Override
    public void onCreate(Database db) {
        super.onCreate(db);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //数据迁移模块
        MigrationHelper.migrate(db,
                CarToonBeanDao.class);
    }
}
