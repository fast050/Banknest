package com.example.basicbankingapp.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.basicbankingapp.R
import com.example.basicbankingapp.adapter.UserItemSelectionAdapter
import com.example.basicbankingapp.databinding.PickTransferUserItemBinding
import com.example.basicbankingapp.model.User
import com.example.basicbankingapp.ui.UsersViewModel

class UserPickDialog : DialogFragment() {


    private val sharedViewModel by activityViewModels<UsersViewModel>()


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