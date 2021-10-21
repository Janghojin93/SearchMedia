package com.kbank.search.ui.search

import androidx.lifecycle.ViewModel
import com.kbank.search.data.repository.MediaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class SearchViewModel @Inject constructor(private val mediaRepository: MediaRepository) :
    ViewModel() {
}