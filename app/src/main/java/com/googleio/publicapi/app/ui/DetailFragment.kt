package com.googleio.publicapi.app.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.googleio.publicapi.R
import com.googleio.publicapi.app.ui.viewmodels.PublicAPIViewModel
import com.googleio.publicapi.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private lateinit var binding : FragmentDetailBinding
    private lateinit var root : View
    private lateinit var viewmodel : PublicAPIViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(layoutInflater)
        root = binding.root

        viewmodel = ViewModelProvider(this).get(PublicAPIViewModel::class.java)

        val web_view = binding.detailWebView

        viewmodel.clickedAPI.observe(viewLifecycleOwner) { url ->
            if (url != "") {
                web_view.loadUrl(url)
            } else {
                Snackbar.make(requireView(), "Broken URL", Snackbar.LENGTH_INDEFINITE)
                    .setAction("Exit") {
                        requireActivity().onBackPressed()
                    }
            }
        }


        return root
    }


}