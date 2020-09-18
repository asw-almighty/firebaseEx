package com.example.firebaseex

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()

        var user = auth.currentUser

        login_btn.setOnClickListener {

            if (user != null) {
                /** 흡연 페이지로 보내기 */
                Toast.makeText(this, "이미 로그인 했습니다", Toast.LENGTH_LONG).show()
                goMainPage(user!!.uid)

            } else {
                /** 로그인하고 흡연 페이지로 보내기 */
                auth.signInAnonymously()
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "로그인 성공", Toast.LENGTH_LONG).show()
                            user = auth.currentUser
                            goMainPage(user!!.uid)
                        } else {
                            Toast.makeText(this, "로그인 실패", Toast.LENGTH_LONG).show()
                        }
                    }
            }


        }


    }

    fun goMainPage(user_uid: String) {
        val intentVal = Intent(this, SecondActivity::class.java)
        /** 데이터를 넘기기 */
        intentVal.putExtra("user_uid", user_uid)
        startActivity(intentVal)
    }

}