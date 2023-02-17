package com.starlight.module.resource.widget

import android.animation.FloatEvaluator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.starlight.module.resource.utils.convertDpToPixel
import kotlin.math.abs

class RangeView : View {
    interface SeekBarViewListener {
        fun onSeekBarProgressStart(processBtn1Value: Float, processBtn2Value: Float)
        fun onSeekBarProgressChange(processBtn1Value: Float, processBtn2Value: Float)
        fun onSeekBarProgressStop(processBtn1Value: Float, processBtn2Value: Float)
    }

    private val heightSwitchDPI = 35f
    private val sizeProcessBarDPI = 3f

    constructor(context: Context?) : super(context) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    private var paint1Button: Paint? = null
    private var paint2Button: Paint? = null

    private var paintProgressBarActive: Paint? = null
    private var paintProgressBarUnactive: Paint? = null
    private var heightSeekBar = 0
    private var sizeProcessBar = 0
    private var colorProgressActive = 0
    private var colorProgressUnactive = 0
    private var radiusButton = 0f
    private var value1 = 0f
    private var value2 = 0f
    private var processBtn1Value = 0f
    private var processBtn2Value = 0f


    private var c1x = -1f
    private var c1y = 0f
    private var c2x = -1f
    private var c2y = 0f


    // -1 = not pressing, 0 = button 1 pressing, 1 = button 2 press
    private var pressState = -1

    private var value1Animator: ValueAnimator? = null
    private var value2Animator: ValueAnimator? = null

    var seekBarViewListener: SeekBarViewListener? = null

