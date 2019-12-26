package io.github.monolithofficial.monolithprofilepage

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()



        Glide.with(this).load("https://monolithofficial.github.io/Images/logo/52958287_1217457288413180_6485153158124273664_o.jpg").into(monolithCover)
        Glide.with(this).load("https://monolithofficial.github.io/Images/logo/Monolith1.jpg").into(monolithPfp)
        Glide.with(this).load("https://monolithofficial.github.io/Images/logo/1557735151242-02.jpeg").into(monolithPfpSec)
        Glide.with(this).load("https://monolithofficial.github.io/Images/logo/Monolith.png").into(monolithPfpThird)

        Glide.with(this).load("https://monolithofficial.github.io/Images/logo/bandcamp-button-circle-line-white-64.png").into(bcBtn)
        Glide.with(this).load("https://monolithofficial.github.io/Images/logo/soundcloud.png").into(scBtn)
        Glide.with(this).load("https://monolithofficial.github.io/Images/logo/youtube.png").into(ytBtn)
        Glide.with(this).load("https://monolithofficial.github.io/Images/logo/facebook.png").into(fbBtn)
        Glide.with(this).load("https://monolithofficial.github.io/Images/logo/Dlabgold.png").into(dlBtn)


        Glide.with(this).load("https://monolithofficial.github.io/Images/logo/sandrika%20too%20deep.jpg").into(img1)
        Glide.with(this).load("https://monolithofficial.github.io/Images/logo/coollogo_com-25863201.png").into(img2)
        Glide.with(this).load("https://monolithofficial.github.io/Images/logo/coollogo_com-3614131.png").into(img3)
        Glide.with(this).load("https://monolithofficial.github.io/Images/logo/coollogo_com-2633672.png").into(img4)
        Glide.with(this).load("https://monolithofficial.github.io/Images/logo/Monolithnew.jpeg").into(img5)
        Glide.with(this).load("https://monolithofficial.github.io/Images/logo/Untitled-1.jpg").into(img6)
        Glide.with(this).load("https://monolithofficial.github.io/Images/logo/WD%20wide.png").into(img7)
        Glide.with(this).load("https://monolithofficial.github.io/Images/logo/Untitled-1-Recovered.png").into(img8)
    }

    private fun init(){

        val email = intent.extras!!.getString("email", "")
        val password = intent.extras!!.getString("password", "")

        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("user")

        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val value = dataSnapshot.getValue(UserModel::class.java)

                Log.d("onDataChange", "Value is: $value")
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("onDataChange", "Failed to read value.", error.toException())
            }
        })


        // display.text = email.substringBefore("@")


        bcBtn.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW, Uri.parse("https://monolithworldline.bandcamp.com"))
            startActivity(i)
        }
        scBtn.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW, Uri.parse("https://soundcloud.com/monolithworldline"))
            startActivity(i)
        }
        ytBtn.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/channel/UC4IrptxENHzpZiOlIGSgPLQ"))
            startActivity(i)
        }
        fbBtn.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/MonolithWorldline/"))
            startActivity(i)
        }
        dlBtn.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW, Uri.parse("https://caspar000.github.io/Darkroom-Website/"))
            startActivity(i)
        }
    }



}
