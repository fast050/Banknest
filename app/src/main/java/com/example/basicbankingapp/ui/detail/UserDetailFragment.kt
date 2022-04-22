package com.example.basicbankingapp.ui.detail

import android.os.Bundle
import android.transition.TransitionInflater
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.navArgs
import com.example.basicbankingapp.BankApplication
import com.example.basicbankingapp.R
import com.example.basicbankingapp.data.DataProvider
import com.example.basicbankingapp.databinding.FragmentUserDetailBinding
import com.example.basicbankingapp.databinding.UserItemTransactionBinding
import com.example.basicbankingapp.ui.*

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserDetailFragment : Fragment() {
    private val args: UserDetailFragmentArgs by navArgs()
    private var _binding: FragmentUserDetailBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel by activityViewModels<UsersViewModel>()


    lateinit var bindingItem: UserItemTransactionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //-----------------------------------------
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        //postponeEnterTransition(250, TimeUnit.MILLISECONDS)
        //-----------------------------------------
        _binding = FragmentUserDetailBinding.inflate(inflater, container, false)
        bindingItem = UserItemTransactionBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {


            // ViewCompat.setTransitionName(binding.detailScreen, "title_${args.user.userId}")


            userNameDetail.text = getString(R.string.user_detail_name, args.user.userName)
            userBalanceDetail.text = args.user.userCurrentBalance.formatMoneyAmount()
            numberDetailCustomer.text = args.user.userMobileNumber
            emailDetailCustomer.text = args.user.userEmail

            // set image at the toolbar
            requireActivity().findViewById<CardView>(R.id.profile_image_container_toolbar).visibility =
                View.VISIBLE
            requireActivity().findViewById<ImageView>(R.id.profile_image_toolbar)
                .setImageResource(args.user.userProfilePicture)

            setTransactionDetail()


            button.setOnClickListener {
                val extras = FragmentNavigatorExtras(binding.button to "hero_image")

                val directions =
                    UserDetailFragmentDirections.actionUserDetailFragmentToUserTransferFragment(
                        args.user,
                        DataProvider.listOfCostumer[9]
                    )


                view.findNavController().navigate(
                    directions, // NavOptions
                    extras
                )

            }
        }
    }


    private fun setTransactionDetail() {
        sharedViewModel.listTransaction(args.user.userId).observe(viewLifecycleOwner) { list ->

            val it = list.transfers


            Log.d("TAG", "onViewCreated: $it")
            if (it.isNotEmpty()) {

                binding.userTransaction1.trasfersUsersContainer.visibility = View.VISIBLE
                binding.userTransaction1.userName.text = it[0].transferTo
                binding.userTransaction1.userTransfer.text =
                    it[0].transferAmount.formatMoneyAmount()
                        .formatMoneyTransaction(it[0].transactionType)
                binding.userTransaction1.userImageDetailTransaction.setImageResource(it[0].transferToProfile)
                binding.userTransaction1.userTransferTime.text =
                    formatDate()
                binding.userTransaction1.userTransfer.setTextColor(
                    formatSendReceiveColor(
                        context = requireContext(),
                         transactionType = it[0].transactionType
                    )
                )


                if (it.size >= 2) {
                    binding.userTransaction2.trasfersUsersContainer.visibility = View.VISIBLE

                    binding.userTransaction2.userName.text = it[1].transferTo
                    binding.userTransaction2.userTransfer.text =
                        it[1].transferAmount.formatMoneyAmount()
                            .formatMoneyTransaction(it[1].transactionType)
                    binding.userTransaction2.userImageDetailTransaction.setImageResource(it[1].transferToProfile)
                    binding.userTransaction2.userTransferTime.text =
                        formatDate()
                    binding.userTransaction2.userTransfer.setTextColor(
                        formatSendReceiveColor(
                            context = requireContext(),
                            transactionType = it[1].transactionType
                        )
                    )

                    if (it.size >= 3) {
                        binding.userTransaction3.trasfersUsersContainer.visibility =
                            View.VISIBLE

                        binding.userTransaction3.userName.text = it[2].transferTo
                        binding.userTransaction3.userTransfer.text =
                            it[2].transferAmount.formatMoneyAmount()
                                .formatMoneyTransaction(it[2].transactionType)
                        binding.userTransaction3.userImageDetailTransaction.setImageResource(it[2].transferToProfile)
                        binding.userTransaction3.userTransferTime.text =
                            formatDate()
                        binding.userTransaction3.userTransfer.setTextColor(
                            formatSendReceiveColor(
                                context = requireContext(),
                                transactionType = it[2].transactionType
                            )
                        )


                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
