package com.example.ctscan.main.data.remote

import com.example.ctscan.main.data.Patient
import com.example.ctscan.main.data.request.AddPatientRequest
import com.example.ctscan.main.data.request.LoginRequest
import com.example.ctscan.main.data.request.RegisterRequest
import com.example.ctscan.main.data.response.*
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @Headers("Content-Type: application/json")
    @POST("user/login")
    fun login(@Body loginRequest: LoginRequest): Call<LoginResponse>


    @Headers("Content-Type: application/json")
    @POST("user/register")
    fun register(@Body registerRequest: RegisterRequest) : Call<RegisterResponse>

   @Headers("Content-Type: application/json")
   @POST("patient/addPatient")
   fun addPatient(
       @Header("Authorization") token: String,
       @Body addPatientRequest: AddPatientRequest
   ) : Call<AddPatientResponse>

    @GET("patient/getAllPatients")
    fun getAllPatient(
        @Header("Authorization") token: String,
    ) : Call<ResponseGetAllPatient>

    @Multipart
    @POST("upload/")
    fun uploadImage(
        @Header("Authorization") token: String,
        @Part file: MultipartBody.Part,
    ) : Call<UploadResponse>

    @GET("patient/getCurrentPatient")
    fun getCurrent(
        @Header("Authorization") token: String,
    ) : Call<GetCurrentResponse>
}