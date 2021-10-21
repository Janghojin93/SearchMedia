package com.kbank.search.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.kbank.search.databinding.ActivitySplashBinding
import com.kbank.search.model.State
import com.kbank.search.ui.base.BaseActivity
import com.kbank.search.ui.search.SearchActivity
import com.kbank.search.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


@AndroidEntryPoint
class SplashActivity : BaseActivity<SplashViewModel, ActivitySplashBinding>() {

    private val activityScope = CoroutineScope(Dispatchers.Main)
    override val mViewModel: SplashViewModel by viewModels()
    override fun getViewBinding() = ActivitySplashBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mViewBinding.root)

        activityScope.launch {
            delay(500)
            val intent = Intent(this@SplashActivity, SearchActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}

