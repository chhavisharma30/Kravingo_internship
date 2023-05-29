package com.example.datingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.activity_select_your_interest.*


class SelectYourInterest : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_your_interest)

        //implementing chip selection
        chip1.setOnClickListener { changeChipColor(chip1) }
        chip2.setOnClickListener { changeChipColor(chip2) }
        chip3.setOnClickListener { changeChipColor(chip3) }
        chip4.setOnClickListener { changeChipColor(chip4) }
        chip5.setOnClickListener { changeChipColor(chip5) }
        chip6.setOnClickListener { changeChipColor(chip6) }
        chip7.setOnClickListener { changeChipColor(chip7) }
        chip8.setOnClickListener { changeChipColor(chip8) }
        chip9.setOnClickListener { changeChipColor(chip9) }
        chip10.setOnClickListener { changeChipColor(chip10) }
        chip11.setOnClickListener { changeChipColor(chip11) }
        chip12.setOnClickListener { changeChipColor(chip12) }
        chip13.setOnClickListener { changeChipColor(chip13) }
        chip14.setOnClickListener { changeChipColor(chip14) }
        chip15.setOnClickListener { changeChipColor(chip15) }
        chip16.setOnClickListener { changeChipColor(chip16) }
        chip17.setOnClickListener { changeChipColor(chip17) }
        chip18.setOnClickListener { changeChipColor(chip18) }

        //implementing back button
        floatingActionButton5.setOnClickListener{
            val i2 = Intent(this@SelectYourInterest,AddBestPhotos::class.java)
            startActivity(i2)
        }

    }

    private fun changeChipColor(chip: Chip) {
        chip.setChipBackgroundColorResource(R.color.cherry)
        chip.setTextColor(ContextCompat.getColor(this, android.R.color.white))
    }
}