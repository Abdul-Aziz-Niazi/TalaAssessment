package com.abdulaziz.tala.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.cardview.widget.CardView
import androidx.databinding.BindingAdapter
import com.abdulaziz.tala.R
import com.abdulaziz.tala.home.data.LoanLevels
import com.abdulaziz.tala.home.data.LoanResponseItem
import com.makeramen.roundedimageview.RoundedImageView
import com.abdulaziz.tala.managers.getLoanLevel

class TalaStatusLayout  @JvmOverloads constructor(
    context: Context, attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CardView(context, attributeSet, defStyleAttr) {
    private var image: AppCompatImageView
    private var label: TextView
    private var description: TextView
    private var viewStatus: TextView

    var loanResponseItem: LoanResponseItem?=null
        set(value) {
            field = value
            when(field?.getLoanLevel()){
                is LoanLevels.Silver -> image.setImageResource(R.drawable.img_silver_badge_large)
                is LoanLevels.Bronze -> image.setImageResource(R.drawable.img_bronze_badge_large)
                is LoanLevels.Gold -> image.setImageResource(R.drawable.img_gold_badge_large)
                is LoanLevels.DEFAULT -> image.setImageResource(R.drawable.img_blue_badge_large)
                else ->  image.setImageResource(R.drawable.img_blue_badge_large)
            }
        }

    init {
        val view = inflate(context, R.layout.layout_tala_status, this)
        image = view.findViewById(R.id.talaStatusImage)
        label = view.findViewById(R.id.myTalaStatusText)
        description = view.findViewById(R.id.statusDescription)
        viewStatus = view.findViewById(R.id.btnViewStatus)

    }
}
@BindingAdapter("setLoanData")
fun TalaStatusLayout.setLoanData(loanResponseItem: LoanResponseItem){
    this.loanResponseItem = loanResponseItem
}