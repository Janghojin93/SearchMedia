package com.kbank.search.ui.search

import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.kbank.search.databinding.ActivitySearchBinding
import com.kbank.search.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SearchActivity : BaseActivity<SearchViewModel, ActivitySearchBinding>() {

    override val mViewModel: SearchViewModel by viewModels()

    override fun getViewBinding() = ActivitySearchBinding.inflate(layoutInflater)
    val tabLayoutTextArray = arrayOf("검색", "보관함")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mViewBinding.root)
        initView()
    }

    private fun initView() {
        mViewBinding.apply {
            viewmodel = mViewModel
            lifecycleOwner = this@SearchActivity
        }

        var pagerAdapter = ScreenSlidePagerAdapter(this)
        mViewBinding.pager.adapter = pagerAdapter
        mViewBinding.pager.isUserInputEnabled = false


        TabLayoutMediator(mViewBinding.tabs, mViewBinding.pager) { tab, position ->
            tab.text = tabLayoutTextArray[position]
        }.attach()

    }


    private inner class ScreenSlidePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = 2
        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> SearchFragment()
                else -> SaveListFragment()
            }
        }
    }

    override fun onBackPressed() {
        if (mViewBinding.pager.currentItem == 0) {
            super.onBackPressed()
        } else {
            mViewBinding.pager.currentItem = mViewBinding.pager.currentItem - 1
        }
    }

}