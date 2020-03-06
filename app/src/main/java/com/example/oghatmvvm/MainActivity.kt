package com.example.oghatmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.jakewharton.rxbinding3.widget.textChanges
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    private val disposable = CompositeDisposable()

    lateinit var viewmodel:TestViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        viewmodel=ViewModelProvider(this).get(TestViewModel::class.java)

        viewmodel.userResponse.observe(this, Observer {
          /*  Toast.makeText(this,it.toString(),Toast.LENGTH_LONG).show()*/
           // txtType.text=it.toString()
        })

        viewmodel.GetUserData(1)

/*        edtText.textChanges().subscribe({
            txtType.text=it.toString()
        },{

        })*/

        btnSetText.setOnClickListener {
            viewmodel.username="SomeText"
        }
        txtType.setText(viewmodel.username)

        /*edtText.textChanges().filter {
            it.length > 2
        }.debounce(1,TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
            txtType.text = it.toString()
        }, {
            Log.d("Error", it.message)
        })*/

        /*disposable.add(
            Observable.just("Hossein", "Ali", "Hassan")
                //.subscribeOn(Schedulers.io())
                //.observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        Log.d("Observer", it.toString())
                    },
                    {
                        Log.d("Observer", it.message)
                    })
        )
        disposable.dispose()*/
    }

    override fun onDestroy() {
        disposable.dispose()
        super.onDestroy()
    }

}
