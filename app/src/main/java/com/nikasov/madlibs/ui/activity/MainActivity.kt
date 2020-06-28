package com.nikasov.madlibs.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.nikasov.madlibs.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpNavigation()
    }

    private fun setUpNavigation() {
        val navController = findNavController(R.id.nav_host_fragment)
        bottom_navigation_view.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.resultFragment -> {
                    hideBottomNav()
                }
                R.id.slideFragment -> {
                    hideBottomNav()
                }
                else -> {
                    showBottomNav()
                }
            }
        }
    }


    private fun showBottomNav() {
        bottom_navigation_view.visibility = View.VISIBLE
    }

    private fun hideBottomNav() {
        bottom_navigation_view.visibility = View.GONE
    }

}