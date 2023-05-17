package com.example.datingapp

import android.content.Context
import android.graphics.drawable.GradientDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_add_best_photos.*

class AddBestPhotos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_best_photos)

        val borderColor = ContextCompat.getColor(this, R.color.cherry)
        val borderDrawable = imageView.background as GradientDrawable
        borderDrawable.setStroke(4.dpToPx(this), borderColor)
    }
    fun Int.dpToPx(context: Context): Int {
        val scale = context.resources.displayMetrics.density
        return (this * scale + 0.5f).toInt()
    }
}