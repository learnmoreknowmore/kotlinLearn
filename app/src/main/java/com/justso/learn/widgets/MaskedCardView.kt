package com.justso.learn.widgets

import android.content.Context
import android.graphics.Canvas
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import com.google.android.material.card.MaterialCardView
import com.google.android.material.shape.ShapeAppearanceModel
import com.google.android.material.shape.ShapeAppearancePathProvider
import com.justso.learn.R

class MaskedCardView @JvmOverloads constructor
    (
    context: Context,
    attributes: AttributeSet? = null,
    defStyle: Int = R.attr.materialCardViewStyle
) : MaterialCardView(context, attributes, defStyle) {
    private val providerPath = ShapeAppearancePathProvider()
    private val path = Path()
    private val shapeAppearance: ShapeAppearanceModel = ShapeAppearanceModel(
        context,
        attributes,
        defStyle,
        R.style.Widget_MaterialComponents_CardView
    )
    private val rectF: RectF = RectF(0F, 0F, 0F, 0F)

    override fun onDraw(canvas: Canvas?) {
        canvas?.clipPath(path)
        super.onDraw(canvas)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        rectF.right = w.toFloat()
        rectF.bottom = h.toFloat()
        providerPath.calculatePath(shapeAppearanceModel, 1F, rectF, path)
        super.onSizeChanged(w, h, oldw, oldh)
    }
}