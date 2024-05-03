package com.example.basicbankingapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.basicbankingapp.R
import com.example.basicbankingapp.adapter.UsersAdapter
import com.example.basicbankingapp.data.DataProvider
import com.example.basicbankingapp.databinding.FragmentHomeBinding
import com.example.basicbankingapp.model.Testimony
import com.example.basicbankingapp.ui.UsersViewModel
import com.example.basicbankingapp.ui.homeUI.TestimonyItem
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {

    private var _binding:FragmentHomeBinding? =null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        binding.composeView?.apply {
            setContent {
                // In Compose world
                TestimonyItem(testimony = DataProvider.listOfComment[0])
            }
        }
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button3.setOnClickListener {


           findNavController().navigate(R.id.action_homeFragment_to_customerListFragment3)

        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}