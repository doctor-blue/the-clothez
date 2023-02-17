package com.starlight.module.resource.utils

import android.view.View
import android.view.animation.Animation
import android.view.animation.TranslateAnimation

// slide the view from below itself to the current position
fun slideLeftToShow(view: View, onStart: () -> Unit, onEnd: () -> Unit) {
    val animate = TranslateAnimation(
        view.width.toFloat(),  // fromXDelta
        0f,  // toXDelta
        0f,  // fromYDelta
        0f
    ) // toYDelta
    animate.setAnimationListener(object : Animation.AnimationListener {
        override fun onAnimationStart(animation: Animation?) {
            onStart()
        }

        override fun onAnimationEnd(animation: Animation?) {
            onEnd()
        }

        override fun onAnimationRepeat(animation: Animation?) {}
    })
    animate.duration = 600
    animate.fillAfter = false
    view.startAnimation(animate)
}

// slide the view from its current position to below itself
fun slideLeftToHide(view: View, onStart: () -> Unit, onEnd: () -> Unit) {
    val animate = TranslateAnimation(
        0f,  // fromXDelta
        -view.width.toFloat(),  // toXDelta
        0f,  // fromYDelta
        0f
    ) // toYDelta
    animate.duration = 600
    animate.fillAfter = false
    animate.setAnimationListener(object : Animation.AnimationListener {
        override fun onAnimationStart(animation: Animation?) = onStart()
        override fun onAnimationEnd(animation: Animation?) = onEnd()
        override fun onAnimationRepeat(animation: Animation?) {}
    })
    view.startAnimation(animate)
}

// slide the view from below itself to the current position
fun slideRightToShow(view: View, onStart: () -> Unit, onEnd: () -> Unit) {
    val animate = TranslateAnimation(
        -view.width.toFloat(),  // fromXDelta
        0f,  // toXDelta
        0f,  // fromYDelta
        0f
    ) // toYDelta
    animate.setAnimationListener(object : Animation.AnimationListener {
        override fun onAnimationStart(animation: Animation?) {
            onStart()
        }

        override fun onAnimationEnd(animation: Animation?) {
            onEnd()
        }

        override fun onAnimationRepeat(animation: Animation?) {}
    })
    animate.duration = 600
    animate.fillAfter = false
    view.startAnimation(animate)
}

// slide the view from its current position to below itself
fun slideRightToHide(view: View, onStart: () -> Unit, onEnd: () -> Unit) {
    val animate = TranslateAnimation(
        0f,  // fromXDelta
        view.width.toFloat(),  // toXDelta
        0f,  // fromYDelta
        0f
    ) // toYDelta
    animate.duration = 600
    animate.fillAfter = false
    animate.setAnimationListener(object : Animation.AnimationListener {
        override fun onAnimationStart(animation: Animation?) {
            onStart()
        }

        override fun onAnimationEnd(animation: Animation?) {
            onEnd()
        }

        override fun onAnimationRepeat(animation: Animation?) {}
    })
    view.startAnimation(animate)
}

fun slideToNextView(startView: View, endView: View) {
    slideLeftToHide(startView, {
        endView.visibility = View.VISIBLE
        slideLeftToShow(endView, {}, {})
    }, { startView.visibility = View.GONE })
}

fun slideToPrevView(startView: View, endView: View) {
    slideRightToHide(startView, {
        endView.visibility = View.VISIBLE
        slideRightToShow(endView, {}, {})
    }) { startView.visibility = View.GONE }
}

