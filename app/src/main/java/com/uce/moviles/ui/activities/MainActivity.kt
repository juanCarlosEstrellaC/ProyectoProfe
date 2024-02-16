package com.uce.moviles.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import com.uce.moviles.core.My_Application
import com.uce.moviles.databinding.ActivityMainBinding
import com.uce.moviles.logic.usercases.local.LoginUserCase
import com.uce.moviles.ui.core.Constants

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initListeners()

    }

    private fun initListeners() {
        binding.btnLogin.setOnClickListener {
            val check = LoginUserCase(
                My_Application.getConnectionDB()!!
            ).checkLogin(
                binding.etxtEmail.text.toString(),
                binding.etxtPassword.text.toString()
            )
            if (check > 0) {
                val intent = Intent(this, PrincipalActivity::class.java)
                intent.putExtra(Constants.USER_ID, check)
                startActivity(intent)
            } else {
                Snackbar.make(
                    binding.etxtEmail,
                    "Nombre de usuario o cantraseña incorrectos",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }
    }
}