package com.kbank.search.ui.search

import android.util.Log
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.kbank.search.R
import com.kbank.search.databinding.FragmentSearchBinding
import com.kbank.search.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import com.kbank.search.model.State


@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search) {

    private val searchViewModel: SearchViewModel by activityViewModels()

    companion object {
        @JvmStatic
        fun newInstance() = SearchFragment()
    }


    private fun getPosts() = searchViewModel.getPosts("안뇽")
    private fun observePosts() {
        lifecycleScope.launchWhenStarted {
            searchViewModel.posts.collect {  state ->
                when (state) {
                    is State.Loading -> {

                    }
                    is State.Success -> {
                        if (state.data.isNotEmpty()) {
                            //mAdapter.submitList(state.data.toMutableList())
                            //여기서 데이터 업데이트
                            Log.d("tetasdasd","받아온 데이터::"+ state.data)

                           // showLoading(false)
                        }
                    }
                    is State.Error -> {
//                        showToast(state.message)
//                        showLoading(false)
                    }
                }

            }
        }
    }

}