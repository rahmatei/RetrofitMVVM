package com.example.oghatmvvm.OghatMVVM

import android.util.Log
import com.example.oghatmvvm.RetrofitInterface
import com.google.gson.Gson
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class OghatModel {
    val disposable = CompositeDisposable()

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://api.aladhan.com/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    fun GetShareiData(city: String, country: String, method: String): Observable<Oghat> {
        var oghat = Oghat()
        val timingInterface = retrofit.create(RetrofitInterface::class.java)
        disposable.add(
            timingInterface.GetSharei(city, country, method)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    oghat.sunrise = it.data.timings.Sunrise
                    oghat.sunset = it.data.timings.Sunset
                    Log.d("Sunrise", oghat.sunrise)
                }, {
                    Log.d("Error", it.message)
                })
        )
        return Observable.just(oghat)
    }
}