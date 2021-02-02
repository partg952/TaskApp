package com.example.taskapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.ArrayList

class Manager(private var context: Context,private var  array: ArrayList<String>) : RecyclerView.Adapter<Manager.ViewHolder>() {
  
  inner class ViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
  
    var num3=0
    var num=1
    var name = FirebaseAuth.getInstance().currentUser?.email?.removeSuffix("@")
    var text = itemView.findViewById<TextView>(R.id.textView3)
    init {
    
    }
    
  }
  
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    var view = LayoutInflater.from(context).inflate(R.layout.card,parent,false)
    return ViewHolder(view)
   
  }
  
  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
      var currentpos = array[position]
      holder.text.text = currentpos
  }
  
  override fun getItemCount():Int {
    
    
      return array.size
    
  }
  
}
