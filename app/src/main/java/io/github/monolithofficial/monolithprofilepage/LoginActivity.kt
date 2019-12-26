package io.github.monolithofficial.monolithprofilepage

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = FirebaseAuth.getInstance()
        init()
    }

    private fun init(){





        readData()
        logInBtn.setOnClickListener {
            if (emailField.text.isNotEmpty() && passwordField.text.isNotEmpty()){
                logIn()
            }
        }
        signUpBtn.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }



    }

    private fun logIn(){
        auth.signInWithEmailAndPassword(emailField.text.toString(), passwordField.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("LogIn", "signInWithEmail:success")
                    saveDta()
                    val user = auth.currentUser
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("email", emailField.text.toString())
                    intent.putExtra("password", passwordField.text.toString())
                    startActivity(intent)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("LogIn", "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }

                // ...
            }

    }

    private fun saveDta(){
        val sharedPreferences = getSharedPreferences("user_data", Context.MODE_PRIVATE)

        val edit = sharedPreferences.edit()
        edit.putString("email", emailField.text.toString())
        edit.putString("password", passwordField.text.toString())

        edit.commit()
    }
    private fun readData(){
        val sharedPreferences = getSharedPreferences("user_data", Context.MODE_PRIVATE)

        emailField.setText(sharedPreferences.getString("email", ""))
        passwordField.setText(sharedPreferences.getString("password", ""))
    }
}
