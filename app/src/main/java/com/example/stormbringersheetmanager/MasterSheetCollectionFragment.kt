package com.example.stormbringersheetmanager

import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MasterSheetCollectionFragment : Fragment() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private lateinit var masterLinearLayout : LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_master_sheet_collection, container, false)

        var bundle = requireArguments()
        masterLinearLayout = view.findViewById(R.id.masterLinearLayout)
        database =
            FirebaseDatabase.getInstance("https://stormbringersheetmanager-default-rtdb.europe-west1.firebasedatabase.app/").reference

        /*database.child("Games").get().addOnSuccessListener { dataData ->
            println("dataData: " + dataData.key)
            dataData.children.forEach(){ gamesData ->
                println("gamesData: " + gamesData.key)
                gamesData.child("players").children.forEach(){ playersData ->
                    println(playersData.key)
                    playersData.children.forEach(){ sheets ->
                        println(sheets.key)
                        var button = Button(requireContext())
                        button.text = sheets.key
                        var params = LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                        )
                        params.gravity = Gravity.CENTER_HORIZONTAL
                        params.setMargins(15,15,15,15)
                        button.layoutParams = params
                        button.setOnClickListener(){
                            var bundle = Bundle()
                            bundle.putString("username", sheets.key)
                            bundle.putString("game", gamesData.key)
                            Navigation.findNavController(view).navigate(R.id.gameSheetFragment, bundle)
                        }
                        masterLinearLayout.addView(button)
                    }
                }
            }
        }*/

        database.child("Games").child(bundle.getString("game")!!).get().addOnSuccessListener{it ->
            it.child("players").children.forEach{p ->
                var button = Button(requireContext())
                button.text = p.key
                var params = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                params.gravity = Gravity.CENTER_HORIZONTAL
                params.setMargins(15,15,15,15)
                button.layoutParams = params
                button.setOnClickListener(){
                    var game = bundle.getString("game")
                    var bundle = Bundle()
                    bundle.putString("username", p.key)
                    bundle.putString("game", game)
                    Navigation.findNavController(view).navigate(R.id.gameSheetFragment, bundle)
                }
                masterLinearLayout.addView(button)
            }
        }

        return view
    }
}