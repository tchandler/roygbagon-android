package com.tomshouseofdollars.roygbagon

sealed class ColorSteps(val step: Int) {
    class AdjustWhite(step: Int): ColorSteps(step)
    class AdjustRed(step: Int): ColorSteps(step)
    class AdjustGreen(step: Int): ColorSteps(step)
    class AdjustBlue(step: Int): ColorSteps(step)
}
