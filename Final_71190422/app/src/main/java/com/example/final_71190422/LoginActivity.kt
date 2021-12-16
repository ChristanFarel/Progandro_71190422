package com.example.final_71190422

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import  com.google.firebase.auth.AuthResult
import  com.google.firebase.auth.FirebaseAuth
import  com.google.firebase.auth.FirebaseUser
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider

class LoginActivity : AppCompatActivity() {

    companion object{
        private const val RC_SIGN_IN = 120
    }

    private lateinit var mAuth: FirebaseAuth
    private  lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val btnLogin: Button = findViewById(R.id.btnLogin)
        val txtInputEmail: com.google.android.material.textfield.TextInputEditText = findViewById(R.id.txtInputEmail)
        val txtInputPass: com.google.android.material.textfield.TextInputEditText = findViewById(R.id.txtInputPass)


        // Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        //Instance dari Firebase Autentication
        mAuth = FirebaseAuth.getInstance()

        val signInGoogle: Button = findViewById(R.id.btnLoginGoogle)

        signInGoogle.setOnClickListener {
            signIn()
        }



        btnLogin.setOnClickListener {
            if (txtInputEmail.text.toString().isEmpty() || txtInputPass.text.toString().isEmpty()){
                Toast.makeText(this,"Email atau Password tidak boleh kosong",Toast.LENGTH_SHORT).show()
            }
            else{
                FirebaseAuth.getInstance().signInWithEmailAndPassword(txtInputEmail.text.toString(),txtInputPass.text.toString())
                    .addOnCompleteListener { task ->

                        if (task.isSuccessful){
                            Toast.makeText(this,"Anda berhasil Login!",Toast.LENGTH_SHORT).show()

                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(this, "Anda gagal Login!", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }

    }



    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            val exception = task.exception
            if(task.isSuccessful){
                try {
                    // Google Sign In was successful, authenticate with Firebase
                    val account = task.getResult(ApiException::class.java)!!
                    Log.d("SignInActivity", "firebaseAuthWithGoogle:" + account.id)
                    firebaseAuthWithGoogle(account.idToken!!)
                } catch (e: ApiException) {
                    // Google Sign In failed, update UI appropriately
                    Log.w("SignInActivity", "Google sign in failed", e)
                }
            } else{
                Log.w("SignInActivity", exception.toString())
            }

        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "SignInActivity")
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "SignInActivity", task.exception)
                }
            }
    }
}