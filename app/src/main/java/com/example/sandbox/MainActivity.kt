package com.example.sandbox

import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.plattysoft.leonids.ParticleSystem

class MainActivity : AppCompatActivity() {

    private lateinit var progressBar: ProgressBar
    private lateinit var progressText: TextView
    private lateinit var button: Button
    private val handler = Handler()
    private var thread: Thread? = null

    private val progressOnSolvingProblems = 2/10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressBar = findViewById(R.id.progressBar)
        progressText = findViewById(R.id.progress_percentage)
        button = findViewById(R.id.button)

        button.setOnClickListener {
            startProgress()
        }
    }

    private fun startProgress() {
        progressBar.progress = 0

        thread = Thread {
            for (i in 0..100) {
                handler.post {
                    progressBar.progress = i
                    progressText.text = "$i % Completed"
                    if (i == 100) {
                        confetti()
                    }
                }
                when (i) {
                    in 1 ..31 -> Thread.sleep(50)
                    in 42 ..53 -> Thread.sleep(500)
                    else -> Thread.sleep(20)
                }
                // simulate some work
            }
        }
        thread?.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        thread?.interrupt()
    }

    private fun confetti() {
        val colors = arrayOf(
            R.drawable.confetti_color_red,
            R.drawable.confetti_color_yellow,
            R.drawable.confetti_color_green,
            R.drawable.confetti,
            R.drawable.confetti_blue,
            R.drawable.confetti_purple
        )
        for (color in colors) {
            val ps = ParticleSystem(this, 20, color, 10000)
            ps.setSpeedRange(0.2f, 0.8f)
            ps.setScaleRange(1.5f, 2.5f)
            ps.setInitialRotationRange(0, 360)
            ps.setFadeOut(2000)
            ps.oneShot(button, 100)
        }
    }
}