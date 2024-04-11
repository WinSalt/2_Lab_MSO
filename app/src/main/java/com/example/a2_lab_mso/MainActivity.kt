package com.example.a2_lab_mso

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.media.Image
import android.os.Bundle
import android.provider.MediaStore
import android.provider.MediaStore.Images
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Switch
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId", "UseSwitchCompatOrMaterialCode", "WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val captureImageView = findViewById<ImageView>(R.id.captureImageView)

        val startForResultActivity = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                val thumbnail: Bitmap? = data?.extras?.get("data") as? Bitmap
                thumbnail?.let {
                    captureImageView.setImageBitmap(thumbnail)
                }
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val buttonDraw = findViewById<Button>(R.id.button2)
        val sw1 = findViewById<Switch>(R.id.switch2)
        val buttonCamera = findViewById<Button>(R.id.button3)
        buttonDraw.setOnClickListener {
            val secondActivityIntent =
                Intent(this, SecondActivity::class.java)
            secondActivityIntent.putExtra("SwitchStatus", sw1.isChecked)
            startActivity(secondActivityIntent)
        }
        buttonCamera.setOnClickListener {
            val camera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startForResultActivity.launch(camera)
        }

    }
}