package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast

class RegisterActivity : AppCompatActivity() {
    lateinit var userDBHelper: DBHelper
    lateinit var inputemail: EditText
    lateinit var inputpass: EditText
    lateinit var inputfullname: EditText
    lateinit var inputaddress: EditText
    lateinit var inputgender: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        inputemail = findViewById(R.id.email)
        inputpass = findViewById(R.id.pass)
        inputfullname = findViewById(R.id.fullname)
        inputaddress = findViewById(R.id.address)
        inputgender = findViewById(R.id.gender)
        userDBHelper = DBHelper(this)
    }

    fun register(view: View){
        var emailin = inputemail.text.toString()
        var passin = inputpass.text.toString()
        var fullnamein = inputfullname.text.toString()
        var genderin = inputgender.text.toString()
        var addressin = inputaddress.text.toString()
        var cekuser = userDBHelper.cekUser(emailin)
        var status = "Gagal"

        if (cekuser == "0") {
            userDBHelper.registerUser(emailin, passin, fullnamein, addressin, genderin)
            status = "Sukses"
            startActivity(Intent(this, LoginActivity::class.java))
        }

        val toast: Toast = Toast.makeText(applicationContext, status, Toast.LENGTH_SHORT)
        toast.show()
    }

    fun cancelme(view: View){
        startActivity(Intent(this, LoginActivity::class.java))
    }

}