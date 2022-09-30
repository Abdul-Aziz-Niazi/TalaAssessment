package com.abdulaziz.tala.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.cardview.widget.CardView
import androidx.core.content.withStyledAttributes
import androidx.databinding.BindingAdapter
import com.abdulaziz.tala.R
import com.abdulaziz.tala.home.data.LoanResponseItem

class TextOverImageCustomLayout @JvmOverloads constructor(
    context: Context, attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CardView(context, attributeSet, defStyleAttr) {
    private var image: RelativeLayout
    private var label: TextView
    var loanResponseItem:LoanResponseItem?=null

    var labelText :String=""
        set(value) {
            field = value
            label.text = field
        }

    var imageResource :Int=0
        set(value) {
            field = value
            image.setBackgroundResource(field)
        }

    init {
        val view = inflate(context, R.layout.layout_text_over_image, this)
        label = view.findViewById(R.id.titleText)
        image = view.findViewById(R.id.rootLayout)
        getAttributes(context,attributeSet)
    }
    private fun getAttributes(context: Context, attributeSet: AttributeSet?) {
        context.withStyledAttributes(attributeSet, R.styleable.TextOverImageCustomLayout) {
            labelText = getString(R.styleable.TextOverImageCustomLayout_quoteText).orEmpty()
            imageResource = getResourceId(R.styleable.TextOverImageCustomLayout_coverImage,0)
        }
    }
}
@BindingAdapter("setLoanData")
fun TextOverImageCustomLayout.setLoanData(loanResponseItem: LoanResponseItem){
    this.loanResponseItem = loanResponseItem
}