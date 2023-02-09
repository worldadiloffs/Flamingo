package com.example.flamingo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatButton

class LevelChooseActivity : AppCompatActivity() {
    private lateinit var btnEasy: AppCompatButton
    private lateinit var btnMid: AppCompatButton
    private lateinit var btnHard: AppCompatButton
    private lateinit var backBtn: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level_choose)
        loadElements()

        btnEasy.setOnClickListener {
            val intent = Intent(this, EasyLevelActivity::class.java)
            startActivity(intent)
            finish()
        }
        btnMid.setOnClickListener {
            val intent = Intent(this, MidLevelActivity::class.java)
            startActivity(intent)
            finish()
        }
        btnHard.setOnClickListener {
            val intent = Intent(this, HardLevelActivity::class.java)
            startActivity(intent)
            finish()
        }

        backBtn.setOnClickListener {
            finish()
        }
    }

    private fun loadElements() {
        btnEasy = findViewById(R.id.btnEasy)
        btnMid = findViewById(R.id.btnMid)
        btnHard = findViewById(R.id.btnHard)
        backBtn = findViewById(R.id.backBtn)
    }

    override fun onBackPressed() {
        finish()
    }
}