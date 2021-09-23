package com.paulacr.datamodule.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.paulacr.dreamdiary.data.model.BaseModel
import kotlinx.android.parcel.Parcelize
import java.time.LocalDateTime

@Parcelize
@Entity
data class DreamEntity(
    @PrimaryKey(autoGenerate = true) var id: Long? = 0L,
    val dateTime: LocalDateTime,
    val description: String,
    val emoji: Int? = null
): Parcelable, BaseModel()