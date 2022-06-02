package com.example.ctscan.main.ui.diagnose

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ctscan.R
import com.example.ctscan.databinding.ActivitySecondProcessBinding

class SecondProcessActivity : AppCompatActivity() {
    private lateinit var secondProcessBinding: ActivitySecondProcessBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        secondProcessBinding = ActivitySecondProcessBinding.inflate(layoutInflater)
        setContentView(secondProcessBinding.root)
        initUI()
    }

    private fun initUI() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = getString(R.string.diagnose)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
    companion object {

        fun start(context: Context) {
            val intent = Intent(context, SecondProcessActivity::class.java)
            context.startActivity(intent)
        }
    }
}