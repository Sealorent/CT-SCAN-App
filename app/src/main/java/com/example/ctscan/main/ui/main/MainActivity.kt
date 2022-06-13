package com.example.ctscan.main.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ctscan.R
import com.example.ctscan.databinding.ActivityMainBinding
import com.example.ctscan.main.Utils.SessionManager
import com.example.ctscan.main.Utils.ViewStateCallback
import com.example.ctscan.main.data.Resource
import com.example.ctscan.main.data.response.AddPatientResponse
import com.example.ctscan.main.data.response.ResponseGetAllPatient

class MainActivity : AppCompatActivity() , ViewStateCallback<ResponseGetAllPatient>{

    private lateinit var patientAdapter: PatientAdapter
    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var pref: SessionManager
    private var token: String? = null
    private val viewModel by viewModels<MainViewModel>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        initUI()
        pref = SessionManager(this@MainActivity)
        token = pref.getToken
        patientAdapter = PatientAdapter()
        mainBinding.rvPatient.apply {
            adapter = patientAdapter
            layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.VERTICAL  ,false)
        }
        getAllPatient(token.toString())

    }



    private fun getAllPatient(token:String){
        viewModel.getAllPatient(token).observe(this){
            when(it){
                is Resource.Error -> onFailed(it.message)
                is Resource.Loading -> onLoading()
                is Resource.Success -> it.data?.let{ it1 ->onSuccess(it1) }
            }
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

    override fun onLoading() {
       Toast.makeText(this@MainActivity,"Loading...", Toast.LENGTH_SHORT).show()
    }

    override fun onFailed(message: String?) {
        Toast.makeText(this@MainActivity,"Failed...", Toast.LENGTH_SHORT).show()
    }

    override fun onSuccess(data: ResponseGetAllPatient) {
        val patients = data.listPatient
        patientAdapter.setAllData(patients)
    }

}