package com.example.ctscan.main.ui.main

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ctscan.R
import com.example.ctscan.databinding.ActivityMainBinding
import com.example.ctscan.main.data.Patient

class MainActivity : AppCompatActivity() {

    private lateinit var patientAdapter: PatientAdapter
    private lateinit var mainBinding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        initUI()
        val data = ArrayList<Patient>()

        for (i in 1..20) {
            data.add(Patient(1,"bdsau","viky","positive","Female",R.drawable.logo  + i))
        }

        patientAdapter = PatientAdapter(data)

        mainBinding.rvPatient.apply {
            adapter = patientAdapter
            layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.VERTICAL,false)
        }
    }

    private fun initUI() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = getString(R.string.patientList)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }



    companion object {

        fun start(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }

}