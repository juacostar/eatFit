package com.example.eatfit.module

import android.content.Context
import androidx.room.Room
import com.example.eatfit.model.data.EatFitDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val EATFIT_DATABASE_NAME = "eatfit_database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) = Room.databaseBuilder(context
        , EatFitDatabase::class.java, EATFIT_DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideRecipeDAO(db: EatFitDatabase) = db.getRecipeDao()
}