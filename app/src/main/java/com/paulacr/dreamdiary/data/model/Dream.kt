package com.paulacr.dreamdiary.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.time.LocalDateTime

@Parcelize
@Entity
data class Dream(
    @PrimaryKey var id: Long,
    val dateTime: LocalDateTime,
    val description: String,
    val emoji: Int? = null
): Parcelable, BaseModel()