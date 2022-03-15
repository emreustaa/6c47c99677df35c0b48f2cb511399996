package com.emreusta.spacechallenge.data.model.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
class SpaceResponseModel : ArrayList<SpaceResponseModel.SpaceResponseModelItem>(), Parcelable {
    @Parcelize
    data class SpaceResponseModelItem(
        @SerializedName("capacity")
        val capacity: Int?,
        @SerializedName("coordinateX")
        val coordinateX: Double?,
        @SerializedName("coordinateY")
        val coordinateY: Double?,
        @SerializedName("name")
        val name: String?,
        @SerializedName("need")
        val need: Int?,
        @SerializedName("stock")
        val stock: Int?
    ) : Parcelable
}