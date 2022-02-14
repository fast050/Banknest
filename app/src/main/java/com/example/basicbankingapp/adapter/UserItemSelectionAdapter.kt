package com.example.basicbankingapp.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.basicbankingapp.R
import com.example.basicbankingapp.databinding.UserSelectionItemBinding
import com.example.basicbankingapp.model.User

class UserItemSelectionAdapter(val context: Context, val listener:(User)->Unit) : ListAdapter<User, UserItemSelectionAdapter.UserViewHolder>(UserDiffCallBack()) {

    private var isItemSelected = true
    private var itemSelectedPosition = RecyclerView.NO_POSITION

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = UserSelectionItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)

    }

    override fun onBindViewHolder(holder: UserItemSelectionAdapter.UserViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)

    }

    inner class UserViewHolder(private val binding: UserSelectionItemBinding) :
        RecyclerView.ViewHolder(binding.root) {


        init {
            //hide the userTransferFrom from the recyclerView
            binding.root.setOnClickListener{
                itemSelected()
                if(itemSelectedPosition!=RecyclerView.NO_POSITION)
                listener(getItem(itemSelectedPosition))
            }
        }

        fun bind(user: User) {
            binding.apply {
                userName.text = user.userName
                userImage.setImageResource(user.userProfilePicture)
            }
        }

        private fun itemSelected()
        {
            if (isItemSelected){
                itemSelectedPosition=adapterPosition
                binding.userItemContainer.context.resources?.getColor(R.color.md_theme_dark_onSecondary)
                binding.checkSelectImg.visibility = View.VISIBLE
            }
            else{
                binding.userItemContainer.setBackgroundColor(Color.TRANSPARENT)
                binding.checkSelectImg.visibility = View.INVISIBLE
            }

            if(itemSelectedPosition==adapterPosition)
                isItemSelected=!isItemSelected
        }

    }

    private class UserDiffCallBack : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean =
            oldItem.userId == newItem.userId

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean =
            oldItem == newItem
    }


}