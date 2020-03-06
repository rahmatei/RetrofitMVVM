package com.example.oghatmvvm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

val disposable = CompositeDisposable()

class TestViewModel : ViewModel() {

    private val model = TestModel()
    val userResponse = MutableLiveData<String>()
    var username=""
    fun GetUserData(id: Int) {
        disposable.add(
            model.getUser(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    userResponse.value = it
                    //Log.d("MVVM",it.toString())
                }, {
                    Log.d("MVVM", it.message)
                })
        )
    }

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }
}