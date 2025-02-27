package com.example.mad_411_assignments

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var textNameView: EditText
    private lateinit var textNameTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textNameView = findViewById(R.id.text_attend_edit_text)
        textNameTextView = findViewById(R.id.show_name_text)
    }

    fun ShowName(view: View) {
        val textAttendStr = textNameView.text.toString()


        textNameTextView.text = "Hello, $textAttendStr"
    }
}