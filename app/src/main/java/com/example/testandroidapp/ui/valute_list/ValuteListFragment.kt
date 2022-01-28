package com.example.testandroidapp.ui.valute_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.testandroidapp.R
import com.example.testandroidapp.data.models.Valute
import com.example.testandroidapp.databinding.FragmentValuteListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ValuteListFragment : Fragment() {

    private val mBinding by viewBinding(FragmentValuteListBinding::bind)

    private val mViewModel by viewModels<ValueListViewModel>()

    private lateinit var mAdapter: ValuteListRecyclerAdapter

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
            mViewModel.getValuteList()
        }
    }

    private fun initObservers() {
        mViewModel.valuteListLiveData.observe(this, {
            mAdapter = ValuteListRecyclerAdapter(it)
            mBinding.rcvValute.adapter = mAdapter
            mAdapter.notifyDataSetChanged()
        })
    }
}