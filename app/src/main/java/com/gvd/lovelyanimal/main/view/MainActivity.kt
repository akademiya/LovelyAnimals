package com.gvd.lovelyanimal.main.view

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.gvd.lovelyanimal.BaseActivity
import com.gvd.lovelyanimal.R
import com.gvd.lovelyanimal.data.PetModel
import com.gvd.lovelyanimal.main.IMainActivity
import com.gvd.lovelyanimal.main.MainAdapter
import com.gvd.lovelyanimal.main.presenter.MainPresenter
import kotlinx.android.synthetic.main.dialog_item_create_new_post.*
import kotlinx.android.synthetic.main.nav_header_main.*
import kotlinx.android.synthetic.main.view_main_card_list.*
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : BaseActivity(), IMainActivity {
    private lateinit var presenter: MainPresenter
    private lateinit var adapter: MainAdapter
    private lateinit var petList: ArrayList<PetModel>
    private val PICK_IMAGE_MULTIPLE = 1
    private var petImgPath: String = ""
    var imagesPathList: MutableList<String> = arrayListOf()
    var imagePath: String? = ""
    private lateinit var editPetPhoto: ByteArray
    private lateinit var iv: ImageSwitcher
    private lateinit var ref: DatabaseReference
    private lateinit var listUri: ArrayList<Uri>
    private var position = 0



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
        petList = arrayListOf()
        listUri = arrayListOf()
        main_pet_list.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        main_pet_list.setHasFixedSize(true)

        start_pet_img.animate().apply {
            duration = 2000
            alpha(1f)
            rotationYBy(540f)
        }.start()
        start.animate().apply {
            duration = 2000
            alpha(1f)
            rotationYBy(540f)
        }.start()

        start.setOnClickListener {
            start_screen.visibility = View.GONE
            ref.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        if (petList.size > 0) petList.clear()
                        for (postSnapshot in snapshot.children) {
                            val pet = postSnapshot.getValue(PetModel::class.java)
                            petList.add(pet!!)
                        }
                    main_pet_list.adapter = MainAdapter(this@MainActivity, petList) { model ->
                        val intent = Intent(this@MainActivity, PetInfoView::class.java)
                        intent.putExtra("petID", model.pid)
                        intent.putExtra("petTitle", model.petTitle)
                        intent.putExtra("petDescription", model.petDescription)
                        intent.putExtra("photo", model.petPhoto)
                        startActivity(intent)
                    }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(this@MainActivity, "error: $error", Toast.LENGTH_SHORT).show()
                }
            })
        }

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
        var uploadPhoto = subView.findViewById<ImageSwitcher>(R.id.img_pet)
        val btnNext = subView.findViewById<ImageView>(R.id.btn_next)
        val btnBack = subView.findViewById<ImageView>(R.id.btn_back)


        iv = uploadPhoto

        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.add_new_post)
        builder.setView(subView)
        builder.create()
        selectPhoto.setOnClickListener { presenter.onSelectImage() }

        uploadPhoto.setFactory { ImageView(applicationContext) }

        btnNext.setOnClickListener {
            if (position < listUri.size - 1) {
                position++
                iv.setImageURI(listUri[position])
            } else Toast.makeText(this@MainActivity, "Last Image Already Shown", Toast.LENGTH_SHORT).show();
        }
        btnBack.setOnClickListener {
            if (position > 0) {
                position--
                iv.setImageURI(listUri[position])
            }
        }

        builder.setPositiveButton(R.string.add_post) { _, _ ->
            val title = titleField.text.toString()
            val description = descriptionFiled.text.toString()

            if (title.isEmpty() || description.isEmpty()) {
                Toast.makeText(this, R.string.something_wrong, Toast.LENGTH_SHORT).show()
            } else {
                val petID = ref.push().key
                val petDB = PetModel(title, description)
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

                val formatter = SimpleDateFormat("yyyy.MM.dd", Locale.getDefault())
                val fileName = formatter.format(Date())
                val imageFolder = FirebaseStorage.getInstance().getReference("images/$fileName")
//                storageReference.putFile(listUri).addOnSuccessListener {
                    
//                }
                var uploads = 0
                while (uploads < listUri.size) {
                    val image: Uri = listUri[uploads]
                    val imagename: StorageReference = imageFolder.child("image/" + image.lastPathSegment)
                    imagename.putFile(listUri[uploads]).addOnSuccessListener {
                        imagename.downloadUrl.addOnSuccessListener { uri ->
                            val url = uri.toString()
                            val hashMap = HashMap<String, String>()
                            hashMap["link"] = url
                            ref.push().setValue(hashMap).addOnCompleteListener {
                                Toast.makeText(this, "Good!", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                    uploads++
                }
            }

        }

        builder.setNegativeButton(R.string.cancel) { _, _ ->
            Toast.makeText(this, R.string.task_cancelled, Toast.LENGTH_SHORT).show()
        }
        builder.show()
    }

    override fun onSelectImageFromGallery(intent: Intent, flag: Boolean) {
        if (intent.resolveActivity(packageManager) != null) {
            startActivityForResult(Intent.createChooser(intent, "Select picture"), PICK_IMAGE_MULTIPLE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_MULTIPLE && resultCode == Activity.RESULT_OK && data != null) {

            if (data.clipData != null) {
                val count = data.clipData?.itemCount
                for (i in 0 until count!!) {
                    val imageUri: Uri = data.clipData!!.getItemAt(i).uri
                    listUri.add(imageUri)
                }
                iv.setImageURI(listUri[0])
                position = 0
            } else {
                imagePath = data.data!!.path
//                listUri.add(imageUri)
                iv.setImageURI(listUri[0])
                position = 0
            }

        }

    }


    private fun getPathFromURI(uri: Uri) {
        var path: String = uri.path!!

        val databaseUri: Uri
        val selection: String?
        val selectionArgs: Array<String>?
        if (path.contains("/document/image:")) { // files selected from "Documents"
            databaseUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            selection = "_id=?"
            selectionArgs = arrayOf(DocumentsContract.getDocumentId(uri).split(":")[1])
        } else { // files selected from all other sources, especially on Samsung devices
            databaseUri = uri
            selection = null
            selectionArgs = null
        }
        try {
            val projection = arrayOf(
                MediaStore.Images.Media.DATA,
                MediaStore.Images.Media._ID,
                MediaStore.Images.Media.ORIENTATION,
                MediaStore.Images.Media.DATE_TAKEN
            )
            val cursor =
                contentResolver.query(databaseUri, projection, selection, selectionArgs, null)
            if (cursor!!.moveToFirst()) {
                val columnIndex = cursor.getColumnIndex(projection[0])
                imagePath = cursor.getString(columnIndex)
                imagesPathList.add(imagePath!!)
            }
            cursor.close()
        } catch (e: Exception) {
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