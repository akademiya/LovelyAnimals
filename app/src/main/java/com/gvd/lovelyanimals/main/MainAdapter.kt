package com.gvd.lovelyanimals.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gvd.lovelyanimals.R
import com.gvd.lovelyanimals.data.PetModel
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import kotlinx.android.synthetic.main.item_main_card_list.view.*

class MainAdapter (private val context: Context,
                   options: FirestoreRecyclerOptions<PetModel>,
                   private val onClickItemMoreInformation: (PetModel) -> Unit) : FirestoreRecyclerAdapter<PetModel, MainAdapter.VH>(options) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = VH(
        LayoutInflater.from(parent.context).inflate(R.layout.item_main_card_list, parent, false)
    )

    override fun onBindViewHolder(holder: VH, position: Int, model: PetModel) {
        holder.apply {
            itemView.main_title_pet.text = model.petTitle
            if (!model.petPhoto.isNullOrBlank()) {
//                GlideApp.with(context)
//                    .load(ImageUtils.pathToReference(model.petPhoto.toString()))
//                    .circleCrop()
//                    .into(itemView.main_img_pet)
            }
            itemView.setOnClickListener { onClickItemMoreInformation(model) }

//            FirestoreUtils.currentUserDocRef.addSnapshotListener { documentSnapshot, _ ->
//                FirestoreUtils.getCurrentUser { owner ->
//                    if (documentSnapshot?.exists()!!) {
//                        this@MainAdapter.owner = owner
//                        itemView.owner_country.text = documentSnapshot.getString(SettingsView.CITY_KEY)
//                        itemView.owner_name.text = documentSnapshot.getString(SettingsView.NAME_KEY)
//                        itemView.owner_email.text = documentSnapshot.getString(SettingsView.EMAIL_KEY)
//                        if (owner.photo != null) {
//                            GlideApp.with(context)
//                                .load(ImageUtils.pathToReference(owner.photo))
//                                .circleCrop()
//                                .into(itemView.img_owner)
//                        }
//                    }
//                }
//            }
        }
    }

    class VH(view: View) : RecyclerView.ViewHolder(view)
}