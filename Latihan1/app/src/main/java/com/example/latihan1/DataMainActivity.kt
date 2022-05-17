package com.example.latihan1

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

object DataMainActivity {
    @Parcelize
    data class User(
        val username: String,
        val gambar: Int,
        val suka: String,
        val postingan: String,
        val totalComment: String,
        val detailUploadedAtResult: String,
        val detailSizeResult: String,
        val detailContentRatingResult: String,
    ) : Parcelable

    fun generateDataUser(): ArrayList<User> {
        val rincian = ArrayList<User>()
        rincian.clear()

        rincian.add(
            User(
                "Budi",
                R.drawable.gambar_1,
                "100 Likes",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua",
                "View all 100 comments",
                "10 Mei 2022",
                "28 MB",
                "Bad",
            )
        )

        rincian.add(
            User(
                "Tuti",
                R.drawable.gambar_2,
                "200 Likes",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua",
                "View all 200 comments",
                "13 Mei 2022",
                "29 MB",
                "Good",
            )
        )

        rincian.add(
            User(
                "Joko",
                R.drawable.gambar_3,
                "210 Likes",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua",
                "View all 200 comments",
                "23 Mei 2022",
                "45 MB",
                "Normal",
            )
        )

        rincian.add(
            User(
                "Asep",
                R.drawable.gambar_4,
                "200 Likes",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua",
                "View all 190 comments",
                "03 Mei 2022",
                "31 MB",
                "Bad",
            )
        )

        rincian.add(
            User(
                "Windy",
                R.drawable.gambar_5,
                "300 Likes",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua",
                "View all 250 comments",
                "22 Mei 2022",
                "24 MB",
                "Bad",
            )
        )

        rincian.add(
            User(
                "Windy",
                R.drawable.gambar_6,
                "242 Likes",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua",
                "View all 50 comments",
                "13 April 2022",
                "33 MB",
                "Good",
            )
        )

        rincian.add(
            User(
                "Ellen",
                R.drawable.gambar_2,
                "205 Likes",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua",
                "View all 250 comments",
                "07 Mei 2022",
                "49 MB",
                "Normal",
            )
        )
        return rincian
    }
}