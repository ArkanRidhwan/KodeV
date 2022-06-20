package com.example.latihan8.data.local

import android.os.Parcelable
import androidx.paging.DataSource
import kotlinx.parcelize.Parcelize

object Data {
    @Parcelize
    data class Dummy1(
        val name: String,
        val img: String,
    ) : Parcelable

    fun generateDataDummy() : DataSource.Factory<Int, Dummy1>{
        val thumbnail = ArrayList<Dummy1>()
        thumbnail.clear()

        thumbnail.add(
            Dummy1(
                "Bakso",
                "https://upload.wikimedia.org/wikipedia/commons/2/28/Bakso_mi_bihun.jpg",
            )
        )

        thumbnail.add(
            Dummy1(
                "Nasi Goreng",
                "https://cdn1-production-images-kly.akamaized.net/KxuztQKl3tnUN0Fw5iAwKsnX_u0=/0x148:1920x1230/640x360/filters:quality(75):strip_icc():format(jpeg)/kly-media-production/medias/3093328/original/069244600_1585909700-fried-2509089_1920.jpg",
            )
        )


        thumbnail.add(
            Dummy1(
                "Sate",
                "https://media.suara.com/pictures/970x544/2020/07/28/38996-resep-sate-maranggi-empuk-dari-chef-devina-hermawan.jpg",
            )
        )

        thumbnail.add(
            Dummy1(
                "Soto",
                "https://cdn0-production-images-kly.akamaized.net/i9qrydutqGtOFNbTVLreH0o_Vpk=/0x0:2400x1352/1200x675/filters:quality(75):strip_icc():format(webp)/kly-media-production/medias/3476081/original/030645300_1623135145-soto_ayam__bening.jpg",
            )
        )

        thumbnail.add(
            Dummy1(
                "Rendang",
                "https://cdn0-production-images-kly.akamaized.net/rM3_abtBi9tM8MD50EuU_Fvp0OM=/1x112:1000x675/469x260/filters:quality(75):strip_icc():format(webp)/kly-media-production/medias/3245094/original/043061400_1600750232-shutterstock_1786027046.jpg",
            )
        )
        return thumbnail
    }
}