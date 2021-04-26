package ch.anoop.rickmorty.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Episode(
    @NonNull @PrimaryKey val id: String,
    val name: String,
    val episodeName: String,
    val air_date: String,
    val created: String,
    val characters: List<Character>
)
