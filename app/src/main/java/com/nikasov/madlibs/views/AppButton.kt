package com.nikasov.madlibs.views

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import androidx.core.content.ContextCompat
import com.nikasov.madlibs.R

class AppButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : AppTextView(context, attrs, defStyle) {

    init {
        val padding = (context.resources.getDimension(R.dimen.big_padding)).toInt()

        setPadding(padding, padding, padding, padding)
        setTextColor(ContextCompat.getColor(context, R.color.white))
        background = ContextCompat.getDrawable(context, R.drawable.btn_bg)
        gravity = Gravity.CENTER
        isClickable = true
    }

}