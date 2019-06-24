package com.zack.pay

import android.app.Activity
import android.content.Intent
import com.zack.pay.callback.PayCallback
import com.zack.pay.entity.PayEntity

/**
 * Created by zack on 2019/4/23.
 * 支付的基础方法
 */
interface PayInterface {

    /**
     * 获取平台图标
     */
    fun getIcon():Int

    /**
     * 获取平台名称
     */
    fun getName():String

    /**
     * 支付
     */
    fun pay(activity: Activity, payEntity: PayEntity, callback: PayCallback?)

    /**
     * 处理支付返回结果
     */
    fun handleIntent(requestCode: Int, resultCode: Int, data: Intent?)

}