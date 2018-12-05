package com.cuneytayyildiz.shutterstockassignment.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cuneytayyildiz.shutterstockassignment.R
import com.cuneytayyildiz.shutterstockassignment.utils.extensions.replaceFragmentInActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        replaceFragmentInActivity(MainFragment(), R.id.fragment_container)
    }
}