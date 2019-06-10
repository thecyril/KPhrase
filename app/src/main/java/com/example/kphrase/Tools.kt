package com.example.kphrase

import android.app.PendingIntent.getActivity
import android.content.Context
import android.util.Log
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

object Tools {

    fun isInList(categories: Categories): Boolean {
        for (i in 0 until Favorites.fav.size) {
            if (categories.Id == Favorites.fav[i].Id)
                return true
        }
        return false
    }

    fun posInList(categories: Categories): Int {
        for (i in 0 until Favorites.fav.size) {
            if (categories.Id == Favorites.fav[i].Id)
                return i
        }
        return 0
    }

    fun loadJSONFromAsset(file: String, context: Context): String {
        var json: String? = null
        try {
            val ips = context.assets.open(file)
            val size = ips.available()
            val buffer = ByteArray(size)
            ips.read(buffer)
            ips.close()
            json = String(buffer, Charsets.UTF_8)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return ""
        }

        return json
    }

    fun getData(categorie: String, context: Context): ArrayList<Categories>? {
        lateinit var categories: Categories
        var list = ArrayList<Categories>()

        try {
            val jsonLocation = loadJSONFromAsset("DB.json", context)
            val jsonobject = JSONObject(jsonLocation)
            val jarray = jsonobject.getJSONArray(categorie) as JSONArray
            for (i in 0 until jarray.length()) {
                val jb = jarray.get(i) as JSONObject
                categories = Categories(
                    jb.getString("ID"),
                    jb.getString("ENG_NAME"),
                    jb.getString("ENG_KOR_NAME"),
                    jb.getString("KOREAN"),
                    jb.getString("AUDIO_FILE_NAME")
                )
                list.add(categories)
//                val engname = jb.getString("ENG_NAME")
//                val engkorname = jb.getString("ENG_KOR_NAME")
//                val korname = jb.getString("KOREAN")

                //Log.d("ENGNAME", engname)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: JSONException) {
            e.printStackTrace()
            return null
        }
        return list
    }
}