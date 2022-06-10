package com.example.ctscan.main.ui.diagnose

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import com.example.ctscan.R
import com.example.ctscan.databinding.ActivityProcessBinding
import com.example.ctscan.main.Utils.SessionManager
import com.example.ctscan.main.Utils.ViewStateCallback
import com.example.ctscan.main.data.Resource
import com.example.ctscan.main.data.response.GetCurrentResponse

class ProcessActivity : AppCompatActivity() , ViewStateCallback<GetCurrentResponse> {
    private lateinit var processBinding: ActivityProcessBinding
    private val viewModel by viewModels<ProcessViewModel>()
    private var token: String? = null
    private var name: String? = null
    private var nik: String? = null
    private var severity: String? = null





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        processBinding = ActivityProcessBinding.inflate(layoutInflater)
        setContentView(processBinding.root)
        initUI()
        token = intent.getStringExtra("token")
//        Log.d("token",token.toString())
        getCurrent()
        processBinding.btnCheck.setOnClickListener {
            val intent = Intent(this@ProcessActivity, SecondProcessActivity::class.java)
            intent.putExtra("name",name)
            intent.putExtra("nik",nik)
            intent.putExtra("severity",severity)
            startActivity(intent)
        }
    }

    private fun getCurrent(){
        viewModel.getCurrent(token.toString()).observe(this){
            when (it){
                is Resource.Error -> onFailed(it.message.toString())
                is Resource.Loading -> onLoading()
                is Resource.Success -> it.data?.let { it1 -> onSuccess(it1) }
            }
        }
    }
    override fun onSuccess(data: GetCurrentResponse) {
        val data = data.patient
        processBinding.apply {
            lbName.text = data[0].name
            idUser.text = data[0].nik
            severity = data[0].severity
            name = data[0].name
            nik = data[0].nik
        }
    }

    override fun onLoading() {
        processBinding.apply {
            Toast.makeText(this@ProcessActivity, "Loading...", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onFailed(message: String?) {
        processBinding.apply {
            Toast.makeText(this@ProcessActivity, "Failed...", Toast.LENGTH_SHORT).show()
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


    companion object {

        fun start(context: Context) {
            val intent = Intent(context, ProcessActivity::class.java)
            context.startActivity(intent)
        }
    }




}