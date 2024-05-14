package com.example.basicbankingapp.adapter

import android.graphics.Color
import android.view.LayoutInflater
import  androidx.recyclerview.widget.ListAdapter

import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.compose.ui.platform.ComposeView
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.basicbankingapp.R
import com.example.basicbankingapp.databinding.UserItemBinding 
import com.example.basicbankingapp.model.User
import com.example.basicbankingapp.ui.Theme.ComposeBanknessAppTheme
import com.example.basicbankingapp.ui.formatMoneyAmount
import com.example.basicbankingapp.ui.listCustomerUI.BankCustomer


class UsersAdapter(private val onClickListener: (User) -> Unit) :
    RecyclerView.Adapter<UsersAdapter.UserViewHolder>() {

    private val mutableList: MutableList<User> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(ComposeView(parent.context))
    }

    override fun getItemCount(): Int {
        return mutableList.size
    }

    fun submitList(list: List<User>) {
        mutableList.clear()
        mutableList.addAll(list)

        notifyItemChanged(0, list.size)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        if (mutableList.getOrNull(position) != null) {
            val user = mutableList.get(index = position)
            holder.bind(user)
        }
    }

    inner class UserViewHolder(private val composeView: ComposeView) :
        RecyclerView.ViewHolder(composeView) {

        fun bind(user: User) {
            composeView.setContent {
                ComposeBanknessAppTheme {
                    BankCustomer(customer = user){ user-> onClickListener(user)}
                }
            }
        }
    }

}