    package com.example.taskapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.lang.StringBuilder

private lateinit var btn:Button
private lateinit var edit:EditText
private lateinit var textView: TextView
private var num:Int = 2 
private var task6:Int=0
//private  var task57:Int=0
private var num56:Int?=0


class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        var array = arrayListOf<String>()
        var array2 = arrayListOf<String>()
        val uid = FirebaseAuth.getInstance().uid
        var recycle = findViewById<RecyclerView>(R.id.re)

        recycle.layoutManager = LinearLayoutManager(this)
        var adapter = Manager(this,array)
        recycle.adapter = adapter
        var email=FirebaseAuth.getInstance().currentUser?.email?.removeSuffix("@gmail.com")



        btn = findViewById<Button>(R.id.button3)
        edit = findViewById<EditText>(R.id.editTextTextPersonName)

       
        Log.d("Main","${FirebaseAuth.getInstance().uid }")


        
        
        
        var ref = FirebaseDatabase.getInstance().getReference().child("users").child("$email")
    
        FirebaseAuth.getInstance().currentUser?.email



       
        
        var num2=0


        if(uid==null){
            var intent = Intent(this@MainActivity3,MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }

        else{
            var getdata = object:ValueEventListener{
                override fun onDataChange(datasnapshot: DataSnapshot) {
                    var sb = StringBuilder()
                    for(i in datasnapshot.children){
                        var num = i.child("num").getValue().toString()
                        if(num=="null"||num=="0"){

                        }else{
                           task6=num.toInt()
                           num2=num.toInt()
                            array.clear()
                            Log.d("Main2","retrieved $num2")
                            while (task6>0){
                                var task = i.child("$task6").getValue().toString()
                                array.add(task)
                                array.asReversed()
                                adapter.notifyDataSetChanged()
                                task6--
                            }
                            
                           
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(applicationContext,"An Error Occured",Toast.LENGTH_SHORT).show()
                }

            }

           ref.addValueEventListener(getdata)
//        var password = FirebaseAuth.getInstance().u

            var getdata2 = object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for(i in snapshot.children){
                        var checked = i.child("checked").getValue().toString()
                        if(checked=="null"||checked=="0"){
                        
                        }
                        else{
                            var checked = checked.toInt()
                            array2.clear()
                            while(0<checked){
                                var data = i.child("$checked").getValue().toString()
                                array2.add(data)
                                checked--
                            }
                        }
                        
                    }
                }
    
                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            }
            


            btn.setOnClickListener {
                if(edit.text.isNullOrEmpty()){
                    Toast.makeText(applicationContext,"Nothing To Save!",Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                else
                {
                    Log.d("Main","saved")
                    num2 = num2+1
                    Log.d("Main2","num $num")
                    var name = edit.text.toString()
                    var ref = FirebaseDatabase.getInstance().getReference().child("users").child("$email").child("Tasks").child("$num2")
                    ref.setValue(name)
                            .addOnCompleteListener {
                                if (it.isSuccessful) {
                                    Toast.makeText(applicationContext, "Task Saved!", Toast.LENGTH_SHORT).show()
                                } else {
                                    Toast.makeText(applicationContext, "An Error Occured!", Toast.LENGTH_SHORT).show()
                                }
                            }
//            textView.append(edit.text.t                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           oString())

                    var ref2 = FirebaseDatabase.getInstance().reference.child("users").child("$email").child("Tasks").child("num")
                    ref2.setValue(num2)
                }



            }


        }








    }

    override fun onStart() {
        super.onStart()

    }
    
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.nav,menu)
        return true
    }
    
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
        when(item?.itemId){
            R.id.sign->{
                FirebaseAuth.getInstance().signOut()
                var intent = Intent(this,MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }
        }
    }
}


    

//class User(var number:Int,var task)