package com.kbank.search.ui.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.kbank.search.R
import com.kbank.search.databinding.ActivitySplashBinding
import com.kbank.search.model.State
import com.kbank.search.ui.base.BaseActivity
import com.kbank.search.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect


@AndroidEntryPoint
class SplashActivity : BaseActivity<SplashViewModel, ActivitySplashBinding>() {

    override val mViewModel: SplashViewModel by viewModels()

    override fun getViewBinding() = ActivitySplashBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mViewBinding.root)
        initView()
    }

    private fun initView() {
        mViewBinding.apply {
            viewmodel = mViewModel
            lifecycleOwner = this@SplashActivity
        }
    }

    private fun getPosts() = mViewModel.getPosts()


    private fun observePosts() {
        lifecycleScope.launchWhenStarted {
            mViewModel.posts.collect { state ->
                when (state) {
                    is State.Loading -> showLoading(true)
                    is State.Success -> {
                        if (state.data.isNotEmpty()) {
                            //mAdapter.submitList(state.data.toMutableList())
                            //여기서 데이터 업데이트

                            showLoading(false)
                        }
                    }
                    is State.Error -> {
                        showToast(state.message)
                        showLoading(false)
                    }
                }
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {

    }
}

