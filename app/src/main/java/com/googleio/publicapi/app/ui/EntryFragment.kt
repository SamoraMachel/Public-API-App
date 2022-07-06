package com.googleio.publicapi.app.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.googleio.publicapi.R
import com.googleio.publicapi.app.ui.viewmodels.PublicAPIViewModel
import com.googleio.publicapi.databinding.FragmentEntryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EntryFragment : Fragment() {
    private lateinit var root : View
    private lateinit var viewmodel : PublicAPIViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentEntryBinding.inflate(layoutInflater)
        root = binding.root

        viewmodel = ViewModelProvider(this).get(PublicAPIViewModel::class.java)

        val entryRecyclerView = binding.EntryRecyclerView

//        return inflater.inflate(R.layout.fragment_entry, container, false)

        return root
    }


}