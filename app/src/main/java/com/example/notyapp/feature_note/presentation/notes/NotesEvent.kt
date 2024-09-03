package com.example.notyapp.feature_note.presentation.notes

import com.example.notyapp.feature_note.domain.model.Note
import com.example.notyapp.feature_note.domain.util.OrderNote

sealed class NotesEvent {
    data class Order(val orderNote: OrderNote): NotesEvent()
    data class Delete(val note: Note): NotesEvent()
    object RestoreNote: NotesEvent()
    object ToggleOrderSection: NotesEvent()
}