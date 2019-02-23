package com.lubulwa.reqrez.ui.component.home

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.lubulwa.reqrez.R
import com.lubulwa.reqrez.data.ImageService
import com.lubulwa.reqrez.data.model.UserModel
import com.lubulwa.reqrez.ui.base.BaseActivity
import com.lubulwa.reqrez.ui.component.user.CreateUserActivity
import com.lubulwa.reqrez.utils.Constants
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class HomeActivity : BaseActivity(), HomeContract.View {

    @Inject lateinit var homePresenter: HomePresenter
    @Inject lateinit var imageService: ImageService

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var userAdapter: UserAdapter

    private var currentPage: Int = 1
    private var pageTotal: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupToolbar()
        initSetup()
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
    }

    private fun initSetup() {

        linearLayoutManager = LinearLayoutManager(this)
        userAdapter = UserAdapter(imageService).apply { setHasStableIds(true) }

        // Setup RecyclerView
        recyclerView.apply {
            layoutManager = linearLayoutManager
            adapter = userAdapter
            setHasFixedSize(true)
            setItemViewCacheSize(Constants.DEFAULT_LIMIT_COUNT)
            addOnScrollListener(object : InfiniteScrollListener(){
                override fun loadMore() {
                    if (currentPage < pageTotal) {
                        currentPage++

                        Toast.makeText(this@HomeActivity, getString(R.string.list_load_more_msg), Toast.LENGTH_SHORT).show()
                        homePresenter.fetchUsers(currentPage)
                    }
                }
            })
        }

        fab.setOnClickListener { view ->
            navigateToActivity(CreateUserActivity::class.java)
        }

        homePresenter.fetchUsers(currentPage)

    }

    private fun resetList() {
        userAdapter.clear()
        currentPage = 1
        pageTotal = 0
        homePresenter.fetchUsers(currentPage)
    }

    override fun fetchUsersStarted() {
        progressBar.visibility = View.VISIBLE
    }

    override fun fetchUsersSuccess(userModel: UserModel) {
        progressBar.visibility = View.GONE

        pageTotal = userModel.totalPages!!
        userAdapter.addAll(userModel.userData!!)
    }

    override fun fetchUsersFailed(message: String) {
        progressBar.visibility = View.GONE
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun isActive(): Boolean = !isFinishing

    override fun onResume() {
        super.onResume()

        homePresenter.takeView(this)
    }

    override fun onPause() {
        homePresenter.dropView()

        super.onPause()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        return if (id == R.id.action_refresh) {
            resetList()
            true
        } else super.onOptionsItemSelected(item)

    }

}
