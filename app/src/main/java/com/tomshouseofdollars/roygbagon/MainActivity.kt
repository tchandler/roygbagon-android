package com.tomshouseofdollars.roygbagon

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import java.util.*
import java.lang.Math



class MainActivity : AppCompatActivity() {

    private val step: Int = 8
    private val currentColor: ColorStepper = ColorStepper(step)
    private val targetColor: ColorStepper = ColorStepper(step)

    private var currentColorView: View? = null
    private var targetColorView: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        currentColorView = findViewById(R.id.currentColorView)
        targetColorView = findViewById(R.id.targetColorView)
        val whiteButton: Button = findViewById(R.id.white)
        val blackButton: Button = findViewById(R.id.black)
        val redButton: Button = findViewById(R.id.red)
        val tealButton: Button = findViewById(R.id.teal)


        whiteButton.setOnClickListener {
            adjustColor(ColorSteps.AdjustWhite(1))
        }

        blackButton.setOnClickListener {
            adjustColor(ColorSteps.AdjustWhite(-1))
        }

        redButton.setOnClickListener {
            adjustColor(ColorSteps.AdjustRed(1))
        }

        tealButton.setOnClickListener {
            adjustColor(ColorSteps.AdjustRed(-1))
        }

    }

    private fun adjustColor(colorStep: ColorSteps) {
        currentColor.adjustColor(colorStep)
        currentColorView?.setBackgroundColor((makeColorFromInt(currentColor.currentColor)))

        Log.d("Target", "${targetColor.currentColor.toString(16)}")
        Log.d("Current", "${currentColor.currentColor.toString(16)}")

        if (currentColor == targetColor) {
            val nextWhiteStep = Random().nextInt(step)
            val nextRedStep = Random().nextInt(step)
            targetColor.resetColorTo(nextRedStep, nextWhiteStep, nextWhiteStep)
            Log.d("New Color:", nextWhiteStep.toString())
            targetColorView?.setBackgroundColor(makeColorFromInt(targetColor.currentColor))
        } else {
            Log.d("Off By:", (currentColor.currentColor - targetColor.currentColor).toString())
        }
    }

    private fun makeColorFromInt(color: Int): Int = Color.parseColor("#${color.toString(16).padStart(6, '0')}")
}
