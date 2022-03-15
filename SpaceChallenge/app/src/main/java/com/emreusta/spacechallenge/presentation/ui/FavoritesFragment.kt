package com.emreusta.spacechallenge.presentation.ui

import android.graphics.Canvas
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.emreusta.spacechallenge.databinding.FragmentFavoritesBinding
import com.emreusta.spacechallenge.presentation.adapter.FavoriteStationsAdapter
import com.emreusta.spacechallenge.presentation.viewmodel.StationsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment() {

    private lateinit var binding: FragmentFavoritesBinding
    private val favoriteStationViewModel: StationsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentFavoritesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        favoriteStationViewModel.getAllFavoriteStations()
        observeFavoriteData()
    }

    private fun observeFavoriteData() {
        favoriteStationViewModel.favoriteStationLiveData.observe(viewLifecycleOwner) {

            val adapter = FavoriteStationsAdapter(it)
            with(binding) {
                rvFavorites.adapter = adapter
                rvFavorites.setHasFixedSize(true)
                adapter.updateFavoriteStations(it)

                val swipeCallback =
                    object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

                        override fun onMove(
                            recyclerView: RecyclerView,
                            viewHolder: RecyclerView.ViewHolder,
                            target: RecyclerView.ViewHolder
                        ): Boolean {
                            return true
                        }

                        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                            val layoutPosition = viewHolder.layoutPosition
                            val selectedStations = adapter.stations[layoutPosition]
                            favoriteStationViewModel.deleteFavoriteStation(model = selectedStations)
                        }
                    }
                ItemTouchHelper(swipeCallback).attachToRecyclerView(rvFavorites)
            }
        }
    }
}