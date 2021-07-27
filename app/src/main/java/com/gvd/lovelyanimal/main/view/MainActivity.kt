package com.gvd.lovelyanimal.main.view

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.text.TextUtils
import android.view.LayoutInflater
import android.widget.*
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.gvd.lovelyanimal.BaseActivity
import com.gvd.lovelyanimal.R
import com.gvd.lovelyanimal.data.PetModel
import com.gvd.lovelyanimal.main.IMainActivity
import com.gvd.lovelyanimal.main.MainAdapter
import com.gvd.lovelyanimal.main.presenter.MainPresenter
import com.gvd.lovelyanimal.module.GlideApp
import kotlinx.android.synthetic.main.view_main_card_list.*
import java.io.ByteArrayOutputStream
import java.io.IOException

class MainActivity : BaseActivity(), IMainActivity {
    private lateinit var presenter: MainPresenter
    private lateinit var adapter: MainAdapter
    private val GALLERY = 1
    private var petImgPath: String = ""
    private lateinit var editPetPhoto: ByteArray
    private lateinit var iv: ImageView
    private lateinit var ref: DatabaseReference



    override fun init(savedInstanceState: Bundle?) {
        super.setContentView(R.layout.view_main_card_list)
        presenter = MainPresenter(this, application)
        ref = Firebase.database("https://lovely-animal-default-rtdb.europe-west1.firebasedatabase.app").getReference("Post")

        fab.hide()
        var counter = 0F
        title_list_pets.setOnClickListener {
            counter++
            presenter.clickByTitle7(counter = counter)
        }
        fab.setOnClickListener { presenter.createNewPostAnimal() }

//        val sortByTime = petCollection.orderBy("currentDate", Query.Direction.DESCENDING)
//        val options = FirestoreRecyclerOptions.Builder<PetModel>()
//            .setQuery(sortByTime, PetModel::class.java)
//            .build()

//        adapter = MainAdapter(this, options) { model ->
//            val intent = Intent(this, PetInfoView::class.java)
//            intent.putExtra("model", model.pid)
//            intent.putExtra("petTitle", model.petTitle)
//            intent.putExtra("photo", model.petPhoto)
//            startActivity(intent)
//        }
//        main_pet_list.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
//        main_pet_list.setHasFixedSize(true)
//        main_pet_list.adapter = adapter
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        presenter.bindView(this)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        presenter.unbindView(this)
    }


    override fun visibilityFabButton(isVisible: Boolean) {
        if (isVisible) {
            fab.show()
        } else fab.hide()
    }

    override fun showDialogToCreateNewPost() {
        val inflater = LayoutInflater.from(this)
        val subView = inflater.inflate(R.layout.dialog_item_create_new_post, null)

        val titleField = subView.findViewById<EditText>(R.id.create_post_title)
        val descriptionFiled = subView.findViewById<EditText>(R.id.create_post_description)
        val selectPhoto = subView.findViewById<Button>(R.id.btn_select_photo)
        var uploadPhoto = subView.findViewById<ImageView>(R.id.img_pet)
        iv = uploadPhoto

        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.add_new_post)
        builder.setView(subView)
        builder.create()
        selectPhoto.setOnClickListener { presenter.onSelectImage() }

        builder.setPositiveButton(R.string.add_post) { _, _ ->
            val title = titleField.text.toString()
            val description = descriptionFiled.text.toString()

            if (title.isEmpty() || description.isEmpty()) {
                Toast.makeText(this, R.string.something_wrong, Toast.LENGTH_SHORT).show()
            } else {
                uploadPhoto = iv
                val petID = ref.push().key
                val petDB = PetModel(title, description, petImgPath)
                petID?.let {
                    ref.child(it)
                        .setValue(petDB)
                        .addOnSuccessListener {
                            Toast.makeText(this, "date successfully added", Toast.LENGTH_SHORT).show()
                        }
                        .addOnFailureListener {
                            Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show()
                        }
                }

            }

        }

        builder.setNegativeButton(R.string.cancel) { _, _ ->
            Toast.makeText(this, R.string.task_cancelled, Toast.LENGTH_SHORT).show()
        }
        builder.show()
    }

    override fun onSelectImageFromGallery(intent: Intent) {
        if (intent.resolveActivity(packageManager) != null) {
            startActivityForResult(intent, GALLERY)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GALLERY && resultCode == Activity.RESULT_OK && data != null) {
            val imgURI = data.data
            try {
                val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, imgURI)
                val bytes = ByteArrayOutputStream()
                bitmap.compress(Bitmap.CompressFormat.WEBP, 90, bytes)
                editPetPhoto = bytes.toByteArray()
                GlideApp.with(this)
                    .load(editPetPhoto)
                    .centerCrop()
                    .into(iv)
                iv.setImageBitmap(bitmap)
            } catch (e: IOException) {
                e.printStackTrace()
                Toast.makeText(this, "Failed!", Toast.LENGTH_SHORT).show()
            }
        }

    }

    /** START SET IMAGE */
//    private fun updateImageRepresentation(src: String) {
//        ImageUtils.removePetPhoto(petImgPath) // FIXME
//        btn_remove_photo.visibility = View.GONE
//        btn_select_photo.isEnabled = true
//        GlideApp.with(baseContext)
//            .load(src)
//            .fallback(resources.getDrawable(R.drawable.ic_nophoto))
//            .error(resources.getDrawable(R.drawable.ic_nophoto))
//            .centerCrop()
//            .into(img_pet)
//    }

}