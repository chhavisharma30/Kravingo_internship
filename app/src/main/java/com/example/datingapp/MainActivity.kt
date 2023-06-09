package com.example.datingapp

import android.content.Intent
import android.graphics.PorterDuff
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        private const val PICK_IMAGE_REQUEST = 1
    }

    private var galleryLauncher: ActivityResultLauncher<String>? = null
    private var selectedImageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //making status bar invisible
        WindowCompat.setDecorFitsSystemWindows(window,false)

        val windowInsetsController=ViewCompat.getWindowInsetsController(window.decorView)

        windowInsetsController?.isAppearanceLightStatusBars=true

        //setting vector icons of floating action button
        val myIcon = resources.getDrawable(R.drawable.ic_baseline_arrow_back_24)
        floatingActionButton2.setImageDrawable(myIcon)

        val myIcon2 = resources.getDrawable(R.drawable.ic_baseline_edit_24)
        floatingActionButton.setImageDrawable(myIcon2)

        //customizing borders of edittext
        setUpEditText(et2)
        setUpEditText(et3)
        setUpEditText(et4)
        setUpEditText(et6)

        //implementing fab to set profile picture
        floatingActionButton.setOnClickListener {
            galleryLauncher?.launch("image/*")
        }

        // Create the gallery launcher
        galleryLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            if (uri != null) {
                selectedImageUri = uri
                profile_image.setImageURI(selectedImageUri)
            }
        }

        //to change color of border of Spinner
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                // Change the border color
                spinner.background.setColorFilter(ContextCompat.getColor(this@MainActivity, R.color.cherry), PorterDuff.Mode.SRC_ATOP)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Do nothing
            }
        }

        //implementing continue button
        button.setOnClickListener{
            val intent = Intent(this@MainActivity,AddBestPhotos::class.java)
            startActivity(intent)
        }


//        //Added button to open and test Inbox activity in device/emulator, will delete once the section is complete
//        findViewById<Button>(R.id.open_chat).setOnClickListener {
//            Intent(this, Chat::class.java).also {
//                startActivity(it)
//            }
//        }

    }


    private fun setUpEditText(editText: EditText) {
        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s.isNullOrEmpty()) {
                    editText.setBackgroundResource(R.drawable.edit_text_border)
                } else {
                    editText.setBackgroundResource(R.drawable.edit_text_border_pink)
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }
}