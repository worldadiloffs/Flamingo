package com.example.flamingo

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import nl.dionsegijn.konfetti.core.Party
import nl.dionsegijn.konfetti.core.Position
import nl.dionsegijn.konfetti.core.emitter.Emitter
import nl.dionsegijn.konfetti.xml.KonfettiView
import java.util.concurrent.TimeUnit

class HardLevelActivity : AppCompatActivity() {
    private lateinit var question: TextView
    private lateinit var description: TextView
    private lateinit var ansA: TextView
    private lateinit var ansB: TextView
    private lateinit var ansC: TextView
    private lateinit var ansD: TextView

    private lateinit var hint: ImageView

    private lateinit var anim1: Animation
    private var hintCount = 0

    private lateinit var viewKonfetti: KonfettiView
    private var correctAnswers = 0

    private var index = 0

    private var lisImgTest = mutableListOf<TextTest>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hard_level)
        loadElements()
        loadAnimation()
        addQuestions()
        insertQuestions()
        answerChoice()

        if (hintCount == 3){
            hint.isClickable = false
            hint.setImageResource(R.drawable.outline_emoji_objects_grey)
        }

        hint.setOnClickListener {
            if (hintCount <= 3) {
                showHintDialog()
                hintCount++
            }
        }
    }

    override fun onBackPressed() {
        showExitDialog()
    }

    private fun answerChoice() {
        ansA.setOnClickListener {
            if (index < lisImgTest.size - 1) {
                checkAns(ansA, lisImgTest[index].variantA)
                index++
                insertQuestions()
            } else {
                checkAns(ansA, lisImgTest[index].variantA)
                showDialog()
            }
        }

        ansB.setOnClickListener {
            if (index < lisImgTest.size - 1) {
                checkAns(ansB, lisImgTest[index].variantB)
                index++
                insertQuestions()
            } else {
                checkAns(ansB, lisImgTest[index].variantB)
                showDialog()
            }
        }

        ansC.setOnClickListener {
            if (index < lisImgTest.size - 1) {
                checkAns(ansC, lisImgTest[index].variantC)
                index++
                insertQuestions()
            } else {
                checkAns(ansC, lisImgTest[index].variantC)
                showDialog()
            }
        }

        ansD.setOnClickListener {
            if (index < lisImgTest.size - 1) {
                checkAns(ansD, lisImgTest[index].variantD)
                index++
                insertQuestions()
            } else {
                checkAns(ansD, lisImgTest[index].variantD)
                showDialog()
            }
        }
    }

    private fun checkAns(btn: TextView, variant: String) {
        if (variant == lisImgTest[index].answer) {
            startParty()
            correctAnswers++
        } else {
            btn.startAnimation(anim1)
        }
    }

    private fun loadElements() {
        question = findViewById(R.id.question)
        description = findViewById(R.id.description)
        ansA = findViewById(R.id.answerA)
        ansB = findViewById(R.id.answerB)
        ansC = findViewById(R.id.answerC)
        ansD = findViewById(R.id.answerD)
        viewKonfetti = findViewById(R.id.konfettiView)
        hint = findViewById(R.id.hint)
    }

    private fun loadAnimation() {
        anim1 = AnimationUtils.loadAnimation(this, R.anim.anim1)
    }

    private fun addQuestions() {
        lisImgTest.add(
            TextTest(
                "I _______ TV yesterday.",
                "*Bo`shliqni to`ldiring!",
                "watched",
                "hear",
                "saw",
                "0",
                "watched",
                "watched"
            )
        )
        lisImgTest.add(
            TextTest(
                "I __ home.",
                "*Bo`shliqni to`ldiring!",
                "go",
                "went",
                "run",
                "0",
                "go",
                "go"
            )
        )
        lisImgTest.add(
            TextTest(
                "He _____ to play phone.",
                "*Bo`shliqni to`ldiring!",
                "does",
                "goes",
                "wants",
                "0",
                "wants",
                "wants"
            )
        )

        lisImgTest.add(
            TextTest(
                "I'm ________ SAT.",
                "*Bo`shliqni to`ldiring!",
                "playing",
                "studying",
                "ruin",
                "0",
                "studying",
                "studying"
            )
        )
        lisImgTest.add(
            TextTest(
                "Did you ____ your hand?",
                "*Bo`shliqni to`ldiring!",
                "drink",
                "shower",
                "wash",
                "0",
                "wash",
                "wash"
            )
        )
        lisImgTest.add(
            TextTest(
                "He comes ____ USA.",
                "*Bo`shliqni to`ldiring!",
                "from",
                "in",
                "the",
                "0",
                "from",
                "from"
            )
        )
        lisImgTest.add(
            TextTest(
                "He is ___ best football player.",
                "*Bo`shliqni to`ldiring!",
                "a",
                "the",
                "an",
                "0",
                "the",
                "the"
            )
        )

        lisImgTest.add(
            TextTest(
                "__ you have brothers?",
                "*Bo`shliqni to`ldiring!",
                "Are",
                "Did",
                "Do",
                "0",
                "Do",
                "Do"
            )
        )

        lisImgTest.add(
            TextTest(
                "Obtuse - _______",
                "*Ma`nodosh so`zni tanlang!",
                "good",
                "dull",
                "idk",
                "0",
                "dull",
                "dull"
            )
        )

        lisImgTest.add(
            TextTest(
                "Flamingo is a ____.",
                "*Bo`shliqni to`ldiring!",
                "bird",
                "insect",
                "fish",
                "river",
                "bird",
                "bird"
            )
        )

    }

    private fun insertQuestions() {
        question.text = lisImgTest[index].question
        description.text = lisImgTest[index].description
        ansA.text = lisImgTest[index].variantA
        ansB.text = lisImgTest[index].variantB
        ansC.text = lisImgTest[index].variantC
        ansD.text = lisImgTest[index].variantD
    }

    private fun startParty() {
        val party = Party(
            speed = 0f,
            maxSpeed = 30f,
            damping = 0.9f,
            spread = 360,
            colors = listOf(0xfce18a, 0xff726d, 0xf4306d, 0xb48def),
            emitter = Emitter(duration = 100, TimeUnit.MILLISECONDS).max(100),
            position = Position.Relative(0.5, 0.3)
        )
        viewKonfetti.start(party)
    }

    @SuppressLint("SetTextI18n")
    private fun showDialog() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.activity_dialog)
        val result = dialog.findViewById(R.id.result) as TextView
        result.text = correctAnswers.toString() + "/" + lisImgTest.size
        val restart = dialog.findViewById(R.id.restart) as ImageView
        val home = dialog.findViewById(R.id.home) as ImageView
        val menu = dialog.findViewById(R.id.menu) as ImageView
        restart.setOnClickListener {
            lisImgTest.shuffle()
            correctAnswers = 0
            index = 0
            dialog.dismiss()
        }
        home.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        menu.setOnClickListener {
            val intent = Intent(this, LevelChooseActivity::class.java)
            startActivity(intent)
            finish()
        }
        dialog.show()
    }

    private fun showHintDialog() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.activity_hint_dialog)
        val hint = dialog.findViewById(R.id.hint) as TextView
        hint.text = lisImgTest[index].hint
        val ok = dialog.findViewById(R.id.ok) as TextView
        ok.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun showExitDialog() {
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