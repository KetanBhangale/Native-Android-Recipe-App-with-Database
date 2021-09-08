package com.example.newrecipeapp.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.newrecipeapp.dao.RecipeDao
import com.example.newrecipeapp.database.RecipeDatabase
import com.example.newrecipeapp.network.APIService
import com.example.newrecipeapp.utils.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun providesRetrofitInstance():APIService{
        return Retrofit.Builder().baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APIService::class.java)

    }

    @Provides
    @Singleton
    fun provideDatabaseInstance(application: Application):RecipeDao{
        return Room.databaseBuilder(application, RecipeDatabase::class.java, "RecipeDatabase").build().getRecipeDao()
    }
}