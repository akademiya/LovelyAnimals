package com.gvd.lovelyanimal.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DatabaseReference
import com.gvd.lovelyanimal.R
import com.gvd.lovelyanimal.data.PetModel
import com.gvd.lovelyanimal.module.GlideApp

class MainAdapter(
    private val context: Context,
    private val petPost: ArrayList<PetModel>,
    private val onClickItemMoreInformation: (PetModel) -> Unit,
    private val onDeleteItem: (String) -> Unit,
    private val isEditItem: Boolean
) : RecyclerView.Adapter<MainAdapter.VH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = VH(
        LayoutInflater.from(parent.context).inflate(R.layout.item_main_card_list, parent, false)
    )

    override fun getItemCount(): Int {
        return petPost.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.apply {
            val currentItem = petPost[position]
            currTitle.text = currentItem.petTitle

            GlideApp.with(context)
                .load(currentItem.petPhoto)
                .circleCrop()
                .into(currImg)

            itemView.setOnClickListener { onClickItemMoreInformation(currentItem) }
            deleteItem.setOnClickListener {
                Toast.makeText(context, "Clicked ${currentItem.petTitle}", Toast.LENGTH_SHORT).show()
                onDeleteItem(currentItem.petTitle.toString())
            }
            deleteItem.visibility = if (isEditItem) View.VISIBLE else View.GONE
        }
    }

    class VH(view: View) : RecyclerView.ViewHolder(view) {
        val currTitle = view.findViewById<TextView>(R.id.short_title)
        val currImg = view.findViewById<ImageView>(R.id.main_img_pet)
        val deleteItem = view.findViewById<ImageView>(R.id.delete_item)
    }
}