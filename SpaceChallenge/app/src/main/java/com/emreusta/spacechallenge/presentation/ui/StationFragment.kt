package com.emreusta.spacechallenge.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.emreusta.spacechallenge.databinding.FragmentStationBinding
import com.emreusta.spacechallenge.presentation.StationActivity
import com.emreusta.spacechallenge.presentation.adapter.StationListAdapter
import com.emreusta.spacechallenge.presentation.viewmodel.StationsViewModel
import javax.inject.Inject

class StationFragment @Inject constructor() : Fragment() {

    private lateinit var binding: FragmentStationBinding
    private val spaceViewModel: StationsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentStationBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        spaceViewModel.getAllStations()
        val name = requireActivity().intent.getStringExtra(StationActivity.NAME)

        with(binding) {
            tvName.text = name

            tvModelName.setOnClickListener {

            }
        }
        initObserver()
    }

    private fun initObserver() {
        spaceViewModel.stationLiveData.observe(viewLifecycleOwner) { stations ->
            val adapter = StationListAdapter(stations)
            with(binding) {
                rvStationList.adapter = adapter
                rvStationList.setHasFixedSize(true)
                adapter.updateStations(stations)

                val manager = rvStationList.layoutManager as LinearLayoutManager
                ivRightArrow.setOnClickListener {
                    rvStationList.smoothScrollToPosition(manager.findLastVisibleItemPosition() + 1)
                }
                ivLeftArrow.setOnClickListener {
                    if (manager.findFirstVisibleItemPosition() > 0) {
                        rvStationList.smoothScrollToPosition(manager.findFirstCompletelyVisibleItemPosition() - 1)
                    }else{
                        rvStationList.smoothScrollToPosition(0)
                    }
                }
            }
        }
    }
}