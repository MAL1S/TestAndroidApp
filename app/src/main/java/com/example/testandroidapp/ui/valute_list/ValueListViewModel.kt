package com.example.testandroidapp.ui.valute_list

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testandroidapp.data.models.Valute
import com.example.testandroidapp.data.repository.Repository
import com.example.testandroidapp.network.ValuteApiInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.util.*
import javax.inject.Inject


@HiltViewModel
class ValueListViewModel @Inject constructor(
    private val apiService: ValuteApiInterface,
    private val repository: Repository
) : ViewModel() {

    val valuteListLiveData: MutableLiveData<List<Valute>> = MutableLiveData()

    @SuppressLint("CheckResult")
    fun getValuteList(ifUpdate: Boolean = false) {
        apiService.getValutes()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val repositoryValuteList = repository.getValuteList()
                if (repositoryValuteList != null && !ifUpdate) {
                    valuteListLiveData.postValue(repositoryValuteList)
                    Log.d("testing", "not updated")
                } else {
                    Log.d("testing", "updated")
                    var str =
                        "[" + it.valute.toString()
                            .subSequence(1, it.valute.toString().lastIndex) + "]"
                    val regex = "[A-Z]{3}=".toRegex()

                    str = str
                        .replace(regex, "")
                        .replace("=", "\":\"")
                        .replace("{", "{\"")
                        .replace(", ", "\", \"")
                        .replace("\", \"{", ", {")
                        .replace("}", "\"}")

                    val valuteList = Json.decodeFromString<List<Valute>>(str)
                    repository.saveValuteList(valuteList)
                    valuteListLiveData.postValue(valuteList)
                }
            }, {
                Log.e("valute_list", it.message!!)
            })
    }
}