package com.fiap.gcs.githubaac.di.modules

import android.app.Application
import android.arch.persistence.room.Room
import com.fiap.gcs.githubaac.data.local.MyDatabase
import com.fiap.gcs.githubaac.data.local.dao.UserDao
import com.fiap.gcs.githubaac.data.local.repositories.UserRepository
import com.fiap.gcs.githubaac.data.remote.UserWebService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule{
    @Provides
    @Singleton
    fun provideDatabase(application: Application):MyDatabase{
        return Room.databaseBuilder(application,
                                    MyDatabase::class.java,
                                   "meuqueridobanco.db").build()
    }

    @Provides
    @Singleton
    fun provideUserDao(database: MyDatabase) : UserDao{
        return database.userDao()
    }

    @Provides
    @Singleton
    fun provideExecutor():Executor{
        return Executors.newSingleThreadExecutor()
    }

    @Provides
    @Singleton
    fun ProvideGson():Gson{
        return GsonBuilder().create()
    }

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson):Retrofit{
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl("http://api.github.com").build()
    }

    @Provides
    @Singleton
    fun ProvideUserWebService(retrofit: Retrofit) : UserWebService{
        return retrofit.create(UserWebService::class.java)
    }

    @Provides
    @Singleton
    fun provideUserRepository(userWebService: UserWebService, userDao: UserDao, executor: Executor) : UserRepository{
        return UserRepository(userWebService, userDao, executor)
    }
}