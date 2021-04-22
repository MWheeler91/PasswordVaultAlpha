package com.example.passwordvault.data

//import AccountDao
import androidx.lifecycle.LiveData

// a repository class abstracts access to multiple data sources.
// this repo is not part of the architecture components libraries,
// but is suggested best practice for code separation and architecture.

class AccountRepository(private val accountDao: AccountDao) {
    // list of AccountInfo Objects
    val getAll: LiveData<List<Account>> = accountDao.getAll()


    suspend fun addAccount(account: Account){
        accountDao.addAccount(account)
    }

}