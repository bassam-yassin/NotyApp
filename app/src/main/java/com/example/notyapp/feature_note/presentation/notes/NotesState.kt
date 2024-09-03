package com.example.notyapp.feature_note.presentation.notes

import com.example.notyapp.feature_note.domain.model.Note
import com.example.notyapp.feature_note.domain.util.OrderNote
import com.example.notyapp.feature_note.domain.util.OrderType

data class NotesState(
    val notes: List<Note> = emptyList(),
    val orderNote: OrderNote = OrderNote.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false,
)
