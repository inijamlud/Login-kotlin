package com.example.myapplication

import android.provider.BaseColumns

object DBInfo {
    class UserInput: BaseColumns {
        companion object {
            val TABLE_NAME = "users"
            val COL_EMAIL = "email"
            val COL_PASS = "pass"
            val COL_FULLNAME = "fullname"
            val COL_ADDRESS = "address"
            val COL_GENDER = "gender"
            val COL_JUMLAH = "jumlah"
        }
    }
}

