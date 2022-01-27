package com.example.testandroidapp.ui.page_viewer

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.testandroidapp.ui.conversion.ConversionFragment
import com.example.testandroidapp.ui.valute_list.ValuteListFragment

class FragmentAdapter(
    fm: FragmentManager,
    lifecycle: Lifecycle
): FragmentStateAdapter(fm, lifecycle) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return ValuteListFragment()
            1 -> return ConversionFragment()
        }
        return ValuteListFragment()
    }
}