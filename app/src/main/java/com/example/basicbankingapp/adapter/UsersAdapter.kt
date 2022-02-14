package com.example.basicbankingapp.adapter

import android.graphics.Color
import android.view.LayoutInflater
import  androidx.recyclerview.widget.ListAdapter

import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.basicbankingapp.R
import com.example.basicbankingapp.databinding.UserItemBinding
import com.example.basicbankingapp.model.User
import com.example.basicbankingapp.ui.formatMoneyAmount


class UsersAdapter(private val onClickListener: (User)->Unit) : ListAdapter<User, UsersAdapter.UserViewHolder>(UserDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)

    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)
    }


   inner class UserViewHolder(private val binding: UserItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

       init {
           binding.root.setOnClickListener{
               onClickListener(getItem(adapterPosition))
           }
       }

        fun bind(user: User) {
            binding.apply {
                userName.text = user.userName
                userAmount.text = user.userCurrentBalance.formatMoneyAmount()
                userImage.setImageResource(user.userProfilePicture)
            }
        }


    }

    private class UserDiffCallBack : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean =
            oldItem.userId == newItem.userId

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean =
            oldItem == newItem
    }

}