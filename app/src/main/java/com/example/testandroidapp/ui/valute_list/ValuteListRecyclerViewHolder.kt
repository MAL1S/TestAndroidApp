package com.example.testandroidapp.ui.valute_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.testandroidapp.R
import com.example.testandroidapp.data.models.Valute
import com.example.testandroidapp.databinding.ValuteItemBinding

class ValuteListRecyclerViewHolder(
    inflater: LayoutInflater,
    parent: ViewGroup
) : RecyclerView.ViewHolder(inflater.inflate(R.layout.valute_item, parent, false)) {

    private val mBinding by viewBinding(ValuteItemBinding::bind)

    fun bind(valute: Valute) {
        val nominal = valute.Nominal.toDouble()
        val value = valute.Value.toDouble() / nominal
        val previous = valute.Previous.toDouble() / nominal

        mBinding.tvValuteName.text = valute.Name
        mBinding.tvValuteCharCode.text = valute.CharCode
        mBinding.tvValuteValue.text = String.format("%.4f", value)
        val change = value - previous
        val plusSign = if (change > 0) "+" else ""
        mBinding.tvValuteChange.text = "($plusSign${String.format("%.4f", change)}) руб."

        if (change > 0) mBinding.ivChangeImage.setImageResource(R.drawable.ic_arrow_up)
        else if (change < 0) mBinding.ivChangeImage.setImageResource(R.drawable.ic_arrow_down)
    }
}