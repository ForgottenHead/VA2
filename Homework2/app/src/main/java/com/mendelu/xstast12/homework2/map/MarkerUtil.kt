package com.mendelu.xstast12.homework2.map

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory


class MarkerUtil {
    companion object {

        fun createMarkerIconFromResource(context: Context, resource: Int): Bitmap {
            val drawable = ContextCompat.getDrawable(context, resource)
            drawable!!.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
            val bm = Bitmap.createBitmap(
                drawable.intrinsicWidth,
                drawable.intrinsicHeight,
                Bitmap.Config.ARGB_8888
            )

            val canvas = Canvas(bm)
            drawable.draw(canvas)
            return bm
        }

//        fun setOnClickMarkerIcon(type: String, clicked: Boolean, context: Context): BitmapDescriptor{
//            when(type){
//                "Savci (Mammalia)" -> {
//                    if (clicked){
//                        return BitmapDescriptorFactory.fromBitmap(
//                            createMarkerIconFromResource (context, R.drawable.mammal_marker_clicked)
//                        )
//                    }else{
//                        return BitmapDescriptorFactory.fromBitmap(
//                            createMarkerIconFromResource (context, R.drawable.mammal_marker)
//                        )
//                    }
//                }
//                "Plazi (Reptilia)" -> {
//                    if (clicked){
//                        return BitmapDescriptorFactory.fromBitmap(
//                            createMarkerIconFromResource (context, R.drawable.reptile_marker_clicked)
//                        )
//                    }else{
//                        return BitmapDescriptorFactory.fromBitmap(
//                            createMarkerIconFromResource (context, R.drawable.reptile_marker)
//                        )
//                    }
//                }
//                else -> {
//                    if (clicked){
//                        return BitmapDescriptorFactory.fromBitmap(
//                            createMarkerIconFromResource (context, R.drawable.fish_marker_clicked)
//                        )
//                    }else{
//                        return BitmapDescriptorFactory.fromBitmap(
//                            createMarkerIconFromResource (context, R.drawable.fish_marker)
//                        )
//                    }
//                }
//            }
//        }

    }
}