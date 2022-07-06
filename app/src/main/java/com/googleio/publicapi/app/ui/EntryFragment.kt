package com.googleio.publicapi.app.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.googleio.publicapi.R
import com.googleio.publicapi.app.ui.adapters.EntryAdapter
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
        entryRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        viewmodel.entryDataList.observe(viewLifecycleOwner) {
            when(it) {
                is PublicAPIViewModel.EntryState.Empty -> {
                    Toast.makeText(requireContext(), "No data available", Toast.LENGTH_LONG).show()
                }
                is PublicAPIViewModel.EntryState.Success -> {
                    entryRecyclerView.adapter = EntryAdapter(it.data.entries) {
                        viewmodel.updateClickedAPI(it.Link)

                        val action = EntryFragmentDirections.actionEntryFragmentToDetailFragment()
                        findNavController().navigate(action)
                    }
                }
                is PublicAPIViewModel.EntryState.Failure -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                }
                is PublicAPIViewModel.EntryState.Loading -> {
                    Toast.makeText(requireContext(), "Loading...", Toast.LENGTH_LONG).show()
                }
                else -> {}
            }
        }

        return root
    }


}