package com.example.basicbankingapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.basicbankingapp.BankApplication
import com.example.basicbankingapp.R
import com.google.android.material.appbar.AppBarLayout
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SuccessfulScreenFragment : Fragment() {


    private val sharedViewModel :UsersViewModel by activityViewModels {
        UsersViewModel.UserViewModelFactory((requireActivity().application as BankApplication).dao)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        requireActivity().findViewById<AppBarLayout>(R.id.appBarLayout2).visibility = View.GONE
        return inflater.inflate(R.layout.fragment_successfull_screen, container, false)
    }

    override fun onResume() {
        super.onResume()


        lifecycleScope.launch{
            delay(3000)
            findNavController().navigate(R.id.action_successfullScreenFragment_to_customerListFragment)
        }

        sharedViewModel.setUserSelected(null)
    }

}