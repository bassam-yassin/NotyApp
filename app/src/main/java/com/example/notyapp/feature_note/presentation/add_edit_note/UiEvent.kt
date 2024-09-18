package com.example.notyapp.feature_note.presentation.add_edit_note

sealed class UiEvent {
    data class ShowSnackBar(val message: String): UiEvent()
    object SaveNote: UiEvent()
}