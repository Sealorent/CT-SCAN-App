package com.example.ctscan.main.menu

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ctscan.databinding.ActivityMenuBinding
import com.example.ctscan.main.ui.diagnose.DiagnoseActivity
import com.example.ctscan.main.ui.main.MainActivity

class MenuActivity : AppCompatActivity() {
    private lateinit var menuBinding : ActivityMenuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        menuBinding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(menuBinding.root)
        toDiagnose()
    }

    private fun toDiagnose(){
        menuBinding.cardDiagnose.setOnClickListener {
            DiagnoseActivity.start(this)
            finish()
        }
        menuBinding.cardPatient.setOnClickListener{
            MainActivity.start(this)
            finish()
        }
    }

    companion object {

        fun start(context: Context) {
            val intent = Intent(context, MenuActivity::class.java)
            context.startActivity(intent)
        }
    }
}