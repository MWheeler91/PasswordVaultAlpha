package com.example.passwordvault.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.passwordvault.data.Account

// class with all functions to insert, edit, delete, update

@Dao
interface AccountDao  {
    @Query("SELECT * FROM account_info ORDER BY title ASC")
    // gets the whole database and stores it in a list
    fun getAll(): LiveData<List<Account>>

    // if conflict happens, ignore insert
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    // Using the account with the AccountInfo table(Entity) class
    suspend fun addAccount(account: Account)

//    @Query("SELECT * FROM account_info WHERE title LIKE :title")
//    fun findByTitle(title: String): Entity
//
//    @Delete
//    fun delete(todo: Entity)
//
//    @Update
//    fun updateTodo(vararg todos: Entity)


    //@Insert(onConflict = OnConflictStrategy.IGNORE)
    //suspend fun addAccount(user: User)



}