package com.example.basicbankingapp.ui

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.compose.ui.platform.ComposeView
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.transition.TransitionInflater
import com.example.basicbankingapp.BankApplication
import com.example.basicbankingapp.R
import com.example.basicbankingapp.adapter.UsersAdapter
import com.example.basicbankingapp.data.DataProvider
import com.example.basicbankingapp.data.UserDao
import com.example.basicbankingapp.databinding.FragmentListCustomerBinding
import com.example.basicbankingapp.databinding.UserItemBinding
import com.example.basicbankingapp.model.Transfers
import com.example.basicbankingapp.model.User
import com.example.basicbankingapp.ui.listCustomerUI.BankCustomersList
import com.google.android.material.appbar.AppBarLayout
import kotlinx.coroutines.launch


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
        //hide the toolbar image , and show the toolbar
        requireActivity().findViewById<AppBarLayout>(R.id.appBarLayout2).visibility = View.VISIBLE
        requireActivity().findViewById<CardView>(R.id.profile_image_container_toolbar).visibility =View.GONE

        composeView.setContent {
            BankCustomersList(shareViewModel = sharedViewModel) {
                findNavController().navigate(
                    ListCustomerFragmentDirections.actionCustomerListFragmentToUserDetailFragment3(
                        it
                    )
                )
            }
        }

    }


}