package com.emreusta.spacechallenge.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.emreusta.spacechallenge.data.model.response.SpaceResponseModel
import com.emreusta.spacechallenge.databinding.ItemStationRowBinding

class StationListAdapter(var stations: SpaceResponseModel) :
    RecyclerView.Adapter<StationListAdapter.StationViewHolder>() {

    class StationViewHolder(val view: ItemStationRowBinding) : RecyclerView.ViewHolder(view.root) {

        fun bind(item: SpaceResponseModel.SpaceResponseModelItem) {
            with(view) {
                tvStationName.text = item.name.orEmpty()
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StationViewHolder {
        val binding =
            ItemStationRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StationViewHolder, position: Int) {

        holder.bind(stations[position])
    }


    override fun getItemCount(): Int = stations.size

    fun updateStations(newStations: SpaceResponseModel) {

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