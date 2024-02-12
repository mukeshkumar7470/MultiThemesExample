package com.mkpateldev.multithemesexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mkpateldev.multithemesexample.ThemeManager.Companion.setCustomizedThemes
import com.mkpateldev.multithemesexample.ThemeStorage.Companion.getThemeColor

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setCustomizedThemes(this,getThemeColor(this));
        setContentView(R.layout.activity_second)
    }
}