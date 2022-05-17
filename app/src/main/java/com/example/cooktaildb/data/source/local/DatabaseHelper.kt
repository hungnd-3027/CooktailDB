package com.example.cooktaildb.data.source.local

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(val context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL(DROP_TABLE)
        onCreate(db)
    }

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "drink_app"
        const val TABLE_NAME = "drinktbl"

        const val ID = "id"
        const val NAME = "name"
        const val CATEGORY = "category"
        const val ALCOHOLIC = "alcoholic"
        const val GLASS = "glass"
        const val INSTRUCTION = "instruction"
        const val THUMB = "thumb"
        const val INGREDIENTS = "ingredients"
        const val MEASURES = "measures"

        private val CREATE_TABLE = String.format(
            "CREATE TABLE %s (%s TEXT PRIMARY KEY,  %s TEXT, %s TEXT, %s TEXT, %s TEXT," +
                    " %s TEXT, %s TEXT, %s TEXT, %s TEXT)",
            TABLE_NAME,
            ID,
            NAME,
            CATEGORY,
            ALCOHOLIC,
            GLASS,
            INSTRUCTION,
            THUMB,
            INGREDIENTS,
            MEASURES)

        private val DROP_TABLE =
            String.format("DROP TABLE IF EXISTS %s", TABLE_NAME)

        private var instance: DatabaseHelper? = null
        private val lock = Any()
        fun getInstance(context: Context?) = instance ?: synchronized(this) {
            instance ?: DatabaseHelper(context).also { instance = it }
        }
    }
}
