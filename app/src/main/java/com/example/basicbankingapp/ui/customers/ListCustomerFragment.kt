package com.example.basicbankingapp.ui.customers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.transition.TransitionInflater
import com.example.basicbankingapp.R
import com.example.basicbankingapp.adapter.UsersAdapter
import com.example.basicbankingapp.databinding.FragmentListCustomerBinding
import com.example.basicbankingapp.ui.UsersViewModel
import com.google.android.material.appbar.AppBarLayout
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ListCustomerFragment : Fragment() {

    private var _binding : FragmentListCustomerBinding?=null
    private val binding : FragmentListCustomerBinding get() = _binding!!
    private val sharedViewModel by viewModels<UsersViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentListCustomerBinding.inflate(inflater, container, false)
        //for animations
        sharedElementReturnTransition = TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //hide the toolbar image , and show the toolbar
        requireActivity().findViewById<AppBarLayout>(R.id.appBarLayout2).visibility = View.VISIBLE
        requireActivity().findViewById<CardView>(R.id.profile_image_container_toolbar).visibility =View.GONE

        binding.apply {

            // When user hits back button transition takes backward
            postponeEnterTransition()
            binding.customersRecycleView.doOnPreDraw {
                startPostponedEnterTransition()
            }


            val adapter = UsersAdapter { user, imageView ->

                val direction =
                    ListCustomerFragmentDirections.actionCustomerListFragmentToUserDetailFragment3(
                        user
                    )

                val extras = FragmentNavigatorExtras(
                    imageView to "child_support",
                )

                findNavController().navigate(direction, extras)

            }

            customersRecycleView.adapter = adapter

            sharedViewModel.users.observe(viewLifecycleOwner)
            {
                adapter.submitList(it)

                (view.parent as? ViewGroup)?.doOnPreDraw {
                    startPostponedEnterTransition()
                }
            }

        }


    }


}