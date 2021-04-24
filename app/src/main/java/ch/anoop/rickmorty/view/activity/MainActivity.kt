package ch.anoop.rickmorty.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ch.anoop.rickmorty.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}