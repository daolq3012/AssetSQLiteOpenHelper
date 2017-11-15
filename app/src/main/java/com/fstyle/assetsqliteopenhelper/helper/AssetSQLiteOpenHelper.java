package com.fstyle.assetsqliteopenhelper.helper;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.annotation.RequiresApi;

/**
 * Created by daolq on 11/14/17.
 */
class AssetSQLiteOpenHelper implements SupportSQLiteOpenHelper {
    private final OpenHelper mDelegate;

    AssetSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
            int version, DatabaseErrorHandler errorHandler, Callback callback) {
        mDelegate = createDelegate(context, name, factory, version, errorHandler, callback);
    }

    private OpenHelper createDelegate(Context context, String name,
            SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler,
            final Callback callback) {
        return new OpenHelper(context, name, factory, version, errorHandler) {
            @Override
            public final void onCreate(SQLiteDatabase sqLiteDatabase) {
                mWrappedDb = new FrameworkSQLiteDatabase(sqLiteDatabase);
                callback.onCreate(mWrappedDb);
            }

            @Override
            public final void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion,
                    int newVersion) {
                callback.onUpgrade(getWrappedDb(sqLiteDatabase), oldVersion, newVersion);
            }

            @Override
            public final void onConfigure(SQLiteDatabase db) {
                callback.onConfigure(getWrappedDb(db));
            }

            @Override
            public final void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                callback.onDowngrade(getWrappedDb(db), oldVersion, newVersion);
            }

            @Override
            public void onOpen(SQLiteDatabase db) {
                callback.onOpen(getWrappedDb(db));
            }
        };
    }

    @Override
    public String getDatabaseName() {
        return mDelegate.getDatabaseName();
    }

    @Override
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void setWriteAheadLoggingEnabled(boolean enabled) {
        mDelegate.setWriteAheadLoggingEnabled(enabled);
    }

    @Override
    public SupportSQLiteDatabase getWritableDatabase() {
        return mDelegate.getWritableSupportDatabase();
    }

    @Override
    public SupportSQLiteDatabase getReadableDatabase() {
        return mDelegate.getReadableSupportDatabase();
    }

    @Override
    public void close() {
        mDelegate.close();
    }

    abstract static class OpenHelper extends SQLiteAssetHelper {

        FrameworkSQLiteDatabase mWrappedDb;

        OpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version,
                DatabaseErrorHandler errorHandler) {
            super(context, name, null, factory, version, errorHandler);
        }

        SupportSQLiteDatabase getWritableSupportDatabase() {
            SQLiteDatabase db = super.getWritableDatabase();
            return getWrappedDb(db);
        }

        SupportSQLiteDatabase getReadableSupportDatabase() {
            SQLiteDatabase db = super.getReadableDatabase();
            return getWrappedDb(db);
        }

        FrameworkSQLiteDatabase getWrappedDb(SQLiteDatabase sqLiteDatabase) {
            if (mWrappedDb == null) {
                mWrappedDb = new FrameworkSQLiteDatabase(sqLiteDatabase);
            }
            return mWrappedDb;
        }

        @Override
        public synchronized void close() {
            super.close();
            mWrappedDb = null;
        }
    }
}
