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
import com.example.dataking.googleloginkotlin.ListActivity
import com.example.dataking.googleloginkotlin.R


/**
 * Created by dataking on 2017-12-14.
 */
class RecyclerAdapter(video: List<Video>, list_item: Int, listActivity: ListActivity) : RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder>() {

    var itemLayout: Int = 0
    var datas: List<Video>? = null
    var context: Context? = null

    fun RecyclerAdapter(datas: List<Video>, itemLayout: Int, context: Context) {
        this.itemLayout = itemLayout
        this.datas = datas
        this.context = context
    }


    override fun onBindViewHolder(holder: RecyclerViewHolder?, position: Int) {

        var data: Video = datas!!.get(position)

        holder!!.tvtitle.setText(data.title)
        holder!!.tvlocation.setText(data.address)
        Glide.with(context).load("https://img.youtube.com/vi/${data.youtubeId}/mqdefault.jpg").into(holder!!.imageview)


    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerViewHolder {

        var view: View = LayoutInflater.from(parent!!.context).inflate(itemLayout, parent, false)
        var rv: RecyclerViewHolder = RecyclerViewHolder(view)

        return rv

    }

    override fun getItemCount(): Int {
        return datas!!.size
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