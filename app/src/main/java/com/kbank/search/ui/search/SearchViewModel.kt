package com.kbank.search.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kbank.search.data.remote.model.DocumentImageResponse
import com.kbank.search.data.repository.MediaRepository
import com.kbank.search.model.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class SearchViewModel @Inject constructor(private val mediaRepository: MediaRepository) :
    ViewModel() {

    private val _posts: MutableStateFlow<State<List<DocumentImageResponse>>> = MutableStateFlow(
        State.loading()
    )

    val posts: StateFlow<State<List<DocumentImageResponse>>> = _posts

    fun getPosts(query: String) {
        viewModelScope.launch {
            mediaRepository.getAllMedias(query)
                .map { resource ->
                    State.fromResource(resource)
                }.collect { state -> _posts.value = state }
        }
    }

}