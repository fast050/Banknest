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
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.example.basicbankingapp.BankApplication
import com.example.basicbankingapp.R
import com.example.basicbankingapp.adapter.UsersAdapter
import com.example.basicbankingapp.data.DataProvider
import com.example.basicbankingapp.data.UserDao
import com.example.basicbankingapp.databinding.FragmentHomeBinding
import com.example.basicbankingapp.databinding.FragmentListCustomerBinding
import com.example.basicbankingapp.model.Transfers
import com.google.android.material.appbar.AppBarLayout
import kotlinx.coroutines.launch


class ListCustomerFragment : Fragment() {

    private var _binding : FragmentListCustomerBinding?=null
    private val binding : FragmentListCustomerBinding get() = _binding!!
    private val sharedViewModel by viewModels<UsersViewModel>{
        UsersViewModel.UserViewModelFactory( (requireActivity().application as BankApplication).dao)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        requireActivity().findViewById<AppBarLayout>(R.id.appBarLayout2).visibility = View.VISIBLE
        requireActivity().findViewById<CardView>(R.id.profile_image_container_toolbar).visibility =View.GONE

        _binding = FragmentListCustomerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.apply {
            val adapter = UsersAdapter { user ->
                Toast.makeText(requireContext(), user.userName, Toast.LENGTH_SHORT).show()
                findNavController().navigate(ListCustomerFragmentDirections.actionCustomerListFragmentToUserDetailFragment3(user))
            }

            customersRecycleView.adapter = adapter

            sharedViewModel.users.observe(viewLifecycleOwner)
            {
                adapter.submitList(it)
            }

        }


    }


}