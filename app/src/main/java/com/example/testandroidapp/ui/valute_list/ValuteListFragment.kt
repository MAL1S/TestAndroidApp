package com.example.testandroidapp.ui.valute_list

import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.testandroidapp.R
import com.example.testandroidapp.databinding.FragmentValuteListBinding
import com.example.testandroidapp.ui.ValuteViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class ValuteListFragment : Fragment() {

    private val mBinding by viewBinding(FragmentValuteListBinding::bind)

    private val mViewModel by viewModels<ValuteViewModel>()

    private lateinit var mAdapter: ValuteListRecyclerAdapter

    private var rcvState: Parcelable? = null

    private var timer = Timer()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_valute_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initVariables()
        initObservers()

        mViewModel.getValuteList()
    }

    private fun initVariables() {
        mAdapter = ValuteListRecyclerAdapter(emptyList())
        mBinding.rcvValute.adapter = mAdapter

        mBinding.fabRefresh.setOnClickListener {
            rcvState = mBinding.rcvValute.layoutManager?.onSaveInstanceState()
            mViewModel.getValuteList(ifUpdate = true)
        }
    }

    private fun initObservers() {
        mViewModel.valuteListLiveData.observe(this, {
            mAdapter = ValuteListRecyclerAdapter(it)
            mBinding.rcvValute.adapter = mAdapter
            mAdapter.notifyDataSetChanged()
            mBinding.rcvValute.layoutManager?.onRestoreInstanceState(rcvState)
        })
    }

    private fun startValuteUpdateTimer() {
        timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                rcvState = mBinding.rcvValute.layoutManager?.onSaveInstanceState()
                mViewModel.getValuteList(ifUpdate = true)
                startValuteUpdateTimer()
            }
        }, 600000) //10 minutes
    }

    override fun onResume() {
        super.onResume()
        Log.d("testing", "STARTED")
        startValuteUpdateTimer()
    }

    override fun onPause() {
        timer.cancel()
        Log.d("testing", "PAUSED")
        super.onPause()
    }

    override fun onDestroy() {
        mViewModel.valuteListLiveData.removeObservers(this)
        super.onDestroy()
    }
}
