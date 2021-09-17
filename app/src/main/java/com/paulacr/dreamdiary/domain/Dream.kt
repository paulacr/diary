package com.paulacr.dreamdiary.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.time.LocalDateTime

@Parcelize
data class Dream(
    val id: Long,
    val dateTime: LocalDateTime,
    val description: String,
    val emoji: Int? = null
): Parcelable