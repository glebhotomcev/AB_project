package com.orangesoft.addressbook

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.orangesoft.addressbook.di.applicationModule
import org.koin.android.ext.android.startKoin

class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        startKoin(this, listOf(applicationModule))
    }

}

