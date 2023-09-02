/*
 * MIT License
 *
 * Copyright (c) 2022 Nauman Khaliq
 *
 */
package com.nk.we1.ui.main.home

import coil.load
import coil.transform.CircleCropTransformation
import com.nk.we1.R
import com.nk.we1.databinding.ViewholderUsersListItemBinding
import com.nk.we1.model.response.user.User
import com.nk.we1.ui.main.adapter.GenericAdapter

/**
 * View holder for User list item
 */
class UserItemViewHolder(private val binding: ViewholderUsersListItemBinding) : GenericAdapter.AbstractViewHolder<User>(binding.root) {

    override fun bindItem(item: User) {
        "${item.name?.title ?: ""}, ${item.name?.first ?: ""} ${item.name?.last}".also { binding.userName.text = it }
        binding.email.text = item.email
        binding.phone.text = item.cell
        "${item.location?.street?.name ?: ""}, ${item.location?.city ?: ""}, ${item.location?.country ?: ""}".also { binding.address.text = it }
        binding.imageView.load(item.picture?.medium) {
            placeholder(R.drawable.ic_photo)
            error(R.drawable.ic_broken_image)
            crossfade(true)
            transformations(CircleCropTransformation())
        }
        val distance = item.distance?.toInt() ?: 0
        binding.distance.text = if (distance >= 1000) "${(distance/1000)} km" else "$distance m"
        binding.root.setOnClickListener {
            clickListener?.onClicked(item)
        }
    }
}