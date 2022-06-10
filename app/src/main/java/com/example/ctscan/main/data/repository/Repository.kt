package com.example.ctscan.main.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.ctscan.main.Utils.Const.KEY_IS_LOGIN
import com.example.ctscan.main.Utils.Const.KEY_TOKEN
import com.example.ctscan.main.Utils.SessionManager
import com.example.ctscan.main.data.Patient
import com.example.ctscan.main.data.Resource
import com.example.ctscan.main.data.remote.ApiClient
import com.example.ctscan.main.data.remote.ApiService
import com.example.ctscan.main.data.request.AddPatientRequest
import com.example.ctscan.main.data.request.LoginRequest
import com.example.ctscan.main.data.request.RegisterRequest
import com.example.ctscan.main.data.response.*
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Response

class Repository(private val application: Application) {

    private val retrofit: ApiService
    private lateinit var pref: SessionManager

    init {
        retrofit = ApiClient.getRetrofitInstance()
        pref = SessionManager(application)
    }


    fun login(loginRequest: LoginRequest): LiveData<Resource<LoginResponse>> {
        val Log = MutableLiveData<Resource<LoginResponse>>()

        Log.postValue(Resource.Loading())
        retrofit.login(loginRequest).enqueue(object : retrofit2.Callback<LoginResponse>{
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.postValue(Resource.Error(t.message))

            }
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.code() == 200) {
                    val res = response.body()
                    if (res == null)
                        Log.postValue(Resource.Error(null))
                    else
                        Log.postValue(Resource.Success(res))
                    pref.apply {
                        pref.setStringPreference(KEY_TOKEN,res?.token.toString())
                        pref.setBooleanPreference(KEY_IS_LOGIN,true)
                    }
                }else {
                    val res = response.body()
                    if (res == null)
                        Log.postValue(Resource.Error(null))
                    else
                        Log.postValue(Resource.Success(res))
                }
            }
        })
        return  Log
    }

    fun register(registerRequest: RegisterRequest): LiveData<Resource<RegisterResponse>> {
        val Log = MutableLiveData<Resource<RegisterResponse>>()

        Log.postValue(Resource.Loading())
        retrofit.register(registerRequest).enqueue(object : retrofit2.Callback<RegisterResponse>{
            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                Log.postValue(Resource.Error(t.message))

            }
            override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                if (response.code() == 200) {
                    val res = response.body()
                    if (res == null)
                        Log.postValue(Resource.Error(null))
                    else
                        Log.postValue(Resource.Success(res))

                }else {
                    val res = response.body()
                    if (res == null)
                        Log.postValue(Resource.Error(null))
                    else
                        Log.postValue(Resource.Success(res))
                }
            }
        })
        return  Log
    }

    fun addPatient(token: String ,addPatientRequest: AddPatientRequest): LiveData<Resource<AddPatientResponse>> {
        val Log = MutableLiveData<Resource<AddPatientResponse>>()

        Log.postValue(Resource.Loading())
        retrofit.addPatient("Bearer"+ " " + token,addPatientRequest).enqueue(object : retrofit2.Callback<AddPatientResponse>{
            override fun onFailure(call: Call<AddPatientResponse>, t: Throwable) {
                Log.postValue(Resource.Error(t.message))

            }
            override fun onResponse(call: Call<AddPatientResponse>, response: Response<AddPatientResponse>) {
                if (response.code() == 200) {
                    val res = response.body()
                    if (res == null)
                        Log.postValue(Resource.Error(null))
                    else
                        Log.postValue(Resource.Success(res))

                }else {
                    val res = response.body()
                    if (res == null)
                        Log.postValue(Resource.Error(null))
                    else
                        Log.postValue(Resource.Success(res))
                }
            }
        })
        return  Log
    }

    fun getAllPatient(token: String): LiveData<Resource<ResponseGetAllPatient>> {
        val Log = MutableLiveData<Resource<ResponseGetAllPatient>>()

        Log.postValue(Resource.Loading())
        retrofit.getAllPatient("Bearer"+ " " + token).enqueue(object : retrofit2.Callback<ResponseGetAllPatient>{
            override fun onFailure(call: Call<ResponseGetAllPatient>, t: Throwable) {
                Log.postValue(Resource.Error(t.message))

            }
            override fun onResponse(call: Call<ResponseGetAllPatient>, response: Response<ResponseGetAllPatient>) {
                if (response.code() == 200) {
                    val res = response.body()
                    if (res == null)
                        Log.postValue(Resource.Error(null))
                    else
                        Log.postValue(Resource.Success(res))

                }else {
                    val res = response.body()
                    if (res == null)
                        Log.postValue(Resource.Error(null))
                    else
                        Log.postValue(Resource.Success(res))
                }
            }
        })
        return  Log
    }


    fun uploadFile(token: String, file : MultipartBody.Part): LiveData<Resource<UploadResponse>> {
        val Log = MutableLiveData<Resource<UploadResponse>>()

        Log.postValue(Resource.Loading())
        retrofit.uploadImage("Bearer"+ " " + token,file).enqueue(object : retrofit2.Callback<UploadResponse>{
            override fun onFailure(call: Call<UploadResponse>, t: Throwable) {
                Log.postValue(Resource.Error(t.message))

            }
            override fun onResponse(call: Call<UploadResponse>, response: Response<UploadResponse>) {
                if (response.code() == 200) {
                    val res = response.body()
                    if (res == null)
                        Log.postValue(Resource.Error(null))
                    else
                        Log.postValue(Resource.Success(res))

                }else {
                    val res = response.body()
                    if (res == null)
                        Log.postValue(Resource.Error(null))
                    else
                        Log.postValue(Resource.Success(res))
                }
            }
        })
        return  Log
    }



    fun getRecentUser(token: String): LiveData<Resource<GetCurrentResponse>> {
        val Log = MutableLiveData<Resource<GetCurrentResponse>>()

        Log.postValue(Resource.Loading())
        retrofit.getCurrent("Bearer"+ " " + token).enqueue(object : retrofit2.Callback<GetCurrentResponse>{
            override fun onFailure(call: Call<GetCurrentResponse>, t: Throwable) {
                Log.postValue(Resource.Error(t.message))

            }
            override fun onResponse(call: Call<GetCurrentResponse>, response: Response<GetCurrentResponse>) {
                if (response.code() == 200) {
                    val res = response.body()
                    if (res == null)
                        Log.postValue(Resource.Error(null))
                    else
                        Log.postValue(Resource.Success(res))

                }else {
                    val res = response.body()
                    if (res == null)
                        Log.postValue(Resource.Error(null))
                    else
                        Log.postValue(Resource.Success(res))
                }
            }
        })
        return  Log
    }
}