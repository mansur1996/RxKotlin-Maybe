package com.example.rxkotlin.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.rxkotlin.entity.User
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
interface UserDao {

    @Insert
    fun addUser(user: User) : Single<Long>

    @Query("select * from user_table")
    fun getAllUsers() : Flowable<List<User>>

    @Query("select * from user_table where id =:id")
    fun getUserById(id : Int) : Maybe<User>
}