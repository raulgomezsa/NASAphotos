package com.example.rual.nasaphotos.activities

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.rual.nasaphotos.R
import com.example.rual.nasaphotos.model.Photos
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.my_text_view.view.*
import java.text.SimpleDateFormat

class MyAdapter(private val myDataset: Photos) :
        RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    class ViewHolder(val cardView: CardView) : RecyclerView.ViewHolder(cardView)

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ViewHolder {

        val cardView = LayoutInflater.from(parent.context)
                .inflate(R.layout.my_text_view, parent, false) as CardView

        return ViewHolder(cardView)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val format = SimpleDateFormat("dd/MM/yyy")

        if (position%2==1) {
            holder.cardView.setCardBackgroundColor(holder.cardView.context.resources.getColor(R.color.accent_material_light))
        }

        holder.cardView.my_text_view_title_textview.text = format.format(myDataset.photos[position].earth_date)
        holder.cardView.my_text_view_description_textview.text = myDataset.photos[position].camera.full_name

        Picasso.get()
                .load(myDataset.photos[position].img_src)
                .placeholder(R.drawable.placeholder)
                .into(holder.cardView.my_text_view_image_imageview)
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = myDataset.photos.size

}