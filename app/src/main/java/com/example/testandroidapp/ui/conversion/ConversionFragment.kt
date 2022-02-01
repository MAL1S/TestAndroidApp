package com.example.testandroidapp.ui.conversion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.testandroidapp.R
import com.example.testandroidapp.data.models.Valute
import com.example.testandroidapp.databinding.FragmentConversionBinding
import com.example.testandroidapp.ui.ValuteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ConversionFragment : Fragment() {

    private val mBinding by viewBinding(FragmentConversionBinding::bind)

    private val mViewModel by viewModels<ValuteViewModel>()

    var list = listOf<Valute>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_conversion, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()
        initListeners()

        mViewModel.getValuteList()
    }

    private fun initListeners() {
        mBinding.btnConvert.setOnClickListener {
            for (i in list) {
                val item = mBinding.spinnerConversionTo.selectedItem.toString()

                if (i.Name == item.substring(0, item.length - 6)) {
                    val text = mBinding.etConversionFrom.text.toString()
                    val isNumber = text.toIntOrNull()

                    if (isNumber == null) {
                        mBinding.tvConversionResult.text = getString(R.string.entered_nan)
                    } else if (text.isNotEmpty()) {
                        mBinding.tvConversionResult.text = getString(
                            R.string.entered_right,
                            mBinding.etConversionFrom.text.toString()
                                .toDouble() / (i.Value.toDouble() / i.Nominal.toDouble()),
                            i.CharCode
                        )
                    } else if (text.isEmpty()) {
                        mBinding.tvConversionResult.text = getString(R.string.entered_null)
                    } else {
                        mBinding.tvConversionResult.text =
                            getString(R.string.entered_not_conversion_valute)
                    }
                }
            }
        }
    }

    private fun initObservers() {
        mViewModel.valuteListLiveData.observe(this, {
            list = it.toMutableList()
            mBinding.spinnerConversionTo.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, it.map { i -> "${i.Name} - ${i.CharCode}" })
        })
    }

    override fun onDestroy() {
        mViewModel.valuteListLiveData.removeObservers(this)
        super.onDestroy()
    }
}