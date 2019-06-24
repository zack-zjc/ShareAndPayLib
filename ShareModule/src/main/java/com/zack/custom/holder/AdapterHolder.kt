package com.zack.custom.holder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.zack.share.R

/**
 * author:zack
 * Date:2019/3/14
 * Description:null
 */
class AdapterHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

  val imageView = itemView.findViewById<ImageView>(R.id.id_share_image_view)

  val textView = itemView.findViewById<TextView>(R.id.id_share_text_view)

}