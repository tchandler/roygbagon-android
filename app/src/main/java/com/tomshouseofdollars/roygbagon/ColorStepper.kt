package com.tomshouseofdollars.roygbagon

fun Int.clamp(min: Int, max: Int): Int = Math.max(min, Math.min(this, max))

class ColorStepper(private val numSteps: Int) {
    private val stepAmount: Int = 0x100 / numSteps
    var currentColor: Int = 0
        private set(value) { field = value.clamp(0, 0xFFFFFF) }

    var currentSteps: Array<Int> = arrayOf(0, 0, 0)

    public fun adjustColorOffset(red: Int, green: Int, blue: Int): Int {
        currentSteps[0] = (currentSteps[0] + red).clamp(0, numSteps)
        currentSteps[1] = (currentSteps[1] + green).clamp(0, numSteps)
        currentSteps[2] = (currentSteps[2] + blue).clamp(0, numSteps)
        val newRed = (currentSteps[0] * stepAmount).clamp(0, 0xFF)
        val newGreen = (currentSteps[1] * stepAmount).clamp(0, 0xFF)
        val newBlue = (currentSteps[2] * stepAmount).clamp(0, 0xFF)
        currentColor = (newRed shl 16) or (newGreen shl 8) or newBlue
        return currentColor
    }

    public fun resetColor() {
        currentColor = 0
        currentSteps = arrayOf(0, 0, 0)
    }
}