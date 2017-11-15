package com.fstyle.assetsqliteopenhelper.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import com.fstyle.assetsqliteopenhelper.database.dao.TestDao;
import com.fstyle.assetsqliteopenhelper.database.entity.TestEntity;
import com.fstyle.library.helper.AssetSQLiteOpenHelperFactory;

/**
 * Created by daolq on 11/15/17.
 */

@Database(entities = {TestEntity.class},version = 1)
public abstract class TestDatabase extends RoomDatabase {

    private static final String DB_NAME = "test.db";


    public abstract TestDao testDao();

    public static TestDatabase createPersistentDatabase(Context context) {
        return Room.databaseBuilder(context, TestDatabase.class, DB_NAME)
                .openHelperFactory(new AssetSQLiteOpenHelperFactory())
                .build();
    }
}
