package com.fiap.gcs.githubaac.data.local.repositories

import android.arch.lifecycle.LiveData
import com.fiap.gcs.githubaac.data.local.entity.User
import com.fiap.gcs.githubaac.data.remote.UserWebService
import com.fiap.gcs.githubaac.data.local.dao.*
import retrofit2.*
import java.util.*
import java.util.concurrent.Executor
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject
constructor(private val webservice: UserWebService, private val userDao: UserDao, private val executor: Executor) {

    fun getUser(userLogin: String): LiveData<User> {
        refreshUser(userLogin)
        return userDao.load(userLogin)
    }

    private fun refreshUser(userLogin: String) {
        executor.execute {

            val userExists = userDao.hasUser(userLogin, getMaxRefreshTime(Date())) != null

            if (!userExists) {
                webservice.getUser(userLogin).enqueue(object : Callback<User> {
                    override fun onResponse(call: Call<User>, response: Response<User>) {
                        executor.execute {
                            val user = response.body()
                            user?.lastRefresh = Date()
                            if (user != null)
                                userDao.save(user)
                        }
                    }

                    override fun onFailure(call: Call<User>, t: Throwable) {}
                })
            }
        }
    }

    private fun getMaxRefreshTime(currentDate: Date): Date {
        val cal = Calendar.getInstance()
        cal.time = currentDate
        cal.add(Calendar.MINUTE, -FRESH_TIMEOUT_IN_MINUTES)
        return cal.time
    }

    companion object {

        private const val FRESH_TIMEOUT_IN_MINUTES = 3
    }
}
