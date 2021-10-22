package com.kbank.search.ui.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.kbank.search.R
import com.kbank.search.databinding.FragmentSearchBinding
import com.kbank.search.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import com.kbank.search.model.State
import com.kbank.search.utils.showToast


@AndroidEntryPoint
class SearchFragment : BaseFragment() {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var mRootView: View
    override val layoutId = R.layout.fragment_search
    private val searchViewModel: SearchViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        mRootView = binding.root


        observePosts()
        getPosts()


        return mRootView
    }

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