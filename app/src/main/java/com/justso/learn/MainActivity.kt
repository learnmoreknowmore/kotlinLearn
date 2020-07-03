package com.justso.learn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil.setContentView
import com.justso.learn.databinding.ActivityMainBinding

/**
 * https://github.com/android/architecture-components-samples
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView<ActivityMainBinding>(this,R.layout.activity_main)
    }
}