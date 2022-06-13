package com.example.ctscan.main.ui.menu


import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.ctscan.databinding.ActivityMenuBinding
import com.example.ctscan.main.ui.diagnose.DiagnoseActivity
import com.example.ctscan.main.ui.main.MainActivity
import com.example.ctscan.R.string
import com.example.ctscan.main.Utils.Const
import com.example.ctscan.main.Utils.SessionManager
import com.example.ctscan.main.ui.login.LoginActivity


class MenuActivity : AppCompatActivity() {
//    private var prefs: SharedPreferences =getApplicationContext().getSharedPreferences(Const.PREFS_NAME, Context.MODE_PRIVATE)
//    private val editor = prefs.edit()

    private lateinit var menuBinding : ActivityMenuBinding
    private lateinit var pref : SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        menuBinding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(menuBinding.root)
        pref = SessionManager(this@MenuActivity)
        toDiagnose()
    }

    private fun toDiagnose(){
        menuBinding.cardDiagnose.setOnClickListener {
            val intent = Intent(this@MenuActivity, DiagnoseActivity::class.java)
            startActivity(intent)
        }
        menuBinding.cardPatient.setOnClickListener{
            val intent = Intent(this@MenuActivity, MainActivity::class.java)
            startActivity(intent)
        }
        menuBinding.btnLogout.setOnClickListener{
            openLogoutDialog()
        }
    }

    private fun openLogoutDialog() {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle(getString(string.message_logout_confirm))
            ?.setPositiveButton(getString(string.action_yes)) { _, _ ->
                pref.clearPreferences()
                val intent = Intent(this@MenuActivity, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finishAffinity()
            }
            ?.setNegativeButton(getString(string.action_cancel), null)
        val alert = alertDialog.create()
        alert.show()
    }

}