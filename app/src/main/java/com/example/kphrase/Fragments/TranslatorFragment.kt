package com.example.kphrase.Fragments


import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.example.kphrase.R

class TranslatorFragment : Fragment() {
    lateinit var koreanEng: TextView
    lateinit var koreanPhrase: TextView
    lateinit var repeat: ImageButton
    lateinit var mediaPlayer: MediaPlayer

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_translator, container, false)

        val arg = arguments?.let { TranslatorFragmentArgs.fromBundle(it) }
        val categories = arg!!.categories

        koreanEng = view.findViewById(R.id.spoken)
        koreanPhrase = view.findViewById(R.id.inkorean)
        repeat = view.findViewById(R.id.repeat)

        koreanEng.text = categories.ENG_KOR_NAME
        koreanPhrase.text = categories.KOREAN

        var sound_id = context?.resources?.getIdentifier(categories.AUDIO_FILE_NAME, "raw", context!!.packageName)
        if (sound_id != 0) {
            Log.d("SOUND", "ok")
            mediaPlayer = MediaPlayer.create(context, sound_id!!)
            mediaPlayer.start()
        }

        repeat.setOnClickListener {
            mediaPlayer.start()
        }

        return view
    }


}
