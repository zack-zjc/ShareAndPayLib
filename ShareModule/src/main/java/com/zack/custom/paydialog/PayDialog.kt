package com.zack.custom.paydialog

import android.app.Activity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import com.zack.pay.PayInterface
import com.zack.pay.callback.DefaultCallback
import com.zack.pay.callback.PayCallback
import com.zack.pay.entity.PayEntity
import com.zack.share.R
import com.zack.share.config.ApplicationContext
import com.zhl.cbdialog.CBDialogBuilder

/**
 * author:zack
 * Date:2019/3/13
 * Description:分享的dialog
 */
object PayDialog {

  /**
   * 展示分享的dialog
   * 可自定义dialog修改这里
   */
  fun showPayDialog(context: Activity,payEntity: PayEntity,platform: Array<PayInterface>,callback: PayCallback= DefaultCallback()){
    val dialogView = LayoutInflater.from(context).inflate(R.layout.layout_share_dialog_view,null)
    val recyclerView = dialogView.findViewById<RecyclerView>(R.id.id_share_recycler_view)
    recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL,false)
    val adapter = PayListAdapter(context, payEntity, platform, callback)
    recyclerView.adapter = adapter
    val dialog = CBDialogBuilder(context, CBDialogBuilder.DIALOG_STYLE_NORMAL, 1.0f)
        .setTouchOutSideCancelable(true)
        .showIcon(false)
        .setTitle(null)
        .setConfirmButtonText(ApplicationContext.CONTEXT.resources.getString(R.string.str_pay_cancel))
        .setDialoglocation(CBDialogBuilder.DIALOG_LOCATION_BOTTOM)
        .setView(dialogView)
        .setDialogAnimation(CBDialogBuilder.DIALOG_ANIM_SLID_BOTTOM)
        .create()
    adapter.setDialogReference(dialog)
    dialog.show()
  }

}