package com.gmail.tpt.grocerystore

import android.animation.Animator
import android.animation.ObjectAnimator
import android.content.Context
import android.transition.Transition
import android.transition.TransitionValues
import android.util.AttributeSet
import android.view.ViewGroup

class PaddingTransition @JvmOverloads constructor(
    context: Context? = null,
    attrs: AttributeSet? = null
) :
    Transition(context, attrs) {

    private val SCALE_FACTOR = "SCALE_FACTOR"

    override fun captureStartValues(transitionValues: TransitionValues?) {
        captureValues(transitionValues)
    }


    override fun captureEndValues(transitionValues: TransitionValues?) {
        captureValues(transitionValues)
    }

    private fun captureValues(transitionValues: TransitionValues?) {
        if (transitionValues == null || transitionValues.view !is RoundedImageView) return
        val scale = (transitionValues.view as RoundedImageView).scaleFactor
        Logger.d("scale capture => $scale")
        transitionValues.values.put(SCALE_FACTOR, scale)
    }

    override fun createAnimator(
        sceneRoot: ViewGroup?,
        startValues: TransitionValues?,
        endValues: TransitionValues?
    ): Animator? {
        if (startValues != null && endValues != null && endValues.view is RoundedImageView) {
            val endRoundedImageView = endValues.view as RoundedImageView

            if (!endRoundedImageView.showRoundedBackground) return null

            val startScale = startValues.values[SCALE_FACTOR] as Float
            val endScale = endValues.values[SCALE_FACTOR] as Float

            endRoundedImageView.scaleFactor = startScale
            Logger.d("start scale => $startScale")
            Logger.d("end scale => $endScale")

            return ObjectAnimator.ofFloat(endRoundedImageView, "scaleFactor", 0.8f)
        }

        return null
    }
}