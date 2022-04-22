package com.example.basicbankingapp.ui.trasnfers

import android.content.ContentValues.TAG
import android.os.Bundle
import android.transition.TransitionInflater
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.basicbankingapp.R
import com.example.basicbankingapp.databinding.FragmentUserTransferBinding
import com.example.basicbankingapp.model.Transfers
import com.example.basicbankingapp.model.User
import com.example.basicbankingapp.ui.*
import com.example.basicbankingapp.ui.dialog.UserPickDialog
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserTransferFragment : Fragment() {

    private val sharedViewModel by activityViewModels<UsersViewModel>()
    private var _binding: FragmentUserTransferBinding? = null
    private val binding get() = _binding!!
    private val args: UserTransferFragmentArgs by navArgs()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val animation =
            TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)
        sharedElementEnterTransition = animation
        sharedElementReturnTransition = animation

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentUserTransferBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.apply {

            binding.userTransferFragment = this@UserTransferFragment

            setTransfersFromUser()

            setTransfersToUser(args.userTransferTo)


            //it livedata of the user selected by the recyclerView dialog alert fragment
            sharedViewModel.userSelected.observe(viewLifecycleOwner) {
                it?.let {
                    setTransfersToUser(it)
                }
            }

            userTransferToProfileImage.setOnClickListener {
                sharedViewModel.setRecyclerUserIdToHide(args.userTransferFrom.userId)
                showSelectDialog()
            }

            submitBtn.setOnClickListener {

                if (sharedViewModel.userSelected.value == null) {
                    Snackbar.make(
                        binding.root,
                        "Please , Select A Payer First",
                        Snackbar.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }

                binding.editTransactionSend.setOnClickListener {
                    binding.editTransactionSend.clearFocus()
                }

                val userTransferMoneyInput = editTransactionSend.text.toString().trimNumber()
                if (userTransferMoneyInput.isEmpty()) {
                    Snackbar.make(binding.root, "Please Enter Send Amount", Snackbar.LENGTH_LONG)
                        .show()
                    return@setOnClickListener
                }

               Log.d(TAG, "onViewCreated: ${userTransferMoneyInput}")

                val isValid = isValidAmount(
                    args.userTransferFrom.userCurrentBalance,
                    userTransferMoneyInput.toDouble()
                )


                if (userTransferMoneyInput.isNotEmpty() && userTransferMoneyInput != "0.0$" && isValid) {


                    Log.d(TAG, "onViewCreated: $userTransferMoneyInput")

                    sharedViewModel.insertTransaction(
                        Transfers(
                            transferAmount = userTransferMoneyInput.toDouble(),
                            transferDate = formatDate(),
                            transferFrom = args.userTransferFrom.userName,
                            transferTo = sharedViewModel.userSelected.value!!.userName,
                            userId = args.userTransferFrom.userId,
                            transferToProfile = sharedViewModel.userSelected.value!!.userProfilePicture,
                            transactionType = TransactionType.SendMoney.name,
                            transferFromProfile = args.userTransferFrom.userProfilePicture
                        )
                    )
                    sharedViewModel.insertTransaction(
                        Transfers(
                            transferAmount = userTransferMoneyInput.toDouble(),
                            transferDate = formatDate(),
                            transferTo = args.userTransferFrom.userName,
                            transferFrom = sharedViewModel.userSelected.value!!.userName,
                            userId = sharedViewModel.userSelected.value!!.userId,
                            transferFromProfile = sharedViewModel.userSelected.value!!.userProfilePicture,
                            transactionType = TransactionType.ReceiveMoney.name,
                            transferToProfile = args.userTransferFrom.userProfilePicture
                        )
                    )


                    sharedViewModel.updateAmountAfterTransaction(
                        userTransferFrom = args.userTransferFrom,
                        userTransferTo = sharedViewModel.userSelected.value!!,
                        transferAmount = userTransferMoneyInput.toDouble()
                    )

                    findNavController().navigate(R.id.action_userTransferFragment_to_successfullScreenFragment)
                } else {

                    if (!isValid)
                        Snackbar.make(
                            binding.root,
                            "The Send Amount larger than your Bank Balance",
                            Snackbar.LENGTH_LONG
                        ).show()
                    else
                        Snackbar.make(binding.root, "enter the amount", Snackbar.LENGTH_SHORT)
                            .show()
                }
            }
        }


    }

    fun enterDigit(view: View?) {
        if (binding.editTransactionSend.text.toString().length > 9)
            Snackbar.make(requireView()
                ,"the Transfer amount too large ,please decrease the amount",
                Snackbar.LENGTH_SHORT).show()

        val digEnter = (view as Button).text.toString()

        val prevString =binding.editTransactionSend.text.toString().trimNumber()

        val appendString =  prevString + digEnter

        if (binding.editTransactionSend.text.toString().length>9)
        binding.editTransactionSend.setText(0.toCurrency())
        else
            binding.editTransactionSend.setText(appendString.toInt().toCurrency())



    }

    fun removeAmount() {
        var currentString = binding.editTransactionSend.text.toString()

        if (currentString.isEmpty())
            return

        currentString = currentString.trimNumber().trimLastNumber()

        
        var currentMoney :String?= ""

        Log.d(TAG, "removeAmount: $currentString")
        
        currentMoney = if (binding.editTransactionSend.text.toString().length<5)
            0.toCurrency()
        else
            currentString.toIntOrNull()?.toCurrency() ?: 0.toCurrency()

        Log.d(TAG, "removeAmount: $currentMoney")
        binding.editTransactionSend.setText(currentMoney)
    }

    private fun FragmentUserTransferBinding.setTransfersFromUser() {
        args.userTransferFrom.userCurrentBalance.formatMoneyAmount()


        // set image at the toolbar
        requireActivity().findViewById<CardView>(R.id.profile_image_container_toolbar).visibility =
            View.VISIBLE
        requireActivity().findViewById<ImageView>(R.id.profile_image_toolbar)
            .setImageResource(args.userTransferFrom.userProfilePicture)
    }

    private fun FragmentUserTransferBinding.setTransfersToUser(user: User) {
        userTrasferToName.text = user.userName
        userTransferToProfileImage.setImageResource(user.userProfilePicture)
    }

    private fun showSelectDialog() {
        UserPickDialog().show(childFragmentManager, "MyCustomFragment")
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}