package com.example.oghatmvvm

import io.reactivex.Observable
import java.util.*
import java.util.concurrent.TimeUnit

class TestModel {
    fun getUser(id: Int) = Observable.just("Hossein","Ali","Mohamad","Ahmad")
        .delay(1,TimeUnit.SECONDS)
}