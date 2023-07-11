package com.example.stormbringersheetmanager.CharacterCreation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.TypedValue
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.example.stormbringersheetmanager.DiceRolls
import com.example.stormbringersheetmanager.R
import com.example.stormbringersheetmanager.Utility.Character
import com.example.stormbringersheetmanager.Utility.Skills
import com.example.stormbringersheetmanager.Utility.Weapon
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.net.URI

class SheetFragment : Fragment() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private lateinit var characterName: TextView
    private lateinit var playerName: TextView
    private lateinit var nationality: TextView
    private lateinit var classes: TextView
    private lateinit var age: TextView
    private lateinit var gender: TextView
    private lateinit var cult: EditText
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
    private lateinit var characterDescription: EditText
    private lateinit var characterImage: ImageView
    private lateinit var imageUri: Uri
    private lateinit var imageButton: Button
    private lateinit var armor: Spinner
    private lateinit var armorProtection: TextView
    private lateinit var graveWounds: TextView
    private lateinit var HP: TextView
    private lateinit var equipmentEditText: EditText
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
    private lateinit var evocationCheckBox: CheckBox
    private lateinit var evocationTextView: TextView
    private lateinit var evocationEditText: EditText
    private lateinit var finalConfirmButton: Button
    private lateinit var notes: EditText
    private lateinit var storageReference: StorageReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sheet, container, false)

        mAuth = Firebase.auth
        storageReference =
            FirebaseStorage.getInstance("gs://stormbringersheetmanager.appspot.com/").reference
        val bundle = requireArguments()
        var skillsList: ArrayList<Skills> = bundle.getParcelableArrayList("finalSkills")!!

        evocationCheckBox = view.findViewById(R.id.evocationCheckBox)
        evocationTextView = view.findViewById(R.id.evocationTextView)
        evocationEditText = view.findViewById(R.id.evocationEditText)

        var evocation = false

        evocationCheckBox.setOnClickListener() {
            if (evocationCheckBox.isChecked) {
                evocationEditText.isEnabled = true
                evocation = true
            } else {
                evocationEditText.isEnabled = false
                evocationEditText.text.clear()
                evocation = false
            }
        }

        notes = view.findViewById(R.id.notes)
        characterName = view.findViewById(R.id.characterName)
        characterName.text = bundle.getString("characterName")
        playerName = view.findViewById(R.id.playerName)
        var username = ""
        database =
            FirebaseDatabase.getInstance("https://stormbringersheetmanager-default-rtdb.europe-west1.firebasedatabase.app/").reference

        database.child("Users").child(mAuth.uid!!).get().addOnSuccessListener { it ->
            username = it.child("username").value.toString()
            playerName.text = username
            println("Username: " + username)
        }.addOnFailureListener() {
            println("NON HA FUNZIONATO")
        }

        gender = view.findViewById(R.id.gender)
        gender.text = resources.getString(R.string.gender) + bundle.getString("gender")

        cult = view.findViewById(R.id.cultTextView)
        elan = view.findViewById(R.id.elan)

        if (bundle.getStringArrayList("class")!!.contains("Sacerdote")) {
            elan.text =
                resources.getString(R.string.elan) + (DiceRolls.D6() + DiceRolls.D6()).toString()
        } else {
            elan.text = "0"
        }

        age = view.findViewById(R.id.age)
        age.text = resources.getString(R.string.age) + bundle.getInt("age").toString()

        nationality = view.findViewById(R.id.nationality)
        nationality.text = bundle.getString("nationality")

        classes = view.findViewById(R.id.classes)
        var classesList: ArrayList<String> = bundle.getStringArrayList("class")!!
        classes.text = listToString(classesList)

        FOR = view.findViewById(R.id.FOR)
        FOR.text = resources.getString(R.string.FOR) + bundle.getInt("FOR").toString()
        COS = view.findViewById(R.id.COS)
        COS.text = resources.getString(R.string.COS) + bundle.getInt("COS").toString()
        TAG = view.findViewById(R.id.TAG)
        TAG.text = resources.getString(R.string.TAG) + bundle.getInt("TAG").toString()
        INT = view.findViewById(R.id.INT)
        INT.text = resources.getString(R.string.INT) + bundle.getInt("INT").toString()
        MAN = view.findViewById(R.id.MAN)
        MAN.text = resources.getString(R.string.MAN) + bundle.getInt("MAN").toString()
        DES = view.findViewById(R.id.DES)
        DES.text = resources.getString(R.string.DES) + bundle.getInt("DES").toString()
        FAS = view.findViewById(R.id.FAS)
        FAS.text = resources.getString(R.string.FAS) + bundle.getInt("FAS").toString()

        var statsList = ArrayList<Int>()
        statsList.add(bundle.getInt("FOR"))
        statsList.add(bundle.getInt("COS"))
        statsList.add(bundle.getInt("TAG"))
        statsList.add(bundle.getInt("INT"))
        statsList.add(bundle.getInt("MAN"))
        statsList.add(bundle.getInt("DES"))
        statsList.add(bundle.getInt("FAS"))

        weight = view.findViewById(R.id.weight)
        weight.text = resources.getString(R.string.weight) + bundle.getInt("weight").toString()
        height = view.findViewById(R.id.height)
        height.text = resources.getString(R.string.height) + bundle.getInt("height").toString()

        imageUri = Uri.EMPTY
        characterDescription = view.findViewById(R.id.characterDescription)
        characterImage = view.findViewById(R.id.characterImage)
        imageButton = view.findViewById(R.id.imageButton)
        imageButton.setOnClickListener() {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, 100)
        }

        armor = view.findViewById(R.id.armor)
        armorProtection = view.findViewById(R.id.armorProtection)
        var armorList = listOf<String>(
            "Armatura in cuoio",
            "Armatura barbara",
            "Armatura di piastre"
        )
        var armorAdapter = ArrayAdapter(requireContext(), R.layout.spinner_item, armorList)
        armor.adapter = armorAdapter

        armor.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                when (armor.selectedItem) {
                    "Armatura in cuoio" -> {
                        //TODO
                        armorProtection.text = "1D6-1"
                    }
                    "Armatura barbara" -> {
                        //TODO
                        armorProtection.text = "1D8-1"
                    }
                    "Armatura di piastre" -> {
                        //TODO
                        armorProtection.text = "1D10-1/1D10+2"
                    }
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        graveWounds = view.findViewById(R.id.graveWounds)
        HP = view.findViewById(R.id.HP)
        HP.text = hpCalculator(bundle.getInt("TAG"), bundle.getInt("COS")).toString()
        graveWounds.text = (hpCalculator(bundle.getInt("TAG"), bundle.getInt("COS")) / 2).toString()

        equipmentEditText = view.findViewById(R.id.equipmentEditText)
        money = view.findViewById(R.id.money)
        money.text =
            moneyCalculator(bundle.getStringArrayList("class")!!, bundle.getInt("INT")).toString()

        handicap = view.findViewById(R.id.handicap)
        if (bundle.getStringArrayList("class")!!.contains("Mendicante")) {
            var handicapRandom = ""
            when (DiceRolls.D20()) {
                1 -> handicapRandom = "Cataratta\nMezzo cieco"
                2 -> {
                    handicapRandom = "Senza un occhio"
                    for (i in skillsList.indices) {
                        if (skillsList[i].name == "Ascoltare") {
                            skillsList[i].iniziale = skillsList[i].iniziale?.div(2)
                        }
                    }
                }
                3 -> handicapRandom = "Muto o \nsenza lingua"
                4 -> handicapRandom = "Orbo"
                5 -> handicapRandom = "Senza naso"
                6 -> handicapRandom = "Denti marci\nAlito pesante"
                7 -> handicapRandom = "Pustole essudanti"
                8 -> handicapRandom = "Lebbroso"
                9 -> handicapRandom = "Emofiliaco"
                10 -> handicapRandom = "Spregevole\nMeschino"
                11 -> handicapRandom = "Senza 2D4 dita"
                12 -> handicapRandom = "Senza una mano"
                13 -> handicapRandom = "Senza un piede"
                14 -> handicapRandom = "Senza un braccio"
                15 -> handicapRandom = "Senza una gamba"
                16 -> handicapRandom = "Senza un braccio\ne una gamba"
                17 -> handicapRandom = "Glabro e/o\nscabbioso"
                18 -> handicapRandom = "Obeso"
                19 -> handicapRandom = "Scheletrico"
                20 -> {
                    handicapRandom = "Ritardato\nmentale"
                    INT.text = (bundle.getInt("INT") - DiceRolls.D6()).toString()
                }
            }
            handicap.text = handicapRandom
        }

        agilityBonus = view.findViewById(R.id.agilityBonus)
        agilityBonus.text = bundle.getInt("agility").toString()
        communicationBonus = view.findViewById(R.id.communicationBonus)
        communicationBonus.text = bundle.getInt("communication").toString()
        perceptionBonus = view.findViewById(R.id.perceptionBonus)
        perceptionBonus.text = bundle.getInt("perception").toString()
        stealthBonus = view.findViewById(R.id.stealthBonus)
        stealthBonus.text = bundle.getInt("stealth").toString()
        manipulationBonus = view.findViewById(R.id.manipulationBonus)
        manipulationBonus.text = bundle.getInt("manipulation").toString()
        knowledgeBonus = view.findViewById(R.id.knowledgeBonus)
        knowledgeBonus.text = bundle.getInt("knowledge").toString()

        highMelniboleano = view.findViewById(R.id.highMelniboleanoValue)
        highMelniboleano.text =
            searchSkill(skillsList, "Leggere/Scrivere Alto Meniboleano").toString()
        lowMelniboleano = view.findViewById(R.id.lowMelniboleanoValue)
        lowMelniboleano.text =
            searchSkill(skillsList, "Leggere/Scrivere Basso Melniboleano").toString()
        lateMelniboleano = view.findViewById(R.id.lateMelniboleanoValue)
        lateMelniboleano.text =
            searchSkill(skillsList, "Leggere/Scrivere Tardo Melniboleano").toString()
        commonLanguage = view.findViewById(R.id.commonValue)
        commonLanguage.text = searchSkill(skillsList, "Leggere/Scrivere Lingua Comune").toString()
        otherLanguages = view.findViewById(R.id.otherLanguagesValue)
        otherLanguages.text =
            searchSkill(skillsList, "Leggere/Scrivere/Parlare Altre Lingue").toString()
        climbValue = view.findViewById(R.id.climbValue)
        climbValue.text = searchSkill(skillsList, "Arrampicarsi").toString()
        jumpValue = view.findViewById(R.id.jumpValue)
        jumpValue.text = searchSkill(skillsList, "Saltare").toString()
        swimValue = view.findViewById(R.id.swimValue)
        swimValue.text = searchSkill(skillsList, "Nuotare").toString()
        dodgeValue = view.findViewById(R.id.dodgeValue)
        dodgeValue.text = searchSkill(skillsList, "Schivare").toString()
        ridingValue = view.findViewById(R.id.ridingValue)
        ridingValue.text = searchSkill(skillsList, "Cavalcare").toString()
        fallValue = view.findViewById(R.id.fallValue)
        fallValue.text = searchSkill(skillsList, "Cadere").toString()
        reputationValue = view.findViewById(R.id.reputationValue)
        reputationValue.text = searchSkill(skillsList, "Reputazione").toString()
        persuasionValue = view.findViewById(R.id.persuasionValue)
        persuasionValue.text = searchSkill(skillsList, "Persuasione").toString()
        speechValue = view.findViewById(R.id.speechValue)
        speechValue.text = searchSkill(skillsList, "Oratoria").toString()
        singingValue = view.findViewById(R.id.singingValue)
        singingValue.text = searchSkill(skillsList, "Cantare").toString()
        firstAidValue = view.findViewById(R.id.firstAidValue)
        firstAidValue.text = searchSkill(skillsList, "Pronto Soccorso").toString()
        memoryValue = view.findViewById(R.id.memoryValue)
        memoryValue.text = searchSkill(skillsList, "Memoria").toString()
        navigationValue = view.findViewById(R.id.navigationValue)
        navigationValue.text = searchSkill(skillsList, "Navigazione").toString()
        poisonValue = view.findViewById(R.id.poisonValue)
        poisonValue.text = searchSkill(skillsList, "Conoscenza dei Veleni").toString()
        treasureValue = view.findViewById(R.id.treasureValue)
        treasureValue.text = searchSkill(skillsList, "Valutare Tesori").toString()
        cartographyValue = view.findViewById(R.id.cartographyValue)
        cartographyValue.text = searchSkill(skillsList, "Cartografia").toString()
        musicValue = view.findViewById(R.id.musicValue)
        musicValue.text = searchSkill(skillsList, "Conoscenza della Musica").toString()
        plantsValue = view.findViewById(R.id.plantsValue)
        plantsValue.text = searchSkill(skillsList, "Conoscenza delle Piante").toString()
        jugglingValue = view.findViewById(R.id.jugglingValue)
        jugglingValue.text = searchSkill(skillsList, "Giocoleria").toString()
        magicValue = view.findViewById(R.id.magicValue)
        magicValue.text = searchSkill(skillsList, "Prestidigitazione").toString()
        trapsValue = view.findViewById(R.id.trapsValue)
        trapsValue.text = searchSkill(skillsList, "Attivare/Disattivare trappole").toString()
        lockValue = view.findViewById(R.id.lockValue)
        lockValue.text = searchSkill(skillsList, "Scassinare").toString()
        nodesValue = view.findViewById(R.id.nodesValue)
        nodesValue.text = searchSkill(skillsList, "Fare/Disfare Nodi").toString()
        stabilityValue = view.findViewById(R.id.stabilityValue)
        stabilityValue.text = searchSkill(skillsList, "Equilibrio").toString()
        smellValue = view.findViewById(R.id.smellValue)
        smellValue.text = searchSkill(skillsList, "Annusare").toString()
        watchValue = view.findViewById(R.id.watchValue)
        watchValue.text = searchSkill(skillsList, "Osservare").toString()
        tracksValue = view.findViewById(R.id.tracksValue)
        tracksValue.text = searchSkill(skillsList, "Seguire Tracce").toString()
        listenValue = view.findViewById(R.id.listenValue)
        listenValue.text = searchSkill(skillsList, "Ascoltare").toString()
        searchingValue = view.findViewById(R.id.searchingValue)
        searchingValue.text = searchSkill(skillsList, "Cercare").toString()
        tasteValue = view.findViewById(R.id.tasteValue)
        tasteValue.text = searchSkill(skillsList, "Gustare").toString()
        ambushValue = view.findViewById(R.id.ambushValue)
        ambushValue.text = searchSkill(skillsList, "Agguato").toString()
        pickpocketValue = view.findViewById(R.id.pickpocketValue)
        pickpocketValue.text = searchSkill(skillsList, "Borseggiare").toString()
        sneakValue = view.findViewById(R.id.sneakValue)
        sneakValue.text = searchSkill(skillsList, "Intrufolarsi").toString()
        concealValue = view.findViewById(R.id.concealValue)
        concealValue.text = searchSkill(skillsList, "Nascondere").toString()
        hideValue = view.findViewById(R.id.hideValue)
        hideValue.text = searchSkill(skillsList, "Nascondersi").toString()
        availableINT = view.findViewById(R.id.availableINT)
        availableINT.text = bundle.getInt("INT").toString()

        weaponsGrid = view.findViewById(R.id.weaponsGrid)

        var weapons: ArrayList<Weapon> = bundle.getParcelableArrayList("weapon")!!
        if (weapons.size != 0) {
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
                paramsAttack.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, GridLayout.FILL, 1f)
                paramsAttack.width = 0
                attack.layoutParams = paramsAttack

                var damage = TextView(requireContext())
                damage.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15f)
                damage.setTextColor(resources.getColor(R.color.black))
                damage.gravity = Gravity.CENTER
                damage.text = weapons[i].damage
                var paramsDamage = GridLayout.LayoutParams()
                paramsDamage.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, GridLayout.FILL, 1f)
                damage.layoutParams = paramsDamage

                var block = TextView(requireContext())
                block.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15f)
                block.setTextColor(resources.getColor(R.color.black))
                block.gravity = Gravity.CENTER
                var paramsBlock = GridLayout.LayoutParams()
                paramsBlock.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, GridLayout.FILL, 1f)
                paramsBlock.width = 0
                block.layoutParams = paramsBlock

                for (k in classesList.indices) {
                    when (classesList[k]) {
                        "Guerriero" -> {
                            if (i == 1) {
                                attack.text = (bundle.getInt("attack") + 50).toString()
                                block.text = (bundle.getInt("block") + 50).toString()
                            } else if (i == 2) {
                                attack.text = (bundle.getInt("attack") + 40).toString()
                                block.text = (bundle.getInt("block") + 40).toString()
                            } else if (i == 3) {
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
                            if (i == 1) {
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
        }

        var agilitySkills = ArrayList<Skills>()
        var communicationSkills = ArrayList<Skills>()
        var knowledgeSkills = ArrayList<Skills>()
        var manipulationSkills = ArrayList<Skills>()
        var perceptionSkills = ArrayList<Skills>()
        var stealthSkills = ArrayList<Skills>()

        for (i in skillsList.indices) {
            when (skillsList[i].type) {
                "Agilità" -> {
                    agilitySkills.add(
                        skillsList[i]
                    )
                }
                "Comunicazione" -> {
                    communicationSkills.add(
                        skillsList[i]
                    )
                }
                "Conoscenza" -> {
                    knowledgeSkills.add(
                        skillsList[i]
                    )
                }
                "Manipolazione" -> {
                    manipulationSkills.add(
                        skillsList[i]
                    )
                }
                "Percezione" -> {
                    perceptionSkills.add(
                        skillsList[i]
                    )
                }
                "Furtività" -> {
                    stealthSkills.add(
                        skillsList[i]
                    )
                }
            }
        }

        finalConfirmButton = view.findViewById(R.id.finalConfirmButton)

        finalConfirmButton.setOnClickListener() {
            database =
                Firebase.database("https://stormbringersheetmanager-default-rtdb.europe-west1.firebasedatabase.app/").reference

            val character = Character(
                characterName.text.toString(),
                bundle.getInt("age"),
                bundle.getString("gender"),
                playerName.text.toString(),
                nationality.text.toString(),
                classesList,
                if (cult.text.toString() == "") {
                    "Nessun culto"
                } else {
                    cult.text.toString()
                },
                elan.text.toString().toInt(),
                statsList,
                bundle.getInt("height"),
                bundle.getInt("weight"),
                if (characterDescription.text.toString() == "") {
                    "Nessuna descrizione"
                } else {
                    characterDescription.text.toString()
                },
                armor.selectedItem.toString(),
                armorProtection.text.toString(),
                graveWounds.text.toString().toInt(),
                HP.text.toString().toInt(),
                HP.text.toString().toInt(),
                if (equipmentEditText.text.toString() == "") {
                    "Il personaggio non ha equipaggiamento aggiuntivo"
                } else {
                    equipmentEditText.text.toString()
                },
                money.text.toString().toInt(),
                handicap.text.toString(),
                weapons,
                if (notes.text.toString() == "") {
                    "Nessuna nota aggiuntiva"
                } else {
                    notes.text.toString()
                },
                agilityBonus.text.toString().toInt(),
                agilitySkills,
                communicationBonus.text.toString().toInt(),
                communicationSkills,
                knowledgeBonus.text.toString().toInt(),
                knowledgeSkills,
                manipulationBonus.text.toString().toInt(),
                manipulationSkills,
                perceptionBonus.text.toString().toInt(),
                perceptionSkills,
                stealthBonus.text.toString().toInt(),
                stealthSkills,
                availableINT.text.toString().toInt(),
                evocation,
                evocationEditText.text.toString()
            )

            database.child("Char").child(mAuth.uid!!).child(characterName.text.toString())
                .setValue(character)

            if (imageUri.toString() != "") {
                storageReference.child(playerName.text.toString()).child(characterName.text.toString())
                    .putFile(imageUri)
            }

            Navigation.findNavController(view).navigate(R.id.homeFragment)
        }

        return view
    }

    private fun moneyCalculator(classes: ArrayList<String>, INT: Int): Int {
        var money = 0
        if (!classes.contains("Nobile")) {
            when {
                classes.contains("Guerriero") -> {
                    money = DiceRolls.D100() * INT
                }
                classes.contains("Assassino") -> {
                    money = DiceRolls.D100() * INT
                }
                classes.contains("Mercante") -> {
                    money = 5 * DiceRolls.D100()
                }
                classes.contains("Marinaio") -> {
                    money = INT * DiceRolls.D20()
                }
                classes.contains("Cacciatore") -> {
                    money = DiceRolls.D100()
                }
                classes.contains("Agricoltore") -> {
                    money = INT * DiceRolls.D20()
                }
                classes.contains("Sacerdote") -> {
                    money = 5 * DiceRolls.D100()
                }
                classes.contains("Ladro") -> {
                    money = 5 * DiceRolls.D20()
                }
                classes.contains("Mendicante") -> {
                    money = DiceRolls.D6()
                }
            }
        } else {
            money = 10000 * DiceRolls.D100()
        }
        return money
    }

    private fun listToString(list: ArrayList<String>): String {
        var string = ""
        for (i in list.indices) {
            if (i == 0) {
                string = list[i]
            } else {
                string = string + "\n" + list[i]
            }
        }
        return string
    }

    private fun hpCalculator(TAG: Int, COS: Int): Int {
        var hp = 0
        if (TAG > 12) {
            hp = COS + (TAG - 12)
        } else if (TAG < 9) {
            hp = COS - (9 - TAG)
        } else {
            hp = COS
        }
        return hp
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 100 && resultCode == AppCompatActivity.RESULT_OK) {
            imageUri = data?.data!!
            characterImage.setImageURI(imageUri)
        }
    }

    private fun searchSkill(skillsList: ArrayList<Skills>, name: String): Int {
        var score = 0
        for (i in skillsList.indices) {
            if (skillsList[i].name == name) {
                score = skillsList[i].iniziale!!
            }
        }
        return score
    }

}