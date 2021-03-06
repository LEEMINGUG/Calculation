package com.zeus.calculation
import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.zeus.calculation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        var actionBar : ActionBar?
        actionBar = supportActionBar
        actionBar?.hide()
        setContentView(binding.root)
        claculationEvent(binding,this)
//        Glide.with(this).load(R.drawable.tenor).into(binding.imageView3)
    }
}

fun claculationEvent(binding:ActivityMainBinding,activity:Activity){
    var totalnum:Double    = 0.0
    var strnum             = ""
    var first:Double       = 0.0
    var second:Double      = 0.0

    var strnumdel:String?  = null
    var buhocheck:Int      = 0
    var commaCount:Int     = 0

    // Text Viewer Init
    binding.Result.text = "0"
    // number 0~9

    binding.btn5.setOnClickListener {
        updateData(binding,"1")
//        Glide.with(activity).load(R.drawable.tenor).into(binding.imageView3)
    }
    binding.btn9.setOnClickListener {
        updateData(binding,"8")
//        Glide.with(activity).load(R.drawable.good).into(binding.imageView3)
    }
    binding.btn10.setOnClickListener {
        updateData(binding,"5")
    }
    binding.btn11.setOnClickListener {
        updateData(binding,"2")
    }
    binding.btn12.setOnClickListener {
        updateData(binding,"0")
    }
    binding.btn15.setOnClickListener {
        updateData(binding,"9")
    }
    binding.btn16.setOnClickListener {
        updateData(binding,"6")
    }
    binding.btn17.setOnClickListener {
        updateData(binding,"3")
    }

    binding.btn20.setOnClickListener {
        strnum = binding.Result.text.toString()
        if (infinityCheck(strnum) == true)
        {
            binding.Result.text = "0"
        }
        else{
            if (strnum!="0"){
                if (buhocheck == 0){
                    zeroExp(binding,"/")
                    buhocheck = 1
                }
            }
        }
    }
    binding.btn21.setOnClickListener {
        strnum = binding.Result.text.toString()
        if (infinityCheck(strnum) == true)
        {
            binding.Result.text = "0"
        }
        else{
            if (strnum!="0"){
                if (buhocheck == 0){
                    zeroExp(binding,"*")
                    buhocheck = 1
                }
            }
        }
    }
    binding.btn22.setOnClickListener {
        strnum = binding.Result.text.toString()
        if (infinityCheck(strnum) == true)
        {
            binding.Result.text = "0"
        }
        else{
            if (strnum!="0"){
                if (buhocheck == 0){
                    zeroExp(binding,"-")
                    buhocheck = 1
                }
            }
        }
    }
    binding.btn23.setOnClickListener {
        strnum = binding.Result.text.toString()
        if (infinityCheck(strnum) == true)
        {
            binding.Result.text = "0"
        }
        else{
            if (strnum!="0"){
                if (buhocheck == 0){
                    zeroExp(binding,"+")
                    buhocheck = 1
                }
            }
        }
    }

    // ????????? ??????
    binding.btn18.setOnClickListener {
        strnum = binding.Result.text.toString()
        // ????????? ?????? ?????? . ??????
        if(strnum.contains(".") ==false)
        {
            binding.Result.append(".")
        }
        // ????????? ?????? ?????? (+,-,*,/) ?????? ??????
        else {
            floatCheck(binding,strnum)
        }
    }

    // ?????? ??????
    binding.btn6.setOnClickListener {
        strnum = binding.Result.text.toString()
        if (infinityCheck(strnum) == true)
        {
            binding.Result.text = "0"
        }else{
            // 0??? ????????????
            if (strnum !="0"){
                strnum = fbuhoCheck(binding,strnum)
            }
            binding.Result.text = strnum
        }
    }

    // ?????? ??????
    binding.btn24.setOnClickListener {
        strnum = binding.Result.text.toString()
        binding.subesult.text = strnum
        var tempNUm = countMatches(strnum,"-")
//        Glide.with(activity).load(R.drawable.result).into(binding.imageView3)
        // - 0??? ?????? 1??? ?????????
        if (tempNUm ==0 || tempNUm == 1 ){
            // +,-,*,/ ??????

            var firstM = strnum.indexOf("-")
            Log.d("tag3","{$firstM}")

            // -A/B ??????
            if( firstM ==0 && strnum.contains("/") == true ){
                firstmCheck(binding,strnum)
                buhocheck = 0
            }
            else{
                buhocheck = resultCheck(binding,strnum)
            }
        }
        // - 2??? ????????? ex) -A-B
        else{
            when {
                strnum.contains("-") == true -> {
                    var temp = strnum.split("-")
                    // -A-B ??????
                    if (temp[2].isNullOrEmpty() == false) {
                        first = temp[1].toDouble()
                        second = temp[2].toDouble()
                        totalnum = -first - second
                        tcheckFloat(binding,totalnum)
                        buhocheck = 0
                    }
                    // -A- ??????
                    else {
                        binding.Result.text = strnum
                        buhocheck = 1
                    }
                }
            }
        }
    }

    // 1/x ?????????
    binding.btn2.setOnClickListener {
        strnum = binding.Result.text.toString()
        first  = strnum.toDouble()

        var boolCheck = infinityCheck(strnum)

        if(boolCheck == true){
            binding.Result.text = "0"
        }
        else{
            totalnum   = 1 / first
            // Float, int ??????
            var temp = totalnum - totalnum.toInt()
            if(temp != 0.0)
            {
                totalnum = String.format("%.6f",totalnum).toDouble()
            }
            binding.Result.text = totalnum.toString()
        }
    }

    // Clean
    binding.btn13.setOnClickListener {
        binding.Result.text   = "0"
        binding.subesult.text = "0"
        buhocheck = 0
    }

    // Delete
    binding.btn19.setOnClickListener {
        var checkString:String = binding.Result.text.toString()

        // infinity Check
        var inifitBool  = infinityCheck(checkString)
        if(inifitBool == true)
        {
            binding.Result.text = "0"
        }
        else {
            strnumdel  = binding.Result.text.toString()?:"0"
            if (strnumdel?.length == 1) {
                binding.Result.text = "0"
            } else {
                Log.d("length", "${strnumdel?.length}")
                var numberstr = strnumdel?.length ?: 1
                strnumdel = strnumdel?.substring(0 until numberstr - 1) ?: ""
                binding.Result.text = strnumdel
            }
        }
        // search ?????? ?????? and ?????????
        buhocheck = checkSearchbuho(strnumdel)
    }
}
fun checkSearchbuho(Search:String?):Int{
    var buhocheck = 0
    if (Search != null) {
        if(Search.contains("+") == true || Search.contains("-") == true || Search.contains("*") == true || Search.contains("/") == true )
        {
            buhocheck = 1
        }
        else{
            buhocheck = 0
        }
    }
    Log.d("Tag300","{$Search},{$buhocheck}")
    return buhocheck
}

