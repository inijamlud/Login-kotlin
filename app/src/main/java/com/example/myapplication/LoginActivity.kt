package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val savedLogin = getSharedPreferences("Login", MODE_PRIVATE)
        val editSavedLogin = savedLogin.edit()
        if (savedLogin.getString("Status", "Off")=="On"){
            startActivity(Intent(this, Home::class.java))
        }
        val editUsername: TextView = findViewById(R.id.login_username)
        val editPassword: TextView = findViewById(R.id.login_password)
        val btnLogin: Button = findViewById(R.id.btn_login)
        val register: TextView = findViewById(R.id.register)

        register.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        btnLogin.setOnClickListener {
            val DBHelper = DBHelper(this)
            var email  = editUsername.text.toString()
            var pass = editPassword.text.toString()
            var cekLogin = DBHelper.cekLogin(email, pass)

            if (cekLogin == "1"){
                editSavedLogin.putString("Email", email)
                editSavedLogin.putString("Password", pass)
                editSavedLogin.putString("Status", "On")
                editSavedLogin.commit()
                startActivity(Intent(this, Home::class.java))
                val toast: Toast = Toast.makeText(applicationContext, "Login berhasil", Toast.LENGTH_SHORT)
                toast.show()
            } else {
                val toast: Toast = Toast.makeText(applicationContext, "Gagal Login", Toast.LENGTH_SHORT)
                toast.show()
            }


        }
    }
}