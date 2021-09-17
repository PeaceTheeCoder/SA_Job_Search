package com.theecoder.sajobsearch.ui.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.theecoder.arkwork.data.api.repository.NetworkState
import com.theecoder.arkwork.data.vo.Post
import com.theecoder.sajobsearch.R
import com.theecoder.sajobsearch.ui.postDetail.DetailsActivity


open class postsAdapter (open val context: Context): PagedListAdapter<Post, RecyclerView.ViewHolder>(PostDiffCallback()){

    val POST_VIEW_TYPE =1
    val NETWORK_VIEW_TYPE =2

     var nnetworkState : NetworkState? = null

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == POST_VIEW_TYPE ){
            (holder as PostItemViewHolder).bind(getItem(position), context)
        }else{
            (holder as NetworkStateItemViewHolder).bind(nnetworkState)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view: View
        if (viewType == POST_VIEW_TYPE){
            view = layoutInflater.inflate(R.layout.searches,parent,false)
            return PostItemViewHolder(view)
        }else
        {
            view = layoutInflater.inflate(R.layout.network_state_item,parent,false)
            return NetworkStateItemViewHolder(view)
        }

    }

    class PostDiffCallback : DiffUtil.ItemCallback<Post>(){

        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem == newItem
        }

    }

    private fun hasExtraRow():Boolean{
        return nnetworkState != null && nnetworkState != NetworkState.LOADED
    }

    override fun getItemCount(): Int {
        return super.getItemCount()+ if(hasExtraRow()) 1 else 0
    }

    override fun getItemViewType(position: Int): Int {
        return if(hasExtraRow() && position == itemCount-1) NETWORK_VIEW_TYPE else POST_VIEW_TYPE
    }

    open class PostItemViewHolder(view: View) :RecyclerView.ViewHolder(view){

        fun bind(post: Post?, context: Context){
            itemView.findViewById<TextView>(R.id.job_title).text = post?.title
            itemView.findViewById<TextView>(R.id.job_date).text = post?.created
            itemView.findViewById<TextView>(R.id.job_location).text = post?.location

            itemView.setOnClickListener{
                val i: Intent = Intent(context, DetailsActivity::class.java)
                i.putExtra("postID",post?.id)
                context.startActivity(i)
            }
        }
    }
    open class NetworkStateItemViewHolder(view: View) : RecyclerView.ViewHolder(view){

        fun bind(networkState: NetworkState?){
            if (networkState !=null && networkState == NetworkState.LOADING){
                itemView.findViewById<ProgressBar>(R.id.progressBar_item).visibility = View.VISIBLE
            }else{
                itemView.findViewById<ProgressBar>(R.id.progressBar_item).visibility = View.GONE
            }

            if (networkState !=null && networkState == NetworkState.ERROR){
                itemView.findViewById<TextView>(R.id.errorText_item).visibility = View.VISIBLE
                itemView.findViewById<TextView>(R.id.errorText_item).text = networkState.msg

            }else if (networkState !=null && networkState == NetworkState.EOL){

                itemView.findViewById<TextView>(R.id.errorText_item).visibility = View.VISIBLE
                itemView.findViewById<TextView>(R.id.errorText_item).text = networkState.msg
            }else{
                itemView.findViewById<TextView>(R.id.errorText_item).visibility = View.GONE
            }
        }
    }

    fun setNetworkState(newNetworkState: NetworkState){
        val previousState : NetworkState? = this.nnetworkState
        val hadExtraRow: Boolean = hasExtraRow()

        this.nnetworkState = newNetworkState
        val hasExtraRow: Boolean = hasExtraRow()

        if(hadExtraRow != hasExtraRow){
            if(hadExtraRow){
                notifyItemRemoved(super.getItemCount())
            }else{
                notifyItemInserted(super.getItemCount())
            }
        }else if (hasExtraRow && previousState != newNetworkState){
            notifyItemChanged(itemCount-1)
        }
    }

}