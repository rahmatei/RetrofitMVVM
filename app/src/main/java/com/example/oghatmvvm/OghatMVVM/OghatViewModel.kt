package com.example.oghatmvvm.OghatMVVM

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import kotlin.math.log

class OghatViewModel : ViewModel() {
    private val oghat_Model = OghatModel()

    val dispose = CompositeDisposable()
    private val ResponseSharei = MutableLiveData<Oghat>()

    fun GetShareidata(city: String, country: String, method: String) {
        dispose.add(
            oghat_Model.GetShareiData(city, country, method)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    ResponseSharei.value = it
                }, {
                    Log.d("Erorr ViewModel", it.message)
                })
        )
        //oghat_Model.GetShareiData(city, country, method)
    }

    fun GetLiveResponseSharei(): LiveData<Oghat> {
        return ResponseSharei
    }

    override fun onCleared() {
        dispose.dispose()
        super.onCleared()
    }

}