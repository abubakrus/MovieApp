package com.example.movieapp.data.cloud.models.actors


import android.os.Parcelable
import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ActorsResponse(
    @SerializedName("cast")
    val peopleCloud: List<PeopleCloud>,
    @SerializedName("crew")
    val crewCloud: List<PeopleCloud>,
    @SerializedName("id")
    val id: Int
) : Parcelable {
    companion object {
        val unknown = ActorsResponse(
            peopleCloud = emptyList(),
            crewCloud = emptyList(),
            id = -1
        )
    }
}