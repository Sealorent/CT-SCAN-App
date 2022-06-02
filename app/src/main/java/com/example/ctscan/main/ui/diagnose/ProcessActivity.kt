package com.example.ctscan.main.ui.diagnose

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ctscan.R
import com.example.ctscan.databinding.ActivityProcessBinding

class ProcessActivity : AppCompatActivity() {
    private lateinit var processBinding: ActivityProcessBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        processBinding = ActivityProcessBinding.inflate(layoutInflater)
        setContentView(processBinding.root)
        initUI()
        processBinding.btnCheck.setOnClickListener {
            checkSeverity()
        }
    }

    private fun checkSeverity(){
        SecondProcessActivity.start(this)
        finish()
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
            val intent = Intent(context, ProcessActivity::class.java)
            context.startActivity(intent)
        }
    }
}