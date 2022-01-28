package com.example.testandroidapp.ui.valute_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testandroidapp.data.models.Valute
import com.example.testandroidapp.data.models.ValuteList

class ValuteListRecyclerAdapter(
    private val valuteList: List<Valute>
): RecyclerView.Adapter<ValuteListRecyclerViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ValuteListRecyclerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ValuteListRecyclerViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: ValuteListRecyclerViewHolder, position: Int) {
        val valute: Valute = valuteList[position]
        holder.bind(valute)
    }

    override fun getItemCount(): Int = valuteList.size
}