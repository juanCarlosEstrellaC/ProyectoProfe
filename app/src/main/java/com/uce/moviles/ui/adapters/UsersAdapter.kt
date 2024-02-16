package com.uce.moviles.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.uce.moviles.R
import com.uce.moviles.data.local.entities.Users
import com.uce.moviles.databinding.UsersItemsBinding

class UsersAdapter(private val listUsers: List<Users>) : RecyclerView.Adapter<UsersAdapter.UsersVH>() {

    class UsersVH(view: View) : RecyclerView.ViewHolder(view) {

        private var binding: UsersItemsBinding = UsersItemsBinding.bind(view)

        fun render(item: Users) {
            binding.avartarImg.load("https://cdn.icon-icons.com/icons2/1378/PNG/512/avatardefault_92824.png"){
                placeholder(R.drawable.avatar_placeholder)
                crossfade(true)
            }

            binding.txtID.text = item.userId.toString()
            binding.txtName.text = item.firstName.toString() + " " + item.lastName.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersVH {
        val inflater = LayoutInflater.from(parent.context)
        return UsersVH(inflater.inflate(R.layout.users_items, parent, false))
    }

    override fun getItemCount(): Int = listUsers.size

    override fun onBindViewHolder(holder: UsersVH, position: Int) {
        holder.render(listUsers[position])
    }
}