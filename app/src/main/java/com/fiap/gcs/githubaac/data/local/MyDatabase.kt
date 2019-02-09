package com.fiap.gcs.githubaac.data.local

import android.arch.persistence.room.*
import com.fiap.gcs.githubaac.data.local.converter.DateConverter
import com.fiap.gcs.githubaac.data.local.dao.UserDao
import com.fiap.gcs.githubaac.data.local.entity.User

@Database(entities = [User::class], version = 1)
@TypeConverters(DateConverter::class)
abstract class MyDatabase : RoomDatabase(){

    abstract fun userDao(): UserDao

    companion object {
        private  val INSTANCE : MyDatabase? = null
    }
}