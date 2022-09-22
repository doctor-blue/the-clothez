package com.starlight.module.uicore.custom_views.text_field

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.android.material.textfield.TextInputLayout.END_ICON_NONE
import com.google.android.material.textfield.TextInputLayout.END_ICON_PASSWORD_TOGGLE
import com.starlight.module.resource.R
import com.starlight.module.uicore.custom_views.top_app_bar.CustomViewStyle
import com.starlight.module.uicore.utils.setPreventDoubleClick

class RoundCornerWithTitleTextField @JvmOverloads constructor(
    private val ctx: Context,
    private val attributeSet: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(ctx, attributeSet, defStyle), CustomViewStyle, CustomViewStyle.ClickEvent {

    private lateinit var textFieldLabel: TextView
    private lateinit var containerTextField: TextInputLayout
    private lateinit var textField: TextInputEditText
    private lateinit var tvTextFieldEnd: TextView

    private var label = ""
    private var endText = ""
    private var isPasswordToggleEnabled = false
    private var isEndTextShow = false
    private var onTextViewOnclick: (() -> Unit)? = null

    init {
        inflateLayout(ctx, R.layout.custom_textfield, this)
        initViews()
        initAttributes()
        initEvents()
    }

    override fun initViews() {
        textFieldLabel = findViewById(R.id.tv_textfield_title)
        containerTextField = findViewById(R.id.container_textfield)
        textField = findViewById(R.id.custom_textfield)
        tvTextFieldEnd = findViewById(R.id.tv_textfield_end)
    }

    override fun initAttributes() {
        val attributes = ctx.obtainStyledAttributes(
            attributeSet,
            R.styleable.RoundCornerWithTitleTextField
        )

        label = attributes
            .getString(R.styleable.RoundCornerWithTitleTextField_setTextFieldLabel) ?: ""

        isPasswordToggleEnabled = attributes.getBoolean(
            R.styleable.RoundCornerWithTitleTextField_setTextFieldPasswordToggleEnabled,
            isPasswordToggleEnabled
        )

        isEndTextShow = attributes.getBoolean(
            R.styleable.RoundCornerWithTitleTextField_setShowEndText,
            isEndTextShow
        )

        endText = attributes.getString(
            R.styleable.RoundCornerWithTitleTextField_setEndText
        ) ?: ""

        setLabel(label)
        setPasswordToggleEnable(isPasswordToggleEnabled)
        showEndText(isEndTextShow)
        setEndText(endText)
        attributes.recycle()
    }

    /**
     * This function set label for textfield
     * @param label label to set into textfield
     */
    fun setLabel(label: String) {
        textFieldLabel.text = label
    }

    /**
     * This function help to get text in the textfield
     * @return text from textfield
     */
    fun getTextFieldText(): String {
        return textField.text?.toString() ?: ""
    }

    /**
     * this function helps to enable password toggle
     * @param isEnabled set enabled to text input layout
     */
    fun setPasswordToggleEnable(isEnabled: Boolean) {
        if (isEnabled) {
            containerTextField.endIconMode = END_ICON_PASSWORD_TOGGLE
        } else {
            containerTextField.endIconMode = END_ICON_NONE
        }
    }

    fun showEndText(isShow: Boolean) {
        tvTextFieldEnd.isVisible = isShow
    }

    fun setEndText(text: String) {
        tvTextFieldEnd.text = text
    }

    fun setOnTextViewOnClicked(onTextViewOnClicked: () -> Unit) {
        onTextViewOnclick = onTextViewOnClicked
    }

    override fun initEvents() {
        tvTextFieldEnd.setPreventDoubleClick {
            onTextViewOnclick?.invoke()
        }
    }

}