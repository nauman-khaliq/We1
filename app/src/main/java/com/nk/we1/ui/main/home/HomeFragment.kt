/*
 * MIT License
 *
 * Copyright (c) 2022 Nauman Khaliq
 *
 */
package com.nk.we1.ui.main.home

import android.annotation.SuppressLint
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nk.we1.R
import com.nk.we1.data.repository.DataFrom
import com.nk.we1.data.repository.HomeRepository
import com.nk.we1.databinding.FragmentHomeBinding
import com.nk.we1.databinding.ViewholderUsersListItemBinding
import com.nk.we1.model.State
import com.nk.we1.model.response.user.User
import com.nk.we1.ui.base.BaseFragment
import com.nk.we1.ui.main.MainViewModel
import com.nk.we1.ui.main.adapter.GenericAdapter
import com.nk.we1.utils.LocationUtil
import com.nk.we1.utils.PreferenceHelper
import dagger.assisted.AssistedInject
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.internal.InjectedFieldSignature
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject
import kotlin.concurrent.scheduleAtFixedRate

// Instances of this class are fragments representing a single
// object in our collection.

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class HomeFragment : BaseFragment<MainViewModel, FragmentHomeBinding>() {
    override val mViewModel: MainViewModel by activityViewModels()
    private var timerTask: TimerTask? = null

    private var usersListAdapter: GenericAdapter<User> = GenericAdapter(
        { view -> UserItemViewHolder(ViewholderUsersListItemBinding.bind(view)) },
        object : GenericAdapter.ClickListener<User> {
            override fun onClicked(item: User) {
            }
        },
        R.layout.viewholder_users_list_item,
        null
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return mViewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews()
        observeUsersList()
        mViewModel.getRandomUsers()
    }

    override fun onPause() {
        super.onPause()
        timerTask?.cancel()
    }

    override fun onResume() {
        super.onResume()
        scheduleRepeatingTimerFor5Seconds()
    }

    override fun getViewBinding(): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(layoutInflater)
    }

    /**
     * Initializing views and register click listeners
     */
    private fun initViews() {
        mViewBinding.run {
            usersListRV.setItemViewCacheSize(10)
            usersListRV.setHasFixedSize(true)
            usersListRV.layoutManager =
                LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            usersListRV.adapter = usersListAdapter
            swipeRefreshLayout.setOnRefreshListener {
                timerTask?.cancel()
                mViewModel.getRandomUsers()
            }
        }
    }

    /**
     * Observing users list State Flow data with states
     */
    @SuppressLint("NotifyDataSetChanged")
    private fun observeUsersList() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                mViewModel.users.collect {
                    when (it) {
                        is State.Loading -> {
                            Log.i("LoadingStateChecking", "Users list Loading Called")
                            mViewBinding.swipeRefreshLayout.isRefreshing = true
                        }
                        is State.Success -> {
                            Log.i("LoadingStateChecking", "Users list Success Called")
                            it.data.let { users ->
                                usersListAdapter.items = users.subList(0, users.size)
                                usersListAdapter.notifyDataSetChanged()
                                mViewBinding.usersListRV.scrollToPosition(0)
                            }
                            if (it.dataFrom == DataFrom.REMOTE) {
                                mViewBinding.swipeRefreshLayout.isRefreshing = false
                                scheduleRepeatingTimerFor5Seconds()
                            }
                        }
                        is State.Error -> {
                            Log.i("LoadingStateChecking", "Users list ERROR Called")
                            val obj = it.message
                            Toast.makeText(context, obj, Toast.LENGTH_SHORT).show()
                            mViewBinding.swipeRefreshLayout.isRefreshing = false
                        }
                    }
                }
            }
        }
    }

    /**
     * Schedule timer task for every minute to fetch updated data for the current view
     */
    private fun scheduleRepeatingTimerFor5Seconds() {
        timerTask?.cancel()
        timerTask = Timer().scheduleAtFixedRate(1000 * 5, 1000 * 5) {
            currentLocation = LocationUtil.generateRandomLocation()
            Log.i(
                "HomeFragment",
                "Timer called for refreshing the current selection Location  = ${currentLocation.latitude}, ${currentLocation.longitude}"
            )
            mViewModel.updateUsersList()
        }
    }

    companion object {
        var currentLocation: Location = LocationUtil.generateRandomLocation()
    }
}