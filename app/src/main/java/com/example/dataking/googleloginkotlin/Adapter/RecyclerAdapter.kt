package com.example.dataking.googleloginkotlin.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dataking.googleloginkotlin.Model.Video
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.dataking.googleloginkotlin.R

/**
 * Created by dataking on 2017-12-14.
 */
class RecyclerAdapter(var data: List<Video>?, private val itemLayout: Int, private val context: Context) : RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder>() {

    override fun onBindViewHolder(holder: RecyclerViewHolder?, position: Int) {

        var data: Video = data!![position]

        holder!!.tvtitle.text = data.title
        holder.tvlocation.text = data.address
        Glide.with(context).load("https://img.youtube.com/vi/${data.youtubeId}/mqdefault.jpg").into(holder.imageview)


    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerViewHolder {

        val view: View = LayoutInflater.from(parent!!.context).inflate(itemLayout, parent, false)
        return RecyclerViewHolder(view)

    }

    override fun getItemCount(): Int {
        return data!!.size
    }

    inner class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var tvtitle: TextView
        internal var tvlocation: TextView
        //internal var cardView: CardView
        internal var imageview: ImageView

        init {
            tvtitle = itemView.findViewById<TextView>(R.id.list_titlle) as TextView
            tvlocation = itemView.findViewById<TextView>(R.id.list_address) as TextView
            //cardView = itemView.findViewById<CardView>(R.id.cardview) as CardView
            imageview = itemView.findViewById<ImageView>(R.id.list_imageview) as ImageView

        }
    }


}