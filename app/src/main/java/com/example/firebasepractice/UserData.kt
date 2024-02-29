package com.example.firebasepractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import org.w3c.dom.Text

class UserData : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_data)

        val welcomeText = findViewById<TextView>(R.id.welcomeText)
        val email = findViewById<TextView>(R.id.emailText)
        val password = findViewById<TextView>(R.id.passwordText)
        val gender = findViewById<TextView>(R.id.genderText)


        val userName = intent.getStringExtra("name")
        val useremail = intent.getStringExtra("email")
        val userpassword = intent.getStringExtra("password")
        val usergender = intent.getStringExtra("gender")




        welcomeText.text= "Welcome $userName"
        email.text ="Email : $useremail"
        password.text ="Password : $userpassword"
        gender.text ="Gender : $usergender"


    }
}