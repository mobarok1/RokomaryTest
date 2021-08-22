package com.mobarok.rokomarytest.view

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.mobarok.rokomarytest.R
import com.mobarok.rokomarytest.model.BookModel
import com.mobarok.rokomarytest.util.loadImage


class NewArrivalAdapter(var books:ArrayList<BookModel>,var context:Context): RecyclerView.Adapter<NewArrivalAdapter.NewArrivalViewHolder>() {

    fun updateCountries(newBooks:List<BookModel>){
        books.clear();
        books.addAll(newBooks);
        notifyDataSetChanged();
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewArrivalViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_arrival_book, parent, false)
        context = parent.context
        return NewArrivalViewHolder(view)
    }
    override fun onBindViewHolder(holder: NewArrivalViewHolder, position: Int) {
        holder.imageView.loadImage(books[position].image_path)
        holder.itemView.setOnClickListener{
            val intent = Intent(context, BookDetails::class.java)
            intent.putExtra("book",books[position])
            context.startActivity(intent)
        }
    }


    override fun getItemCount() = books.size

    class  NewArrivalViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.itemViewNewArrival)
    }

}