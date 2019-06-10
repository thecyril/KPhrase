package com.example.kphrase.Fragments


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import com.example.kphrase.*
import org.json.JSONException
import org.json.JSONObject
import org.json.JSONArray
import com.example.kphrase.Tools.loadJSONFromAsset
import java.io.IOException


class MainFragment : Fragment(), View.OnClickListener {
    private var vId : Int = 0
    lateinit var categoriesList: CategoriesList

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        val btn: ImageButton = view.findViewById(R.id.Favorite)
        val btn2: ImageButton = view.findViewById(R.id.Greeting)
        val btn3: ImageButton = view.findViewById(R.id.Direction)
        val btn4: ImageButton = view.findViewById(R.id.Food)
        val btn5: ImageButton = view.findViewById(R.id.Transport)
        val btn6: ImageButton = view.findViewById(R.id.Social)
        val btn7: ImageButton = view.findViewById(R.id.Emergency)
        btn.setOnClickListener(this)
        btn2.setOnClickListener(this)
        btn3.setOnClickListener(this)
        btn4.setOnClickListener(this)
        btn5.setOnClickListener(this)
        btn6.setOnClickListener(this)
        btn7.setOnClickListener(this)

//        try {
//            val jsonLocation = loadJSONFromAsset("DB.json", context!!)
//            val jsonobject = JSONObject(jsonLocation)
//            val jarray = jsonobject.getJSONArray("greeting") as JSONArray
//            for (i in 0 until jarray.length()) {
//                val jb = jarray.get(i) as JSONObject
//                val engname = jb.getString("ENG_NAME")
//                val engkorname = jb.getString("ENG_KOR_NAME")
//                val korname = jb.getString("KOREAN")
//                Log.d("ENGNAME", engname)
//            }
//        } catch (e: IOException) {
//            e.printStackTrace()
//        } catch (e: JSONException) {
//            e.printStackTrace()
//        }


        return view
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.Favorite ->
                categoriesList = CategoriesList(Favorites.fav)
            R.id.Greeting ->
                categoriesList = CategoriesList(Tools.getData("greeting", context!!))
            R.id.Direction ->
                categoriesList = CategoriesList(Tools.getData("navigation", context!!))
            R.id.Food ->
                categoriesList = CategoriesList(Tools.getData("eating", context!!))
            R.id.Transport ->
                categoriesList = CategoriesList(Tools.getData("tranportation", context!!))
            R.id.Social ->
                categoriesList = CategoriesList(Tools.getData("communication", context!!))
            R.id.Emergency ->
                categoriesList = CategoriesList(Tools.getData("emergency", context!!))
        }
        val phrases =  MainFragmentDirections.actionMainFragmentToPhraseListFragment(categoriesList)
        v!!.findNavController().navigate(phrases)
    }

}
