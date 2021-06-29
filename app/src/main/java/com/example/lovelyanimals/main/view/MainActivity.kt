package com.example.lovelyanimals.main.view

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lovelyanimals.BaseActivity
import com.example.lovelyanimals.FirestoreUtils
import com.example.lovelyanimals.R
import com.example.lovelyanimals.data.PetModel
import com.example.lovelyanimals.main.IMainActivity
import com.example.lovelyanimals.main.MainAdapter
import com.example.lovelyanimals.main.presenter.MainPresenter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.view_main_card_list.*

class MainActivity : BaseActivity(), IMainActivity {
    private lateinit var presenter: MainPresenter
    private lateinit var adapter: MainAdapter
    private val petCollection = FirestoreUtils.allPetDocRef

    override fun init(savedInstanceState: Bundle?) {
        super.setContentView(R.layout.view_main_card_list)
        presenter = MainPresenter(this, application)
//        FirebaseApp.initializeApp(this)

        val sortByTime = petCollection.orderBy("currentDate", Query.Direction.DESCENDING)
        val options = FirestoreRecyclerOptions.Builder<PetModel>()
            .setQuery(sortByTime, PetModel::class.java)
            .build()

        adapter = MainAdapter(this, options) { model ->
            val intent = Intent(this, PetInfoView::class.java)
            intent.putExtra("model", model.pid)
            intent.putExtra("petTitle", model.petTitle)
            intent.putExtra("photo", model.petPhoto)
            startActivity(intent)
        }
        main_pet_list.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        main_pet_list.setHasFixedSize(true)
        main_pet_list.adapter = adapter
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        presenter.bindView(this)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        presenter.unbindView(this)
    }


}