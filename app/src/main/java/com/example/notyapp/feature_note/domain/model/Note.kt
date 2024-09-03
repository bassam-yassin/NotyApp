package com.example.notyapp.feature_note.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.notyapp.ui.theme.BabyBlue
import com.example.notyapp.ui.theme.LightBlue
import com.example.notyapp.ui.theme.LightGreen
import com.example.notyapp.ui.theme.RedOrange
import com.example.notyapp.ui.theme.RedPink

@Entity
data class Note(
    @PrimaryKey val id: Int? = null,
    val title: String,
    val content: String,
    val timestamp: Long,
    val color: Int,
){
    companion object{
        val noteColors = listOf(
            RedOrange, RedPink, BabyBlue, LightBlue, LightGreen
        )
    }
}

class InValidNoteException(message: String): Exception(message)
