package com.rockspace.myapplication.Model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CommunityPost(
    val title: String = "",
    val author: String = "",
    val description:String = "",
    val postDateTime: String = "",
    val district: String = "",
    val category: String = "",
    val totalTeam: Int = 0,
    val numberOfPeopleJoined: Int = 0,
    val location: String = "",
    val eventDateTime: String = "",
    val allowPhoneNumber: String = "",
    val phoneNumberMasked: String = "",
    val phoneNumberUnmasked: String = "",
    val pendingInvite: ArrayList<String> = ArrayList(),
    val confirmInvite: ArrayList<String> = ArrayList()
): Parcelable
