package com.isaacdelosreyes.example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.isaacdelosreyes.aboutapp.AboutAppFragment
import com.isaacdelosreyes.example.databinding.ActivityExampleBinding

class ExampleActivity : AppCompatActivity() {

    private var binding: ActivityExampleBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExampleBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        initAboutAppFragment()
    }

    private fun initAboutAppFragment() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val fragment = AboutAppFragment.newInstance()
        fragmentTransaction.replace(
            R.id.main__container__preference_fragment,
            fragment,
            "ABOUT_APP_FRAGMENT_TAG"
        )
        fragmentTransaction.commit()
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}