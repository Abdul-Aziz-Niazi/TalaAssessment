package com.abdulaziz.tala.home.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.abdulaziz.tala.databinding.FragmentMainBinding
import com.abdulaziz.tala.home.MainActivity
import com.abdulaziz.tala.home.data.LoanResponseItem
import com.abdulaziz.tala.home.data.LocaleData
import com.abdulaziz.tala.home.domain.MainActivityViewModel
import com.abdulaziz.tala.managers.navigate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {
    private var localeData: LocaleData? = null
    private lateinit var binding: FragmentMainBinding
    private val mainActivityViewModel: MainActivityViewModel by activityViewModels()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
    }

    private fun observeData() {
        mainActivityViewModel.getLocaleData().observe(viewLifecycleOwner) { localeData ->
            this.localeData = localeData
        }
        mainActivityViewModel.getLoanData().observe(viewLifecycleOwner) {
            val adapter = DataAdapter()
            adapter.data = it
            binding.itemsRV.adapter = adapter
            adapter.onItemClick = { loanItem ->
                loanItem.currency = mainActivityViewModel.getCurrencyForLoanData(loanItem,localeData)
                navigate(MainFragmentDirections.mainToLoanStatus(loanItem))
            }
        }
    }


}