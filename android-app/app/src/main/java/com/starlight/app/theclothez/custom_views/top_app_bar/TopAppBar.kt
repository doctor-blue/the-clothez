package com.starlight.app.theclothez.custom_views.top_app_bar

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.starlight.app.theclothez.R

class TopAppBar @JvmOverloads constructor(
    private val ctx: Context,
    private val attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(ctx, attributeSet, defStyleAttr), CustomViewStyle, CustomViewStyle.ClickEvent {

    private lateinit var tvTitle: TextView
    private lateinit var btnBack: ImageView
    private var onBackButtonTapped: (() -> Unit)? = null

    private var titleDefault: String = ""

    init {
        inflateLayout(ctx, R.layout.custom_top_app_bar, this)
        initViews()
        initAttributes()
        initEvents()
    }

    override fun initViews() {
        tvTitle = findViewById(R.id.tv_title_top_bar)
        btnBack = findViewById(R.id.ic_start_top_bar)
    }

    override fun initAttributes() {
        val attributes = ctx.obtainStyledAttributes(attributeSet, R.styleable.TopAppBar)
        titleDefault = attributes.getString(R.styleable.TopAppBar_setTitle) ?: ""
        setTitle(titleDefault)
        attributes.recycle()
    }

    override fun initEvents() {
        btnBack.setOnClickListener {
            onBackButtonTapped?.invoke()
        }
    }

    /**
     * This function use for set clickListener for start icon
     * @param onBackButtonTapped listener callback
     */
    fun setOnBackButtonTapped(onBackButtonTapped: () -> Unit) {
        this.onBackButtonTapped = onBackButtonTapped
    }

    /**
     * This function use for set text for top bar title
     * @param title text to set into top bar
     */
    fun setTitle(title: String) {
        tvTitle.text = title
    }

    /**
     * This function use for set title color
     * @param colorId Color Resource
     */
    fun setTitleColor(colorId: Int) {
        tvTitle.setTextColor(colorId)
    }

}