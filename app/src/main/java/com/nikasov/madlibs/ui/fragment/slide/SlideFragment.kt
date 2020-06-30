package com.nikasov.madlibs.ui.fragment.slide

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.nikasov.madlibs.R
import com.nikasov.madlibs.ui.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.dialog_back.*
import kotlinx.android.synthetic.main.fragment_slide.*


@AndroidEntryPoint
class SlideFragment : Fragment(R.layout.fragment_slide) {

    //todo: add motionlayout when next slide clicked

    private val viewModel : SlideViewModel by viewModels()
    private val args : SlideFragmentArgs by navArgs()

//    private lateinit var enterAnim : Animation
//    private lateinit var exitAnim : Animation
    private lateinit var backDialog : MaterialDialog

    private lateinit var callback: OnBackPressedCallback

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpUi()
    }

    private fun setUpUi() {

        viewModel.setListByType(args.typeOfGame)
        viewModel.setQuestion()

        setUpBackDialog()

//        enterAnim = AnimationUtils.loadAnimation(requireContext(), R.anim.btn_anim_enter)
//        exitAnim = AnimationUtils.loadAnimation(requireContext(), R.anim.btn_anim_enter)

        callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            backDialog.show()
        }

        editText.doAfterTextChanged {
            if (it.toString().isNotEmpty()) {
                showBtn()
            } else {
                hideBtn()
            }
        }

        nextBtn.setOnClickListener {
            if (editText.text.toString().isNotEmpty()) {
                viewModel.saveAndGoNext(editText.text.toString())
                editText.text.clear()
            }
        }

        viewModel.currentQuestion.observe(viewLifecycleOwner, Observer {result ->
            when (result) {
                is Resource.InProgress -> {
                    titleTxt.text = result.data
                }
                is Resource.Success -> {
                    showResult()
                }
            }
        })

        viewModel.currentDescription.observe(viewLifecycleOwner, Observer {description ->
            exampleTxt.text = description
        })
    }

    private fun setUpBackDialog() {
        backDialog = MaterialDialog(requireContext())
            .noAutoDismiss()
            .customView(R.layout.dialog_back)

        backDialog.yes.setOnClickListener {
            navigateHome()
            backDialog.dismiss()
        }
        backDialog.no.setOnClickListener {
            backDialog.dismiss()
        }
    }

    private fun hideBtn() {
        nextBtn.isEnabled = false
    }

    private fun showBtn() {
        nextBtn.isEnabled = true
    }

    private fun showResult() {
        val action = SlideFragmentDirections.actionSlideFragmentToResultFragment(args.typeOfGame, false)
        findNavController().navigate(action)
    }

    private fun navigateHome() {
        val action = SlideFragmentDirections.actionSlideFragmentToHomeFragment()
        findNavController().navigate(action)
    }
}