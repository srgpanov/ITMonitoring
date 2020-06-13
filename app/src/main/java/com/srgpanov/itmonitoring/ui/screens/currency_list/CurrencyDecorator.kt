package com.srgpanov.itmonitoring.ui.screens.currency_list

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.RecyclerView
import com.srgpanov.itmonitoring.R

class CurrencyDecorator (
    context: Context
) : RecyclerView.ItemDecoration() {
    //  val drawable: Drawable = ContextCompat.getDrawable(context, R.drawable.custom_divider)!!
    var drawable: Drawable? = context.getDrawable(R.drawable.custom_divider)
    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight
        drawable?.let {
            val childCount = parent.adapter?.itemCount ?: 0
            for (i in 0 until childCount-1) {
                val child = parent.getChildAt(i)
                if (child != null) {
                    val params = child.layoutParams as RecyclerView.LayoutParams
                    val top = child.bottom + params.bottomMargin
                    val bottom = top + it.intrinsicHeight
                    it.setBounds(left, top, right, bottom)
                    it.draw(c)
                }
            }
        }
    }
}
