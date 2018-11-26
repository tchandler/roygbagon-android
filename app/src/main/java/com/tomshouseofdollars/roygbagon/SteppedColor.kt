package com.tomshouseofdollars.roygbagon

import java.util.*

fun Int.clamp(min: Int, max: Int): Int = Math.max(min, Math.min(this, max))

class SteppedColor(private val numSteps: Int, var color: Int = 0) {
    private var currentSteps: Array<Int> = arrayOf(0, 0, 0)
    private val stepAmount: Int = 0x100 / numSteps

    fun adjustColor(steps: ColorSteps) {
        when (steps) {
            is ColorSteps.AdjustWhite -> this.adjustColorOffset(steps.step, steps.step, steps.step)
            is ColorSteps.AdjustRed -> this.adjustColorOffset(steps.step, -steps.step, -steps.step)
            is ColorSteps.AdjustCyan -> this.adjustColorOffset(-steps.step, steps.step, steps.step)
            is ColorSteps.AdjustGreen -> this.adjustColorOffset(-steps.step, steps.step, -steps.step)
            is ColorSteps.AdjustMagenta -> this.adjustColorOffset(steps.step, -steps.step, steps.step)
            is ColorSteps.AdjustBlue -> this.adjustColorOffset(-steps.step, -steps.step, steps.step)
            is ColorSteps.AdjustYellow -> this.adjustColorOffset(steps.step, steps.step, -steps.step)
        }
    }

    fun scrambleColor() {
        currentSteps = arrayOf(0, 0, 0)
        adjustColorOffset(
            Random().nextInt(numSteps),
            Random().nextInt(numSteps),
            Random().nextInt(numSteps)
        )
    }

    private fun adjustColorOffset(red: Int, green: Int, blue: Int) {
        currentSteps[0] = (currentSteps[0] + red).clamp(0, numSteps)
        currentSteps[1] = (currentSteps[1] + green).clamp(0, numSteps)
        currentSteps[2] = (currentSteps[2] + blue).clamp(0, numSteps)
        val newRed = (currentSteps[0] * stepAmount).clamp(0, 0xFF)
        val newGreen = (currentSteps[1] * stepAmount).clamp(0, 0xFF)
        val newBlue = (currentSteps[2] * stepAmount).clamp(0, 0xFF)
        color = (newRed shl 16) or (newGreen shl 8) or newBlue
    }

    override fun equals(other: Any?): Boolean {
        return when (other) {
            is SteppedColor -> other.color == color
            else -> return super.equals(other)
        }
    }
}