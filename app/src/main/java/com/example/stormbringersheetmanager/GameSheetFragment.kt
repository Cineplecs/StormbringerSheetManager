package com.example.stormbringersheetmanager

import android.graphics.BitmapFactory
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.TypedValue
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.isGone
import androidx.navigation.Navigation
import com.example.stormbringersheetmanager.Utility.Character
import com.example.stormbringersheetmanager.Utility.Skills
import com.example.stormbringersheetmanager.Utility.Weapon
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.File


class GameSheetFragment : Fragment() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private lateinit var characterName: TextView
    private lateinit var playerName: TextView
    private lateinit var nationality: TextView
    private lateinit var classes: TextView
    private lateinit var age: TextView
    private lateinit var gender: TextView
    private lateinit var cult: TextView
    private lateinit var elan: TextView
    private lateinit var FOR: TextView
    private lateinit var COS: TextView
    private lateinit var TAG: TextView
    private lateinit var INT: TextView
    private lateinit var MAN: TextView
    private lateinit var DES: TextView
    private lateinit var FAS: TextView
    private lateinit var weight: TextView
    private lateinit var height: TextView
    private lateinit var characterDescription: TextView
    private lateinit var characterImage: ImageView
    private lateinit var armor: TextView
    private lateinit var armorProtection: TextView
    private lateinit var graveWounds: TextView
    private lateinit var gravePlus: Button
    private lateinit var graveMinus: Button
    private lateinit var HP: TextView
    private lateinit var hpPlus: Button
    private lateinit var hpMinus: Button
    private lateinit var equipmentEditText: EditText
    private lateinit var money: TextView
    private lateinit var handicap: TextView
    private lateinit var weaponsGrid: GridLayout
    private lateinit var agilityBonus: TextView
    private lateinit var climbValue: EditText
    private lateinit var jumpValue: EditText
    private lateinit var swimValue: EditText
    private lateinit var dodgeValue: EditText
    private lateinit var ridingValue: EditText
    private lateinit var fallValue: EditText
    private lateinit var communicationBonus: TextView
    private lateinit var reputationValue: EditText
    private lateinit var persuasionValue: EditText
    private lateinit var speechValue: EditText
    private lateinit var singingValue: EditText
    private lateinit var knowledgeBonus: TextView
    private lateinit var firstAidValue: EditText
    private lateinit var memoryValue: EditText
    private lateinit var navigationValue: EditText
    private lateinit var poisonValue: EditText
    private lateinit var treasureValue: EditText
    private lateinit var cartographyValue: EditText
    private lateinit var musicValue: EditText
    private lateinit var plantsValue: EditText
    private lateinit var lowMelniboleano: EditText
    private lateinit var commonLanguage: EditText
    private lateinit var lateMelniboleano: EditText
    private lateinit var highMelniboleano: EditText
    private lateinit var otherLanguages: EditText
    private lateinit var manipulationBonus: TextView
    private lateinit var jugglingValue: EditText
    private lateinit var magicValue: EditText
    private lateinit var trapsValue: EditText
    private lateinit var lockValue: EditText
    private lateinit var nodesValue: EditText
    private lateinit var perceptionBonus: TextView
    private lateinit var stabilityValue: EditText
    private lateinit var smellValue: EditText
    private lateinit var watchValue: EditText
    private lateinit var tracksValue: EditText
    private lateinit var listenValue: EditText
    private lateinit var searchingValue: EditText
    private lateinit var tasteValue: EditText
    private lateinit var stealthBonus: TextView
    private lateinit var ambushValue: EditText
    private lateinit var pickpocketValue: EditText
    private lateinit var sneakValue: EditText
    private lateinit var concealValue: EditText
    private lateinit var hideValue: EditText
    private lateinit var availableINT: TextView
    private lateinit var evocationTextView: TextView
    private lateinit var evocationEditText: EditText
    private lateinit var notes: EditText
    private lateinit var FORplus: Button
    private lateinit var FORminus: Button
    private lateinit var COSplus: Button
    private lateinit var COSminus: Button
    private lateinit var TAGplus: Button
    private lateinit var TAGminus: Button
    private lateinit var INTplus: Button
    private lateinit var INTminus: Button
    private lateinit var MANplus: Button
    private lateinit var MANminus: Button
    private lateinit var DESplus: Button
    private lateinit var DESminus: Button
    private lateinit var FASplus: Button
    private lateinit var FASminus: Button
    private lateinit var changeConfirmButton: Button
    private lateinit var storageReference: StorageReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_game_sheet, container, false)

        var bundle = requireArguments()

        changeConfirmButton = view.findViewById(R.id.changeConfirmButton)

        storageReference =
            FirebaseStorage.getInstance("gs://stormbringersheetmanager.appspot.com/").reference
        mAuth = Firebase.auth
        database =
            FirebaseDatabase.getInstance("https://stormbringersheetmanager-default-rtdb.europe-west1.firebasedatabase.app/").reference

        characterName = view.findViewById(R.id.characterName)
        playerName = view.findViewById(R.id.playerName)
        nationality = view.findViewById(R.id.nationality)
        classes = view.findViewById(R.id.classes)
        gender = view.findViewById(R.id.gender)
        age = view.findViewById(R.id.age)
        cult = view.findViewById(R.id.cultTextView)
        elan = view.findViewById(R.id.elan)
        FOR = view.findViewById(R.id.FOR)
        COS = view.findViewById(R.id.COS)
        TAG = view.findViewById(R.id.TAG)
        INT = view.findViewById(R.id.INT)
        MAN = view.findViewById(R.id.MAN)
        DES = view.findViewById(R.id.DES)
        FAS = view.findViewById(R.id.FAS)
        weight = view.findViewById(R.id.weight)
        height = view.findViewById(R.id.height)
        characterDescription = view.findViewById(R.id.characterDescription)
        characterImage = view.findViewById(R.id.characterImage)
        armor = view.findViewById(R.id.armor)
        armorProtection = view.findViewById(R.id.armorProtection)
        graveWounds = view.findViewById(R.id.graveWounds)
        gravePlus = view.findViewById(R.id.gravePlus)
        graveMinus = view.findViewById(R.id.graveMinus)
        HP = view.findViewById(R.id.HP)
        hpPlus = view.findViewById(R.id.hpPlus)
        hpMinus = view.findViewById(R.id.hpMinus)
        equipmentEditText = view.findViewById(R.id.equipmentEditText)
        money = view.findViewById(R.id.money)
        handicap = view.findViewById(R.id.handicap)
        weaponsGrid = view.findViewById(R.id.weaponsGrid)
        agilityBonus = view.findViewById(R.id.agilityBonus)
        climbValue = view.findViewById(R.id.climbValue)
        jumpValue = view.findViewById(R.id.jumpValue)
        swimValue = view.findViewById(R.id.swimValue)
        dodgeValue = view.findViewById(R.id.dodgeValue)
        ridingValue = view.findViewById(R.id.ridingValue)
        fallValue = view.findViewById(R.id.fallValue)
        communicationBonus = view.findViewById(R.id.communicationBonus)
        reputationValue = view.findViewById(R.id.reputationValue)
        persuasionValue = view.findViewById(R.id.persuasionValue)
        speechValue = view.findViewById(R.id.speechValue)
        singingValue = view.findViewById(R.id.singingValue)
        knowledgeBonus = view.findViewById(R.id.knowledgeBonus)
        firstAidValue = view.findViewById(R.id.firstAidValue)
        memoryValue = view.findViewById(R.id.memoryValue)
        navigationValue = view.findViewById(R.id.navigationValue)
        poisonValue = view.findViewById(R.id.poisonValue)
        treasureValue = view.findViewById(R.id.treasureValue)
        cartographyValue = view.findViewById(R.id.cartographyValue)
        musicValue = view.findViewById(R.id.musicValue)
        plantsValue = view.findViewById(R.id.plantsValue)
        lowMelniboleano = view.findViewById(R.id.lowMelniboleanoValue)
        highMelniboleano = view.findViewById(R.id.highMelniboleanoValue)
        lateMelniboleano = view.findViewById(R.id.lateMelniboleanoValue)
        commonLanguage = view.findViewById(R.id.commonValue)
        otherLanguages = view.findViewById(R.id.otherLanguagesValue)
        manipulationBonus = view.findViewById(R.id.manipulationBonus)
        jugglingValue = view.findViewById(R.id.jugglingValue)
        magicValue = view.findViewById(R.id.magicValue)
        trapsValue = view.findViewById(R.id.trapsValue)
        lockValue = view.findViewById(R.id.lockValue)
        nodesValue = view.findViewById(R.id.nodesValue)
        perceptionBonus = view.findViewById(R.id.perceptionBonus)
        stabilityValue = view.findViewById(R.id.stabilityValue)
        smellValue = view.findViewById(R.id.smellValue)
        watchValue = view.findViewById(R.id.watchValue)
        tracksValue = view.findViewById(R.id.tracksValue)
        listenValue = view.findViewById(R.id.listenValue)
        searchingValue = view.findViewById(R.id.searchingValue)
        tasteValue = view.findViewById(R.id.tasteValue)
        stealthBonus = view.findViewById(R.id.stealthBonus)
        ambushValue = view.findViewById(R.id.ambushValue)
        pickpocketValue = view.findViewById(R.id.pickpocketValue)
        sneakValue = view.findViewById(R.id.sneakValue)
        concealValue = view.findViewById(R.id.concealValue)
        hideValue = view.findViewById(R.id.hideValue)
        availableINT = view.findViewById(R.id.availableINT)
        evocationTextView = view.findViewById(R.id.evocationTextView)
        evocationEditText = view.findViewById(R.id.evocationEditText)
        notes = view.findViewById(R.id.notes)
        playerName.text = bundle.getString("username").toString()

        val localFile = File.createTempFile("tempImage", "jpg")
        storageReference.child(playerName.text.toString())
            .child(characterName.text.toString()).downloadUrl.continueWith() {
                val bitmap = BitmapFactory.decodeFile(localFile.absolutePath)
                characterImage.setImageBitmap(bitmap)
            }

        var rifPlayer: String = ""
        var forza = 0
        var const = 0
        var size = 0
        var int = 0
        var man = 0
        var dex = 0
        var fas = 0
        var hp = 0
        var grave = 0

        var key = database.child("Games").child(bundle.getString("game")!!).child("players").ref

        database.child("Games").get().addOnSuccessListener { game ->
            game.child(bundle.getString("game").toString())
                .child("players").children.forEach() { playerData ->
                    playerData.children.forEach() {
                        if (it.key.toString().trim() == bundle.getString("username")) {
                            var finalClasses: ArrayList<String> = ArrayList()
                            var weapons: ArrayList<Weapon> = ArrayList()
                            if (it.child("weapons").value != null) {
                                it.child("finalClass").children.forEach() { classData ->
                                    var classes =
                                        classData.value.toString()
                                    if (classes != null) {
                                        finalClasses.add(classes)
                                    }
                                }
                                it.child("weapons").children.forEach() { weaponData ->
                                    var weapon =
                                        weaponData.getValue(Weapon::class.java)
                                    if (weapon != null) {
                                        weapons.add(weapon)
                                    }
                                }
                                for (i in weapons.indices) {
                                    var name = TextView(requireContext())
                                    name.text = weapons[i].name
                                    name.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15f)
                                    var params = GridLayout.LayoutParams()
                                    params.columnSpec =
                                        GridLayout.spec(
                                            GridLayout.UNDEFINED,
                                            GridLayout.FILL,
                                            1f
                                        )
                                    params.width = 0
                                    name.layoutParams = params

                                    var attack = TextView(requireContext())
                                    attack.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15f)
                                    attack.setTextColor(resources.getColor(R.color.black))
                                    attack.gravity = Gravity.CENTER
                                    var paramsAttack = GridLayout.LayoutParams()
                                    paramsAttack.columnSpec =
                                        GridLayout.spec(
                                            GridLayout.UNDEFINED,
                                            GridLayout.FILL,
                                            1f
                                        )
                                    paramsAttack.width = 0
                                    attack.layoutParams = paramsAttack

                                    var damage = TextView(requireContext())
                                    damage.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15f)
                                    damage.setTextColor(resources.getColor(R.color.black))
                                    damage.gravity = Gravity.CENTER
                                    damage.text = weapons[i].damage
                                    var paramsDamage = GridLayout.LayoutParams()
                                    paramsDamage.columnSpec =
                                        GridLayout.spec(
                                            GridLayout.UNDEFINED,
                                            GridLayout.FILL,
                                            1f
                                        )
                                    damage.layoutParams = paramsDamage

                                    var block = TextView(requireContext())
                                    block.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15f)
                                    block.setTextColor(resources.getColor(R.color.black))
                                    block.gravity = Gravity.CENTER
                                    var paramsBlock = GridLayout.LayoutParams()
                                    paramsBlock.columnSpec =
                                        GridLayout.spec(
                                            GridLayout.UNDEFINED,
                                            GridLayout.FILL,
                                            1f
                                        )
                                    paramsBlock.width = 0
                                    block.layoutParams = paramsBlock

                                    for (k in finalClasses.indices) {
                                        when (finalClasses[k]) {
                                            "Guerriero" -> {
                                                if (i == 0) {
                                                    attack.text =
                                                        (bundle.getInt("attack") + 50).toString()
                                                    block.text =
                                                        (bundle.getInt("block") + 50).toString()
                                                } else if (i == 1) {
                                                    attack.text =
                                                        (bundle.getInt("attack") + 40).toString()
                                                    block.text =
                                                        (bundle.getInt("block") + 40).toString()
                                                } else if (i == 2) {
                                                    attack.text =
                                                        (bundle.getInt("attack") + 30).toString()
                                                    block.text =
                                                        (bundle.getInt("block") + 30).toString()
                                                } else {
                                                    attack.text =
                                                        bundle.getInt("attack").toString()
                                                    block.text =
                                                        (bundle.getInt("block")).toString()
                                                }
                                            }
                                            "Mercante" -> {
                                                if (i == 1) {
                                                    attack.text =
                                                        (bundle.getInt("attack") + 40).toString()
                                                    block.text =
                                                        (bundle.getInt("block") + 40).toString()
                                                } else {
                                                    attack.text =
                                                        bundle.getInt("attack").toString()
                                                    block.text =
                                                        (bundle.getInt("block")).toString()
                                                }
                                            }
                                            "Marinaio" -> {
                                                if (i == 0) {
                                                    attack.text =
                                                        (bundle.getInt("attack") + 40).toString()
                                                    block.text =
                                                        (bundle.getInt("block") + 40).toString()
                                                } else {
                                                    attack.text =
                                                        bundle.getInt("attack").toString()
                                                    block.text =
                                                        (bundle.getInt("block")).toString()
                                                }
                                            }
                                            "Cacciatore" -> {
                                                if (weapons[i].name != "Arco lungo") {
                                                    attack.text =
                                                        (bundle.getInt("attack") + 30).toString()
                                                    block.text =
                                                        (bundle.getInt("block") + 30).toString()
                                                } else {
                                                    attack.text =
                                                        (bundle.getInt("attack") + 25).toString()
                                                    block.text = "0"
                                                }
                                            }
                                            "Agricoltore" -> {
                                                if (weapons[i].name == "Clava" ||
                                                    weapons[i].name == "Bastone (2 mani)"
                                                ) {
                                                    attack.text =
                                                        (bundle.getInt("attack") + 30).toString()
                                                    block.text =
                                                        (bundle.getInt("block") + 30).toString()
                                                } else if (weapons[i].name == "Accetta") {
                                                    attack.text =
                                                        (bundle.getInt("attack") + 15).toString()
                                                    block.text =
                                                        (bundle.getInt("block") + 15).toString()
                                                }
                                            }
                                            "Ladro" -> {
                                                if (weapons[i].name == "Pugnale") {
                                                    attack.text =
                                                        (bundle.getInt("attack") + 45).toString()
                                                    block.text =
                                                        (bundle.getInt("block") + 45).toString()
                                                } else {
                                                    attack.text =
                                                        (bundle.getInt("attack") + 35).toString()
                                                    block.text =
                                                        (bundle.getInt("block") + 35).toString()
                                                }
                                            }
                                            "Mendicante" -> {
                                                attack.text = "10"
                                                block.text = "10"
                                            }
                                            else -> {
                                                attack.text =
                                                    (bundle.getInt("attack")).toString()
                                                block.text = (bundle.getInt("block")).toString()
                                            }
                                        }
                                    }

                                    weaponsGrid.addView(name)
                                    weaponsGrid.addView(attack)
                                    weaponsGrid.addView(damage)
                                    weaponsGrid.addView(block)
                                }
                            }
                        }
                    }
                }
        }

        //compilazione scheda personaggio modificabile
        var listener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                dataSnapshot.child("Games").child(bundle.getString("game").toString())
                    .child("players").children.forEach()
                    { playerData ->
                        playerData.children.forEach() { it ->
                            if (it.key.toString().trim() == bundle.getString("username")) {
                                rifPlayer = playerData.key.toString()
                                characterName.text = it.child("characterName").value.toString()
                                playerName.text = bundle.getString("username")
                                nationality.text = it.child("nationality").value.toString()
                                var finalClasses = ArrayList<String>()
                                it.child("finalClass").children.forEach() { classChild ->
                                    finalClasses.add(classChild.value.toString())
                                }
                                for (i in finalClasses.indices) {
                                    when (i) {
                                        0 -> {
                                            classes.text = finalClasses[i] + "\n"
                                        }
                                        finalClasses.size - 1 -> {
                                            classes.text = classes.text.toString() + finalClasses[i]
                                        }
                                        else -> {
                                            classes.text = finalClasses[i] + "\n"
                                        }
                                    }
                                }
                                gender.text =
                                    resources.getString(R.string.gender) + it.child("characterGender").value.toString()
                                age.text =
                                    resources.getString(R.string.age) + it.child("characterAge").value.toString()
                                if (it.child("cult").value.toString() == "Inserire un culto") {
                                    cult.text = "Nessun culto selezionato"
                                } else {
                                    cult.text = it.child("cult").value.toString()
                                }
                                elan.text = it.child("elan").value.toString()
                                var stats = ArrayList<Int>()
                                stats = it.child("stats").value as ArrayList<Int>
                                FOR.text = resources.getString(R.string.FOR) + stats[0]
                                COS.text = resources.getString(R.string.COS) + stats[1]
                                TAG.text = resources.getString(R.string.TAG) + stats[2]
                                INT.text = resources.getString(R.string.INT) + stats[3]
                                MAN.text = resources.getString(R.string.MAN) + stats[4]
                                DES.text = resources.getString(R.string.DES) + stats[5]
                                FAS.text = resources.getString(R.string.FAS) + stats[6]
                                grave = it.child("graveWounds").value.toString().toInt()
                                hp = it.child("hp").value.toString().toInt()
                                forza = it.child("stats").child("0").value.toString().toInt()
                                const = it.child("stats").child("1").value.toString().toInt()
                                size = it.child("stats").child("2").value.toString().toInt()
                                int = it.child("stats").child("3").value.toString().toInt()
                                man = it.child("stats").child("4").value.toString().toInt()
                                dex = it.child("stats").child("5").value.toString().toInt()
                                fas = it.child("stats").child("6").value.toString().toInt()
                                weight.text =
                                    resources.getString(R.string.weight) + it.child("weight").value.toString()
                                height.text =
                                    resources.getString(R.string.height) + it.child("height").value.toString()
                                if (it.child("description").value.toString() == "Inserire la descrizione del personaggio") {
                                    characterDescription.text = "----\n----\n----"
                                } else {
                                    characterDescription.text =
                                        it.child("description").value.toString()
                                }
                                armor.text = it.child("armor").value.toString()
                                armorProtection.text = it.child("armorProtection").value.toString()
                                graveWounds.text = it.child("graveWounds").value.toString()
                                HP.text = it.child("hp").value.toString()
                                equipmentEditText.setText(it.child("equipment").value.toString())
                                equipmentEditText.addTextChangedListener(object : TextWatcher {
                                    override fun beforeTextChanged(
                                        p0: CharSequence?,
                                        p1: Int,
                                        p2: Int,
                                        p3: Int
                                    ) {

                                    }

                                    override fun onTextChanged(
                                        p0: CharSequence?,
                                        p1: Int,
                                        p2: Int,
                                        p3: Int
                                    ) {

                                    }

                                    override fun afterTextChanged(p0: Editable?) {
                                        key.child(rifPlayer).child(bundle.getString("username")!!)
                                            .child("equipment").setValue(
                                                equipmentEditText.text.toString()
                                            )
                                    }

                                })
                                money.text = it.child("money").value.toString()
                                handicap.text = it.child("handicap").value.toString()

                                agilityBonus = view.findViewById(R.id.agilityBonus)
                                agilityBonus.text = it.child("agility").value.toString()
                                climbValue = view.findViewById(R.id.climbValue)
                                jumpValue = view.findViewById(R.id.jumpValue)
                                swimValue = view.findViewById(R.id.swimValue)
                                dodgeValue = view.findViewById(R.id.dodgeValue)
                                ridingValue = view.findViewById(R.id.ridingValue)
                                fallValue = view.findViewById(R.id.fallValue)
                                var agilitySkills: ArrayList<Skills> = ArrayList()
                                if (it.child("agilitySkills").value != null) {
                                    it.child("agilitySkills").children.forEach() { child ->
                                        var agilitySkill =
                                            child.getValue(Skills::class.java)!!
                                        agilitySkills.add(agilitySkill)
                                    }
                                }
                                for (i in agilitySkills.indices) {
                                    when (agilitySkills[i].name) {
                                        "Saltare" -> {
                                            jumpValue.setText(agilitySkills[i].iniziale.toString())
                                        }
                                        "Arrampicarsi" -> {
                                            climbValue.setText(agilitySkills[i].iniziale.toString())
                                        }
                                        "Nuotare" -> {
                                            swimValue.setText(agilitySkills[i].iniziale.toString())
                                        }
                                        "Schivare" -> {
                                            dodgeValue.setText(agilitySkills[i].iniziale.toString())
                                        }
                                        "Cavalcare" -> {
                                            ridingValue.setText(agilitySkills[i].iniziale.toString())
                                        }
                                        "Cadere" -> {
                                            fallValue.setText(agilitySkills[i].iniziale.toString())
                                        }
                                    }
                                }


                                communicationBonus = view.findViewById(R.id.communicationBonus)
                                communicationBonus.text = it.child("communication").value.toString()
                                reputationValue = view.findViewById(R.id.reputationValue)
                                persuasionValue = view.findViewById(R.id.persuasionValue)
                                speechValue = view.findViewById(R.id.speechValue)
                                singingValue = view.findViewById(R.id.singingValue)
                                var communicationSkills: ArrayList<Skills> = ArrayList()
                                if (it.child("communicationSkills").value != null) {
                                    for (i in 0 until it.child("communicationSkills").childrenCount) {
                                        var communicationSkill =
                                            it.child("communicationSkills").child(i.toString())
                                                .getValue(Skills::class.java)!!
                                        communicationSkills.add(communicationSkill)
                                    }
                                }
                                for (i in communicationSkills.indices) {
                                    when (communicationSkills[i].name) {
                                        "Reputazione" -> {
                                            reputationValue.setText(communicationSkills[i].iniziale.toString())
                                        }
                                        "Persuasione" -> {
                                            persuasionValue.setText(communicationSkills[i].iniziale.toString())
                                        }
                                        "Oratoria" -> {
                                            speechValue.setText(communicationSkills[i].iniziale.toString())
                                        }
                                        "Cantare" -> {
                                            singingValue.setText(communicationSkills[i].iniziale.toString())
                                        }
                                    }
                                }


                                knowledgeBonus = view.findViewById(R.id.knowledgeBonus)
                                knowledgeBonus.text = it.child("knowledge").value.toString()
                                firstAidValue = view.findViewById(R.id.firstAidValue)
                                memoryValue = view.findViewById(R.id.memoryValue)
                                navigationValue = view.findViewById(R.id.navigationValue)
                                poisonValue = view.findViewById(R.id.poisonValue)
                                treasureValue = view.findViewById(R.id.treasureValue)
                                cartographyValue = view.findViewById(R.id.cartographyValue)
                                musicValue = view.findViewById(R.id.musicValue)
                                plantsValue = view.findViewById(R.id.plantsValue)
                                lowMelniboleano = view.findViewById(R.id.lowMelniboleanoValue)
                                highMelniboleano = view.findViewById(R.id.highMelniboleanoValue)
                                lateMelniboleano = view.findViewById(R.id.lateMelniboleanoValue)
                                commonLanguage = view.findViewById(R.id.commonValue)
                                otherLanguages = view.findViewById(R.id.otherLanguagesValue)
                                var knowledgeSkills: ArrayList<Skills> = ArrayList()
                                if (it.child("knowledgeSkills").value != null) {
                                    it.child("knowledgeSkills").children.forEach() { child ->
                                        var knowledgeSkill =
                                            child.getValue(Skills::class.java)!!
                                        knowledgeSkills.add(knowledgeSkill)
                                    }
                                }
                                for (i in knowledgeSkills.indices) {
                                    when (knowledgeSkills[i].name) {
                                        "Pronto Soccorso" -> {
                                            firstAidValue.setText(knowledgeSkills[i].iniziale.toString())
                                        }
                                        "Memoria" -> {
                                            memoryValue.setText(knowledgeSkills[i].iniziale.toString())
                                        }
                                        "Navigazione" -> {
                                            navigationValue.setText(knowledgeSkills[i].iniziale.toString())
                                        }
                                        "Conoscenza dei Veleni" -> {
                                            poisonValue.setText(knowledgeSkills[i].iniziale.toString())
                                        }
                                        "Valutare Tesori" -> {
                                            treasureValue.setText(knowledgeSkills[i].iniziale.toString())
                                        }
                                        "Cartografia" -> {
                                            cartographyValue.setText(knowledgeSkills[i].iniziale.toString())
                                        }
                                        "Conoscenza della Musica" -> {
                                            musicValue.setText(knowledgeSkills[i].iniziale.toString())
                                        }
                                        "Conoscenza delle Piante" -> {
                                            plantsValue.setText(knowledgeSkills[i].iniziale.toString())
                                        }
                                        "Leggere/Scrivere Lingua Comune" -> {
                                            commonLanguage.setText(knowledgeSkills[i].iniziale.toString())
                                        }
                                        "Leggere/Scrivere Basso Melniboleano" -> {
                                            lowMelniboleano.setText(knowledgeSkills[i].iniziale.toString())
                                        }
                                        "Leggere/Scrivere Tardo Melniboleano" -> {
                                            lateMelniboleano.setText(knowledgeSkills[i].iniziale.toString())
                                        }
                                        "Leggere/Scrivere Alto Meniboleano" -> {
                                            highMelniboleano.setText(knowledgeSkills[i].iniziale.toString())
                                        }
                                        "Leggere/Scrivere/Parlare Altre Lingue" -> {
                                            otherLanguages.setText(knowledgeSkills[i].iniziale.toString())
                                        }
                                    }
                                }

                                manipulationBonus = view.findViewById(R.id.manipulationBonus)
                                manipulationBonus.text = it.child("manipulation").value.toString()
                                jugglingValue = view.findViewById(R.id.jugglingValue)
                                magicValue = view.findViewById(R.id.magicValue)
                                trapsValue = view.findViewById(R.id.trapsValue)
                                lockValue = view.findViewById(R.id.lockValue)
                                nodesValue = view.findViewById(R.id.nodesValue)
                                var manipulationSkills: ArrayList<Skills> = ArrayList()
                                if (it.child("manipulationSkills").value != null) {
                                    it.child("manipulationSkills").children.forEach() { child ->
                                        var manipulationSkill =
                                            child.getValue(Skills::class.java)!!
                                        manipulationSkills.add(manipulationSkill)
                                    }
                                }
                                for (i in manipulationSkills.indices) {
                                    when (manipulationSkills[i].name) {
                                        "Giocoleria" -> {
                                            jugglingValue.setText(manipulationSkills[i].iniziale.toString())
                                        }
                                        "Prestidigitazione" -> {
                                            magicValue.setText(manipulationSkills[i].iniziale.toString())
                                        }
                                        "Attivare/Disattivare trappole" -> {
                                            trapsValue.setText(manipulationSkills[i].iniziale.toString())
                                        }
                                        "Scassinare" -> {
                                            lockValue.setText(manipulationSkills[i].iniziale.toString())
                                        }
                                        "Fare/Disfare Nodi" -> {
                                            nodesValue.setText(manipulationSkills[i].iniziale.toString())
                                        }
                                    }
                                }

                                perceptionBonus = view.findViewById(R.id.perceptionBonus)
                                perceptionBonus.text = it.child("perception").value.toString()
                                stabilityValue = view.findViewById(R.id.stabilityValue)
                                smellValue = view.findViewById(R.id.smellValue)
                                watchValue = view.findViewById(R.id.watchValue)
                                tracksValue = view.findViewById(R.id.tracksValue)
                                listenValue = view.findViewById(R.id.listenValue)
                                searchingValue = view.findViewById(R.id.searchingValue)
                                tasteValue = view.findViewById(R.id.tasteValue)
                                var perceptionSkills: ArrayList<Skills> = ArrayList()
                                if (it.child("perceptionSkills").value != null) {
                                    it.child("perceptionSkills").children.forEach() { child ->
                                        var perceptionSkill =
                                            child.getValue(Skills::class.java)!!
                                        perceptionSkills.add(perceptionSkill)
                                    }
                                }
                                for (i in perceptionSkills.indices) {
                                    when (perceptionSkills[i].name) {
                                        "Equilibrio" -> {
                                            stabilityValue.setText(perceptionSkills[i].iniziale.toString())
                                        }
                                        "Annusare" -> {
                                            smellValue.setText(perceptionSkills[i].iniziale.toString())
                                        }
                                        "Osservare" -> {
                                            watchValue.setText(perceptionSkills[i].iniziale.toString())
                                        }
                                        "Seguire Tracce" -> {
                                            tracksValue.setText(perceptionSkills[i].iniziale.toString())
                                        }
                                        "Ascoltare" -> {
                                            listenValue.setText(perceptionSkills[i].iniziale.toString())
                                        }
                                        "Cercare" -> {
                                            searchingValue.setText(perceptionSkills[i].iniziale.toString())
                                        }
                                        "Gustare" -> {
                                            tasteValue.setText(perceptionSkills[i].iniziale.toString())
                                        }
                                    }
                                }

                                stealthBonus = view.findViewById(R.id.stealthBonus)
                                stealthBonus.text = it.child("stealth").value.toString()
                                ambushValue = view.findViewById(R.id.ambushValue)
                                pickpocketValue = view.findViewById(R.id.pickpocketValue)
                                sneakValue = view.findViewById(R.id.sneakValue)
                                concealValue = view.findViewById(R.id.concealValue)
                                hideValue = view.findViewById(R.id.hideValue)
                                var stealthSkills: ArrayList<Skills> = ArrayList()
                                if (it.child("stealthSkills").value != null) {
                                    it.child("stealthSkills").children.forEach() { child ->
                                        var stealthSkill =
                                            child.getValue(Skills::class.java)!!
                                        stealthSkills.add(stealthSkill)
                                    }
                                }
                                for (i in stealthSkills.indices) {
                                    when (stealthSkills[i].name) {
                                        "Agguato" -> {
                                            ambushValue.setText(stealthSkills[i].iniziale.toString())
                                        }
                                        "Borseggiare" -> {
                                            pickpocketValue.setText(stealthSkills[i].iniziale.toString())
                                        }
                                        "Intrufolarsi" -> {
                                            sneakValue.setText(stealthSkills[i].iniziale.toString())
                                        }
                                        "Celare" -> {
                                            concealValue.setText(stealthSkills[i].iniziale.toString())
                                        }
                                        "Nascondersi" -> {
                                            hideValue.setText(stealthSkills[i].iniziale.toString())
                                        }
                                    }
                                }
                                notes.setText(it.child("notes").value.toString())
                                availableINT = view.findViewById(R.id.availableINT)
                                availableINT.text =
                                    resources.getString(R.string.availableINT) + it.child("availableINT").value.toString()
                                evocationTextView = view.findViewById(R.id.evocationTextView)
                                if (it.child("availablEvocations").value.toString() == ""
                                ) {
                                    evocationTextView.text =
                                        resources.getString(R.string.evocation)
                                } else {
                                    evocationTextView.text =
                                        it.child("availableEvocations").value.toString()
                                }
                                if (it.child("evocation").value == true) {
                                    evocationEditText.isGone = false
                                    evocationTextView.isGone = true
                                    evocationEditText.setText(it.child("availableEvocations").value.toString())
                                } else {
                                    evocationTextView.isGone = false
                                    evocationTextView.isGone = true
                                }

                                FORplus = view.findViewById(R.id.FORplus)
                                FORplus.setOnClickListener()
                                {
                                    forza += 1
                                    key.child(rifPlayer).child(bundle.getString("username")!!)
                                        .child("stats").child("0").setValue(forza)
                                }
                                FORminus = view.findViewById(R.id.FORminus)
                                FORminus.setOnClickListener()
                                {
                                    forza -= 1
                                    key.child(rifPlayer).child(bundle.getString("username")!!)
                                        .child("stats").child("0").setValue(forza)
                                }
                                COSplus = view.findViewById(R.id.COSplus)
                                COSplus.setOnClickListener()
                                {
                                    const += 1
                                    key.child(rifPlayer).child(bundle.getString("username")!!)
                                        .child("stats").child("1").setValue(const)
                                }
                                COSminus = view.findViewById(R.id.COSminus)
                                COSminus.setOnClickListener()
                                {
                                    const -= 1
                                    key.child(rifPlayer).child(bundle.getString("username")!!)
                                        .child("stats").child("1").setValue(const)
                                }
                                TAGplus = view.findViewById(R.id.TAGplus)
                                TAGplus.setOnClickListener()
                                {
                                    size += 1
                                    key.child(rifPlayer).child(bundle.getString("username")!!)
                                        .child("stats").child("2").setValue(size)
                                }
                                TAGminus = view.findViewById(R.id.TAGminus)
                                TAGminus.setOnClickListener()
                                {
                                    size -= 1
                                    key.child(rifPlayer).child(bundle.getString("username")!!)
                                        .child("stats").child("2").setValue(size)
                                }
                                INTplus = view.findViewById(R.id.INTplus)
                                INTplus.setOnClickListener()
                                {
                                    int += 1
                                    key.child(rifPlayer).child(bundle.getString("username")!!)
                                        .child("stats").child("3").setValue(int)
                                }
                                INTminus = view.findViewById(R.id.INTminus)
                                INTminus.setOnClickListener()
                                {
                                    int -= 1
                                    key.child(rifPlayer).child(bundle.getString("username")!!)
                                        .child("stats").child("3").setValue(int)
                                }
                                MANplus = view.findViewById(R.id.MANplus)
                                MANplus.setOnClickListener()
                                {
                                    man += 1
                                    key.child(rifPlayer).child(bundle.getString("username")!!)
                                        .child("stats").child("4").setValue(man)
                                }
                                MANminus = view.findViewById(R.id.MANminus)
                                MANminus.setOnClickListener()
                                {
                                    man -= 1
                                    key.child(rifPlayer).child(bundle.getString("username")!!)
                                        .child("stats").child("4").setValue(man)
                                }
                                DESplus = view.findViewById(R.id.DESplus)
                                DESplus.setOnClickListener()
                                {
                                    dex += 1
                                    key.child(rifPlayer).child(bundle.getString("username")!!)
                                        .child("stats").child("5").setValue(dex)
                                }
                                DESminus = view.findViewById(R.id.DESminus)
                                DESminus.setOnClickListener()
                                {
                                    dex -= 1
                                    key.child(rifPlayer).child(bundle.getString("username")!!)
                                        .child("stats").child("5").setValue(dex)
                                }
                                FASplus = view.findViewById(R.id.FASplus)
                                FASplus.setOnClickListener()
                                {
                                    fas += 1
                                    key.child(rifPlayer).child(bundle.getString("username")!!)
                                        .child("stats").child("6").setValue(fas)
                                }
                                FASminus = view.findViewById(R.id.FASminus)
                                FASminus.setOnClickListener()
                                {
                                    fas -= 1
                                    key.child(rifPlayer).child(bundle.getString("username")!!)
                                        .child("stats").child("6").setValue(fas)
                                }
                                hpPlus.setOnClickListener {
                                    hp += 1
                                    key.child(rifPlayer).child(bundle.getString("username")!!)
                                        .child("hp").setValue(hp)
                                }
                                hpMinus.setOnClickListener {
                                    hp -= 1
                                    key.child(rifPlayer).child(bundle.getString("username")!!)
                                        .child("hp").setValue(hp)
                                }
                                gravePlus.setOnClickListener {
                                    grave += 1
                                    key.child(rifPlayer).child(bundle.getString("username")!!)
                                        .child("graveWounds").setValue(grave)
                                }
                                graveMinus.setOnClickListener {
                                    grave -= 1
                                    key.child(rifPlayer).child(bundle.getString("username")!!)
                                        .child("graveWounds").setValue(grave)
                                }
                                var finalKey =
                                    database.child("Games").child(bundle.getString("game")!!)
                                        .child("players")
                                        .child(rifPlayer).child(bundle.getString("username")!!).ref

                                changeConfirmButton.setOnClickListener() {
                                    finalKey.get().addOnSuccessListener { final ->
                                        if (hideValue.text.toString()
                                                .matches("-?\\d+(\\.\\d+)?".toRegex())
                                        ) {
                                            final.children.forEach() { elements ->
                                                when (elements.key.toString()) {
                                                    "stealthSkills" -> {
                                                        elements.children.forEach() {
                                                            when (it.child("name").value.toString()) {
                                                                "Nascondersi" -> {
                                                                    finalKey.child("stealthSkills")
                                                                        .child(it.key.toString())
                                                                        .child("iniziale").setValue(
                                                                            hideValue.text.toString()
                                                                                .toInt()
                                                                        )
                                                                }
                                                                "Intrufolarsi" -> {
                                                                    finalKey.child("stealhSkills")
                                                                        .child(it.key.toString())
                                                                        .child("iniziale")
                                                                        .setValue(
                                                                            sneakValue.text.toString()
                                                                                .toInt()
                                                                        )
                                                                }
                                                                "Agguato" -> {
                                                                    finalKey.child("stealhSkills")
                                                                        .child(it.key.toString())
                                                                        .child("iniziale")
                                                                        .setValue(
                                                                            ambushValue.text.toString()
                                                                                .toInt()
                                                                        )
                                                                }
                                                                "Celare" -> {
                                                                    finalKey.child("stealhSkills")
                                                                        .child(it.key.toString())
                                                                        .child("iniziale")
                                                                        .setValue(
                                                                            concealValue.text.toString()
                                                                                .toInt()
                                                                        )
                                                                }
                                                                "Borseggiare" -> {
                                                                    finalKey.child("stealhSkills")
                                                                        .child(it.key.toString())
                                                                        .child("iniziale")
                                                                        .setValue(
                                                                            pickpocketValue.text.toString()
                                                                                .toInt()
                                                                        )
                                                                }
                                                            }
                                                        }
                                                    }
                                                    "perceptionSkills" -> {
                                                        elements.children.forEach() {
                                                            when (it.child("name").value.toString()) {
                                                                "Gustare" -> {
                                                                    finalKey.child("perceptionSkills")
                                                                        .child(it.key.toString())
                                                                        .child("iniziale")
                                                                        .setValue(
                                                                            tasteValue.text.toString()
                                                                                .toInt()
                                                                        )
                                                                }
                                                                "Cercare" -> {
                                                                    finalKey.child("perceptionSkills")
                                                                        .child(it.key.toString())
                                                                        .child("iniziale")
                                                                        .setValue(
                                                                            searchingValue.text.toString()
                                                                                .toInt()
                                                                        )
                                                                }
                                                                "Ascoltare" -> {
                                                                    finalKey.child("perceptionSkills")
                                                                        .child(it.key.toString())
                                                                        .child("iniziale")
                                                                        .setValue(
                                                                            listenValue.text.toString()
                                                                                .toInt()
                                                                        )
                                                                }
                                                                "Seguire Tracce" -> {
                                                                    finalKey.child("perceptionSkills")
                                                                        .child(it.key.toString())
                                                                        .child("iniziale")
                                                                        .setValue(
                                                                            tracksValue.text.toString()
                                                                                .toInt()
                                                                        )
                                                                }
                                                                "Osservare" -> {
                                                                    finalKey.child("perceptionSkills")
                                                                        .child(it.key.toString())
                                                                        .child("iniziale")
                                                                        .setValue(
                                                                            watchValue.text.toString()
                                                                                .toInt()
                                                                        )
                                                                }
                                                                "Annusare" -> {
                                                                    finalKey.child("perceptionSkills")
                                                                        .child(it.key.toString())
                                                                        .child("iniziale")
                                                                        .setValue(
                                                                            smellValue.text.toString()
                                                                                .toInt()
                                                                        )
                                                                }
                                                                "Equilibrio" -> {
                                                                    finalKey.child("perceptionSkills")
                                                                        .child(it.key.toString())
                                                                        .child("iniziale")
                                                                        .setValue(
                                                                            stabilityValue.text.toString()
                                                                                .toInt()
                                                                        )
                                                                }
                                                            }
                                                        }
                                                    }
                                                    "manipulationSkills" -> {
                                                        elements.children.forEach() {
                                                            when (it.child("name").value.toString()) {
                                                                "Fare/Disfare Nodi" -> {
                                                                    finalKey.child("manipulationSkills")
                                                                        .child(it.key.toString())
                                                                        .child("iniziale")
                                                                        .setValue(
                                                                            nodesValue.text.toString()
                                                                                .toInt()
                                                                        )
                                                                }
                                                                "Scassinare" -> {
                                                                    finalKey.child("manipulationSkills")
                                                                        .child(it.key.toString())
                                                                        .child("iniziale")
                                                                        .setValue(
                                                                            lockValue.text.toString()
                                                                                .toInt()
                                                                        )
                                                                }
                                                                "Attivare/Disattivare Trappole" -> {
                                                                    finalKey.child("manipulationSkills")
                                                                        .child(it.key.toString())
                                                                        .child("iniziale")
                                                                        .setValue(
                                                                            trapsValue.text.toString()
                                                                                .toInt()
                                                                        )
                                                                }
                                                                "Prestidigitazione" -> {
                                                                    finalKey.child("manipulationSkills")
                                                                        .child(it.key.toString())
                                                                        .child("iniziale")
                                                                        .setValue(
                                                                            magicValue.text.toString()
                                                                                .toInt()
                                                                        )
                                                                }
                                                                "Giocoleria" -> {
                                                                    finalKey.child("manipulationSkills")
                                                                        .child(it.key.toString())
                                                                        .child("iniziale")
                                                                        .setValue(
                                                                            jugglingValue.text.toString()
                                                                                .toInt()
                                                                        )
                                                                }
                                                            }
                                                        }
                                                    }
                                                    "knowledgeSkills" -> {
                                                        elements.children.forEach() {
                                                            when (it.child("name").value.toString()) {
                                                                "Pronto Soccorso" -> {
                                                                    finalKey.child("knowledgeSkills")
                                                                        .child(it.key.toString())
                                                                        .child("iniziale")
                                                                        .setValue(
                                                                            firstAidValue.text.toString()
                                                                                .toInt()
                                                                        )
                                                                }
                                                                "Memoria" -> {
                                                                    finalKey.child("knowledgeSkills")
                                                                        .child(it.key.toString())
                                                                        .child("iniziale")
                                                                        .setValue(
                                                                            memoryValue.text.toString()
                                                                                .toInt()
                                                                        )
                                                                }
                                                                "Navigazione" -> {
                                                                    finalKey.child("knowledgeSkills").child(it.key.toString())
                                                                        .child("iniziale")
                                                                        .setValue(navigationValue.text.toString().toInt())
                                                                }
                                                                "Conoscenza dei Veleni" -> {
                                                                    finalKey.child("knowledgeSkills").child(it.key.toString())
                                                                        .child("iniziale")
                                                                        .setValue(poisonValue.text.toString().toInt())
                                                                }
                                                                "Valutare Tesori" -> {
                                                                    finalKey.child("knowledgeSkills").child(it.key.toString())
                                                                        .child("iniziale")
                                                                        .setValue(treasureValue.text.toString().toInt())
                                                                }
                                                                "Cartografia" -> {
                                                                    finalKey.child("knowledgeSkills").child(it.key.toString())
                                                                        .child("iniziale")
                                                                        .setValue(cartographyValue.text.toString().toInt())
                                                                }
                                                                "Conoscenza della Musica" -> {
                                                                    finalKey.child("knowledgeSkills").child(it.key.toString())
                                                                        .child("iniziale")
                                                                        .setValue(musicValue.text.toString().toInt())
                                                                }
                                                                "Conoscenza delle Piante" -> {
                                                                    finalKey.child("knowledgeSkills").child(it.key.toString())
                                                                        .child("iniziale")
                                                                        .setValue(plantsValue.text.toString().toInt())
                                                                }
                                                                "Leggere/Scrivere Basso Melniboleano" -> {
                                                                    finalKey.child("knowledgeSkills").child(it.key.toString())
                                                                        .child("iniziale")
                                                                        .setValue(lowMelniboleano.text.toString().toInt())
                                                                }
                                                                "Leggere/Scrivere Alto Melniboleano" -> {
                                                                    finalKey.child("knowledgeSkills").child(it.key.toString())
                                                                        .child("iniziale")
                                                                        .setValue(highMelniboleano.text.toString().toInt())
                                                                }
                                                                "Leggere/Scrivere Tardo Melniboleano" -> {
                                                                    finalKey.child("knowledgeSkills").child(it.key.toString())
                                                                        .child("iniziale")
                                                                        .setValue(lateMelniboleano.text.toString().toInt())
                                                                }
                                                                "Leggere/Scrivere Lingua Comune" -> {
                                                                    finalKey.child("knowledgeSkills").child(it.key.toString())
                                                                        .child("iniziale")
                                                                        .setValue(commonLanguage.text.toString().toInt())
                                                                }
                                                                "Leggere/Scrivere/Parlare Altre Lingue" -> {
                                                                    finalKey.child("knowledgeSkills").child(it.key.toString())
                                                                        .child("iniziale")
                                                                        .setValue(otherLanguages.text.toString().toInt())
                                                                }
                                                            }
                                                        }
                                                    }
                                                    "communicationSkills" -> {
                                                        elements.children.forEach() {
                                                            when(it.child("name").value.toString()){
                                                                "Persuasione" -> {
                                                                    finalKey.child("communicationSkills").child(it.key.toString())
                                                                        .child("iniziale")
                                                                        .setValue(persuasionValue.text.toString().toInt())
                                                                }
                                                                "Oratoria" -> {
                                                                    finalKey.child("communicationSkills").child(it.key.toString())
                                                                        .child("iniziale")
                                                                        .setValue(speechValue.text.toString().toInt())
                                                                }
                                                                "Cantare" -> {
                                                                    finalKey.child("communicationSkills").child(it.key.toString())
                                                                        .child("iniziale")
                                                                        .setValue(singingValue.text.toString().toInt())
                                                                }
                                                                "Reputazione" -> {
                                                                    finalKey.child("communicationSkills").child(it.key.toString())
                                                                        .child("iniziale")
                                                                        .setValue(reputationValue.text.toString().toInt())
                                                                }
                                                            }
                                                        }
                                                    }
                                                    "agilitySkills" -> {
                                                        elements.children.forEach() {
                                                            when(it.child("name").value.toString()){
                                                                "Cadere" -> {
                                                                    finalKey.child("agilitySkills").child(it.key.toString())
                                                                        .child("iniziale")
                                                                        .setValue(fallValue.text.toString().toInt())
                                                                }
                                                                "Cavalcare" -> {
                                                                    finalKey.child("agilitySkills").child(it.key.toString())
                                                                        .child("iniziale")
                                                                        .setValue(ridingValue.text.toString().toInt())
                                                                }
                                                                "Schivare" -> {
                                                                    finalKey.child("agilitySkills").child(it.key.toString())
                                                                        .child("iniziale")
                                                                        .setValue(dodgeValue.text.toString().toInt())
                                                                }
                                                                "Nuotare" -> {
                                                                    finalKey.child("agilitySkills").child(it.key.toString())
                                                                        .child("iniziale")
                                                                        .setValue(swimValue.text.toString().toInt())
                                                                }
                                                                "Arrampicarsi" -> {
                                                                    finalKey.child("agilitySkills").child(it.key.toString())
                                                                        .child("iniziale")
                                                                        .setValue(climbValue.text.toString().toInt())
                                                                }
                                                                "Saltare" -> {
                                                                    finalKey.child("agilitySkills").child(it.key.toString())
                                                                        .child("iniziale")
                                                                        .setValue(jumpValue.text.toString().toInt())
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    finalKey
                                        .child("notes").setValue(notes.text.toString())
                                }
                            }
                        }
                    }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        }

        database.addValueEventListener(listener)

        return view
    }

}

//per controllare se la stringa contiene solo numeri: .matches("-?\\d+(\\.\\d+)?".toRegex())