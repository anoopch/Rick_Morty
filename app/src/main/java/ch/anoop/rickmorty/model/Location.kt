package ch.anoop.rickmorty.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Location(
    @NonNull @PrimaryKey val id: String,
    val name: String,
    val type: String,
    val dimension: String,
    val residentsList: List<Character>,
    val created: String
)
