package com.example.codechallengestorage.UserLogin

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.codechallengestorage.GameView.MenuPermainan
import com.example.codechallengestorage.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_halaman_login.*

class HalamanLogin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_halaman_login)

        val sharedPreferences = getSharedPreferences(dataLogin.SP_NAME, Context.MODE_PRIVATE)
        val userDeafault = sharedPreferences.getString(dataLogin.FIELD_USERNAME, "Belum Ada Data")
        val passDefault = sharedPreferences.getString(dataLogin.FIELD_PASSWORD, "Belum Ada Data")

        btnLogin.setOnClickListener{
            val username = etUname.text.toString()
            val password = etPass.text.toString()

            if (username == "" || password == ""){
                Snackbar.make(it, "Username dan Password Wajib Diisi !", Snackbar.LENGTH_LONG).show()
            } else if (username != userDeafault || password != passDefault){
                Snackbar.make(it, "Username atau password salah ! Silahkan coba kembali.", Snackbar.LENGTH_LONG).show()
            } else {
                startActivity(Intent (this, MenuPermainan::class.java))
                finish()
            }
        }

        btnReset.setOnClickListener{
            val editor = sharedPreferences.edit()
            editor.clear()

            editor.putString(dataLogin.FIELD_USERNAME, "sabrina")
            editor.putString(dataLogin.FIELD_PASSWORD, "binar123")
            editor.putString(dataLogin.FIELD_EMAIL, "sabrina@binar.co.id")

            editor.apply()

            Snackbar.make(it, "Username dan password berhasil direset !", Snackbar.LENGTH_LONG).show()

        }
    }
}