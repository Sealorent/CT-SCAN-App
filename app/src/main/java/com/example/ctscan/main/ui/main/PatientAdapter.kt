package com.example.ctscan.main.ui.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ctscan.databinding.CardPatientBinding
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
            fun bind(patient: Patient) {
                with(binding) {
                    lbStatus.text = patient.covid_status
                    lbGender.text = patient.gender
                    lbTanggal.text = patient.date
                    lbName.text = patient.name
                }

            }
        }

}




