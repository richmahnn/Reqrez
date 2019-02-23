package com.lubulwa.reqrez.ui.component.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lubulwa.reqrez.R
import com.lubulwa.reqrez.data.ImageService
import com.lubulwa.reqrez.data.model.UserModel
import kotlinx.android.synthetic.main.user_list_item.view.*

/**
 * RecyclerView adapter for handling user data.
 */
class UserAdapter(private val imageService: ImageService) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

  private val data = arrayListOf<UserModel.UserData>()

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): ViewHolder = ViewHolder(LayoutInflater.from(parent.context)
    .inflate(R.layout.user_list_item, parent, false))

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val userData = getItem(position)

    holder.itemView.tv_user_name.text = holder.itemView.context.getString(R.string.user_first_last_name, userData.firstName, userData.lastName)

    // Load profile pic
    imageService.load(userData.imageUrl!!)
      .placeholder(R.mipmap.ic_launcher_round)
      .error(R.mipmap.ic_launcher_round)
      .into(holder.itemView.iv_avatar)

  }

  override fun onViewRecycled(holder: ViewHolder) {
    super.onViewRecycled(holder)
    //Forget view, try to free resources
    Glide.with(holder.itemView.context).clear(holder.itemView.iv_avatar)
    holder.itemView.apply {
      iv_avatar.setImageDrawable(null)
    }

  }

  override fun getItemCount() = data.size

  override fun getItemId(position: Int) = getItem(position).id .hashCode().toLong()

  fun getItem(location: Int) = data[location]

  fun clear() {
    val size = data.size
    if (size > 0) {
      for (i in 0 until size) data.removeAt(0)

      notifyItemRangeRemoved(0, size)
    }
  }

  fun addAll(collection: List<UserModel.UserData>) = data.addAll(collection).apply {
    notifyDataSetChanged()
  }

  inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}
