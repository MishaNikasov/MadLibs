package com.nikasov.madlibs.ui.fragment.slide

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.nikasov.madlibs.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SlideFragment : Fragment(R.layout.fragment_slide) {

    private val slideViewModel : SlideViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }

}