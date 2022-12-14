package com.mendelu.xstast12.homework2.model

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem
import java.io.Serializable

class Store(
    var id: Long,
    var name: String,
    var address: String,
    var latitude: Double,
    var longitude: Double,
    var type: String
): Serializable, ClusterItem {
    override fun getPosition(): LatLng {
        return LatLng(latitude, longitude)
    }

    override fun getTitle(): String? {
        return name
    }

    override fun getSnippet(): String? {
        return address
    }

    fun getLocation(): LatLng{
        return LatLng(latitude, longitude)
    }
}