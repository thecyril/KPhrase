package com.example.kphrase

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.BounceInterpolator
import android.view.animation.ScaleAnimation
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.kphrase.Fragments.MainFragmentDirections
import com.example.kphrase.Fragments.PhraseListFragmentDirections
import com.example.kphrase.Tools.isInList
import com.example.kphrase.Tools.posInList
import kotlinx.android.synthetic.main.list_view.view.*

class PhrasesViewRecyclerViewAdapter(val item: ArrayList<Categories>, val context: Context?) :
    RecyclerView.Adapter<PhrasesViewRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_view, parent, false), item, context)
    }

    override fun getItemCount(): Int {
        return item.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.item.text = item[position].ENG_NAME
        holder.subitem.text = item[position].ENG_KOR_NAME
        holder.button_favorite.isChecked = isInList(item[position])

    }

    class ViewHolder(view: View, item: ArrayList<Categories>, context: Context?) : RecyclerView.ViewHolder(view) {

        var layout: RelativeLayout
        var text: TextView
        var button_favorite: ToggleButton

        val item = view.item!!
        val subitem = view.subitem

        init {
            layout = view.findViewById(R.id.card)
            text = view.findViewById(R.id.item)
            button_favorite = view.findViewById(R.id.button_favorite)

            val scaleAnimation = ScaleAnimation(
                0.7f,
                1.0f,
                0.7f,
                1.0f,
                Animation.RELATIVE_TO_SELF,
                0.7f,
                Animation.RELATIVE_TO_SELF,
                0.7f
            )
            scaleAnimation.duration = 500
            val bounceInterpolator = BounceInterpolator()
            scaleAnimation.interpolator = bounceInterpolator

            button_favorite.setOnCheckedChangeListener(object : View.OnClickListener,
                CompoundButton.OnCheckedChangeListener {
                override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {
                    if(!button_favorite.isPressed) {
                        return
                    }
                    p0?.startAnimation(scaleAnimation)
                    if (p1)
                        Favorites.fav.add(item[adapterPosition])
                    else
                        Favorites.fav.removeAt(posInList(item[adapterPosition]))
                }

                override fun onClick(p0: View?) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }
            })

            text.setOnClickListener {
                //view.isEnabled = false
                val phrases =
                    PhraseListFragmentDirections.actionPhraseListFragmentToTranslatorFragment(item[adapterPosition])
                view.findNavController().navigate(phrases)
            }

            layout.setOnClickListener {
                //view.isEnabled = false
                val phrases =
                    PhraseListFragmentDirections.actionPhraseListFragmentToTranslatorFragment(item[adapterPosition])
                view.findNavController().navigate(phrases)
            }

        }
    }
}