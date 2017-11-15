package com.fstyle.assetsqliteopenhelper;

import android.app.Application;
import com.fstyle.assetsqliteopenhelper.database.TestDatabase;

/**
 * Created by daolq on 11/15/17.
 */

public class MainApplication extends Application {

    private TestDatabase mDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        mDatabase = TestDatabase.createPersistentDatabase(this);
    }

    public TestDatabase getDatabase() {
        return mDatabase;
    }
}
