package com.example.shemajamebelin5

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shemajamebelin5.databinding.FragmentNotificationBinding
import kotlinx.coroutines.launch

class NotificationFragment : BaseFragment<FragmentNotificationBinding>(FragmentNotificationBinding::inflate) {
    lateinit var notificationsAdapter: NotificationsRecyclerAdapter
    @OptIn(ExperimentalStdlibApi::class)
    private val notificationsViewModel: NotificationVIewModel by viewModels()

    override fun setUp() {
        setUpRecycler()
        onObserve()
    }

    override fun setUpListeners() {
    }

    private fun setUpRecycler() {
        binding.recyclerNotifications.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerNotifications.adapter = NotificationsRecyclerAdapter().apply {
            notificationsAdapter = this
        }
    }

    @OptIn(ExperimentalStdlibApi::class)
    private fun onObserve() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
               notificationsViewModel.notifications.collect {
                    notificationsAdapter.setData(it)
                }
            }
        }
    }

}