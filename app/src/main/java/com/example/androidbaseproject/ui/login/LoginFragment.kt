package com.example.androidbaseproject.ui.login

import android.os.Bundle
import com.example.androidbaseproject.R
import com.example.androidbaseproject.arch.extensions.viewBinding
import com.example.androidbaseproject.databinding.FragmentLoginBinding
import com.example.androidbaseproject.ui.base.BaseFragment
import com.example.androidbaseproject.ui.base.BaseViewModel

class LoginFragment : BaseFragment(R.layout.fragment_login) {
    private val binding by viewBinding(FragmentLoginBinding::bind)

    override fun getViewModel(): BaseViewModel? {
        return null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    companion object {
        fun newInstance() = LoginFragment()
    }
} 