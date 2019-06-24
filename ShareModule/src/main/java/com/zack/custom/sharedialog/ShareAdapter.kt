package com.zack.custom.sharedialog

import android.app.Activity
import android.app.Dialog
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Toast
import com.zack.custom.holder.AdapterHolder
import com.zack.share.R
import com.zack.share.R.layout
import com.zack.share.platform.SharePlatformInterface
import com.zack.share.callback.Callback
import com.zack.share.config.ApplicationContext
import com.zack.share.entity.ShareEntity
import com.zack.share.platform.PlatformSetting
import java.lang.ref.WeakReference

/**
 * author:zack
 * Date:2019/3/14
 * Description:null
 */
class ShareAdapter(private val activity: Activity,private val shareEntity: ShareEntity,private val platform: Array<PlatformSetting>,
  private val callback: Callback): RecyclerView.Adapter<AdapterHolder>() ,OnClickListener{

  private lateinit var dialogReference:WeakReference<Dialog>

  fun setDialogReference(dialog:Dialog){
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
    holder.imageView.setImageResource(platform[position].getPlatformLogo())
    holder.textView.text = platform[position].getPlatformName()
    holder.itemView.tag = platform[position].getSharePlatform()
    holder.itemView.setOnClickListener(this)
  }

  override fun onClick(view: View?) {
    val platform = view?.tag
    if (platform is SharePlatformInterface){
      if (platform.isPlatformInstalled()){
        platform.registerApp(activity)
        platform.share(shareEntity,callback)
      }else{
        Toast.makeText(ApplicationContext.CONTEXT, R.string.str_share_app_miss_error, Toast.LENGTH_SHORT).show()
      }
      if (dialogReference.get() != null && dialogReference.get() is Dialog){
        (dialogReference.get() as Dialog).dismiss()
      }
    }
  }
}