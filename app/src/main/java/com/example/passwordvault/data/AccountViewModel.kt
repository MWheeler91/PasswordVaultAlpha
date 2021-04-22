package com.example.passwordvault.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// The View Model is to provide data to the UI and survive configuration changes.
// A viewmodel acts as a communication center between the repo and the UI

class AccountViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Account>> // list of all account objects
    private val repository: AccountRepository  // repo var of time account repo

    init{
        //pulls the account database from the AccountDataBase Class.
        // If there is no database currently created then it creates a new one.
        val accountDoa = AccountDatabase.getDatabase(application).AccountDao()
        // creates a repo with the DAO
        repository = AccountRepository(accountDoa)
        // uses the readalldata object created above and runs the getall function from the repo
        // which ultimately traces back to the Account Data Access Object
        readAllData = repository.getAll
    }

    fun addAccount(account: Account){
        viewModelScope.launch(Dispatchers.IO){
            repository.addAccount(account)
        }
        
    }
}