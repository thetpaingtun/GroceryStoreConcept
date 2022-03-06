package com.gmail.tpt.grocerystore.views

import android.animation.Animator
import android.animation.ObjectAnimator
import android.content.Context
import android.transition.Transition
import android.transition.TransitionValues
import android.util.AttributeSet
import android.view.ViewGroup

class RoundedImageScaleFactorTranstion @JvmOverloads constructor(
    context: Context? = null,
    attrs: AttributeSet? = null
) :
    Transition(context, attrs) {


    override fun captureStartValues(transitionValues: TransitionValues?) {
    }


    override fun captureEndValues(transitionValues: TransitionValues?) {
    }


    override fun createAnimator(
        sceneRoot: ViewGroup?,
        startValues: TransitionValues?,
        endValues: TransitionValues?
    ): Animator? {
        if (startValues != null && endValues != null && endValues.view is RoundedImageView) {
            val endRoundedImageView = endValues.view as RoundedImageView

            if (!endRoundedImageView.showRoundedBackground) return null

            return ObjectAnimator.ofFloat(
                endRoundedImageView,
                "scaleFactor",
                RoundedImageView.IMAGE_SCALE_FACTOR
            )
        }

        return null
    }
}