    private fun init() {
        heightSeekBar = context.convertDpToPixel(heightSwitchDPI).toInt()
        sizeProcessBar = context.convertDpToPixel(sizeProcessBarDPI).toInt()
        radiusButton = heightSeekBar * 0.35f

        paint1Button = Paint(Paint.ANTI_ALIAS_FLAG)
        paint1Button!!.style = Paint.Style.FILL
        paint1Button!!.setShadowLayer(radiusButton / 4, 0f, 0f, Color.parseColor("#CCCCCC"))

        paint2Button = Paint(Paint.ANTI_ALIAS_FLAG)
        paint2Button!!.style = Paint.Style.FILL
        paint2Button!!.setShadowLayer(radiusButton / 4, 0f, 0f, Color.parseColor("#CCCCCC"))

        paintProgressBarActive = Paint(Paint.ANTI_ALIAS_FLAG)
        paintProgressBarActive!!.style = Paint.Style.FILL
        paintProgressBarUnactive = Paint(Paint.ANTI_ALIAS_FLAG)
        paintProgressBarUnactive!!.style = Paint.Style.FILL
        colorProgressActive = Color.parseColor("#7685fc")
        colorProgressUnactive = Color.parseColor("#eeeeee")
        paintProgressBarActive!!.color = colorProgressActive
        paintProgressBarUnactive!!.color = colorProgressUnactive
        value1 = 1f
        processBtn1Value = 0f

        value1Animator = ValueAnimator()
        value1Animator!!.duration = 150
        value1Animator!!.setEvaluator(FloatEvaluator())
        value1Animator!!.addUpdateListener { valueAnimator: ValueAnimator ->
            c1x = valueAnimator.animatedValue as Float
            invalidate()
        }
        value2Animator = ValueAnimator()
        value2Animator!!.duration = 150
        value2Animator!!.setEvaluator(FloatEvaluator())
        value2Animator!!.addUpdateListener { valueAnimator: ValueAnimator ->
            c2x = valueAnimator.animatedValue as Float
            invalidate()
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        setMeasuredDimension(
            widthSize,
            heightSeekBar + paddingTop + paddingBottom
        )
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val left = paddingLeft + heightSeekBar / 2
        val top = paddingTop
        val right = width - (paddingRight + heightSeekBar / 2)

        c1y = (top + heightSeekBar / 2).toFloat()
        c2y = (top + heightSeekBar / 2).toFloat()

        if (c1x == -1f) {
            val percent = processBtn1Value / value1 * 100
            c1x = left + (right - left) * percent / 100
        }

        if (c2x == -1f) {
            val percent = 100f
            c2x = left + (right - left) * percent / 100
        }

        if (c1x == c2x) return

        val topProcessBar = c1y - sizeProcessBar / 2
        val bottomProcessBar = c1y + sizeProcessBar / 2

        canvas.drawRect(
            left.toFloat(),
            topProcessBar,
            right.toFloat(),
            bottomProcessBar,
            paintProgressBarUnactive!!
        )

        canvas.drawRect(
            c2x,
            topProcessBar,
            c1x,
            bottomProcessBar,
            paintProgressBarActive!!
        )

        paint1Button!!.color = Color.parseColor("#f5f5f5")
        paint2Button!!.color = Color.parseColor("#f5f5f5")

        canvas.drawCircle(c1x, c1y, radiusButton, paint1Button!!)

        canvas.drawCircle(c2x, c2y, radiusButton, paint2Button!!)

    }

    fun setValue(value: Float) {
        this.value1 = value
        processBtn1Value = 0f
        c1x = -1f
        invalidate()
    }

    fun setProcessValue(process1Value: Float, process2Value: Float) {
        setProcessValue(process1Value, process2Value, false)
    }

    fun setProcessValue(process1Value: Float, process2Value: Float, animation: Boolean) {
        if (process1Value < value1) this.processBtn1Value =
            process1Value else this.processBtn1Value =
            value1
        if (c1x != -1f || c2x != -1f) {
            val left = paddingLeft + heightSeekBar / 2
            val right = width - (paddingRight + heightSeekBar / 2)
          if (c1x != -1f){
              val percent = process1Value / value1 * 100
              val newcx = left + (right - left) * percent / 100
              if (animation) {
                  value1Animator!!.setFloatValues(c1x, newcx)
                  value1Animator!!.start()
              } else {
                  c1x = newcx
                  invalidate()
              }
          }
            if (c2x != -1f){
                val percent = process2Value / value1 * 100
                val newcx = left + (right - left) * percent / 100
                if (animation) {
                    value2Animator!!.setFloatValues(c2x, newcx)
                    value2Animator!!.start()
                } else {
                    c2x = newcx
                    invalidate()
                }
            }

        } else invalidate()
    }

    override fun onTouchEvent(e: MotionEvent): Boolean {
        //If the finger move to ouside
        var touchX = e.x
        val touchY = e.y
        val left = paddingLeft + heightSeekBar / 2
        val right = width - (paddingRight + heightSeekBar / 2)
        val width = right - left
        when (e.action) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_UP -> {
                val top = paddingTop
                val bottom = top + heightSeekBar
                if (touchX > left && touchX < right && touchY > top && touchY < bottom) {
                    if (e.action == MotionEvent.ACTION_DOWN) {
//                        isPressing = true
                        pressState = if (abs(touchX - c1x) > abs(touchX - c2x)) {
                            1
                        } else {
                            0
                        }

                        if (pressState == 0)
                            if (touchX > c1x - heightSeekBar && touchX < c1x + heightSeekBar) {
                                c1x = touchX

                                invalidate()
                            } else {
                                value1Animator!!.setFloatValues(c1x, touchX)
                                value1Animator!!.start()
                                //Start Animation;
                            }
                        else
                            if (touchX > c2x - heightSeekBar && touchX < c2x + heightSeekBar) {
                                c2x = touchX

                                invalidate()
                            } else {
                                value2Animator!!.setFloatValues(c2x, touchX)
                                value2Animator!!.start()
                                //Start Animation;
                            }

                        val progress = (touchX - left) / width
                        if (pressState == 0)
                            processBtn1Value = progress
                        if (pressState == 1)
                            processBtn2Value = progress
                        if (seekBarViewListener != null) seekBarViewListener!!.onSeekBarProgressStart(
                            processBtn1Value, processBtn2Value
                        )
                        attemptClaimDrag()
                        return true
                    } else if (pressState >= 0) {
//                        c1x = touchX
                        if (abs(touchX - c1x) > abs(touchX - c2x)) {
                            c2x = touchX
                        } else {
                            c1x = touchX
                        }
                        val progress = (touchX - left) / width
                        if (pressState == 0)
                            processBtn1Value = progress
                        if (pressState == 1)
                            processBtn2Value = progress
                        pressState = -1
                        if (seekBarViewListener != null) seekBarViewListener!!.onSeekBarProgressStop(
                            processBtn1Value,
                            processBtn2Value
                        )
                        attemptClaimDrag()
                        invalidate()
                    }
                    return true
                } else if (e.action == MotionEvent.ACTION_UP && pressState >= 0) {
                    if (touchX < left) touchX = left.toFloat() else if (touchX > right) touchX =
                        right.toFloat()
//                    c1x = touchX
                    if (abs(touchX - c1x) > abs(touchX - c2x)) {
                        c2x = touchX
                    } else {
                        c1x = touchX
                    }

                    val progress = (touchX - left) / width
                    if (pressState == 0)
                        processBtn1Value = progress
                    if (pressState == 1)
                        processBtn2Value = progress

                    pressState = -1
                    if (seekBarViewListener != null) seekBarViewListener!!.onSeekBarProgressStop(
                        processBtn1Value,
                        processBtn2Value
                    )
                    attemptClaimDrag()
                    invalidate()
                }
                return false
            }
            MotionEvent.ACTION_MOVE -> return if (pressState >= 0) {
                if (touchX > left && touchX < right) {
//                    c1x = touchX
                    if (abs(touchX - c1x) > abs(touchX - c2x)) {
                        c2x = touchX
                    } else {
                        c1x = touchX
                    }

                    if (seekBarViewListener != null) {
                        val progress = (touchX - left) / width
                        if (pressState == 0)
                            processBtn1Value = progress
                        if (pressState == 1)
                            processBtn2Value = progress
                        seekBarViewListener!!.onSeekBarProgressChange(
                            processBtn1Value,
                            processBtn2Value
                        )
                    }
                    invalidate()
                    attemptClaimDrag()
                }
                true
            } else false
        }
        return super.onTouchEvent(e)
    }

    private fun attemptClaimDrag() {
        parent.requestDisallowInterceptTouchEvent(true)
    }

    fun getValue(): Float {
        return value1
    }

    fun getProcessValue1(): Float {
        return processBtn1Value
    }
    fun getProcessValue2(): Float {
        return processBtn2Value
    }
}