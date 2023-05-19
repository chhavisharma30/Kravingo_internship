package com.example.datingapp

import android.content.Context
import android.content.Intent
import android.graphics.drawable.GradientDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_add_best_photos.*
import kotlinx.android.synthetic.main.activity_main.*

class AddBestPhotos : AppCompatActivity() {

    companion object {
        private const val PICK_IMAGE_REQUEST = 1
    }

    private var galleryLauncher: ActivityResultLauncher<String>? = null
    private var selectedImageUri: Uri? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_best_photos)

        //implementing border color
        val borderColor = ContextCompat.getColor(this, R.color.cherry)
        val borderDrawable = imageView.background as GradientDrawable
        borderDrawable.setStroke(4.dpToPx(this), borderColor)

        //implementing back floating action button
        floatingActionButton3.setOnClickListener{
            val intent = Intent(this@AddBestPhotos,MainActivity::class.java)
            startActivity(intent)
        }

        //implementing fabs
        floatingActionButton7.setOnClickListener {
            galleryLauncher?.launch("image/*")
        }
        floatingActionButton8.setOnClickListener {
            galleryLauncher?.launch("image/*")
        }
        floatingActionButton6.setOnClickListener {
            galleryLauncher?.launch("image/*")
        }
        floatingActionButton4.setOnClickListener {
            galleryLauncher?.launch("image/*")
        }

    }
    fun Int.dpToPx(context: Context): Int {
        val scale = context.resources.displayMetrics.density
        return (this * scale + 0.5f).toInt()
    }

    //function to implement fab

    private fun implementingFab(b : FloatingActionButton) {
        // Create the gallery launcher
        galleryLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            if (uri != null) {
                selectedImageUri = uri

                when (b) {
                    floatingActionButton4 -> {
                        imageView.setImageURI(selectedImageUri)
                    }
                    floatingActionButton6 -> {
                        imageView2.setImageURI(selectedImageUri)
                    }
                    floatingActionButton7 -> {
                        imageView3.setImageURI(selectedImageUri)
                    }
                    floatingActionButton8 -> {
                        imageView4.setImageURI(selectedImageUri)
                    }
                }
            }
        }
    }
}