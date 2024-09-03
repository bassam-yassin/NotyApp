package com.example.notyapp.feature_note.presentation.notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notyapp.feature_note.domain.model.Note
import com.example.notyapp.feature_note.domain.use_case.NoteUseCases
import com.example.notyapp.feature_note.domain.util.OrderNote
import com.example.notyapp.feature_note.domain.util.OrderType
import com.example.notyapp.feature_note.presentation.notes.NotesEvent
import com.example.notyapp.feature_note.presentation.notes.NotesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val notesUseCases: NoteUseCases
): ViewModel(){

    private val _state = mutableStateOf(NotesState())
    val state: State<NotesState> = _state

    private var recentlyDeletedNote: Note? = null

    private var getRecentlyJob : Job? = null

    init {
        getNotes(OrderNote.Date(OrderType.Descending))
    }
    fun onEvent(event: NotesEvent){
        when(event){
            is NotesEvent.Order -> {
                if(state.value.orderNote::class == event.orderNote::class &&
                    state.value.orderNote.orderType == event.orderNote.orderType){
                    return
                }
                getNotes(event.orderNote)

            }
            is NotesEvent.Delete -> {
                viewModelScope.launch {
                    notesUseCases.deleteNoteUseCase(event.note)
                    recentlyDeletedNote = event.note
                }
            }
            is NotesEvent.RestoreNote -> {
                viewModelScope.launch {
                    notesUseCases.addNoteUseCase(recentlyDeletedNote ?: return@launch)
                    recentlyDeletedNote = null

                }
            }
            is NotesEvent.ToggleOrderSection -> {
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }
        }
    }

    private fun getNotes(orderNote: OrderNote) {
        getRecentlyJob?.cancel()
        getRecentlyJob = notesUseCases.getNotesUseCase(orderNote).onEach { notes ->
            _state.value = state.value.copy(notes = notes)
        }.launchIn(viewModelScope)
    }

}