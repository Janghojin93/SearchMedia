package com.kbank.search.ui.search

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.kbank.search.R
import com.kbank.search.databinding.ActivitySearchBinding
import com.kbank.search.ui.base.BaseActivity
import com.kbank.search.utils.NetworkUtils
import com.kbank.search.utils.getColorRes
import com.kbank.search.utils.hide
import com.kbank.search.utils.show
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SearchActivity : BaseActivity<SearchViewModel, ActivitySearchBinding>() {

    override val mViewModel: SearchViewModel by viewModels()

    override fun getViewBinding() = ActivitySearchBinding.inflate(layoutInflater)
    private val tabLayoutTextArray = arrayOf("검색", "보관함")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mViewBinding.root)
        initView()
        handleNetworkChanges()
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
                0 -> SearchFragment.newInstance()
                else -> SaveListFragment.newInstance()
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


    private fun handleNetworkChanges() {
        NetworkUtils.getNetworkLiveData(applicationContext).observe(this) { isConnected ->
            if (!isConnected) {
                mViewBinding.textViewNetworkStatus.text = getString(R.string.no_text_connectivity)
                mViewBinding.networkStatusLayout.apply {
                    setBackgroundColor(getColorRes(R.color.colorStatusNotConnected))
                    animate()
                        .alpha(1f)
                        .setStartDelay(ANIMATION_DURATION)
                        .setDuration(ANIMATION_DURATION)
                        .setListener(object : AnimatorListenerAdapter() {
                            override fun onAnimationEnd(animation: Animator) {
                                show()
                            }
                        })
                }
            } else {
                mViewBinding.textViewNetworkStatus.text = getString(R.string.text_connectivity)
                mViewBinding.networkStatusLayout.apply {
                    setBackgroundColor(getColorRes(R.color.colorStatusConnected))
                    animate()
                        .alpha(0.01f)
                        .setStartDelay(ANIMATION_DURATION)
                        .setDuration(ANIMATION_DURATION)
                        .setListener(object : AnimatorListenerAdapter() {
                            override fun onAnimationEnd(animation: Animator) {
                                hide()
                            }
                        })
                }
            }
        }
    }

    companion object {
        const val ANIMATION_DURATION = 1000L
    }


}