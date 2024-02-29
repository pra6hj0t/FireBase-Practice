package com.example.firebasepractice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    lateinit var DataBase : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fullName = findViewById<EditText>(R.id.et_fullName)
        val userName = findViewById<EditText>(R.id.et_userName)
        val email = findViewById<EditText>(R.id.et_email)
        val password = findViewById<EditText>(R.id.et_password)
        val retypePassword = findViewById<EditText>(R.id.et_retypePassword)
        val male_Radio = findViewById<RadioButton>(R.id.male_Box)
        val female_Radio = findViewById<RadioButton>(R.id.female_box)
        val signUp_Btn = findViewById<Button>(R.id.btn_signup)
        val loginNow = findViewById<TextView>(R.id.text_loginNow)
        val radioGroup = findViewById<RadioGroup>(R.id.radio_group)


        signUp_Btn.setOnClickListener {

            val FullName = fullName.text.toString()
            val UserName = userName.text.toString()
            val Email = email.text.toString()
            val Password = password.text.toString()
            val RetypePassword = retypePassword.text.toString()
            var gender =""
            if (male_Radio.isChecked){
                gender="Male"
            }else{
                if (female_Radio.isChecked){
                    gender="Female"
                }
            }

            if (FullName.trim().equals("")){
                fullName.setError("Name is Required")
                return@setOnClickListener
            }
            if (UserName.trim().equals("")){
                userName.setError("Username is required")
                return@setOnClickListener
            }
            if (Email.trim().equals("")){
                email.setError("Email is required")
                return@setOnClickListener
            }
            if (Password.trim().equals("")){
                password.setError("Password is required")
                return@setOnClickListener
            }
            if (RetypePassword.trim().equals("")){
                retypePassword.setError("Retype Password is required")
                return@setOnClickListener
            }
            if(gender.equals("")){
                Toast.makeText(this,"Select Gender",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(!Password.equals(RetypePassword)){
                retypePassword.setError("Not Matched")
                return@setOnClickListener
            }


            val users = Users(FullName,UserName,Email,Password,gender)

            DataBase=FirebaseDatabase.getInstance().getReference("Users")
            DataBase.child(UserName).setValue(users).addOnSuccessListener {
                fullName.text.clear()
                userName.text.clear()
                email.text.clear()
                password.text.clear()
                retypePassword.text.clear()
                radioGroup.clearCheck()
                Toast.makeText(this,"Registered",Toast.LENGTH_SHORT).show()

            }


        }

        loginNow.setOnClickListener {
            intent= Intent(this,SignInActivity::class.java)
            startActivity(intent)
        }
    }
}