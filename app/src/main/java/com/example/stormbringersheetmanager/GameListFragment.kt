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
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class GameListFragment : Fragment() {

    private lateinit var gameListLinearLayout: LinearLayout
    private lateinit var mAuth : FirebaseAuth
    private lateinit var database : DatabaseReference
    private lateinit var username: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_game_list, container, false)

        gameListLinearLayout = view.findViewById(R.id.gameListLinearLayout)
        mAuth = Firebase.auth
        database = FirebaseDatabase.getInstance("https://stormbringersheetmanager-default-rtdb.europe-west1.firebasedatabase.app/").reference

        if(mAuth.currentUser != null){
            database.child("Users").child(mAuth.uid!!).get().addOnSuccessListener { it ->
                username = it.child("username").value.toString()
            }
        }

        database.child("Games").get().addOnSuccessListener { it ->
            it.children.forEach(){ childData ->
                println(childData.value.toString())

                var playerName = ""
                var master = childData.child("gameMaster").value.toString()
                println("MASTER: $master")
                println("USERNAME: $username")
                childData.child("players").children.forEach(){child ->
                    playerName = child.key.toString()
                    println("PLAYER NAME: $playerName")

                }

                if(username.equals(playerName) || username.equals(master)) {
                    var button = Button(requireContext())
                    button.text = childData.key.toString()
                    var params = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    params.gravity = Gravity.CENTER
                    button.layoutParams = params
                    button.setOnClickListener {
                        database.child("Users").child(mAuth.uid!!).get()
                            .addOnSuccessListener { userData ->
                                var username = userData.child("username").value.toString()
                                var bundle = Bundle()
                                bundle.putString("game", childData.key)
                                bundle.putString("username", username)
                                if (childData.child("gameMaster").value.toString()
                                        .trim() == username.trim()
                                ) {
                                    println("Entro nella schermata master")
                                    Navigation.findNavController(view)
                                        .navigate(R.id.masterSheetCollectionFragment, bundle)
                                } else {
                                    println("Entro nella schermata player")
                                    Navigation.findNavController(view)
                                        .navigate(R.id.gameSheetFragment, bundle)
                                }
                            }
                    }
                    gameListLinearLayout.addView(button)
                }
            }
        }

        return view
    }
}