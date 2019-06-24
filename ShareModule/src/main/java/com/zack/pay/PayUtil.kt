package com.zack.pay

import android.app.Activity
import android.content.Intent
import com.zack.custom.paydialog.PayDialog
import com.zack.pay.callback.DefaultCallback
import com.zack.pay.callback.PayCallback
import com.zack.pay.config.PayConfig
import com.zack.pay.entity.PayEntity

/**
 * Created by zack on 2019/4/23.
 * 支付管理类
 */
object PayUtil {

    /**
     * 展示支付弹框可自定义
     */
    fun showPay(context: Activity,payEntity: PayEntity,callback: PayCallback = DefaultCallback()){
        PayDialog.showPayDialog(context,payEntity,PayConfig.payList.toTypedArray(),callback)
    }

    /**
     * 处理支付返回
     * 在有支付的页面添加
     */
    fun handleIntent(requestCode: Int, resultCode: Int, data: Intent?){
        for (payInterface in PayConfig.payList){
            payInterface.handleIntent(requestCode, resultCode, data)
        }
    }
}