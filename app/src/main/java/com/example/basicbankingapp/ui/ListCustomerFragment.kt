package com.example.basicbankingapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.basicbankingapp.BankApplication
import com.example.basicbankingapp.R
import com.example.basicbankingapp.ui.listCustomerUI.BankCustomersList
import com.google.android.material.appbar.AppBarLayout


class ListCustomerFragment : Fragment() {
    private lateinit var composeView: ComposeView
    private val sharedViewModel by viewModels<UsersViewModel> {
        UsersViewModel.UserViewModelFactory((requireActivity().application as BankApplication).dao)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        composeView = ComposeView(requireContext())
        return composeView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        composeView.setContent {
            BankCustomersList(shareViewModel = sharedViewModel) {

            }
        }

    }


}