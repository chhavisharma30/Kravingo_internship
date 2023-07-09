package com.example.datingapp.presentation.activities

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import com.example.datingapp.R

class ReportMatchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report_match)
        val matchID = intent.getStringExtra("MATCH_ID")
        if (matchID != null) {
            setUpFields(matchID)
        }
    }

    private fun reportMatch(matchID : String) {
        // TODO: Implement this function to report a match
    }


    private val reportImagePicker = registerForActivityResult(ActivityResultContracts.GetContent()) { imageURI: Uri? ->
        val reportImage : ImageView = findViewById(R.id.report_image)
        val reportImageText : TextView = findViewById(R.id.attach_ss_text)
        val reportImageRemoveBtn : ImageView = findViewById(R.id.report_image_reemove_btn)

        if (imageURI != null) {
            reportImage.setImageURI(imageURI)
            reportImageText.visibility = View.GONE
            reportImageRemoveBtn.visibility = View.VISIBLE
        }
    }

    private fun setUpFields(matchID : String) {
        val reportImage : ImageView = findViewById(R.id.report_image)
        val reportImageRemoveBtn : ImageView = findViewById(R.id.report_image_reemove_btn)
        val reportImageText : TextView = findViewById(R.id.attach_ss_text)
        val sendReportBtn : Button = findViewById(R.id.send_report_btn)

        reportImage.setOnClickListener {
            reportImagePicker.launch("image/*")
        }

        reportImageRemoveBtn.setOnClickListener {
            reportImage.setImageResource(R.drawable.report_image)
            reportImageRemoveBtn.visibility = View.INVISIBLE
            reportImageText.visibility = View.VISIBLE
        }

        sendReportBtn.setOnClickListener {

            // reportMatch(matchID)

            //TODO : Might have to add an if else condition to show either a successful or failure dialog box depending on response from the server

            val reportConfirmationDialog =  Dialog(this)

            reportConfirmationDialog.apply {
                requestWindowFeature(Window.FEATURE_NO_TITLE)
                setContentView(R.layout.report_success_dialog)

                window?.apply {
                    setLayout(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )
                    setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                    setGravity(Gravity.CENTER)

                    attributes?.windowAnimations = R.style.DialogAnimation
                }
                show()
            }

            reportConfirmationDialog.setOnDismissListener {
                val intent = Intent(this, Inbox::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
            }
        }

    }
}