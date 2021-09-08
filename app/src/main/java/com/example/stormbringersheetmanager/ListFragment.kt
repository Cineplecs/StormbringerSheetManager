package com.example.stormbringersheetmanager

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class ListFragment : Fragment() {

    private lateinit var listLinearLayout: LinearLayout
    private lateinit var mAuth: FirebaseAuth
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        mAuth = Firebase.auth
        database =
            FirebaseDatabase.getInstance("https://stormbringersheetmanager-default-rtdb.europe-west1.firebasedatabase.app/").reference

        var bundle = Bundle()
        listLinearLayout = view.findViewById(R.id.listLinearLayout)

        database.child("Char").child(mAuth.uid!!)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    dataSnapshot.children.forEach(){ it ->
                        var button = Button(requireContext())
                        var params = LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                        )
                        params.gravity = Gravity.CENTER_HORIZONTAL
                        button.layoutParams = params
                        button.text = it.key.toString()
                        button.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15f)
                        button.setOnClickListener {
                            bundle.putString("charName", button.text.toString())
                            Navigation.findNavController(view).navigate(R.id.sheetViewFragment, bundle)
                        }
                        listLinearLayout.addView(button)
                    }
                    for(i in 0 until dataSnapshot.childrenCount){

                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.w(TAG, "Failed to read value.", error.toException())
                }
            }
            )




        return view
    }
}