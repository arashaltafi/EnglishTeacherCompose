package com.arash.altafi.englishteachercompose.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResponsePaging(
    val id: String,
    val name: String,
    val family: String,
    val avatar: String
) : Parcelable