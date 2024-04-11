package com.example.a2_lab_mso

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

class SecondActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val receivedData = intent.getBooleanExtra("SwitchStatus", false)
        setContentView(R.layout.activity_second)
        logger()

        var newView: CustomView
        newView = CustomView(this, receivedData)
        val combatLayout = findViewById<ConstraintLayout>(R.id.myLayout22)
        combatLayout.addView(newView)
    }
    private fun logger() {
        val receivedData = intent.getBooleanExtra("SwitchStatus", false)
        Log.d("SecondActivity", receivedData.toString())
    }
}

class CustomView : View {
    private val receivedData: Boolean
    constructor(context: Context, receivedData: Boolean) : super(context) {
        this.receivedData = receivedData
    }
    constructor(context: Context, attrs: AttributeSet, defStyle: Int, receivedData: Boolean) : super(context, attrs, defStyle) {
        this.receivedData = receivedData
    }

    constructor(context: Context, attrs: AttributeSet, receivedData: Boolean) : super(context, attrs) {
        this.receivedData = receivedData
    }
    override fun draw(canvas: Canvas) {
        super.draw(canvas)
        val paint = Paint()
        canvas.apply {
            if(receivedData)
            {
                drawColor(Color.RED)
                paint.color = Color.GREEN
                paint.style = Paint.Style.FILL
                val path = Path()
                path.moveTo(100f, 300f)
                path.lineTo(200f, 100f)
                path.lineTo(300f, 300f)
                path.close()
                canvas.drawPath(path, paint)
                paint.color = Color.BLUE
            }
            else
            {
                drawColor(Color.YELLOW)
                paint.color = Color.BLUE
                paint.style = Paint.Style.FILL
                val path = Path()
                path.moveTo(100f, 300f)
                path.lineTo(200f, 100f)
                path.lineTo(300f, 300f)
                path.close()
                canvas.drawPath(path, paint)
                paint.color = Color.GREEN
            }
            drawCircle(500f, 500f, 100f, paint)
            drawRect(100f, 1400f, 500f, 900f, paint)
        }
    }
}