package com.mobarok.rokomarytest.view

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.button.MaterialButton
import com.mobarok.rokomarytest.R
import com.mobarok.rokomarytest.model.BookModel
import com.mobarok.rokomarytest.util.loadImage

class BookDetails : AppCompatActivity() {
    lateinit var name: TextView
    lateinit var details: TextView
    lateinit var price: TextView
    lateinit var author: TextView
    lateinit var image: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val book :BookModel = intent.getSerializableExtra("book") as BookModel

        setContentView(R.layout.activity_book_details)
        name = findViewById(R.id.name)
        price = findViewById(R.id.price)
        details = findViewById(R.id.description)
        author = findViewById(R.id.author)
        image = findViewById(R.id.imageView)

        name.text = book.name_en;
        price.text = "%.2".format(book.price);
        details.text = book.summary;
        author.text = book.author_name_bn;
        image.loadImage(book.image_path);
    }
}