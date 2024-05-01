package com.enons.techcareerbootcamp.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.enons.techcareerbootcamp.data.entity.ToDos

@Database(entities = [ToDos::class], version = 1)
abstract class MyDatabase : RoomDatabase(){
    abstract fun getToDos() : ToDosDao
}