package com.example.latihan1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val imgDetailImage: ImageView = findViewById(R.id.iv_detailImage)
        val tvDetailUsername: TextView = findViewById(R.id.tv_detailUsername)
        val tvDetailUploadedAtResult: TextView = findViewById(R.id.tv_detailUploadedAtResult)
        val tvDetailSizeResult: TextView = findViewById(R.id.tv_detailSizeResult)
        val tvDetailContentRatingResult: TextView = findViewById(R.id.tv_detailContentRatingResult)
        val tvDetailContentResult: TextView = findViewById(R.id.tv_detailContentResult)

        val data = intent.getParcelableExtra<DataMainActivity.User>(EXTRA_DATA)

        Glide.with(imgDetailImage)
            .load(data?.gambar)
            .into(imgDetailImage)
        tvDetailUsername.text = data?.username
        tvDetailUploadedAtResult.text = data?.detailUploadedAtResult
        tvDetailSizeResult.text = data?.detailSizeResult
        tvDetailContentRatingResult.text = data?.detailContentRatingResult
        tvDetailContentResult.text = data?.postingan
    }

    companion object{
        const val EXTRA_DATA = "extra_data"
    }
}