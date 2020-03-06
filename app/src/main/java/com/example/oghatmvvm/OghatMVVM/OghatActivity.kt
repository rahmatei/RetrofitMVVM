package com.example.oghatmvvm.OghatMVVM

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.oghatmvvm.R
import kotlinx.android.synthetic.main.activity_oghat.*

class OghatActivity : AppCompatActivity() {

    lateinit var oghat_ViewModel: OghatViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_oghat)

        oghat_ViewModel = ViewModelProvider(this).get(OghatViewModel::class.java)

        oghat_ViewModel.GetLiveResponseSharei().observe(this, Observer {
            Toast.makeText(this,it.sunrise,Toast.LENGTH_LONG).show()
            txtsunrise.text=it.sunrise
            txtsunset.text=it.sunset
        })

        btngetsharei.setOnClickListener {
            oghat_ViewModel.GetShareidata(edtCity.text.toString(),edtCountry.text.toString(),"8")
        }

    }
}
