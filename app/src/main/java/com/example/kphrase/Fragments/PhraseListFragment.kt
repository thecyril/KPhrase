package com.example.kphrase.Fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kphrase.PhrasesViewRecyclerViewAdapter

import com.example.kphrase.R

class PhraseListFragment : Fragment() {
    var TAG = "LISTFRAG"
    lateinit var phrasesView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_phrase_list, container, false)

        val arg = arguments?.let { PhraseListFragmentArgs.fromBundle(it) }
        val list = arg!!.list.categorie

        phrasesView = view.findViewById(R.id.phrasesview)

        phrasesView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        val itemDecor = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        itemDecor.setDrawable(ContextCompat.getDrawable(context!!, R.drawable.dividerwhite)!!)
        phrasesView.addItemDecoration(itemDecor)

        phrasesView.adapter = PhrasesViewRecyclerViewAdapter(list!!, context)

        return view
    }



}
