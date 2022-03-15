package com.emreusta.spacechallenge.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.emreusta.spacechallenge.data.model.room.FavoriteStationModel
import com.emreusta.spacechallenge.databinding.ItemFavoritesRowBinding

class FavoriteStationsAdapter(
    var stations: List<FavoriteStationModel>
) :
    RecyclerView.Adapter<FavoriteStationsAdapter.FavoriteStationViewHolder>() {

    inner class FavoriteStationViewHolder(val view: ItemFavoritesRowBinding) :
        RecyclerView.ViewHolder(view.root) {

        fun bind(item: FavoriteStationModel) {
            with(view) {
                tvItemName.text = item.name
                tvItemEus.text = "${item.capacity}UGS | Distance : ${item.distance}"
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoriteStationViewHolder {
        val binding =
            ItemFavoritesRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteStationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteStationViewHolder, position: Int) {
        holder.bind(stations[position])
    }


    override fun getItemCount(): Int = stations.size

    fun updateFavoriteStations(newStations: List<FavoriteStationModel>) {

        val diffUtil = object : DiffUtil.Callback() {

            override fun getOldListSize(): Int {
                return stations.size
            }

            override fun getNewListSize(): Int {
                return newStations.size
            }

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return stations[oldItemPosition] == newStations[newItemPosition]
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return stations[oldItemPosition] == newStations[newItemPosition]
            }

        }
        val result = DiffUtil.calculateDiff(diffUtil)
        result.dispatchUpdatesTo(this)

        stations = newStations
    }
}