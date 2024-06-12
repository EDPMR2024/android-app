package fr.ec.app.ui

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import fr.ec.app.R
import fr.ec.app.data.DataProvider
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val swipeRefresh by lazy { findViewById<SwipeRefreshLayout>(R.id.swipeRefresh) }
    private val list by lazy { findViewById<RecyclerView>(R.id.list_item) }
    private val progressBar by lazy { findViewById<ProgressBar>(R.id.progress_circular) }
    private val adapter = PostAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupToolbar()
        setupRecyclerView()

        loadPosts()
        swipeRefresh.setOnRefreshListener {
            onRefresh(swipeRefresh)
        }
    }

    private fun setupToolbar() {
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Product List" // Set
    }

    private fun setupRecyclerView() {
        list.layoutManager = LinearLayoutManager(this)
        list.adapter = adapter
    }

    private fun loadPosts() {
        lifecycleScope.launch {
            progressBar.visibility = View.VISIBLE
            val post = getPosts()
            adapter.show(post)
            list.visibility = View.VISIBLE
            progressBar.visibility = View.GONE
        }
    }

    private fun onRefresh(swipeRefresh: SwipeRefreshLayout) {
        lifecycleScope.launch {
            val post = getPosts()
            adapter.show(post)
            swipeRefresh.isRefreshing = false
        }
    }
    private suspend fun getPosts(): List<Post> {
        val postResponseList = DataProvider.getPosts()
        return postResponseList.map { postResponse ->
            Post(
                title = postResponse.name.orEmpty(),
                subTitle = postResponse.tagline.orEmpty()
            )

        }
    }
}