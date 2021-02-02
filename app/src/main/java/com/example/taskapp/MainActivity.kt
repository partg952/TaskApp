package com.example.taskapp

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase

private lateinit var email:EditText
private lateinit var password:EditText
private lateinit var btn:Button
private lateinit var auth:FirebaseAuth
private lateinit var text:TextView
private  var num:Int = 1
private lateinit var name:EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    


        btn = findViewById<Button>(R.id.button)
        password = findViewById<EditText>(R.id.editTextNumberPassword)
        email = findViewById<EditText>(R.id.editTextTextEmailAddress)
        text = findViewById<TextView>(R.id.textView)
        name = findViewById<EditText>(R.id.editTextTextPersonName2)
  
        
        var displaname=FirebaseAuth.getInstance().currentUser?.displayName
	




        btn.setOnClickListener {
            if(password.text.isNullOrEmpty()||email.text.isNullOrEmpty()){
                Toast.makeText(applicationContext,"Please Enter Info!",Toast.LENGTH_SHORT).show()
            }
            else{
                var pass = password.text.toString()
                var gmai = email.text.toString()
                var name2 = name.text.toString()
                

                   FirebaseAuth.getInstance().createUserWithEmailAndPassword(gmai,pass)
                        .addOnCompleteListener {
                            if(it.isSuccessful){
  
						var uid = FirebaseAuth.getInstance().uid
  
						
  
						var num=0
                                
                                var path = FirebaseAuth.getInstance().currentUser?.email?.removeSuffix("@gmail.com")
  
  
                                FirebaseDatabase.getInstance().getReference().child("users").child("$path").child("Tasks").child("num").setValue(num)
  
						FirebaseDatabase.getInstance().getReference().child("users").child("$path").child("email").setValue(FirebaseAuth.getInstance().currentUser?.email)
                                
                                Log.d("Main","$path")
						
						
                                Toast.makeText(applicationContext,"success",Toast.LENGTH_SHORT).show()
                                var intent = Intent(this@MainActivity,MainActivity3::class.java)
                                intent.putExtra("gmail",gmai)
                                intent.putExtra("pass",pass)
                                startActivity(intent)

                               
                                
//                                FirebaseDatabase.getInstance().getReference().child(uid.toString()).child("$gmai")
//                                        .setValue(gmai)
                            }
                            else{
                                Toast.makeText(applicationContext,"failed",Toast.LENGTH_SHORT).show()

                            }
                        }
            }

        }




        text.setOnClickListener {
            var intent = Intent(this@MainActivity,MainActivity2::class.java)
            startActivity(intent)
        }
    }
}