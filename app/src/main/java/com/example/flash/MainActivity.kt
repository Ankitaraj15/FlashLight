package com.example.flash

import android.content.pm.PackageManager
import android.hardware.camera2.CameraManager
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    var cameraFlash = false
    var flashOn = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val imageButton: ImageButton = findViewById(R.id.imgButton)

        cameraFlash= packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)

        imageButton.setOnClickListener{
            if (cameraFlash){
                if(flashOn){
                    flashOn=false
                    imageButton.setImageResource(R.drawable.off1)

                    flashLightOff()
                }
                else{
                    flashOn = true
                    imageButton.setImageResource(R.drawable.on1)

                    flashLightOn()
                }
            }
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun flashLightOff() {
        val cameraManager: CameraManager = getSystemService(CAMERA_SERVICE) as CameraManager
        var cameraId: String
        try {
            cameraId = cameraManager.cameraIdList[0]
            cameraManager.setTorchMode(cameraId, false)
        }catch(e: Exception){}
    }

    private fun flashLightOn() {
        val cameraManager: CameraManager = getSystemService(CAMERA_SERVICE) as CameraManager
        var cameraId: String
        try {
            cameraId = cameraManager.cameraIdList[0]
            cameraManager.setTorchMode(cameraId, true)
        }catch (e: Exception){}
        //change1
    }
}