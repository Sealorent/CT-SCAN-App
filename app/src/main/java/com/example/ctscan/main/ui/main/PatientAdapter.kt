package com.example.ctscan.main.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ctscan.databinding.CardPatientBinding
import com.example.ctscan.main.data.Patient


class PatientAdapter(private val patients: List<Patient>) : RecyclerView.Adapter<PatientAdapter.PatientViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): PatientAdapter.PatientViewHolder {
        val binding = CardPatientBinding.inflate(LayoutInflater.from(p0.context),p0,false)
        return PatientViewHolder(  binding )
    }

    override fun onBindViewHolder(holder: PatientAdapter.PatientViewHolder, position: Int) {
//        holder.bind(patients.get(0),position)
        holder.bind(patients.get(0),position)
    }

    override fun getItemCount(): Int = 4

    inner class PatientViewHolder(private val binding: CardPatientBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(patients: Patient, position: Int,) {
            with(binding){
                binding.lbName.text = patients.name
                binding.lbResult.text = patients.status
                binding.lbGender.text = patients.gender
                binding.lbTanggal.text = patients.date
                binding.background.setImageResource(patients.image)
            }

        }
    }
}


//class MainAdapter(
//    private val travels: List<Patient>,
//    private val listener: (Patient) -> Unit
//) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {
//
//    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MainViewHolder =
//        MainViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.card_patient, p0, false))
//
//    override fun onBindViewHolder(p0: MainViewHolder, p1: Int) {
//        p0.bind(travels.get(0), listener)
//    }
//
//    override fun getItemCount() = 4
//
//    inner class MainViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
//
//        fun bind(travel: Patient, listener: (Patient) -> Unit) {
//            view.lbNam = travel.name
//            view.tvDescription.text = travel.description
//            view.setOnClickListener { listener(travel) }
//        }
//    }
//}