package com.kbank.search.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.kbank.search.R
import com.kbank.search.databinding.FragmentSearchBinding
import com.kbank.search.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


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

        return mRootView
    }

    companion object {
        @JvmStatic
        fun newInstance() = SearchFragment()
    }
}