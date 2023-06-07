package com.example.datingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.core.view.children
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import kotlinx.android.synthetic.main.activity_select_your_interest.*


class SelectYourInterest : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_your_interest)

        //implementing chip selection
        val chipGrp: ChipGroup = findViewById(R.id.chipGroup)

        for (chip in chipGrp.children.filterIsInstance<Chip>()) {
            chip.setOnClickListener { changeChipColor(chip)}
        }

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