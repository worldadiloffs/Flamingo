package com.example.flamingo

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var play: ImageView
    private lateinit var exit: ImageView
    private lateinit var info: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        play = findViewById(R.id.playBtn)
        exit = findViewById(R.id.exitBtn)
        info = findViewById(R.id.infoBtn)

        play.setOnClickListener {
            val intent = Intent(this, LevelChooseActivity::class.java)
            startActivity(intent)
        }
        info.setOnClickListener {
            val dialog = Dialog(this)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setCancelable(false)
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
            dialog.setContentView(R.layout.activity_info_dialog)
            val ok = dialog.findViewById(R.id.ok) as TextView
            ok.setOnClickListener {
                dialog.dismiss()
            }
            dialog.show()
        }

        exit.setOnClickListener {
            val dialog = Dialog(this)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setCancelable(false)
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
            dialog.setContentView(R.layout.activity_exit_dialog)
            val no = dialog.findViewById(R.id.no) as TextView
            val yes = dialog.findViewById(R.id.yes) as TextView
            yes.setOnClickListener {
                finish()
                dialog.dismiss()
            }
            no.setOnClickListener {
                dialog.dismiss()
            }
            dialog.show()
        }
    }

    override fun onBackPressed() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.activity_exit_dialog)
        val no = dialog.findViewById(R.id.no) as TextView
        val yes = dialog.findViewById(R.id.yes) as TextView
        yes.setOnClickListener {
            finish()
            dialog.dismiss()
        }
        no.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }
}