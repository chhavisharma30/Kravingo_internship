package com.example.datingapp

import android.content.Context
import android.content.Intent
import android.graphics.drawable.GradientDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
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

    private lateinit var galleryLauncher: ActivityResultLauncher<String>
    private lateinit var selectedImageUri: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_best_photos)

        //implementing border color
        //val borderColor = ContextCompat.getColor(this, R.color.cherry)
        //val borderDrawable = imageView.background as GradientDrawable
        //borderDrawable.setStroke(4.dpToPx(this), borderColor)

        //implementing back floating action button
        floatingActionButton3.setOnClickListener{
            val i = Intent(this@AddBestPhotos,MainActivity::class.java)
            startActivity(i)
        }

        //implementing fabs
        floatingActionButton7.setOnClickListener {
            implementingFab(imageView3)
        }
        floatingActionButton8.setOnClickListener {
            implementingFab(imageView4)
        }
        floatingActionButton6.setOnClickListener {
            implementingFab(imageView2)
        }
        floatingActionButton4.setOnClickListener {
            implementingFab(imageView)
        }

        //implementing next button
        button2.setOnClickListener{
            val i1 = Intent(this@AddBestPhotos,SelectYourInterest::class.java)
            startActivity(i1)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK) {
            if(data!=null) {
                val imageUri:Uri?=data.data
                imageView.setImageURI(imageUri)
            }
        }
    }

    //fun Int.dpToPx(context: Context): Int {
        //val scale = context.resources.displayMetrics.density
        //return (this * scale + 0.5f).toInt()
    //}

    //function to implement fab

    private fun implementingFab(imageView: ImageView) {
    val intent = Intent(Intent.ACTION_GET_CONTENT)
    intent.type = "image/*"
    startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }
}