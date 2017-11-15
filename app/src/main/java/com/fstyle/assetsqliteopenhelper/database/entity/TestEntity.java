package com.fstyle.assetsqliteopenhelper.database.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by daolq on 11/15/17.
 */

@Entity(tableName = "test")
public class TestEntity {
    @PrimaryKey
    public int id;
    @ColumnInfo(name = "name")
    public String name;
}
