package com.example.stormbringersheetmanager

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.stormbringersheetmanager.Utility.Skills
import com.example.stormbringersheetmanager.Utility.Weapon
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.File


class SheetViewFragment : Fragment() {

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
    private lateinit var HP: TextView
    private lateinit var equipmentEditText: TextView
    private lateinit var money: TextView
    private lateinit var handicap: TextView
    private lateinit var weaponsGrid: GridLayout
    private lateinit var agilityBonus: TextView
    private lateinit var climbValue: TextView
    private lateinit var jumpValue: TextView
    private lateinit var swimValue: TextView
    private lateinit var dodgeValue: TextView
    private lateinit var ridingValue: TextView
    private lateinit var fallValue: TextView
    private lateinit var communicationBonus: TextView
    private lateinit var reputationValue: TextView
    private lateinit var persuasionValue: TextView
    private lateinit var speechValue: TextView
    private lateinit var singingValue: TextView
    private lateinit var knowledgeBonus: TextView
    private lateinit var firstAidValue: TextView
    private lateinit var memoryValue: TextView
    private lateinit var navigationValue: TextView
    private lateinit var poisonValue: TextView
    private lateinit var treasureValue: TextView
    private lateinit var cartographyValue: TextView
    private lateinit var musicValue: TextView
    private lateinit var plantsValue: TextView
    private lateinit var lowMelniboleano: TextView
    private lateinit var commonLanguage: TextView
    private lateinit var lateMelniboleano: TextView
    private lateinit var highMelniboleano: TextView
    private lateinit var otherLanguages: TextView
    private lateinit var manipulationBonus: TextView
    private lateinit var jugglingValue: TextView
    private lateinit var magicValue: TextView
    private lateinit var trapsValue: TextView
    private lateinit var lockValue: TextView
    private lateinit var nodesValue: TextView
    private lateinit var perceptionBonus: TextView
    private lateinit var stabilityValue: TextView
    private lateinit var smellValue: TextView
    private lateinit var watchValue: TextView
    private lateinit var tracksValue: TextView
    private lateinit var listenValue: TextView
    private lateinit var searchingValue: TextView
    private lateinit var tasteValue: TextView
    private lateinit var stealthBonus: TextView
    private lateinit var ambushValue: TextView
    private lateinit var pickpocketValue: TextView
    private lateinit var sneakValue: TextView
    private lateinit var concealValue: TextView
    private lateinit var hideValue: TextView
    private lateinit var availableINT: TextView
    private lateinit var evocationTextView: TextView
    private lateinit var notes: EditText
    private lateinit var storageReference: StorageReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sheet_view, container, false)

        var bundle: Bundle = requireArguments()
        var charName = bundle.getString("charName")

        storageReference =
            FirebaseStorage.getInstance("gs://stormbringersheetmanager.appspot.com/").reference
        mAuth = Firebase.auth
        database =
            FirebaseDatabase.getInstance("https://stormbringersheetmanager-default-rtdb.europe-west1.firebasedatabase.app/").reference

        characterName = view.findViewById(R.id.characterName)
        characterName.text = charName
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
        HP = view.findViewById(R.id.HP)
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

        database.child("Users").child(mAuth.uid!!).get().addOnSuccessListener { it ->
            playerName.text = it.child("username").value.toString()
        }.addOnFailureListener() {
            println("NON HA FUNZIONATO")
        }

        val localFile = File.createTempFile("tempImage", "jpg")
        storageReference.child(mAuth.uid!! + "/").child(charName!! + "/").getFile(localFile).addOnSuccessListener {
            val bitmap = BitmapFactory.decodeFile(localFile.absolutePath)
            characterImage.setImageBitmap(bitmap)
        }

        database.child("Char").child(mAuth.uid!!).child(charName!!).get()
            .addOnSuccessListener { it ->
                nationality.text = it.child("nationality").value.toString()
                var finalClasses = ArrayList<String>()
                finalClasses = it.child("finalClass").value as ArrayList<String>
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
                weight.text =
                    resources.getString(R.string.weight) + it.child("weight").value.toString()
                height.text =
                    resources.getString(R.string.height) + it.child("height").value.toString()
                if (it.child("description").value.toString() == "Inserire la descrizione del personaggio") {
                    characterDescription.text = "----\n----\n----"
                } else {
                    characterDescription.text = it.child("description").value.toString()
                }
                armor.text = it.child("armor").value.toString()
                armorProtection.text = it.child("armorProtection").value.toString()
                graveWounds.text = resources.getString(R.string.wounds) + it.child("graveWounds").value.toString()
                HP.text = resources.getString(R.string.hp) + it.child("hp").value.toString()
                equipmentEditText.text = it.child("equipment").value.toString()
                money.text = it.child("money").value.toString()
                handicap.text = it.child("handicap").value.toString()
                var weapons: ArrayList<Weapon> = ArrayList()
                if (it.child("weapons").value != null) {
                    for (i in 0 until it.child("weapons").childrenCount) {
                        var weapon =
                            it.child("weapons").child(i.toString()).getValue(Weapon::class.java)!!
                        weapons.add(weapon)
                    }
                }
                for (i in weapons.indices) {
                    var name = TextView(requireContext())
                    name.text = weapons[i].name
                    name.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15f)
                    var params = GridLayout.LayoutParams()
                    params.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, GridLayout.FILL, 1f)
                    params.width = 0
                    name.layoutParams = params

                    var attack = TextView(requireContext())
                    attack.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15f)
                    attack.setTextColor(resources.getColor(R.color.black))
                    attack.gravity = Gravity.CENTER
                    var paramsAttack = GridLayout.LayoutParams()
                    paramsAttack.columnSpec =
                        GridLayout.spec(GridLayout.UNDEFINED, GridLayout.FILL, 1f)
                    paramsAttack.width = 0
                    attack.layoutParams = paramsAttack

                    var damage = TextView(requireContext())
                    damage.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15f)
                    damage.setTextColor(resources.getColor(R.color.black))
                    damage.gravity = Gravity.CENTER
                    damage.text = weapons[i].damage
                    var paramsDamage = GridLayout.LayoutParams()
                    paramsDamage.columnSpec =
                        GridLayout.spec(GridLayout.UNDEFINED, GridLayout.FILL, 1f)
                    damage.layoutParams = paramsDamage

                    var block = TextView(requireContext())
                    block.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15f)
                    block.setTextColor(resources.getColor(R.color.black))
                    block.gravity = Gravity.CENTER
                    var paramsBlock = GridLayout.LayoutParams()
                    paramsBlock.columnSpec =
                        GridLayout.spec(GridLayout.UNDEFINED, GridLayout.FILL, 1f)
                    paramsBlock.width = 0
                    block.layoutParams = paramsBlock

                    for (k in finalClasses.indices) {
                        when (finalClasses[k]) {
                            "Guerriero" -> {
                                if (i == 0) {
                                    attack.text = (bundle.getInt("attack") + 50).toString()
                                    block.text = (bundle.getInt("block") + 50).toString()
                                } else if (i == 1) {
                                    attack.text = (bundle.getInt("attack") + 40).toString()
                                    block.text = (bundle.getInt("block") + 40).toString()
                                } else if (i == 2) {
                                    attack.text = (bundle.getInt("attack") + 30).toString()
                                    block.text = (bundle.getInt("block") + 30).toString()
                                } else {
                                    attack.text = bundle.getInt("attack").toString()
                                    block.text = (bundle.getInt("block")).toString()
                                }
                            }
                            "Mercante" -> {
                                if (i == 1) {
                                    attack.text = (bundle.getInt("attack") + 40).toString()
                                    block.text = (bundle.getInt("block") + 40).toString()
                                } else {
                                    attack.text = bundle.getInt("attack").toString()
                                    block.text = (bundle.getInt("block")).toString()
                                }
                            }
                            "Marinaio" -> {
                                if (i == 0) {
                                    attack.text = (bundle.getInt("attack") + 40).toString()
                                    block.text = (bundle.getInt("block") + 40).toString()
                                } else {
                                    attack.text = bundle.getInt("attack").toString()
                                    block.text = (bundle.getInt("block")).toString()
                                }
                            }
                            "Cacciatore" -> {
                                if (weapons[i].name != "Arco lungo") {
                                    attack.text = (bundle.getInt("attack") + 30).toString()
                                    block.text = (bundle.getInt("block") + 30).toString()
                                } else {
                                    attack.text = (bundle.getInt("attack") + 25).toString()
                                    block.text = "0"
                                }
                            }
                            "Agricoltore" -> {
                                if (weapons[i].name == "Clava" ||
                                    weapons[i].name == "Bastone (2 mani)"
                                ) {
                                    attack.text = (bundle.getInt("attack") + 30).toString()
                                    block.text = (bundle.getInt("block") + 30).toString()
                                } else if (weapons[i].name == "Accetta") {
                                    attack.text = (bundle.getInt("attack") + 15).toString()
                                    block.text = (bundle.getInt("block") + 15).toString()
                                }
                            }
                            "Ladro" -> {
                                if (weapons[i].name == "Pugnale") {
                                    attack.text = (bundle.getInt("attack") + 45).toString()
                                    block.text = (bundle.getInt("block") + 45).toString()
                                } else {
                                    attack.text = (bundle.getInt("attack") + 35).toString()
                                    block.text = (bundle.getInt("block") + 35).toString()
                                }
                            }
                            "Mendicante" -> {
                                attack.text = "10"
                                block.text = "10"
                            }
                            else -> {
                                attack.text = (bundle.getInt("attack")).toString()
                                block.text = (bundle.getInt("block")).toString()
                            }
                        }
                    }

                    weaponsGrid.addView(name)
                    weaponsGrid.addView(attack)
                    weaponsGrid.addView(damage)
                    weaponsGrid.addView(block)
                }
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
                    println("VEDIAMO SE ARRIVA QUI " + it.child("agilitySkills").value)
                    for (i in 0 until it.child("agilitySkills").childrenCount) {
                        var agilitySkill = it.child("agilitySkills").child(i.toString())
                            .getValue(Skills::class.java)!!
                        agilitySkills.add(agilitySkill)
                    }
                    println("Gained data: $agilitySkills")
                }
                for (i in agilitySkills.indices) {
                    when (agilitySkills[i].name) {
                        "Saltare" -> {
                            jumpValue.text = agilitySkills[i].iniziale.toString()
                        }
                        "Arrampicarsi" -> {
                            climbValue.text = agilitySkills[i].iniziale.toString()
                        }
                        "Nuotare" -> {
                            swimValue.text = agilitySkills[i].iniziale.toString()
                        }
                        "Schivare" -> {
                            dodgeValue.text = agilitySkills[i].iniziale.toString()
                        }
                        "Cavalcare" -> {
                            ridingValue.text = agilitySkills[i].iniziale.toString()
                        }
                        "Cadere" -> {
                            fallValue.text = agilitySkills[i].iniziale.toString()
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
                        var communicationSkill = it.child("communicationSkills").child(i.toString())
                            .getValue(Skills::class.java)!!
                        communicationSkills.add(communicationSkill)
                    }
                    System.out.println("Gained data: $agilitySkills")
                }
                for (i in communicationSkills.indices) {
                    when (communicationSkills[i].name) {
                        "Reputazione" -> {
                            reputationValue.text = communicationSkills[i].iniziale.toString()
                        }
                        "Persuasione" -> {
                            persuasionValue.text = communicationSkills[i].iniziale.toString()
                        }
                        "Oratoria" -> {
                            speechValue.text = communicationSkills[i].iniziale.toString()
                        }
                        "Cantare" -> {
                            singingValue.text = communicationSkills[i].iniziale.toString()
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
                    for (i in 0 until it.child("knowledgeSkills").childrenCount) {
                        var knowledgeSkill = it.child("knowledgeSkills").child(i.toString())
                            .getValue(Skills::class.java)!!
                        knowledgeSkills.add(knowledgeSkill)
                    }
                    System.out.println("Gained data: $agilitySkills")
                }
                for (i in knowledgeSkills.indices) {
                    when (knowledgeSkills[i].name) {
                        "Pronto Soccorso" -> {
                            firstAidValue.text = knowledgeSkills[i].iniziale.toString()
                        }
                        "Memoria" -> {
                            memoryValue.text = knowledgeSkills[i].iniziale.toString()
                        }
                        "Navigazione" -> {
                            navigationValue.text = knowledgeSkills[i].iniziale.toString()
                        }
                        "Conoscenza dei Veleni" -> {
                            poisonValue.text = knowledgeSkills[i].iniziale.toString()
                        }
                        "Valutare Tesori" -> {
                            treasureValue.text = knowledgeSkills[i].iniziale.toString()
                        }
                        "Cartografia" -> {
                            cartographyValue.text = knowledgeSkills[i].iniziale.toString()
                        }
                        "Conoscenza della Musica" -> {
                            musicValue.text = knowledgeSkills[i].iniziale.toString()
                        }
                        "Conoscenza delle Piante" -> {
                            plantsValue.text = knowledgeSkills[i].iniziale.toString()
                        }
                        "Leggere/Scrivere Lingua Comune" -> {
                            commonLanguage.text = knowledgeSkills[i].iniziale.toString()
                        }
                        "Leggere/Scrivere Basso Melniboleano" -> {
                            lowMelniboleano.text = knowledgeSkills[i].iniziale.toString()
                        }
                        "Leggere/Scrivere Tardo Melniboleano" -> {
                            lateMelniboleano.text = knowledgeSkills[i].iniziale.toString()
                        }
                        "Leggere/Scrivere Alto Meniboleano" -> {
                            highMelniboleano.text = knowledgeSkills[i].iniziale.toString()
                        }
                        "Leggere/Scrivere/Parlare Altre Lingue" -> {
                            otherLanguages.text = knowledgeSkills[i].iniziale.toString()
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
                    for (i in 0 until it.child("manipulationSkills").childrenCount) {
                        var manipulationSkill = it.child("manipulationSkills").child(i.toString())
                            .getValue(Skills::class.java)!!
                        manipulationSkills.add(manipulationSkill)
                    }
                    println("Gained data: $agilitySkills")
                }
                for (i in manipulationSkills.indices) {
                    when (manipulationSkills[i].name) {
                        "Giocoleria" -> {
                            jugglingValue.text = manipulationSkills[i].iniziale.toString()
                        }
                        "Prestidigitazione" -> {
                            magicValue.text = manipulationSkills[i].iniziale.toString()
                        }
                        "Attivare/Disattivare trappole" -> {
                            trapsValue.text = manipulationSkills[i].iniziale.toString()
                        }
                        "Scassinare" -> {
                            lockValue.text = manipulationSkills[i].iniziale.toString()
                        }
                        "Fare/Disfare Nodi" -> {
                            nodesValue.text = manipulationSkills[i].iniziale.toString()
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
                    for (i in 0 until it.child("perceptionSkills").childrenCount) {
                        var perceptionSkill = it.child("perceptionSkills").child(i.toString())
                            .getValue(Skills::class.java)!!
                        perceptionSkills.add(perceptionSkill)
                    }
                    System.out.println("Gained data: $agilitySkills")
                }
                for (i in perceptionSkills.indices) {
                    when (perceptionSkills[i].name) {
                        "Equilibrio" -> {
                            stabilityValue.text = perceptionSkills[i].iniziale.toString()
                        }
                        "Annusare" -> {
                            smellValue.text = perceptionSkills[i].iniziale.toString()
                        }
                        "Osservare" -> {
                            watchValue.text = perceptionSkills[i].iniziale.toString()
                        }
                        "Seguire Tracce" -> {
                            tracksValue.text = perceptionSkills[i].iniziale.toString()
                        }
                        "Ascoltare" -> {
                            listenValue.text = perceptionSkills[i].iniziale.toString()
                        }
                        "Cercare" -> {
                            searchingValue.text = perceptionSkills[i].iniziale.toString()
                        }
                        "Gustare" -> {
                            tasteValue.text = perceptionSkills[i].iniziale.toString()
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
                    println("Stealth VALUE " + it.child("stealthSkills").value)
                    for (i in 0 until it.child("stealthSkills").childrenCount) {
                        var stealthSkill = it.child("stealthSkills").child(i.toString())
                            .getValue(Skills::class.java)!!
                        stealthSkills.add(stealthSkill)
                    }
                    System.out.println("Gained data: $agilitySkills")
                }
                for (i in stealthSkills.indices) {
                    when (stealthSkills[i].name) {
                        "Agguato" -> {
                            ambushValue.text = stealthSkills[i].iniziale.toString()
                        }
                        "Borseggiare" -> {
                            pickpocketValue.text = stealthSkills[i].iniziale.toString()
                        }
                        "Intrufolarsi" -> {
                            sneakValue.text = stealthSkills[i].iniziale.toString()
                        }
                        "Nascondere" -> {
                            concealValue.text = stealthSkills[i].iniziale.toString()
                        }
                        "Nascondersi" -> {
                            hideValue.text = stealthSkills[i].iniziale.toString()
                        }
                    }
                }
                availableINT = view.findViewById(R.id.availableINT)
                availableINT.text =
                    resources.getString(R.string.availableINT) + it.child("availableINT").value.toString()
                evocationTextView = view.findViewById(R.id.evocationTextView)
                if (it.child("availablEvocations").value.toString() == ""
                ) {
                    evocationTextView.text = resources.getString(R.string.evocation)
                } else {
                    evocationTextView.text = it.child("availableEvocations").value.toString()
                }
            }.addOnFailureListener() {
                println("NON HA FUNZIONATO")
            }

        return view
    }
}