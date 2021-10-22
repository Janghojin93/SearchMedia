package com.kbank.search.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.kbank.search.R
import com.kbank.search.databinding.FragmentSaveListBinding
import com.kbank.search.databinding.FragmentSearchBinding
import com.kbank.search.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SaveListFragment : BaseFragment<FragmentSaveListBinding>(R.layout.fragment_save_list) {
    private val searchViewModel: SearchViewModel by activityViewModels()


    companion object {
        @JvmStatic
        fun newInstance() = SaveListFragment()
    }



}