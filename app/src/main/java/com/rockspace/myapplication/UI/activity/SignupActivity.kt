package com.rockspace.myapplication.UI.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.rockspace.myapplication.R

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val signIn = findViewById<TextView>(R.id.tv_signIn)

        signIn.setOnClickListener {

            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        val first_name_input =
            findViewById<com.google.android.material.textfield.TextInputLayout>(R.id.til_firstName)
        val last_name_input =
            findViewById<com.google.android.material.textfield.TextInputLayout>(R.id.til_lastName)
        val email_input =
            findViewById<com.google.android.material.textfield.TextInputLayout>(R.id.til_email)
        val password_input =
            findViewById<com.google.android.material.textfield.TextInputLayout>(R.id.til_password)
        val confirm_password_input =
            findViewById<com.google.android.material.textfield.TextInputLayout>(R.id.til_confirmPassword)
        val btn_signup = findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.btn_singup)

        btn_signup.setOnClickListener {

            when {

                //error handling only for empty value
                //note: no error handling for
                //first name and last name formatting
                // password and confirm password match

                TextUtils.isEmpty(first_name_input.editText?.text.toString().trim()) -> {
                    Toast.makeText(
                        this,
                        "Please enter first name.",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                TextUtils.isEmpty(last_name_input.editText?.text.toString().trim()) -> {
                    Toast.makeText(
                        this,
                        "Please enter last name.",
                        Toast.LENGTH_SHORT
                    ).show()
                }

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

                TextUtils.isEmpty(confirm_password_input.editText?.text.toString().trim()) -> {
                    Toast.makeText(
                        this,
                        "Please re-enter password.",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                //auth code

                else -> {

                    val email: String = email_input.editText?.text.toString().trim()
                    val password: String = password_input.editText?.text.toString().trim()

                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(
                            OnCompleteListener<AuthResult> { task ->

                                if (task.isSuccessful) {

                                    val firebaseUser: FirebaseUser = task.result!!.user!!

                                    Toast.makeText(
                                        this,
                                        "You are registered successfully.",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                    //navigate to the required activity here (change inside intent)
                                    val intent = Intent(this, LoginActivity::class.java)
                                    intent.putExtra("user_id", firebaseUser.uid)
                                    intent.putExtra("email_id", email)

                                    startActivity(intent)
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