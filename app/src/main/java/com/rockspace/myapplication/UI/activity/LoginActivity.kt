package com.rockspace.myapplication.UI.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.rockspace.myapplication.R

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val signUp = findViewById<TextView>(R.id.tv_signUp)
        val btn_login = findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.btn_login)

        signUp.setOnClickListener {

            startActivity(Intent(this, SignupActivity::class.java))
            finish()
        }

        val email_input =
            findViewById<com.google.android.material.textfield.TextInputLayout>(R.id.til_email)
        val password_input =
            findViewById<com.google.android.material.textfield.TextInputLayout>(R.id.til_password)


        btn_login.setOnClickListener {
            when {

                TextUtils.isEmpty(email_input.editText?.text.toString().trim()) -> {
                    Toast.makeText(
                        this,
                        "Please enter email.",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                TextUtils.isEmpty(password_input.editText?.text.toString().trim()) -> {
                    Toast.makeText(
                        this,
                        "Please enter password.",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                //auth code

                else -> {

                    val email: String = email_input.editText?.text.toString().trim()
                    val password: String = password_input.editText?.text.toString().trim()

                    FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(
                            OnCompleteListener<AuthResult> { task ->

                                if (task.isSuccessful) {

                                    val firebaseUser: FirebaseUser = task.result!!.user!!

                                    Toast.makeText(
                                        this,
                                        "You are successfully logged in.",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                    //navigate to the required activity here (change inside intent)
                                    val intent = Intent(this, DashboardActivity::class.java)
                                    intent.putExtra("user_id", FirebaseAuth.getInstance().currentUser!!.uid)
                                    intent.putExtra("email_id", email)

                                    startActivity(intent)

                                    //remove finish() if don't want back button straight away exit apps
                                    finish()

                                } else {

                                    Toast.makeText(
                                        this,
                                        task.exception!!.message.toString(),
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }

                            }

                        )
                }


            }
        }

    }
}