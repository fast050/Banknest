package com.example.basicbankingapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.viewModels
import androidx.navigation.compose.rememberNavController
import androidx.navigation.fragment.findNavController
import com.example.basicbankingapp.BankApplication
import com.example.basicbankingapp.R
import com.example.basicbankingapp.ui.Theme.ComposeBanknessAppTheme
import com.example.basicbankingapp.ui.homeUI.HomePage
import com.google.android.material.appbar.AppBarLayout


class HomeFragment : Fragment() {

    private lateinit var composeView: ComposeView
    private val sharedViewModel by viewModels<UsersViewModel> {
        UsersViewModel.UserViewModelFactory((requireActivity().application as BankApplication).dao)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).also {
            composeView = it
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //hide the toolbar image , and show the toolbar
        requireActivity().findViewById<AppBarLayout>(R.id.appBarLayout2).visibility = View.VISIBLE
        requireActivity().findViewById<CardView>(R.id.profile_image_container_toolbar).visibility =View.GONE

        composeView.setContent {
            // In Compose world
            ComposeBanknessAppTheme {
                val navHostController = rememberNavController()
                BanknestNavHost(
                    navHostController = navHostController,
                    shardViewModel = sharedViewModel
                ) {
                    findNavController().navigate(
                        HomeFragmentDirections.actionHomeFragmentToUserDetailFragment(
                            it
                        )
                    )
                }
            }
        }
    }
}