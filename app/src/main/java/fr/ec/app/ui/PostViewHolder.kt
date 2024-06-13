package fr.ec.app.ui

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.ec.app.R

class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val titleTextView = itemView.findViewById<TextView>(R.id.title)
    private val subTitleTextView = itemView.findViewById<TextView>(R.id.subTitle)
    private val image = itemView.findViewById<ImageView>(R.id.postImage)

    fun bind(post: Post) {
        titleTextView.text = post.title
        subTitleTextView.text = post.subTitle
        Picasso.get().load(post.imageUrl).into(image)
    }
}