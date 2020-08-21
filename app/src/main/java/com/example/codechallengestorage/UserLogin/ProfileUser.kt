package com.example.codechallengestorage.UserLogin

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.codechallengestorage.Memo.*
import com.example.codechallengestorage.R
import kotlinx.android.synthetic.main.activity_profile_user.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ProfileUser : AppCompatActivity() {
    private lateinit var db: DbMemo
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_user)


        DbMemo.getInstance(this)?.let {
            db = it
        }

        val sharedPreferences = getSharedPreferences(dataLogin.SP_NAME, Context.MODE_PRIVATE)
        val userDeafault = sharedPreferences.getString(dataLogin.FIELD_USERNAME, "Belum Ada Data")
        val emailDefault = sharedPreferences.getString(dataLogin.FIELD_EMAIL, "Belum Ada Data")
        val editor = sharedPreferences.edit()

        et_profile_username.setText(userDeafault)

        if(!emailDefault.isNullOrBlank()) {
            et_profile_email.setText(emailDefault)
        }

        et_profile_username.addTextChangedListener {
            editor.putString(dataLogin.FIELD_USERNAME, et_profile_username.text.toString())
            editor.apply()
        }

        et_profile_email.addTextChangedListener {
            editor.putString(dataLogin.FIELD_EMAIL, et_profile_email.text.toString())
            editor.apply()
        }

        iv_profile_back.setOnClickListener {
            finish()
        }

        fab_tambah_memo.setOnClickListener {
            FragmentAddMemo.newInstance().show(supportFragmentManager, null)
        }
    }

    override fun onResume() {
        super.onResume()
        fetchData()
    }

    fun fetchData() {
        GlobalScope.launch {
            val listItem = db.memoDao().getMemo()

            runOnUiThread {
                val adapter = MemoAdapter(listItem)
                rv_profile.layoutManager =
                    LinearLayoutManager(this@ProfileUser, LinearLayoutManager.VERTICAL, false)
                rv_profile.adapter = adapter
            }
        }
    }

    fun editMemo(date: String, memo: String, list: Memo){
        FragmentEditMemo.newInstance(date, memo, list).show(supportFragmentManager, null)
    }
}