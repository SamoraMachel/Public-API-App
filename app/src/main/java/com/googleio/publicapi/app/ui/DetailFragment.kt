package com.googleio.publicapi.app.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import androidx.fragment.app.activityViewModels
import com.google.android.material.snackbar.Snackbar
import com.googleio.publicapi.app.ui.viewmodels.PublicAPIViewModel
import com.googleio.publicapi.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private lateinit var binding : FragmentDetailBinding
    private lateinit var root : View
    private val publicApiViewModel : PublicAPIViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(layoutInflater)
        root = binding.root

        val web_view = binding.detailWebView

        publicApiViewModel.clickedAPI.observe(viewLifecycleOwner) { url ->
            if (url != "") {
                binding.webProgressBar.visibility = View.VISIBLE
                web_view.apply {
                    settings.javaScriptEnabled = true
                    loadUrl(url)
                    Log.d("ProgressBar", "onCreateView: $progress")
                }
                web_view.webViewClient = object : WebViewClient() {
                    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                        view?.loadUrl(url)
                        return false
                    }
                    override fun onPageFinished(view: WebView?, url: String?) {
                        super.onPageFinished(view, url)
                        binding.webProgressBar.visibility = View.INVISIBLE
                    }
                }
            } else {
//                Snackbar.make(requireView(), "Broken URL", Snackbar.LENGTH_INDEFINITE)
//                    .setAction("Exit") {
//                        requireActivity().onBackPressed()
//                    }.show()
                binding.webProgressBar.visibility = View.INVISIBLE
            }
        }


        return root
    }


}