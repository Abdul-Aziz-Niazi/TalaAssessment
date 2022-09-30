package com.abdulaziz.tala.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.cardview.widget.CardView
import androidx.core.content.withStyledAttributes
import androidx.databinding.BindingAdapter
import com.abdulaziz.tala.R
import com.abdulaziz.tala.home.data.LoanResponseItem
import com.makeramen.roundedimageview.RoundedImageView

class ImageTextCustomCard  @JvmOverloads constructor(
    context: Context, attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CardView(context, attributeSet, defStyleAttr) {
    private var image: AppCompatImageView
    private var label: TextView
    var loanResponseItem: LoanResponseItem? = null
    set(value) {
        field = value
        labelText = field?.username.orEmpty()
    }
    var labelText :String=""
        set(value) {
            field = value
            label.text = field
        }

    var imageResource :Int=0
        set(value) {
            field = value
            image.setImageResource(field)
        }
    init {
        val view = inflate(context, R.layout.layout_image_text_card, this)
        label = view.findViewById(R.id.label)
        image = view.findViewById(R.id.icon)
        getAttributes(context, attributeSet)
    }

    private fun getAttributes(context: Context, attributeSet: AttributeSet?) {
        context.withStyledAttributes(attributeSet, R.styleable.ImageTextCustomCard) {
            labelText = getString(R.styleable.ImageTextCustomCard_itemText).orEmpty()
            imageResource = getResourceId(R.styleable.ImageTextCustomCard_icon,0)
        }
    }
}
@BindingAdapter("setLoanData")
fun ImageTextCustomCard.setLoanData(loanResponseItem: LoanResponseItem){
    this.loanResponseItem = loanResponseItem
}