package com.starlight.module.uicore.utils

import java.util.*

class TimerUtils {
    private var timer = Timer()
    private var timerTask: TimerTask? = null


    fun start(onRun: () -> Unit, delay: Long) {
        cancel()
        timerTask = object : TimerTask() {
            override fun run() {
                onRun()
            }

        }
        timer = Timer()
        timer.scheduleAtFixedRate(timerTask, delay, 1000)
    }

    fun cancel() {
        try {
            timerTask?.cancel()
            timer.purge()
            timer.cancel()
        } catch (e: Exception) {

        }
    }
}