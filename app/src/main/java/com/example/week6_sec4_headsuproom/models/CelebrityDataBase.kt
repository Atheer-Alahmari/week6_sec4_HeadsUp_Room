package com.example.week6_sec4_headsuproom.models

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase



@Database(entities = [Celebrity::class],version = 1,exportSchema = false)
abstract class CelebrityDataBase: RoomDatabase() {

    companion object{
        var instance: CelebrityDataBase?=null
        fun getInstance(ctx: Context): CelebrityDataBase
        {
            if(instance!=null)
            {
                return  instance as CelebrityDataBase;
            }
            instance= Room.databaseBuilder(ctx,CelebrityDataBase::class.java,"somename").run { allowMainThreadQueries() }.build();
            return instance as CelebrityDataBase
        }
    }
    abstract fun CelebrityDao():CelebrityDao
}