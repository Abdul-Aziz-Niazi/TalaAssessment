package com.abdulaziz.tala.home.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.abdulaziz.tala.databinding.FragmentLoanStatusBinding
import com.abdulaziz.tala.home.domain.LoanStatusViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoanStatusFragment: Fragment() {
    private lateinit var binding: FragmentLoanStatusBinding
    private val loanStatusViewModel: LoanStatusViewModel by viewModels()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentLoanStatusBinding.inflate(inflater, container, false)
        binding.viewModel = loanStatusViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}