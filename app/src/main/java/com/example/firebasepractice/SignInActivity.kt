package com.example.firebasepractice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignInActivity : AppCompatActivity() {
    lateinit var DatabaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)


        val newUserSignUp = findViewById<TextView>(R.id.text_SignUpNow)
        val userName = findViewById<EditText>(R.id.et_userName_singin)
        val password = findViewById<EditText>(R.id.et_password_signin)
        val signInBtn = findViewById<Button>(R.id.btn_SignIn)


        signInBtn.setOnClickListener {
            val UserName = userName.text.toString()
            val Password = password.text.toString()

            DatabaseReference=FirebaseDatabase.getInstance().getReference("Users")


            if(UserName.trim().equals("")){
                userName.setError("Username Required")
            return@setOnClickListener
            }
            if (Password.trim().equals("")){
                password.setError("Password Required")
                return@setOnClickListener
            }else{
                DatabaseReference.child(UserName).get().addOnSuccessListener {
                    if (it.exists()){
                        val name = it.child("fullName").value
                        val password1 = it.child("password").value
                        val email = it.child("email").value
                        val gender = it.child("gender").value

                        if (Password.equals(password1)){
                            intent=Intent(this,UserData::class.java)


                            intent.putExtra("name",name.toString())
                            intent.putExtra("email",email.toString())
                            intent.putExtra("gender",gender.toString())
                            intent.putExtra("password",password1.toString())


                            startActivity(intent)
                            finish()
                        }else{
                            Toast.makeText(this," Wrong Password",Toast.LENGTH_SHORT).show()
                        }
                    }else{
                        Toast.makeText(this,"User Not Found",Toast.LENGTH_SHORT).show()
                    }
                }
            }

        }


        newUserSignUp.setOnClickListener {
            intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
}