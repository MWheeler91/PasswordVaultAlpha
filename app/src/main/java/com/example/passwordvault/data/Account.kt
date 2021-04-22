package com.example.passwordvault.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// Table data class also called an Entity
@Entity(tableName = "account_info")
data class Account(
    @PrimaryKey(autoGenerate = true)  var id: Int,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "username") var username: String,
    @ColumnInfo(name = "password") var password: String,
    @ColumnInfo(name = "website") var website: String
)
