package com.example.basicbankingapp.ui

 import android.content.ContentValues.TAG
import android.os.Bundle
 import android.transition.TransitionInflater
 import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.cardview.widget.CardView
 import androidx.core.app.ActivityOptionsCompat
 import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
 import androidx.navigation.ActivityNavigatorExtras
 import androidx.navigation.findNavController
 import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.basicbankingapp.BankApplication
import com.example.basicbankingapp.R
import com.example.basicbankingapp.adapter.UserItemSelectionAdapter
import com.example.basicbankingapp.databinding.FragmentUserTransferBinding
import com.example.basicbankingapp.databinding.PickTransferUserItemBinding
import com.example.basicbankingapp.model.Transfers
import com.example.basicbankingapp.model.User
import com.google.android.material.snackbar.Snackbar
import java.lang.StringBuilder

class UserTransferFragment : Fragment() {

    private val sharedViewModel by activityViewModels<UsersViewModel> {
        UsersViewModel.UserViewModelFactory((requireActivity().application as BankApplication).dao)
    }
    private var _binding: FragmentUserTransferBinding? = null
    private val binding get() = _binding!!
    private val args: UserTransferFragmentArgs by navArgs()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val animation = TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)
        sharedElementEnterTransition =animation
        sharedElementReturnTransition=animation

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

                val userTransferMoneyInput = editTransactionSend.text.toString()
                val isValid= isValidAmount(args.userTransferFrom.userCurrentBalance,userTransferMoneyInput.toDouble())

                Log.d(TAG, "onViewCreated: $userTransferMoneyInput  //   $isValid")

                if (userTransferMoneyInput.isNotEmpty() && userTransferMoneyInput != "0.0$" && isValid) {



                    sharedViewModel.insertTransaction(
                        Transfers(
                            transferAmount = userTransferMoneyInput.toDouble(),
                            transferDate = String().formatDate(),
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
                            transferDate = String().formatDate(),
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
                        Snackbar.make(binding.root, "The Send Amount larger than your Bank Balance", Snackbar.LENGTH_LONG).show()
                    else
                    Snackbar.make(binding.root, "enter the amount", Snackbar.LENGTH_SHORT).show()
                }
            }
        }


    }

    fun enterDigit(view: View?) {
        var currentString = StringBuilder("")
        if (binding.editTransactionSend.text.isNotEmpty())
            currentString.append(binding.editTransactionSend.text.toString())
        val inputDigit = (view as Button).text
        currentString.append(inputDigit)
        binding.editTransactionSend.setText(currentString)

    }

    fun removeAmount() {
        var currentString = binding.editTransactionSend.text.toString()

        if (currentString.isEmpty())
            return

        currentString = currentString.subSequence(0, currentString.length - 1).toString()

        binding.editTransactionSend.setText(currentString)
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
        MyCustomDialog().show(childFragmentManager, "MyCustomFragment")
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}

class MyCustomDialog : DialogFragment() {


    private val sharedViewModel by activityViewModels<UsersViewModel> {
        UsersViewModel.UserViewModelFactory((requireActivity().application as BankApplication).dao)
    }


    private var _binding: PickTransferUserItemBinding? = null
    val binding get() = _binding!!
    var userSelected: User? = null
    lateinit var adapter: UserItemSelectionAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        getDialog()!!.getWindow()?.setBackgroundDrawableResource(R.drawable.rounded_corner);

        //return inflater.inflate(R.layout.pick_transfer_user_item, container, false)
        _binding = PickTransferUserItemBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerViewPickerDialog(view)

        setSearchView()

        binding.pickTrasnferUserBtn.setOnClickListener {
            userSelected?.let {
                sharedViewModel.setUserSelected(it)
            }
            dismiss()
        }
    }

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.85
                ).toInt()
        dialog!!.window?.setLayout(width, height)//, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    private fun setRecyclerViewPickerDialog(view: View) {
        val recyclerview = view.findViewById<RecyclerView>(R.id.recyclerView_pick_user)

        adapter = UserItemSelectionAdapter(requireContext()) { user ->
            userSelected = user
        }

        sharedViewModel.users.observe(viewLifecycleOwner)
        { it ->
            val listWithItemId = it.filter { it.userId != sharedViewModel.recyclerUserIdToHide }
            adapter.submitList(listWithItemId)
        }

        recyclerview.adapter = adapter

        // call the method addItemDecoration with the
        // recyclerView instance and add default Item Divider
        recyclerview.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                LinearLayoutManager(requireContext()).orientation
            )
        )
    }

    private fun setSearchView() {

        binding.seachTransferToUser.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.seachTransferToUser.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                newText?.let {
                    sharedViewModel.users.observe(viewLifecycleOwner) { it ->
                        val newlist = it.filter {
                            it.userName.contains(
                                newText,
                                ignoreCase = true
                            ) && it.userId != sharedViewModel.recyclerUserIdToHide
                        }
                        adapter.submitList(newlist)
                    }
                }

                return true
            }

        })
    }

}