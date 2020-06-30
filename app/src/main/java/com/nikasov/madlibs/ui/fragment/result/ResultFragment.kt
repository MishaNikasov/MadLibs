package com.nikasov.madlibs.ui.fragment.result

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.nikasov.madlibs.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_result.*
import java.lang.StringBuilder

@AndroidEntryPoint
class ResultFragment : Fragment(R.layout.fragment_result) {

    private val resultViewModel : ResultViewModel by viewModels()
    private val args : ResultFragmentArgs by navArgs()

    private lateinit var callback : OnBackPressedCallback

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            navigateHome()
        }

        setUpUi()
    }

    private fun setUpUi() {

        if (args.historyId != -1) {
            resultViewModel.setHistoryById(args.historyId)
            saveBtn.visibility = View.GONE
            retryBtn.visibility = View.GONE
            callback.isEnabled = false
        } else {
            resultViewModel.setStories(args.typeOfGame)
                //todo: save snackbar
            saveBtn.setOnClickListener {
                resultViewModel.addToHistoryList(
                    firstStoryText.text.toString(),
                    secondStoryText.text.toString()
                )
            }
            retryBtn.setOnClickListener {
                retryGame()
            }
        }

        resultViewModel.firstStory.observe(viewLifecycleOwner, Observer {
            firstStoryText.text = it
        })
        resultViewModel.secondStory.observe(viewLifecycleOwner, Observer {
            secondStoryText.text = it
        })

        homeBtn.setOnClickListener {
            navigateHome()
        }
        shareBtn.setOnClickListener {
            share()
        }
    }

    private fun share() {
            val s = StringBuilder().append(
                resources.getString(R.string.first_story_title), "\n",
                firstStoryText.text, "\n\n",
                resources.getString(R.string.second_story_title), "\n",
                secondStoryText.text
            )
            val sendIntent = Intent().apply {
                action = Intent.ACTION_SEND
                type="text/plain"
                putExtra(Intent.EXTRA_TEXT, s.toString())
            }
            val shareIntent = Intent.createChooser(sendIntent, "Поделится историей...")
            startActivity(shareIntent)
    }

    private fun navigateHome() {
        val action = ResultFragmentDirections.actionResultFragmentToHomeFragment()
        findNavController().navigate(action)
    }

    private fun retryGame() {
        val action = ResultFragmentDirections.actionResultFragmentToSlideFragment(args.typeOfGame)
        findNavController().navigate(action)
    }

    override fun onPause() {
        super.onPause()
        resultViewModel.deleteAllSlides()
    }
}