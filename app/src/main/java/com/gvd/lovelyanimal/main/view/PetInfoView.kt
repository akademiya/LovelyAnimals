package com.gvd.lovelyanimal.main.view

import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.Toast
import com.google.firebase.storage.FirebaseStorage
import com.gvd.lovelyanimal.BaseActivity
import com.gvd.lovelyanimal.R
import kotlinx.android.synthetic.main.view_card_info.*
import java.io.File
import java.util.ArrayList

class PetInfoView : BaseActivity() {
    private var position = 0
    private lateinit var listUri: ArrayList<Uri>

    override fun init(savedInstanceState: Bundle?) {
        super.setContentView(R.layout.view_card_info)

        toolbar.setNavigationIcon(R.drawable.ic_back_white)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        val storageRef = FirebaseStorage.getInstance().reference.child("image/")
        val localFile = File.createTempFile("tempImage", "jpg")
        storageRef.getFile(localFile).addOnSuccessListener {
            val bitmap = BitmapFactory.decodeFile(localFile.absolutePath)
            info_img_switch.setImageURI(listUri[0])
        }

        info_title.text = intent.extras?.getString("petTitle")
        info_description.text = intent.extras?.getString("petDescription")

        info_img_switch.setFactory { ImageView(applicationContext) }

        val into = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left)
        info_img_switch?.inAnimation = into

        val out = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right)
        info_img_switch?.outAnimation = out

        info_next_img.setOnClickListener {
            if (position < listUri.size - 1) {
                position++
                info_img_switch.setImageURI(listUri[position])
            } else Toast.makeText(this, "Last Image Already Shown", Toast.LENGTH_SHORT).show();
        }
        info_back_img.setOnClickListener {
            if (position > 0) {
                position--
                info_img_switch.setImageURI(listUri[position])
            }
        }

    }

}