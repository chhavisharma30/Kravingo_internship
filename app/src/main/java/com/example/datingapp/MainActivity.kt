package com.example.datingapp

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.WindowManager
import android.widget.EditText
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //making status bar invisible
        WindowCompat.setDecorFitsSystemWindows(window,false)

        val windowInsetsController=ViewCompat.getWindowInsetsController(window.decorView)

        windowInsetsController?.isAppearanceLightStatusBars=true

        //setting vector icons of floating action button
        val fab = findViewById<FloatingActionButton>(R.id.floatingActionButton2)
        val myIcon = resources.getDrawable(R.drawable.ic_baseline_arrow_back_24)
        fab.setImageDrawable(myIcon)

        val fab2 = findViewById<FloatingActionButton>(R.id.floatingActionButton)
        val myIcon2 = resources.getDrawable(R.drawable.ic_baseline_edit_24)
        fab2.setImageDrawable(myIcon2)

        //customizing borders of edittext
        val edittext1 = findViewById<EditText>(R.id.et2)
        val edittext2 = findViewById<EditText>(R.id.et3)
        val edittext3 = findViewById<EditText>(R.id.et4)


        setUpEditText(edittext1)
        setUpEditText(edittext2)
        setUpEditText(edittext3)
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