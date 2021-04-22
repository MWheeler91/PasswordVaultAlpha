package com.example.passwordvault.data

//import DAO
//import AccountDao
import android.content.Context

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.passwordvault.data.Account
import com.example.passwordvault.data.AccountDao


// The actual database
// Contains the database holder and serves as the main access point for hte underlying connection
// to your app's persisted, relational data
@Database(
    entities = [Account::class],  // the table or tables if there are more than one
    version = 1,                       // version number
    exportSchema = true
)

abstract class AccountDatabase : RoomDatabase(){
    // function that will return the Account Data Access Object
    abstract fun AccountDao(): AccountDao

    // companion object instant var initial value = 0
    // the get databae function checks if the database exist, if so return it
    // else the synchronized block creates a new instance
    // visable to other classes
    companion object {
        @Volatile private var INSTANCE: AccountDatabase? = null
        private val LOCK = Any()

//        operator fun invoke(context: Context)= INSTANCE ?: synchronized(LOCK){
//            INSTANCE ?: getDatabase(context).also { INSTANCE = it}
//        }

        fun getDatabase(context: Context): AccountDatabase{
            val tempInstance = INSTANCE
            // if temp instance is not null then return the instance
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AccountDatabase::class.java,
                    "Passwords").build()
                INSTANCE = instance
                return instance
            }
        }
    }
}