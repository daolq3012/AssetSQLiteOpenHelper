package com.fstyle.assetsqliteopenhelper.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;
import com.fstyle.assetsqliteopenhelper.database.entity.TestEntity;
import io.reactivex.Flowable;
import java.util.List;

/**
 * Created by daolq on 11/15/17.
 */

@Dao
public interface TestDao {

    @Query("SELECT * FROM test")
    Flowable<List<TestEntity>> getAll();
}
