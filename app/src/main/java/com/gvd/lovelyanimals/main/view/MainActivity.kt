package com.gvd.lovelyanimals.main.view

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.Query
import com.gvd.lovelyanimals.BaseActivity
import com.gvd.lovelyanimals.FirestoreUtils
import com.gvd.lovelyanimals.R
import com.gvd.lovelyanimals.data.PetModel
import com.gvd.lovelyanimals.main.IMainActivity
import com.gvd.lovelyanimals.main.MainAdapter
import com.gvd.lovelyanimals.main.presenter.MainPresenter
import kotlinx.android.synthetic.main.view_main_card_list.*

class MainActivity : BaseActivity(), IMainActivity {
    private lateinit var presenter: MainPresenter
    private lateinit var adapter: MainAdapter
    private val petCollection = FirestoreUtils.allPetDocRef

    override fun init(savedInstanceState: Bundle?) {
        super.setContentView(R.layout.view_main_card_list)
        presenter = MainPresenter(this, application)

        fab.hide()
        var counter = 0F
        title_list_pets.setOnClickListener {
            counter++
            presenter.clickByTitle7(counter = counter)
        }

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


    override fun visibilityFabButton(isVisible: Boolean) {
        if (isVisible) {
            fab.show()
        } else fab.hide()
    }
}