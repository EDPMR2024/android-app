package fr.ec.app.ui

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.ec.app.R

class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val titleTextView = itemView.findViewById<TextView>(R.id.title)
    private val subTitleTextView = itemView.findViewById<TextView>(R.id.subTitle)

    fun bind(post : Post) {
        titleTextView.text = post.title
        subTitleTextView.text = post.subTitle

    }

}