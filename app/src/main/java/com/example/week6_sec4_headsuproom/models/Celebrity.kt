package com.example.week6_sec4_headsuproom.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Celebrities")
data class Celebrity (
    @PrimaryKey(autoGenerate = true)@ColumnInfo(name="id")val id:Int=0,
    @ColumnInfo(name="Name")val name:String="",
    @ColumnInfo(name="Taboo1")val taboo1:String="",
    @ColumnInfo(name="Taboo2")val taboo2:String="",
    @ColumnInfo(name="Taboo3")val taboo3:String=""
)
