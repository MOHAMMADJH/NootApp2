package com.example.nootapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.add_producer.view.*

class MainActivity : AppCompatActivity() {


    val db = FirebaseDatabase.getInstance()
    var mRef:DatabaseReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
         mRef = db.getReference("Cart")


        Add_to_cart.setOnClickListener{
            showAddCart()

        }
    }

    fun showAddCart(){
        val alertBuilder  = AlertDialog.Builder(this)
        val view = layoutInflater.inflate(R.layout.add_producer, null)
        alertBuilder.setView(view)

        val alertDialog = alertBuilder.create()
        alertBuilder.show()

        view.addButton.setOnClickListener {
           val producer =  view.productText.text.toString()
            val weight =  view.weightText.text.toString()
            val unit =  view.unit.text.toString()

            if(producer.isNotEmpty() && weight.isNotEmpty() && unit.isNotEmpty()){
                var id = mRef!!.push().key
                var myProducer = Producer(id ,producer, weight ,unit)
                mRef!!.child(id.toString()).setValue(myProducer)
                view.productText.setText("")
                view.weightText.setText("")
                view.unit.setText("")

                alertDialog.dismiss()
            }else{
                Toast.makeText(this, "الحقول فارغة" , Toast.LENGTH_LONG).show()
            }
        }
    }
}
