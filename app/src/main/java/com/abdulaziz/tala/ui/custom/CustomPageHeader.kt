package com.abdulaziz.tala.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.withStyledAttributes
import androidx.databinding.BindingAdapter
import com.abdulaziz.tala.R
import com.abdulaziz.tala.home.data.LoanResponseItem
import com.abdulaziz.tala.home.data.LoanStatus
import com.abdulaziz.tala.managers.getStatus
import com.abdulaziz.tala.managers.hide
import com.abdulaziz.tala.managers.show
import com.google.android.material.button.MaterialButton
import com.makeramen.roundedimageview.RoundedImageView

class CustomPageHeader @JvmOverloads constructor(
    context: Context, attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CardView(context, attributeSet, defStyleAttr) {
    private var image: RoundedImageView
    private var label: TextView
    private var description: TextView
    private var loanAmount: TextView
    private var btnApply: MaterialButton
    private var howToRepay: TextView

    var loanResponseItem: LoanResponseItem? = null
        set(value) {
            field = value
            loanAmount.hide()
            howToRepay.hide()
            when (field?.getStatus()) {
                is LoanStatus.Approved -> approvedLoan()
                is LoanStatus.Paid -> fullyPaid()
                is LoanStatus.Due -> due()
                is LoanStatus.DEFAULT -> applyForALoan()
                else -> applyForALoan()
            }
        }

    var labelText: String = ""
        set(value) {
            field = value
            label.text = field
        }
    var descText: String = ""
        set(value) {
            field = value
            description.text = field
        }

    var showHeader: Boolean = false
        set(value) {
            field = value
            image.visibility = if (field) VISIBLE else GONE
        }

    var imageResource: Int = 0
        set(value) {
            field = value
            image.setImageResource(field)
        }

    init {
        val view = inflate(context, R.layout.layout_custom_page_header, this)
        label = view.findViewById(R.id.label)
        image = view.findViewById(R.id.headerImage)
        description = view.findViewById(R.id.description)
        loanAmount = view.findViewById(R.id.loanAmount)
        btnApply = view.findViewById(R.id.btnApplyNow)
        howToRepay = view.findViewById(R.id.btnHowToRepay)
        getAttributes(context, attributeSet)
    }

    private fun getAttributes(context: Context, attributeSet: AttributeSet?) {
        context.withStyledAttributes(attributeSet, R.styleable.CustomPageHeader) {
            labelText = getString(R.styleable.CustomPageHeader_label).orEmpty()
            descText = getString(R.styleable.CustomPageHeader_description).orEmpty()
            showHeader = getBoolean(R.styleable.CustomPageHeader_showHeader, false)
            imageResource = getResourceId(R.styleable.CustomPageHeader_headerImage, 0)
        }
    }

    private fun approvedLoan() {
        labelText = context.getString(R.string.your_application_approved)
        descText = context.getString(R.string.you_have_been_approved,loanResponseItem?.currency.orEmpty(), loanResponseItem?.loan?.approved?.toString().orEmpty())
        btnApply.text = context.getString(R.string.view_loan_offer)
    }

    private fun fullyPaid() {
        labelText = context.getString(R.string.your_loan_is_paid)
        descText = context.getString(R.string.apply_for_another_loan,loanResponseItem?.currency,loanResponseItem?.loan?.approved?.toString().orEmpty())
        btnApply.text = context.getString(R.string.apply_now)
    }

    private fun due() {
        labelText = context.getString(R.string.you_are_on_track)
        loanAmount.text = loanResponseItem?.currency + loanResponseItem?.loan?.due.toString()
        loanAmount.show()
        howToRepay.show()
        btnApply.text = context.getString(R.string.make_payment)
        descText = context.getString(R.string.is_due_tomorrow)
    }

    private fun applyForALoan() {
        labelText = context.getString(R.string.apply_for_loan)
        descText = context.getString(R.string.repay_on_time_to_get,loanResponseItem?.currency.orEmpty(),loanResponseItem?.loan?.approved?.toString().orEmpty())
        btnApply.text = context.getString(R.string.apply_now)
    }
}

@BindingAdapter("setLoanData")
fun CustomPageHeader.setLoanData(loanResponseItem: LoanResponseItem) {
    this.loanResponseItem = loanResponseItem
}