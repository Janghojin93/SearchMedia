package com.kbank.search.ui.base

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.kbank.search.R
import com.kbank.search.utils.NetworkUtils

abstract class BaseActivity<VM : ViewModel, VB : ViewBinding>  : AppCompatActivity() {

    protected abstract val mViewModel: VM

    protected lateinit var mViewBinding: VB

    protected abstract fun getViewBinding(): VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewBinding = getViewBinding()
    }



}