package com.googleio.publicapi.app.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.slidingpanelayout.widget.SlidingPaneLayout
import com.googleio.publicapi.app.ui.adapters.EntryAdapter
import com.googleio.publicapi.app.ui.viewmodels.PublicAPIViewModel
import com.googleio.publicapi.databinding.FragmentEntryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EntryFragment : Fragment() {
    private lateinit var root : View
    private val publicApiViewModel : PublicAPIViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentEntryBinding.inflate(layoutInflater)
        root = binding.root

        val slidingPaneLayout = binding.slidingPaneLayout


        val entryRecyclerView = binding.EntryRecyclerView
        entryRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        publicApiViewModel.entryDataList.observe(viewLifecycleOwner) {
            when(it) {
                is PublicAPIViewModel.EntryState.Empty -> {
                    Toast.makeText(requireContext(), "No data available", Toast.LENGTH_LONG).show()
                }
                is PublicAPIViewModel.EntryState.Success -> {
                    entryRecyclerView.adapter = EntryAdapter(it.data.entries) {
                        publicApiViewModel.updateClickedAPI(it.Link)

                        slidingPaneLayout.openPane()
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

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, EntryListBackPressed(slidingPaneLayout))

        return root
    }
}

private class EntryListBackPressed(
    private val slidingPaneLayout: SlidingPaneLayout
) : OnBackPressedCallback(slidingPaneLayout.isOpen && slidingPaneLayout.isSlideable), SlidingPaneLayout.PanelSlideListener {
    override fun handleOnBackPressed() {
        slidingPaneLayout.closePane()
    }

    init {
        slidingPaneLayout.addPanelSlideListener(this)
    }

    override fun onPanelSlide(panel: View, slideOffset: Float) {
    }

    override fun onPanelOpened(panel: View) {
        isEnabled = true
    }

    override fun onPanelClosed(panel: View) {
        isEnabled = false
    }
}