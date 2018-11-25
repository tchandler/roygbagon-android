package com.tomshouseofdollars.roygbagon

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import java.util.*
import java.lang.Math

fun Int.clamp(min: Int, max: Int): Int = Math.max(min, Math.min(this, max))

class MainActivity : AppCompatActivity() {

    private val stepCount: Int = 8
    private val offset: Int = 0x100 / 8
    private val whiteOffset: Int = (offset shl 16) or (offset shl 8) or offset

    private var currentColor: Int = 0x000000
        set(value) { field = value.clamp(0, 0xFFFFFF) }

    private var currentStep: Int = 0
        set(value) { field = value.clamp(0, stepCount)  }

    private var targetStep: Int = 0
        set(value) { field = value.clamp(0, stepCount)  }

    private var targetColor: Int = 0xFFFFFF
        set(value) { field = value.clamp(0, 0xFFFFFF) }

    private var currentColorView: View? = null
    private var targetColorView: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        currentColorView = findViewById(R.id.currentColorView)
        targetColorView = findViewById(R.id.targetColorView)
        val whiteButton: Button = findViewById(R.id.white)
        val blackButton: Button = findViewById(R.id.black)

        whiteButton.setOnClickListener {
            adjustColor(1)
        }

        blackButton.setOnClickListener {
            adjustColor(-1)
        }

    }

    private fun adjustColor(adjustmentAmount: Int) {
        currentStep += adjustmentAmount
        currentColor = getColorFromOffset(currentStep)
        currentColorView?.setBackgroundColor((makeColorFromInt(currentColor)))

        Log.d("Target", "${targetColor.toString(16)}")
        Log.d("Current", "${currentColor.toString(16)}")
        if (currentColor == targetColor) {
            val nextStep = Random().nextInt(stepCount) + 1
            val greyScale = nextStep * offset

            targetStep = nextStep
            targetColor = (greyScale shl 16) or (greyScale shl 8) or greyScale
            targetColorView?.setBackgroundColor(makeColorFromInt(targetColor))
        }
    }

    private fun makeColorFromInt(color: Int): Int = Color.parseColor("#${color.toString(16).padStart(6, '0')}")

    private fun getColorFromOffset(offset: Int): Int = whiteOffset * offset
}
