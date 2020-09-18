package com.example.firebaseex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_third.*

class ThirdActivity : AppCompatActivity() {

    private var user_uid: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        user_uid = intent.getStringExtra("user_uid").toString()

        FirebaseDatabase.getInstance().getReference().child(user_uid)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val dataFromFB = snapshot.getValue(Data::class.java)
                    text_result_value.setText("금연일: "+dataFromFB?.day_count +", 담배피는 갯수: "+dataFromFB?.smoking_count+", 담배 가격: "+dataFromFB?.money_count)
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })

    }
}