package fr.ec.app.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.Visibility
import fr.ec.app.R
import fr.ec.app.data.DataProvider
import fr.ec.app.data.api.response.PostResponse

class MainActivity : AppCompatActivity() {

    private val adapter = PostAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        setupInsets()

        val list = findViewById<RecyclerView>(R.id.list_item)
        val progressBar = findViewById<ProgressBar>(R.id.progress_circular)
        list.layoutManager = LinearLayoutManager(this)
        list.adapter = adapter

        progressBar.visibility = View.VISIBLE

        getPosts(onSuccess = { posts ->
            runOnUiThread {
                list.visibility = View.VISIBLE
                progressBar.visibility = View.GONE
                adapter.show(posts)
            }
        })
    }

    private fun getPosts(onSuccess: (List<Post>) -> Unit) {

        DataProvider.getPosts(
            onSuccess = { postResponses ->
                Log.d("MainActivity", "Result: $postResponses")
                val posts = postResponses.map { postResponse ->
                    Post(
                        title = postResponse.name.orEmpty(),
                        subTitle = postResponse.tagline.orEmpty()
                    )

                }
                onSuccess(posts)
            },
            onError = { exception ->
                Log.e("MainActivity", "Error : $exception")
            }
        )
    }


    private fun setupInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}