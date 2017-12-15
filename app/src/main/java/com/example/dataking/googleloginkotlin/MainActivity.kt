package com.example.dataking.googleloginkotlin

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gooogle()


        txt_signupnow.setOnClickListener(View.OnClickListener {
            var intent: Intent = Intent(this, ListActivity::class.java)
            startActivity(intent)
        })

    }

    private fun gooogle() {

        val gso : GoogleSignInOptions  = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN).requestEmail().build()

    }


}
