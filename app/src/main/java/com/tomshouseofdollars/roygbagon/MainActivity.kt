package com.tomshouseofdollars.roygbagon

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import java.util.*


class MainActivity : AppCompatActivity() {
    private val step: Int = 8
    private val currentColor: SteppedColor = SteppedColor(step)
    private val targetColor: SteppedColor = SteppedColor(step)

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
    }

    private fun registerColorChange(colorButton: Button, colorStep: ColorSteps) {
        colorButton.setOnClickListener {
            adjustColor(colorStep)
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
