package com.example.codechallengestorage.GameView

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.codechallengestorage.UserLogin.ProfileUser
import com.example.codechallengestorage.R
import com.example.codechallengestorage.UserLogin.dataLogin
import kotlinx.android.synthetic.main.activity_menu_permainan.*

class MenuPermainan : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_permainan)

        val sharedPreferences = getSharedPreferences(dataLogin.SP_NAME, Context.MODE_PRIVATE)

        val bundle = intent.extras
        val nama = sharedPreferences.getString(dataLogin.FIELD_USERNAME, "Belum Ada Data")

        tvVsPemain.setText("$nama VS Pemain")
        tvVsCom.setText("$nama VS Komputer")

        ivVsPemain.setOnClickListener {
            val intent = Intent(this, Pemain1vsPemain2::class.java)
            startActivity(intent)
        }

        ivVsCom.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        iv_profile.setOnClickListener{
            val intent = Intent (this, ProfileUser::class.java)
            startActivity(intent)
        }
    }
}