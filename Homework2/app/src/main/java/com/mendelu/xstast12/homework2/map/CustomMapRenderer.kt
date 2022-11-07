package com.mendelu.xstast12.homework2.map

import android.content.Context
import android.graphics.Bitmap
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.clustering.Cluster
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.view.DefaultClusterRenderer
import com.mendelu.xstast12.homework2.R
import com.mendelu.xstast12.homework2.model.Brno
import com.mendelu.xstast12.homework2.model.Store


class CustomMapRenderer(val context: Context,
                           map: GoogleMap,
                           clusterManager: ClusterManager<Store>,
) : DefaultClusterRenderer<Store>(context,map, clusterManager) {

    private val icons: MutableMap<String, Bitmap> = mutableMapOf()

    override fun shouldRenderAsCluster(cluster: Cluster<Store>): Boolean {
        return cluster.size > 5
    }


    override fun onBeforeClusterItemRendered(item: Store, markerOptions: MarkerOptions) {
        super.onBeforeClusterItemRendered(item, markerOptions)
        if (!icons.containsKey(item.type)){
            when(item.type){
                "clothes" -> {icons[item.type] =
                    MarkerUtil.createMarkerIconFromResource(context, R.drawable.ic_clothes)}
                "electronics" -> {icons[item.type] =
                    MarkerUtil.createMarkerIconFromResource(context, R.drawable.ic_electronics)}
                "fast_food"-> {icons[item.type] =
                    MarkerUtil.createMarkerIconFromResource(context, R.drawable.ic_fast_food)}
                "food" -> {icons[item.type] =
                    MarkerUtil.createMarkerIconFromResource(context, R.drawable.ic_food)}
                "kids" -> {icons[item.type] =
                    MarkerUtil.createMarkerIconFromResource(context, R.drawable.ic_kids)}
            }
        }

        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(icons[item.type]!!))
    }


}