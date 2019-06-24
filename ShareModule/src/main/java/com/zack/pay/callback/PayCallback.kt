package com.zack.pay.callback

import com.zack.pay.PayInterface

/**
 * Created by zack on 2019/4/23.
 * 支付结果回调
 */
interface PayCallback {

    /**
     * 支付成功回调
     */
    fun onSuccess(payInterface: PayInterface)

    /**
     * 支付失败回调
     */
    fun onFail(error:String)

    /**
     * 支付取消
     */
    fun onCancel(payInterface: PayInterface)

}