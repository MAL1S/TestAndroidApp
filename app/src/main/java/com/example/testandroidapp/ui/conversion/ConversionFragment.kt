package com.example.testandroidapp.ui.conversion

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.testandroidapp.R
import com.example.testandroidapp.data.models.Valute
import com.example.testandroidapp.databinding.FragmentConversionBinding
import com.example.testandroidapp.databinding.FragmentValuteListBinding
import com.example.testandroidapp.ui.valute_list.ValueListViewModel
import com.example.testandroidapp.ui.valute_list.ValuteListRecyclerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ConversionFragment : Fragment() {

    private val mBinding by viewBinding(FragmentConversionBinding::bind)

    private val mViewModel by viewModels<ValueListViewModel>()

    var list = mutableListOf<Valute>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_conversion, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initVariables()
        initObservers()

        mViewModel.getValuteList()
    }

    private fun initVariables() {
        mBinding.btnConvert.setOnClickListener {
            for (i in list) {
                if (i.CharCode == mBinding.spinnerConversionTo.selectedItem) {
                    when {
                        mBinding.etConversionFrom.text.toString().isNotEmpty() -> {
                            mBinding.tvConversionResult.text =
                                String.format("%.4f",
                                    mBinding.etConversionFrom.text.toString()
                                        .toDouble() / (i.Value.toDouble() / i.Nominal.toDouble())
                                ) + " ${i.CharCode}"
                        }
                        mBinding.etConversionFrom.text.toString().isEmpty() -> {
                            mBinding.tvConversionResult.text = "Поле ввода не заполнено"
                        }
                        else -> {
                            mBinding.tvConversionResult.text = "Валюта для конвертации не выбрана"
                        }
                    }
                }
            }
        }
    }

    private fun initObservers() {
        mViewModel.valuteListLiveData.observe(this, {
            list = it.toMutableList()
            mBinding.spinnerConversionTo.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, it.map { i -> i.CharCode })
        })
    }
}