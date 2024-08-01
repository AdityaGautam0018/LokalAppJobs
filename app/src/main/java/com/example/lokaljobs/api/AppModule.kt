package com.example.lokaljobs.api

import android.content.Context
import androidx.room.Room
import com.example.lokaljobs.AppDatabase
import com.example.lokaljobs.JobDao
import com.example.lokaljobs.viewmodel.JobsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://testapi.getlokalapp.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideJobsApi(retrofit: Retrofit): JobsInterface =
        retrofit.create(JobsInterface::class.java)


    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideJobDao(appDatabase: AppDatabase): JobDao = appDatabase.jobDao()

    @Provides
    @Singleton
    fun provideJobsRepository(api: JobsInterface, jobDao: JobDao): JobsRepository =
        JobsRepository(api, jobDao)
}
