package com.example.dataking.googleloginkotlin

import android.content.Intent
import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.common.api.ResultCallback
import com.google.android.gms.common.api.Status
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), GoogleApiClient.OnConnectionFailedListener {
    override fun onConnectionFailed(connectionResult: ConnectionResult) {
        Log.d("LOGIN", "======================================================================== GOOGLE LOGIN FAIL")
    }

    private val RC_SIGN_IN = 9001
    private var mGoogleApiClient: GoogleApiClient? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        updateUI(false)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()

        mGoogleApiClient = GoogleApiClient.Builder(this).enableAutoManage(this, this).addApi(Auth.GOOGLE_SIGN_IN_API, gso).build()

        sign_in_button.setOnClickListener(View.OnClickListener {
            var signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient)
            startActivityForResult(signInIntent, RC_SIGN_IN)
        })
        btn_logout.setOnClickListener(View.OnClickListener {
            Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback {

                object : ResultCallback<Status> {
                    override fun onResult(status: Status) {
                        updateUI(status.isSuccess)
                    }
                }
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            var result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
            updateUI(result.isSuccess)
        }
    }

    private fun updateUI(isLogin: Boolean) {
        if (isLogin) {
            sign_in_button.visibility = View.GONE
            btn_logout?.visibility = View.VISIBLE
        } else {
            sign_in_button?.visibility = View.VISIBLE
            btn_logout?.visibility = View.GONE

        }
    }


}
