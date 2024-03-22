package com.example.androidbaseproject.ui

import com.example.androidbaseproject.R
import com.example.androidbaseproject.arch.extensions.replaceFragment
import com.example.androidbaseproject.arch.extensions.viewBinding
import com.example.androidbaseproject.databinding.ActivityMainBinding
import com.example.androidbaseproject.ui.base.BaseActivity
import com.example.androidbaseproject.ui.coin.CoinFragment

class MainActivity : BaseActivity(R.layout.activity_main) {
    private val binding by viewBinding(ActivityMainBinding::inflate)

    override fun initialize() {
        replaceFragment(R.id.nav_host_fragment, CoinFragment.newInstance())
    }
}