package com.example.ctscan.main.ui.diagnose

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ctscan.R.string
import com.example.ctscan.databinding.ActivityDiagnoseBinding
import com.example.ctscan.main.ui.login.LoginActivity

class DiagnoseActivity : AppCompatActivity() {
    lateinit var diagnoseBinding: ActivityDiagnoseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        diagnoseBinding = ActivityDiagnoseBinding.inflate(layoutInflater)
        setContentView(diagnoseBinding.root)
        initUI()
    }

    private fun initUI() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = getString(string.diagnose)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
    companion object {

        fun start(context: Context) {
            val intent = Intent(context, DiagnoseActivity::class.java)
            context.startActivity(intent)
        }
    }
}