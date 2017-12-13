package com.example.dataking.googleloginkotlin

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txt_signupnow.setOnClickListener(View.OnClickListener {
            var intent : Intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        })

    }
}