fun updateData(binding:ActivityMainBinding, valuestr:String){
    var stm  = binding.Result.text.toString()?:"0"
    var inifitBool  = infinityCheck(stm)

    if( inifitBool == true){
        binding.Result.text = valuestr
    }
    else {
        if (stm == "0") {
            binding.Result.text = ""
        }
        binding.Result.append(valuestr)
    }
}

fun zeroExp(binding:ActivityMainBinding, valuestr:String){
    var stm = binding.Result.text.toString()
    if (stm!="0")
    {
        binding.Result.append(valuestr)
    }
}

// ?????? ?????? ?????? ??????
fun countMatches(text: String, template: String): Int {
    var cnt = 0
    var pos = 0
    while (true) {
        pos = text.indexOf(template, pos)
        if (pos != -1) {
            cnt++
            pos++
        } else {
            return cnt
        }
    }
}
// ????????? ??????
fun floatCheck(binding:ActivityMainBinding, strnum:String){
    when {
        // + ??????
        strnum.contains("+") == true -> {
            var temp = strnum.split("+")
            // ?????? ????????? ?????? ??????
            if (temp[1].isNullOrEmpty() == false) {
                // ???????????? ?????? ??????
                if (temp[1].contains(".") == false) {
                    // ????????? ??????
                    binding.Result.append(".")
                }
            }
        }
        // * ??????
        strnum.contains("*") == true -> {
            var temp = strnum.split("*")
            // ?????? ????????? ?????? ??????
            if (temp[1].isNullOrEmpty() == false) {
                if (temp[1].contains(".") == false) {
                    binding.Result.append(".")
                }
            }
        }
        // - ??????
        strnum.contains("-") == true -> {
            var temp = strnum.split("-")
            // ?????? ????????? ?????? ??????
            if (temp[1].isNullOrEmpty() == false) {
                if (temp[1].contains(".") == false) {
                    binding.Result.append(".")
                }
            }
        }
        // / ??????
        strnum.contains("/") == true -> {
            var temp = strnum.split("/")
            // ?????? ????????? ?????? ??????
            if (temp[1].isNullOrEmpty() == false) {
                if (temp[1].contains(".") == false) {
                    binding.Result.append(".")
                }
            }
        }
    }
}

