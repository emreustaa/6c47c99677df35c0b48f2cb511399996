package com.emreusta.spacechallenge.presentation.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.emreusta.spacechallenge.data.model.room.FavoriteStationModel
import com.emreusta.spacechallenge.databinding.FragmentStationBinding
import com.emreusta.spacechallenge.presentation.adapter.StationListAdapter
import com.emreusta.spacechallenge.presentation.viewmodel.StationsViewModel
import com.emreusta.spacechallenge.utils.Constants.CAPACITY
import com.emreusta.spacechallenge.utils.Constants.DURABILITY
import com.emreusta.spacechallenge.utils.Constants.NAME
import com.emreusta.spacechallenge.utils.Constants.SPEED
import com.emreusta.spacechallenge.utils.extensions.DistanceCalculator
import com.emreusta.spacechallenge.utils.extensions.doubleOrZero
import com.emreusta.spacechallenge.utils.extensions.format
import com.emreusta.spacechallenge.utils.extensions.orZero
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StationFragment : Fragment() {

    private lateinit var binding: FragmentStationBinding
    private val spaceViewModel: StationsViewModel by activityViewModels()
    val adapter = StationListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentStationBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val name = requireActivity().intent.getStringExtra(NAME)
        val getDurability = requireActivity().intent.getIntExtra(DURABILITY, 0)
        val getSpeed = requireActivity().intent.getIntExtra(SPEED, 0)
        val getCapacity = requireActivity().intent.getIntExtra(CAPACITY, 0)

        with(binding) {

            tvName.text = name
            tvEus.text = "EUS : ${getSpeed * 20}"
            tvDs.text = "DS : ${getDurability * 10000}"
            tvUgs.text = "UGS : ${getCapacity * 10000}"

            adapter.selectedItem = {
                tvModelName.text = it.name.orEmpty()
            }
        }
        initObserver()
    }

    private fun initObserver() {
        spaceViewModel.stationLiveData.observe(viewLifecycleOwner) { stations ->
            with(binding) {
                frameLoading.visibility = View.GONE
                rvStationList.adapter = adapter
                rvStationList.setHasFixedSize(true)
                adapter.filteredStations = stations
                val manager = rvStationList.layoutManager as LinearLayoutManager
                ivRightArrow.setOnClickListener {
                    rvStationList.smoothScrollToPosition(manager.findLastVisibleItemPosition() + 1)
                }
                ivLeftArrow.setOnClickListener {
                    if (manager.findFirstVisibleItemPosition() > 0) {
                        rvStationList.smoothScrollToPosition(manager.findFirstCompletelyVisibleItemPosition() - 1)
                    } else {
                        rvStationList.smoothScrollToPosition(0)
                    }
                }
                adapter.updateStations(stations)

                adapter.itemClickListener = {
                    if (!spaceViewModel.isFavoriteStation(it.name.orEmpty())) {
                        spaceViewModel.addFavoriteStation(
                            FavoriteStationModel(
                                name = it.name.orEmpty(),
                                distance = DistanceCalculator.calculateDistance(
                                    0.0,
                                    0.0,
                                    it.coordinateX.doubleOrZero(),
                                    it.coordinateY.doubleOrZero()
                                ).format(2),
                                capacity = it.capacity.orZero()
                            )
                        )
                        adapter.setFavoriteItem(it.name.orEmpty())
                        Toast.makeText(
                            requireContext(),
                            "Add to favorites successfully",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "This station already added favorites",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                etSearch.addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(char: CharSequence?, p1: Int, p2: Int, p3: Int) {

                    }

                    override fun onTextChanged(char: CharSequence?, p1: Int, p2: Int, p3: Int) {
                        adapter.filter.filter(char)
                    }

                    override fun afterTextChanged(char: Editable?) {

                    }

                })
            }
        }
    }
}