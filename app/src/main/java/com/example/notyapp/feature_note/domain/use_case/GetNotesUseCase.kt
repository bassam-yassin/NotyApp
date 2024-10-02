package com.example.notyapp.feature_note.domain.use_case

import com.example.notyapp.feature_note.domain.model.Note
import com.example.notyapp.feature_note.domain.repository.NoteRepository
import com.example.notyapp.feature_note.domain.util.OrderNote
import com.example.notyapp.feature_note.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetNotesUseCase(
    private val repository: NoteRepository
) {
    operator fun invoke(
        orderNote: OrderNote = OrderNote.Date(OrderType.Descending)
    ): Flow<List<Note>>{
        return repository.getAllNotes().map { notes ->
            when(orderNote.orderType){
                is OrderType.Ascending -> {
                    when(orderNote){
                        is OrderNote.Title -> notes.sortedBy { it.title.lowercase() }
                        is OrderNote.Date -> notes.sortedBy { it.timestamp }
                        is OrderNote.Color -> notes.sortedBy { it.color }
                    }
                }
                is OrderType.Descending -> {
                    when(orderNote){
                        is OrderNote.Title -> notes.sortedByDescending { it.title.lowercase() }
                        is OrderNote.Date -> notes.sortedByDescending { it.timestamp }
                        is OrderNote.Color -> notes.sortedByDescending { it.color }
                    }
                }
            }
        }
    }
}