fun fbuhoCheck(binding:ActivityMainBinding, strnum:String):String {
    val buhom:String?  = "-"
    var stringTemp:String = ""
    Log.d("String","{$strnum}")
    when {
        // - ?????????
        strnum.contains("-") == true -> {
            var temp = strnum.split("-")
            var tempNUm = countMatches(strnum, "-")
            Log.d("tag","{$strnum}")
            Log.d("tag1","{$temp}")
            var setNull = temp[1].isNullOrEmpty()
            Log.d("tag2","{$setNull}")

            // ?????? ?????? ?????? ?????? ex) [3, ] -> - ?????????
            if (temp[1].isNullOrEmpty() == false) {
                // - ????????????, ?????? ?????? ?????? ex) -3
                Log.d("tag1","{$tempNUm}")
                if (tempNUm == 1) {
                    // - ??????
                    stringTemp = strnum?.substring(1) ?: ""
                }
            }
            else{
                stringTemp = strnum
            }
        }
        strnum.contains("+") == true -> {
            stringTemp = strnum
        }
        strnum.contains("*") == true -> {
            stringTemp = strnum
        }
        strnum.contains("/") == true -> {
            stringTemp = strnum
        }
        else -> {
            stringTemp = buhom + strnum
        }
    }
    return stringTemp
}

fun resultCheck(binding:ActivityMainBinding, strnum:String):Int {
    var first:Double    = 0.0
    var second:Double   = 0.0
    var totalnum:Double = 0.0
    var checkReturn     = 0
    when{
        strnum.contains("+") == true->{
            var temp   = strnum.split("+")

            if (temp[1].isNullOrEmpty()==false)
            {
                first      = temp[0].toDouble()
                second     = temp[1].toDouble()
                totalnum   = first + second
                tcheckFloat(binding,totalnum)
                checkReturn= 0
            }
            else {
                binding.Result.text = strnum
                checkReturn= 1
            }
        }

        strnum.contains("*") == true->{
            var temp = strnum.split("*")
            if (temp[1].isNullOrEmpty()==false)
            {
                first = temp[0].toDouble()
                second = temp[1].toDouble()
                totalnum = first * second
                tcheckFloat(binding,totalnum)
                checkReturn= 0
            }
            else {
                binding.Result.text = strnum
                checkReturn= 1
            }
        }

        strnum.contains("-") == true->{
            var temp   = strnum.split("-")
            if (temp[1].isNullOrEmpty()==false)
            {
                first = temp[0].toDouble()
                second = temp[1].toDouble()
                totalnum = first - second
                tcheckFloat(binding,totalnum)
                checkReturn= 0
            }
            else {
                binding.Result.text = strnum
                checkReturn= 1
            }
        }

        strnum.contains("/") == true->{
            var temp   = strnum.split("/")
            if (temp[1].isNullOrEmpty()==false)
            {
                first = temp[0].toDouble()
                second = temp[1].toDouble()
                totalnum = first / second
                tcheckFloat(binding,totalnum)
                checkReturn= 0
            }
            else {
                binding.Result.text = strnum
                checkReturn= 1
            }
        }
    }
    return checkReturn
}

fun firstmCheck(binding:ActivityMainBinding, strnum:String) {
    var first:Double    = 0.0
    var second:Double   = 0.0
    var totalnum:Double = 0.0

    var temp   = strnum.split("/")
    if (temp[1].isNullOrEmpty()==false)
    {
        first = temp[0].toDouble()
        second = temp[1].toDouble()
        totalnum = first / second
        tcheckFloat(binding,totalnum)
    }
    else {
        binding.Result.text = strnum
    }
}
fun tcheckFloat(binding:ActivityMainBinding, totalnum:Double){
    // Float, int ??????
    var temp = totalnum - totalnum.toInt()
    if(temp == 0.0)
    {
        var tempvalue = totalnum.toInt()
        binding.Result.text = tempvalue.toString()
    }
    else{
        var numberTotal:Double = String.format("%.6f",totalnum).toDouble()
        binding.Result.text = numberTotal.toString()
    }
}

fun infinityCheck(checkString:String):Boolean{
    if(checkString == "Infinity"){
        return true
    }
    else{
        return false
    }
}

fun stringCount(tempString:String):Int{
    var countNum = 0
    return countNum
}
