package com.example.ctscan.main.ui.diagnose

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.example.ctscan.R
import com.example.ctscan.R.string
import com.example.ctscan.databinding.ActivityDiagnoseBinding
import com.example.ctscan.main.Utils.*
import com.example.ctscan.main.data.Resource
import com.example.ctscan.main.data.request.AddPatientRequest
import com.example.ctscan.main.data.response.AddPatientResponse
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class DiagnoseActivity : AppCompatActivity() , ViewStateCallback<AddPatientResponse>{
    lateinit var diagnoseBinding: ActivityDiagnoseBinding
    private val viewModel by viewModels<DiagnoseViewModel>()
    private var token: String? = null
    private lateinit var pref: SessionManager
    private var uploadFile: File? = null
    private var gender: String? = "male"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        diagnoseBinding = ActivityDiagnoseBinding.inflate(layoutInflater)
        setContentView(diagnoseBinding.root)
        pref = SessionManager(this@DiagnoseActivity)
        token = pref.getToken
        initUI()
        radioButton()
        addPhoto()
        diagnoseBinding.btnDiagnose.setOnClickListener {
            diagnose()
            uploadImage()
        }
    }

    private fun radioButton(){
        diagnoseBinding.radioGroup.setOnCheckedChangeListener(
            RadioGroup.OnCheckedChangeListener{
                    group, checkedId ->
                val radio_langange: RadioButton = findViewById(checkedId)
                gender = radio_langange.text.toString()
            }
        )
    }

    private fun addPhoto(){
        diagnoseBinding.btnAddPhoto.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_GET_CONTENT
            intent.type = "image/*"
            val chooser = Intent.createChooser(intent, getString(string.title_choose_a_picture))
            launchIntentGallery.launch(chooser)
        }
    }



    private val launchIntentGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val selectedImg: Uri = result.data?.data as Uri
            val file = uriToFile(selectedImg, this)

            uploadFile = file
//            diagnoseBinding.lbAdd.setText(selectedImg)
        }
    }



    private fun diagnose() {
        diagnoseBinding.btnDiagnose.setOnClickListener {
            val name = diagnoseBinding.edtName.text.toString()
            val nik = diagnoseBinding.edtNIK.text.toString()
            val age = diagnoseBinding.edtAge.text.toString()
            val address = diagnoseBinding.edtAddress.text.toString()
            closeKeyboard()
            Handler(Looper.getMainLooper()).postDelayed({
                when {
                    name.isBlank() -> diagnoseBinding.edtName.error = getString(R.string.error_empty_email)
                    nik.isBlank() -> diagnoseBinding.edtNIK.error = getString(R.string.error_empty_nik)
                    age.isBlank() -> diagnoseBinding.edtAge.error = getString(R.string.error_empty_age)
                    address.isBlank() -> diagnoseBinding.edtAddress.error = getString(R.string.error_empty_address)
                    else -> {
                        val addPatientRequest = AddPatientRequest( name = name, nik = nik,age = age, address = address, gender = gender )
                        viewModel.addPatient(token.toString(),addPatientRequest).observe(this) {
                            when (it) {
                                is Resource.Error -> onFailed(it.message.toString())
                                is Resource.Loading -> onLoading()
                                is Resource.Success -> it.data?.let { it1 -> onSuccess(it1) }
                            }
                        }
                    }
                }
            }, Const.ACTION_DELAYED_TIME)
        }
    }

    private fun uploadImage() {
        if (uploadFile != null) {
            val file = reduceFileImage(uploadFile as File)
                val requestImageFile = file.asRequestBody("image/jpeg".toMediaTypeOrNull())
                val imageMultipart = MultipartBody.Part.createFormData(
                    "file",
                    file.name,
                    requestImageFile
                )
                viewModel.uploadFile(token.toString(), imageMultipart)
                    .observe(this) {
                        when (it) {
                            is Resource.Error -> onFailed(it.message)
                            is Resource.Loading -> onLoading()
                            is Resource.Success -> success(it.data?.image)
                        }
                    }

        } else {
            Toast.makeText(
                this@DiagnoseActivity,
                getString(string.title_message) + getString(string.pick_image),
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun success (message : String?){
        val message = message
        diagnoseBinding.apply {
            Toast.makeText(this@DiagnoseActivity, message.toString(), Toast.LENGTH_SHORT).show()
        }
    }
    private fun initUI() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = getString(string.diagnose)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun closeKeyboard() {
        val view: View? = this.currentFocus
        if (view != null) {
            val manager: InputMethodManager = getSystemService(
                Context.INPUT_METHOD_SERVICE
            ) as InputMethodManager
            manager
                .hideSoftInputFromWindow(
                    view.windowToken, 0
                )
        }
    }

    override fun onSuccess(data: AddPatientResponse) {
        diagnoseBinding.apply {
            val data = data
            try {
                Toast.makeText(this@DiagnoseActivity, data.message, Toast.LENGTH_SHORT).show()
            }finally {
                val intent = Intent(this@DiagnoseActivity, ProcessActivity::class.java)
                intent.putExtra("token",token)
                startActivity(intent)
            }

        }
    }

    override fun onLoading() {
        diagnoseBinding.apply {
            Toast.makeText(this@DiagnoseActivity, "Loading...", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onFailed(message: String?) {
        val message = message
        diagnoseBinding.apply {
            Toast.makeText(this@DiagnoseActivity, message.toString(), Toast.LENGTH_SHORT).show()
        }
    }

}