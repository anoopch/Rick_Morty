package ch.anoop.rickmorty.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import ch.anoop.rickmorty.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityMainBinding
    private lateinit var navigationController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(viewBinding.navHostFragment.id) as NavHostFragment
        navigationController = navHostFragment.findNavController()
        setupActionBarWithNavController(navigationController)
    }

    override fun onSupportNavigateUp(): Boolean {
        navigationController.navigateUp()
        return super.onSupportNavigateUp()
    }
}