package com.example.dataking.googleloginkotlin

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
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


        btn_googlemap.setOnClickListener(View.OnClickListener {

            var intent : Intent = Intent(this, GooglemapActivity::class.java)
            startActivity(intent)

        })

        sign_in_button.setOnClickListener(View.OnClickListener {
            var signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient)
            startActivityForResult(signInIntent, RC_SIGN_IN)


        })
        btn_logout.setOnClickListener(View.OnClickListener {

            signOut()
            revokeAccess()

        })

        btn_fuel.setOnClickListener(View.OnClickListener {

            var intent : Intent = Intent(this, FuelActivity::class.java)
            startActivity(intent)

        })
    }

    private fun revokeAccess() {
        Auth.GoogleSignInApi.revokeAccess(mGoogleApiClient).setResultCallback {
            // [START_EXCLUDE]
            Log.d("Signin", "revokeAccess====================================================")
            updateUI(false)
            // [END_EXCLUDE]

            getDisplayName.setText("")
            getEmail.setText("")
            getFamilyName.setText("")
            getGivenName.setText("")
            getId.setText("")
            getIdToken.setText("")
            getPhotoUrl.setText("")
            account_photo_image.setImageBitmap(null)
        }
    }

    private fun signOut() {

        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback {
            // [START_EXCLUDE]
            Log.d("Signin", "signOut====================================================")
            updateUI(false)
            // [END_EXCLUDE]
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            var result: GoogleSignInResult = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
            updateUI(result.isSuccess)
            getAccountInfo(result);
        }
    }

    private fun getAccountInfo(result: GoogleSignInResult) {

        var account: GoogleSignInAccount? = result.getSignInAccount()

        getDisplayName.setText("" + account!!.displayName)
        getEmail.setText("" + account!!.email)
        getFamilyName.setText("" + account!!.familyName)
        getGivenName.setText("" + account!!.givenName)
        getId.setText("" + account!!.id)
        getIdToken.setText("" + account!!.idToken)
        getPhotoUrl.setText("" + account!!.photoUrl)

        Glide.with(applicationContext).load(account.photoUrl).into(account_photo_image)


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
