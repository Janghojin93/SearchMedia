package com.kbank.search.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kbank.search.data.repository.MediaRepository
import com.kbank.search.model.Media
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
class SplashViewModel @Inject constructor(private val mediaRepository: MediaRepository) :
    ViewModel() {


    private val _posts: MutableStateFlow<State<List<Media>>> = MutableStateFlow(State.loading())

    val posts: StateFlow<State<List<Media>>> = _posts

    fun getPosts() {
        viewModelScope.launch {
            mediaRepository.getAllMedias()
//                .filter {
//                    var ggg = Gson()
//                    var asdasd  = ggg.toJson(it)
//
//                    Log.d("as1232dasd",""+asdasd)
//                    true
//                }
                .map { resource ->
                    State.fromResource(resource)
                }.collect { state -> _posts.value = state }
        }
    }

}