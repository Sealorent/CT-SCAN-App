package com.example.ctscan.main.ui.diagnose

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ctscan.R
import com.example.ctscan.databinding.ActivitySecondProcessBinding
import com.example.ctscan.main.ui.menu.MenuActivity

class SecondProcessActivity : AppCompatActivity() {
    private lateinit var secondProcessBinding: ActivitySecondProcessBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        secondProcessBinding = ActivitySecondProcessBinding.inflate(layoutInflater)
        setContentView(secondProcessBinding.root)
        initUI()
        secondProcessBinding.apply {
            val id = intent.getStringExtra("name")
            val name = intent.getStringExtra("nik")
            val severity = intent.getStringExtra("severity")
            idUser.text = id
            lbName.text = name
            lbResult.text = severity
        }
        done()
    }


    private fun done(){
        secondProcessBinding.btnDone.setOnClickListener {
            val intent = Intent(this@SecondProcessActivity, MenuActivity::class.java)
            startActivity(intent)
        }

    }
    private fun initUI() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = getString(R.string.diagnose)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

}