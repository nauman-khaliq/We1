/*
 * MIT License
 *
 * Copyright (c) 2022 Nauman Khaliq
 *
 */

package com.nk.we1.data.repository

import com.nk.we1.data.local.dao.UserDao
import com.nk.we1.data.remote.api.We1Service
import com.nk.we1.model.response.BaseResponse
import com.nk.we1.model.response.user.User
import com.nk.we1.utils.PreferenceHelper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

interface HomeRepository {
    /**
     * Fetches random users list from either server or database
     * @return Flow<Resource<List<User>>>
     */
    fun getRandomUsers(): Flow<Resource<List<User>>>
}

/**
 * Singleton repository for fetching data from remote and storing it in database
 * for offline capability. This is Single source of data.
 */
@ExperimentalCoroutinesApi
class DefaultHomeRepository @Inject constructor(
    private val userDao: UserDao,
    private val preferenceHelper: PreferenceHelper,
    private val we1Service: We1Service
) : HomeRepository {

    override fun getRandomUsers(): Flow<Resource<List<User>>> {
        return object : NetworkAndDbBoundRepository<List<User>, List<User>>() {
            override suspend fun saveRemoteData(response: List<User>) {
                userDao.deleteAllUsers()
                userDao.addUsers(response)
            }
            override fun fetchFromLocal(): List<User> = userDao.getAllUsers()
            override suspend fun fetchFromRemote(): Response<BaseResponse<List<User>>> =
                we1Service.getRandomUsers()
        }.asFlow()
    }

}
