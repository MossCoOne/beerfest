package com.example.beerhive.beerlist

import android.content.DialogInterface
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.beerhive.R
import com.example.beerhive.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }
    override fun onPostCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onPostCreate(savedInstanceState, persistentState)
        val navController = findNavController(R.id.app_host_fragment)
        val appBarConfiguration = AppBarConfiguration(
            topLevelDestinationIds = setOf(),
            fallbackOnNavigateUpListener = {
                finish()
                true
            })
        binding.mainToolbar.setupWithNavController(navController,appBarConfiguration)
    }

    private fun showCustomDialog(titleText: String?) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(titleText)
        builder.setIcon(R.drawable.ic_error_outline_black_24dp)
        builder.setNegativeButton(R.string.cancel_text) { _: DialogInterface?, _: Int -> finish() }
        builder.show().setCancelable(false)
    }
}