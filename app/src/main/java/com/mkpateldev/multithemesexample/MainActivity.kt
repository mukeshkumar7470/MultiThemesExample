package com.mkpateldev.multithemesexample

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.mkpateldev.multithemesexample.DialogManager.Companion.showCustomAlertDialog
import com.mkpateldev.multithemesexample.ThemeManager.Companion.setCustomizedThemes
import com.mkpateldev.multithemesexample.ThemeStorage.Companion.getThemeColor
import com.mkpateldev.multithemesexample.ThemeStorage.Companion.setThemeColor
import com.mkpateldev.multithemesexample.databinding.ActivityMainBinding

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity(){
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setCustomizedThemes(this,getThemeColor(this));
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window: Window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            if (getThemeColor(this).equals("blue")) {
                window.statusBarColor = ContextCompat.getColor(this, R.color.blue)
            }
        }

        binding.txtChooseColor.setOnClickListener {
            showCustomAlertDialog(this, object : ColorDialogCallback {
                override fun onChosen(chosenColor: String) {
                    if (chosenColor == getThemeColor(applicationContext)) {
                        Toast.makeText(this@MainActivity, "Theme has already chosen", Toast.LENGTH_SHORT).show()
                        return
                    }
                    Log.d(TAG, chosenColor)
                    setThemeColor(applicationContext, chosenColor)
                    setCustomizedThemes(applicationContext, chosenColor)
                    recreate()
                }
            })
        }

        binding.btnGo.setOnClickListener {
            val intent = Intent(this@MainActivity, SecondActivity::class.java)
            startActivity(intent)
        }
    }
}