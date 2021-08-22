package com.mobarok.rokomarytest.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mobarok.rokomarytest.R
import com.mobarok.rokomarytest.model.BookModel
import com.mobarok.rokomarytest.util.loadImage


class ExploreAdapter(var books:ArrayList<BookModel>): RecyclerView.Adapter<ExploreAdapter.NewArrivalViewHolder>() {

    fun updateCountries(newBooks:List<BookModel>){
        books.clear();
        books.addAll(newBooks);
        notifyDataSetChanged();
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewArrivalViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_explore, parent, false)

        return NewArrivalViewHolder(view)
    }
    override fun onBindViewHolder(holder: NewArrivalViewHolder, position: Int) {
        holder.imageView.loadImage(books[position].image_path)
        holder.name.text = books[position].name_en
        holder.author.text = books[position].author_name_bn
        holder.price.text = "%.2f".format (books[position].price)
    }

    override fun getItemCount() = books.size

    class  NewArrivalViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.imageView)
        val name: TextView = view.findViewById(R.id.name)
        val author: TextView = view.findViewById(R.id.author)
        val price: TextView = view.findViewById(R.id.price)
    }

}