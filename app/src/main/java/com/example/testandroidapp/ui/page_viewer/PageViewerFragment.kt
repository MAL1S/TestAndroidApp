package com.example.testandroidapp.ui.page_viewer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.widget.ViewPager2
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.testandroidapp.R
import com.example.testandroidapp.databinding.FragmentPageViewerBinding
import com.google.android.material.tabs.TabLayout

class PageViewerFragment : Fragment() {

    private val mBinding by viewBinding(FragmentPageViewerBinding::bind)

    private lateinit var tabLayout: TabLayout
    private lateinit var pager: ViewPager2
    private lateinit var adapter: FragmentAdapter
    private lateinit var fm: FragmentManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return inflater.inflate(R.layout.fragment_page_viewer, container, false)
    }

    override fun onStart() {
        super.onStart()

        initViewPager()
    }

    private fun initViewPager() {

        tabLayout = mBinding.tabLayout
        pager = mBinding.viewPager

        fm = parentFragmentManager
        adapter = FragmentAdapter(fm, lifecycle)
        if (pager.adapter == null) {
            pager.adapter = adapter
            tabLayout.addTab(tabLayout.newTab().setText(R.string.valutes))
            tabLayout.addTab(tabLayout.newTab().setText(R.string.conversion))
            pager.isSaveEnabled = false
        }

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                pager.currentItem = tab?.position!!
                //CURRENT_TAB = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })

        pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                tabLayout.selectTab(tabLayout.getTabAt(position))
            }
        })
    }

    override fun onDestroyView() {
        pager.adapter = null
        super.onDestroyView()
    }
}