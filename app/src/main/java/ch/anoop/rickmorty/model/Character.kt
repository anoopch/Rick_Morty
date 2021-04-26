package ch.anoop.rickmorty.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Character(
    @NonNull @PrimaryKey val id: String,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: Location,
    val location: Location,
    val image: String,
    val episodes: List<Episode>,
    val created: String
)
