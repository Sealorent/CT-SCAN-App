package com.example.ctscan.main.ui.main

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.ctscan.R
import com.example.ctscan.databinding.CardPatientBinding
import com.example.ctscan.main.Utils.Const.BASE_URL
import com.example.ctscan.main.Utils.setImageUrl
import com.example.ctscan.main.data.Patient


class PatientAdapter : RecyclerView.Adapter<PatientAdapter.PatientViewHolder>() {

    private val patientList = ArrayList<Patient>()

    @SuppressLint("NotifyDataSetChanged")
    fun setAllData(data: List<Patient>) {
        patientList.apply {
            clear()
            addAll(data)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientAdapter.PatientViewHolder {
        val binding = CardPatientBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PatientViewHolder(  binding )
    }

    override fun onBindViewHolder(holder: PatientAdapter.PatientViewHolder, position: Int) {
        patientList[position].let { patient ->
            holder.bind(patient)
        }
    }

    override fun getItemCount(): Int = patientList.size

        inner class PatientViewHolder(private val binding: CardPatientBinding): RecyclerView.ViewHolder(binding.root) {
            @SuppressLint("SetTextI18n")
            fun bind(patient: Patient) {
                with(binding) {
                    val date = patient.date.toString()
                    lbStatus.text = patient.covid_status
                    lbGender.text = patient.gender
                    lbTanggal.text = date.substring(0..9)
                    lbName.text = patient.name
                    if (patient.covid_status == null){
                        lbStatus.text = "status null"
                    }else{
                        lbStatus.text = patient.covid_status
                    }

                    if (patient.ct_image != null){
                        background.setImageUrl("http://35.227.163.204:5000" + patient.ct_image.toString(), true)
                    }else{
                        background.setImageResource(R.drawable.background_menu)
                    }
                }

            }
        }

}






