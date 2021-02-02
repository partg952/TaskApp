package com.example.taskapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

private lateinit var email2: EditText
private lateinit var password2:EditText
private lateinit var btn:Button
private lateinit var auth:FirebaseAuth
private  var num:Int = 1



class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)







        btn = findViewById<Button>(R.id.button2)
        password2 = findViewById<EditText>(R.id.editTextNumberPassword2)
        email2 = findViewById<EditText>(R.id.editTextTextEmailAddress2)

        btn.setOnClickListener {
            var email = email2.text.toString()
            var pass = password2.text.toString()

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email,pass)
                    .addOnCompleteListener {
                        if(it.isSuccessful){
                            Toast.makeText(applicationContext,"Sign In Success",Toast.LENGTH_SHORT).show()
                            var intent = Intent(this@MainActivity2,MainActivity3::class.java)
                            intent.putExtra("email",email2.text.toString())
                            intent.putExtra("password", password2.text.toString())
                            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(intent)


                        }

                        else{
                            Toast.makeText(applicationContext,"Sign In Failed",Toast.LENGTH_SHORT).show()
                        }

                        var uid = FirebaseAuth.getInstance().uid

//                        FirebaseDatabase.getInstance().getReference().child("/")
//                                .setValue(uid.toString())


                    }
        }






    }


}