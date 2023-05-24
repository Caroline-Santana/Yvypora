package com.example.yvypora.domain.models

import android.annotation.SuppressLint
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper



class HistoricSearchDBHelper(context: Context) : SQLiteOpenHelper(context,"historic_search.db", null, 1){

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE IF NOT EXISTS historic_search (id INTEGER PRIMARY KEY, search TEXT, date_search DATETIME)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS historic_search")
        onCreate(db)
    }

    fun insertSearch (search: String){
        val db = writableDatabase
            db.execSQL("INSERT INTO historic_search (search, date_search) VALUES ('$search', datetime('now'))")
            db.close()
    }

    @SuppressLint("Range")
    fun obeterHistorico(): List<String>{
        val historicoPesquisa = mutableListOf<String>()
        val db = readableDatabase
        val cursor: Cursor? = db.rawQuery("SELECT search, date_search FROM  historic_search ORDER BY date_search DESC", null)
        cursor?.let {
            if (cursor.moveToFirst()){
                do {
                    val search = cursor.getString(cursor.getColumnIndex("search"))
                    historicoPesquisa.add(search)
                } while (cursor.moveToNext())
            }
            cursor.close()
        }
        db.close()
        return historicoPesquisa
    }
     fun deleteSearch(search: String){
         val db = writableDatabase
         db.execSQL("DELETE FROM historic_search WHERE search = '$search'")
         db.close()
     }
}