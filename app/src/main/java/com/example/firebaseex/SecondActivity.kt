package com.example.firebaseex

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    private var user_uid: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        if(intent.hasExtra("user_uid")){
            user_uid = intent.getStringExtra("user_uid")!!
        }

        /** 입력 버튼을 눌렀을 때**/
        input_btn.setOnClickListener{
            val database: FirebaseDatabase = FirebaseDatabase.getInstance()
            val myRef: DatabaseReference = database.getReference()

            val day_count: Int = Integer.valueOf(day_count.text.toString())
            val smoking_count: Int = Integer.valueOf(smoking_count.text.toString())
            val money_count: Int = Integer.valueOf(money_count.text.toString())

//            myRef.setValue(user_uid)
            val dataInput = Data(day_count,smoking_count, money_count)

            //식별자로 uid. 그 밑에 데이터들이 들어간다.
            myRef.child(user_uid).setValue(dataInput)

            val intentVal = Intent(this, ThirdActivity::class.java)
            intentVal.putExtra("user_uid", user_uid)
            startActivity(intentVal)
        }
    }
}