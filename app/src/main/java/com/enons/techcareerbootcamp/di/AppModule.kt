package com.enons.techcareerbootcamp.di

import android.content.Context
import androidx.room.Room
import com.enons.techcareerbootcamp.data.datasource.ToDosDataSource
import com.enons.techcareerbootcamp.data.repo.ToDosRepository
import com.enons.techcareerbootcamp.room.MyDatabase
import com.enons.techcareerbootcamp.room.ToDosDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideToDosRepository(toDosDataSource: ToDosDataSource) : ToDosRepository {
        return ToDosRepository(toDosDataSource)
    }

    @Provides
    @Singleton
    fun provideToDosDataSource(toDosDao: ToDosDao) : ToDosDataSource {
        return ToDosDataSource(toDosDao)
    }

    @Provides
    @Singleton
    fun provideToDosDao(@ApplicationContext context: Context) : ToDosDao {
        val db = Room.databaseBuilder(context,MyDatabase::class.java,"todos_app.sqlite")
            .createFromAsset("todos_app.sqlite")
            .build()
        return db.getToDos()
    }
}