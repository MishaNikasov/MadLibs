package com.nikasov.madlibs.views

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import com.nikasov.madlibs.R

class AppImageButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : AppCompatImageView(context, attrs, defStyle) {

    init {
        val padding = (context.resources.getDimension(R.dimen.big_padding)).toInt()

        setColorFilter(ContextCompat.getColor(context, R.color.white))
        setPadding(padding, padding, padding, padding)
        background = ContextCompat.getDrawable(context, R.drawable.btn_bg)
        isClickable = true
    }

}