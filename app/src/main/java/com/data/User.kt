package com.data

import android.provider.ContactsContract.CommonDataKinds.Email

data class User(
    val userId: String,
    val name: String,
    val avatarUrl: String?,
    val age: Int,
    val score: Int,
    val registerDate: String,
    val email: String,
    val bio: String,

)
