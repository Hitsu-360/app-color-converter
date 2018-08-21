package com.appcolorconverter

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.SeekBar

class MainActivity : Activity() {

    internal lateinit var editHex: EditText
    internal lateinit var editR: EditText
    internal lateinit var editG: EditText
    internal lateinit var editB: EditText
    internal lateinit var seekR: SeekBar
    internal lateinit var seekG: SeekBar
    internal lateinit var seekB: SeekBar
    internal lateinit var mainLayout: RelativeLayout
    internal var flagHex: Int = 0
    internal var flagRgb: Int = 0
    internal var flagSeekBars: Int = 0

    private val hexTextWatcher = object : TextWatcher {

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            if (flagHex == 0) {
                val hex = editHex.text.toString()

                if (hex.matches("^#([A-Fa-f0-9]{6})$".toRegex())) {
                    val color = Color.parseColor(hex)

                    mainLayout.setBackgroundColor(color)
                    flagRgb = 1
                    flagSeekBars = 1
                    setRgbByColor(color)
                    setSeekBarsByColor(color)
                }
            }
            flagHex = 0
        }

        override fun afterTextChanged(s: Editable) {

        }
    }

    private val rgbTextWatcher = object : TextWatcher {

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            if (flagRgb == 0) {
                val strR = editR.text.toString()
                val strG = editG.text.toString()
                val strB = editB.text.toString()

                if (!strR.isEmpty() && !strG.isEmpty() && !strB.isEmpty()) {
                    val r = Integer.parseInt(strR)
                    val g = Integer.parseInt(strG)
                    val b = Integer.parseInt(strB)

                    val color = Color.rgb(r, g, b)

                    mainLayout.setBackgroundColor(color)
                    flagHex = 1
                    flagSeekBars = 1
                    setHexEditTextByColor(color)
                    setSeekBarsByColor(color)
                }
            }
            flagRgb = 0
        }

        override fun afterTextChanged(s: Editable) {

        }
    }

    private val mySeekBarChangeListener = object : SeekBar.OnSeekBarChangeListener {

        override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
            if (flagSeekBars == 0) {
                val progR = seekR.progress
                val progB = seekB.progress
                val progG = seekG.progress

                val color = Color.rgb(progR, progG, progB)

                mainLayout.setBackgroundColor(color)
                flagHex = 1
                flagRgb = 1
                setHexEditTextByColor(color)
                setRgbByColor(color)
            }
            flagSeekBars = 0
        }

        override fun onStartTrackingTouch(seekBar: SeekBar) {

        }

        override fun onStopTrackingTouch(seekBar: SeekBar) {

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editHex = findViewById(R.id.editHex) as EditText
        editR = findViewById(R.id.editR) as EditText
        editG = findViewById(R.id.editG) as EditText
        editB = findViewById(R.id.editB) as EditText
        seekR = findViewById(R.id.seekR) as SeekBar
        seekG = findViewById(R.id.seekG) as SeekBar
        seekB = findViewById(R.id.seekB) as SeekBar
        mainLayout = findViewById(R.id.mainLayout) as RelativeLayout

        editHex.addTextChangedListener(hexTextWatcher)

        editR.addTextChangedListener(rgbTextWatcher)
        editG.addTextChangedListener(rgbTextWatcher)
        editB.addTextChangedListener(rgbTextWatcher)

        seekR.setOnSeekBarChangeListener(mySeekBarChangeListener)
        seekG.setOnSeekBarChangeListener(mySeekBarChangeListener)
        seekB.setOnSeekBarChangeListener(mySeekBarChangeListener)

        flagHex = 0
        flagRgb = 0
        flagSeekBars = 0

    }

    private fun setHexEditTextByColor(color: Int) {
        val hex = String.format("#%02x%02x%02x", Color.red(color), Color.green(color), Color.blue(color))
        editHex.setText(hex)
    }

    private fun setRgbByColor(color: Int) {
        editR.setText(Color.red(color).toString())
        editG.setText(Color.green(color).toString())
        editB.setText(Color.blue(color).toString())
    }

    private fun setSeekBarsByColor(color: Int) {
        seekR.progress = Color.red(color)
        seekG.progress = Color.green(color)
        seekB.progress = Color.blue(color)
    }


}

