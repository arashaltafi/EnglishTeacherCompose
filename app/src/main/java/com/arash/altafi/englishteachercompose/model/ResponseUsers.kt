package com.arash.altafi.englishteachercompose.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResponseUsers(
    val id: Int,
    val name: String,
    val username: String,
    val email: String,
    val phone: String,
    val website: String,
    val company: Company
) : Parcelable

@Parcelize
data class Company(
    val name: String,
    val catchPhrase: String,
    val bs: String
) : Parcelable