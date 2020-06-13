package com.srgpanov.itmonitoring.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.srgpanov.itmonitoring.R
import com.srgpanov.itmonitoring.data.local.LocalDataSource
import com.srgpanov.itmonitoring.other.OnBackPressedListener

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if (it is OnBackPressedListener){
                it.onBackPressed()
            }else{
                super.onBackPressed()
            }
        }
    }
    fun onBackPressedSuper(){
        super.onBackPressed()
    }
}