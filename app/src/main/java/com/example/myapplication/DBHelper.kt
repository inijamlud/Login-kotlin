package com.example.myapplication

import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        val DATABASE_NAME = "myaps.db"
        val DATABASE_VERSION = 1
        private val SQL_CREATE_USER =
            "CREATE TABLE " + DBInfo.UserInput.TABLE_NAME + " (" + DBInfo.UserInput.COL_EMAIL +
                    " VARCHAR(200) PRIMARY KEY, " + DBInfo.UserInput.COL_PASS + " TEXT, " +
                    DBInfo.UserInput.COL_FULLNAME + " TEXT," + DBInfo.UserInput.COL_ADDRESS + " TEXT, " +
                    DBInfo.UserInput.COL_GENDER + " TEXT)"
        private val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + DBInfo.UserInput.TABLE_NAME
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_USER)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    @Throws(SQLiteConstraintException::class)
    fun registerUser(emailin: String, passin: String, fullnamein: String, addressin: String, genderin: String): Boolean {
        val db = writableDatabase
        val namatablet = DBInfo.UserInput.TABLE_NAME
        val emailt = DBInfo.UserInput.COL_EMAIL
        val passt = DBInfo.UserInput.COL_PASS
        val fullnamet = DBInfo.UserInput.COL_FULLNAME
        val addresst = DBInfo.UserInput.COL_ADDRESS
        val gendert = DBInfo.UserInput.COL_GENDER
        var sql = "INSERT INTO "+ namatablet +"("+emailt+", "+passt+", "+fullnamet+", "+addresst+", "+ gendert + ") " + "VALUES('"+emailin+"', '"+passin+"', '"+fullnamein+"', '"+addressin+"', '"+ genderin +"')"
        db.execSQL(sql)
        return true
    }

    fun cekUser(emailin: String):String {
        val db = writableDatabase
        var cursor: Cursor? = null
        var jumlah = ""
        try {
            cursor = db.rawQuery("SELECT COUNT("+DBInfo.UserInput.COL_EMAIL+") as jumlah FROM "+DBInfo.UserInput.TABLE_NAME + " WHERE " +
                    DBInfo.UserInput.COL_EMAIL + " =='" + emailin +"'", null)
        } catch (e: SQLException) {
            db.execSQL(SQL_CREATE_USER)
            return "Tabel Dibuat"
        }

        if (cursor!!.moveToFirst()){
            jumlah = cursor.getString(cursor.getColumnIndex(DBInfo.UserInput.COL_JUMLAH))
        }
        return jumlah
    }

    fun cekLogin(emailin: String, passin: String) : String {
        val db = writableDatabase
        var cursor: Cursor? = null
        var jumlah = ""
        try {
            cursor = db.rawQuery("SELECT COUNT("+DBInfo.UserInput.COL_EMAIL+") as jumlah FROM "+DBInfo.UserInput.TABLE_NAME + " WHERE " +
                    DBInfo.UserInput.COL_EMAIL + " =='" + emailin +"' AND " + DBInfo.UserInput.COL_PASS + "=='" +passin+ "'", null)
        } catch (e: SQLException) {
            db.execSQL(SQL_CREATE_USER)
            return "Tabel Dibuat"
        }

        if (cursor!!.moveToFirst()){
            jumlah = cursor.getString(cursor.getColumnIndex(DBInfo.UserInput.COL_JUMLAH))
        }
        return jumlah
    }

}

