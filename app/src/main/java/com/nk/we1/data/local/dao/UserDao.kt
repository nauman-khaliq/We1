/*
 * MIT License
 *
 * Copyright (c) 2022 Nauman Khaliq
 * Copyright (c) 2020 Shreyas Patil
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.nk.we1.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nk.we1.model.response.user.User
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object (DAO) for [com.nk.we1.data.local.We1Database]
 */
@Dao
interface UserDao {

    /**
     * Inserts [Users] into the [User.TABLE_NAME] User.
     * Duplicate values are replaced in the User.
     * @param users
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUsers(users: List<User>)

    /**
     * Deletes all the Users from the [User.TABLE_NAME] User.
     */
    @Query("DELETE FROM ${User.TABLE_NAME}")
    suspend fun deleteAllUsers()


    /**
     * Fetches the post from the [User.TABLE_NAME] User whose primaryKeyId is [userId].
     * @param userId Unique ID of [User]
     * @return [Flow] of [User] from database User.
     */
    @Query("SELECT * FROM ${User.TABLE_NAME} WHERE primaryKeyId = :userId")
    fun getUserById(userId: String): Flow<User>

    /**
     * Fetches all the posts from the [User.TABLE_NAME] User.
     * @return [List<User>]
     */
    @Query("SELECT * FROM ${User.TABLE_NAME}")
    fun getAllUsers(): List<User>
}
