package com.example.english_dictionary.presentation.screen.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.english_dictionary.presentation.navigation.WordArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WordDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val wordId: WordArgs = WordArgs(savedStateHandle)

}