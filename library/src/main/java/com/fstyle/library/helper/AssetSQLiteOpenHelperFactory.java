package com.fstyle.library.helper;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by daolq on 11/14/17.
 */

public class AssetSQLiteOpenHelperFactory implements SupportSQLiteOpenHelper.Factory {
    private String assetDirectory = "";

    public AssetSQLiteOpenHelperFactory() {
        this(SQLiteAssetHelper.ASSET_DB_PATH);
    }

    public AssetSQLiteOpenHelperFactory(String assetDirectory) {
        this.assetDirectory = assetDirectory;
    }

    @Override
    public SupportSQLiteOpenHelper create(SupportSQLiteOpenHelper.Configuration configuration) {
        return new AssetSQLiteOpenHelper(configuration.context, configuration.name, assetDirectory, null,
                configuration.callback.version, new DatabaseErrorHandler() {
            @Override
            public void onCorruption(SQLiteDatabase sqLiteDatabase) {
                // TODO
            }
        }, configuration.callback);
    }
}
