package com.emreusta.spacechallenge.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.emreusta.spacechallenge.R
import com.emreusta.spacechallenge.data.model.response.SpaceResponseModel
import com.emreusta.spacechallenge.databinding.ItemStationRowBinding
import com.emreusta.spacechallenge.utils.extensions.DistanceCalculator
import com.emreusta.spacechallenge.utils.extensions.doubleOrZero
import com.emreusta.spacechallenge.utils.extensions.format
import com.emreusta.spacechallenge.utils.extensions.orZero
import java.util.*

class StationListAdapter(
) : RecyclerView.Adapter<StationListAdapter.StationViewHolder>(), Filterable {

    var itemClickListener: ((model: SpaceResponseModel.SpaceResponseModelItem) -> Unit) = { _ -> }
    var selectedItem: ((model: SpaceResponseModel.SpaceResponseModelItem) -> Unit) = { _ -> }
    var isFavorite = ""
    var stations: SpaceResponseModel = SpaceResponseModel()
    var filteredStations: SpaceResponseModel = SpaceResponseModel()

    inner class StationViewHolder(val view: ItemStationRowBinding) :
        RecyclerView.ViewHolder(view.root) {

        fun bind(item: SpaceResponseModel.SpaceResponseModelItem) {
            with(view) {
                tvStationName.text = item.name.orEmpty()
                "${item.capacity} / ${item.stock}".also { tvCapacityInfo.text = it }
                val distance = DistanceCalculator.calculateDistance(
                    0.0,
                    0.0,
                    item.coordinateX.doubleOrZero(),
                    item.coordinateY.doubleOrZero()
                ).format(2)
                tvEusInfo.text = "${item.capacity.orZero()}UGS | Distance : ${distance}"

                ivFavorite.setOnClickListener {
                    itemClickListener(item)
                }

                btnTravel.setOnClickListener {
                    selectedItem(item)
                }

                if (isFavorite == item.name) {
                    ivFavorite.setImageDrawable(
                        ContextCompat.getDrawable(
                            itemView.context,
                            R.drawable.ic_favorite
                        )
                    )
                } else {
                    ivFavorite.setImageDrawable(
                        ContextCompat.getDrawable(
                            itemView.context,
                            R.drawable.ic_favorite_empty
                        )
                    )
                }
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
        holder.bind(filteredStations[position])
    }

    override fun getItemCount(): Int = filteredStations.size

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

    fun setFavoriteItem(favoriteItem: String) {
        this.isFavorite = favoriteItem
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(char: CharSequence?): FilterResults {
                val query = char?.toString()?.lowercase(Locale.getDefault())
                val filterResults = FilterResults()
                filteredStations = if (query.isNullOrEmpty()) {
                    stations
                } else {
                    val resultList = SpaceResponseModel()
                    for (row in stations) {
                        row.name?.let { name ->
                            if (name.lowercase(Locale.getDefault())
                                    .contains(query.lowercase(Locale.getDefault()))
                            ) {
                                resultList.add(row)
                            }
                        }
                    }
                    resultList
                }

                filterResults.values = filteredStations
                return filterResults
            }

            override fun publishResults(char: CharSequence?, results: FilterResults?) {
                filteredStations = results?.values as SpaceResponseModel
                notifyDataSetChanged()
            }
        }
    }
}