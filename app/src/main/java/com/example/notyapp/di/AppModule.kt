package com.example.notyapp.di

import android.app.Application
import androidx.room.Room
import com.example.notyapp.feature_note.data.data_source.NoteDatabase
import com.example.notyapp.feature_note.data.repository.NoteRepositoryImpl
import com.example.notyapp.feature_note.domain.repository.NoteRepository
import com.example.notyapp.feature_note.domain.use_case.DeleteNoteUseCase
import com.example.notyapp.feature_note.domain.use_case.GetNotesUseCase
import com.example.notyapp.feature_note.domain.use_case.NoteUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import javax.inject.Singleton

@Module
@InstallIn(Singleton::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDataBase(app: Application): NoteDatabase{
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            NoteDatabase.DATABASE_NAME
        ).build()
    }
    @Provides
    @Singleton
    fun provideNoteRepository(database: NoteDatabase) : NoteRepository {
        return NoteRepositoryImpl(database.noteDao)
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: NoteRepository): NoteUseCases {
        return NoteUseCases(
            getNotesUseCase = GetNotesUseCase(repository),
            deleteNoteUseCase = DeleteNoteUseCase(repository)
        )
    }
}