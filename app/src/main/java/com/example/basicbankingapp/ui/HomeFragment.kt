package com.example.basicbankingapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.navigation.fragment.findNavController
import com.example.basicbankingapp.R
import com.example.basicbankingapp.ui.homeUI.ComposeBanknessAppTheme
import com.example.basicbankingapp.ui.homeUI.HomePage


class HomeFragment : Fragment() {

    private lateinit var composeView: ComposeView
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

        composeView.setContent {
            // In Compose world
            ComposeBanknessAppTheme {
                HomePage {
                    findNavController().navigate(R.id.action_homeFragment_to_customerListFragment3)
                }
            }
        }
    }
}