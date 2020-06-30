package com.nikasov.madlibs.ui.fragment.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.nikasov.madlibs.R
import com.nikasov.madlibs.common.Constants
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(R.layout.fragment_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpUi()
    }

    private fun setUpUi() {

        singlePlayerBtn.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToSlideFragment(Constants.TYPE_SINGLE_PLAYER)
            findNavController().navigate(action)
        }

        twoPlayersBtn.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToSlideFragment(Constants.TYPE_TWO_PLAYERS)
            findNavController().navigate(action)
        }
    }
}