package com.fiap.gcs.githubaac.data.local.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import com.fiap.gcs.githubaac.data.local.entity.User
import java.util.*

@Dao
interface UserDao {

    @Insert(onConflict = REPLACE)
    fun save(user: User)

    @Query("SELECT * FROM User WHERE login = :login")
    fun load(login: String) : LiveData<User>

    @Query("SELECT * FROM User WHERE login = :login AND lastRefresh = :lastReflesh LIMIT 1")
    fun hasUser(login: String, lastReflesh: Date) : User
}