package com.tomshouseofdollars.roygbagon

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button


class MainActivity : AppCompatActivity() {
    private val step: Int = 4
    private val currentColor: SteppedColor = SteppedColor(step)
    private val targetColor: SteppedColor = SteppedColor(step, 0xFFFFFF)

    private lateinit var currentColorView: View
    private lateinit var targetColorView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        currentColorView = findViewById(R.id.currentColorView)
        targetColorView = findViewById(R.id.targetColorView)

        val whiteButton: Button = findViewById(R.id.white)
        val blackButton: Button = findViewById(R.id.black)
        val redButton: Button = findViewById(R.id.red)
        val cyanButton: Button = findViewById(R.id.cyan)
        val greenButton: Button = findViewById(R.id.green)
        val magentaButton: Button = findViewById(R.id.magenta)
        val blueButton: Button = findViewById(R.id.blue)
        val yellowButton: Button = findViewById(R.id.yellow)


        registerColorChange(whiteButton, ColorSteps.AdjustWhite(1))
        registerColorChange(blackButton, ColorSteps.AdjustWhite(-1))
        registerColorChange(redButton, ColorSteps.AdjustRed(1))
        registerColorChange(cyanButton, ColorSteps.AdjustCyan(1))
        registerColorChange(greenButton, ColorSteps.AdjustGreen(1))
        registerColorChange(magentaButton, ColorSteps.AdjustMagenta(1))
        registerColorChange(blueButton, ColorSteps.AdjustBlue(1))
        registerColorChange(yellowButton, ColorSteps.AdjustYellow(1))

        updateColorViews()
    }

    private fun registerColorChange(colorButton: Button, colorStep: ColorSteps) {
        colorButton.setOnClickListener {
            adjustColor(colorStep)
        }
    }

    private fun adjustColor(colorStep: ColorSteps) {
        currentColor.adjustColor(colorStep)

        if (currentColor == targetColor) {
            targetColor.scrambleColor()
        }

        updateColorViews()
        Log.d("Target", targetColor.color.toString(16))
        Log.d("Current", currentColor.color.toString(16))
    }

    private fun updateColorViews() {
        currentColorView.setBackgroundColor(makeColorFromInt(currentColor.color))
        targetColorView.setBackgroundColor(makeColorFromInt(targetColor.color))
    }

    private fun makeColorFromInt(color: Int): Int = Color.parseColor("#${color.toString(16).padStart(6, '0')}")
}
