package com.rockspace.myapplication.Model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val firstName: String = "",
    val lastName: String = "",
    val profileImage: String = "",
    val district: String = "",
    val email: String = "",
    val interestedSport: ArrayList<String> = ArrayList(),
    val currentPoints: Int = 0,
    val currentLevel: Int = 0,
    val achievedBadges: ArrayList<String> = ArrayList()
) : Parcelable