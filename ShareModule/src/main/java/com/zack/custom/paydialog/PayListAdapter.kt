package com.zack.custom.paydialog

import android.app.Activity
import android.app.Dialog
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import com.zack.custom.holder.AdapterHolder
import com.zack.pay.PayInterface
import com.zack.pay.callback.PayCallback
import com.zack.pay.entity.PayEntity
import com.zack.share.R.layout
import java.lang.ref.WeakReference

/**
 * author:zack
 * Date:2019/6/24
 * Description:对应列表的adapter
 */
open class PayListAdapter(private val activity: Activity,private val payEntity: PayEntity,private val platform: Array<PayInterface>,
  private val callback: PayCallback) : RecyclerView.Adapter<AdapterHolder>(),OnClickListener {

  private lateinit var dialogReference: WeakReference<Dialog>

  fun setDialogReference(dialog: Dialog){
    dialogReference = WeakReference(dialog)
  }

  override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): AdapterHolder {
    return AdapterHolder(
        LayoutInflater.from(parent.context).inflate(
            layout.layout_share_item_view
            , parent, false
        )
    )
  }

  override fun getItemCount(): Int {
    return platform.size
  }

  override fun onBindViewHolder(holder: AdapterHolder,position: Int) {
    holder.imageView.setImageResource(platform[position].getIcon())
    holder.textView.text = platform[position].getName()
    holder.itemView.tag = platform[position]
    holder.itemView.setOnClickListener(this)
  }

  override fun onClick(view: View?) {
    val platform = view?.tag
    if (platform is PayInterface){
      platform.pay(activity,payEntity,callback)
      if (dialogReference.get() != null && dialogReference.get() is Dialog){
        (dialogReference.get() as Dialog).dismiss()
      }
    }
  }

}