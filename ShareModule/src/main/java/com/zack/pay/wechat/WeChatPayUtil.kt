package com.zack.pay.wechat

import android.app.Activity
import android.content.Intent
import com.tencent.mm.opensdk.constants.Build
import com.tencent.mm.opensdk.modelpay.PayReq
import com.zack.pay.PayInterface
import com.zack.pay.callback.PayCallback
import com.zack.pay.entity.PayEntity
import com.zack.share.R
import com.zack.share.config.ApplicationContext
import com.zack.share.wechat.WeChatUtil

/**
 * Created by zack on 2019/4/23.
 * 微信支付
 */
object WeChatPayUtil : PayInterface {

    val mApi = WeChatUtil.instance.getIWXAPI()

    var payCallback : PayCallback? = null

    override fun getIcon(): Int = R.mipmap.icon_pay_weichat

    override fun getName(): String = ApplicationContext.CONTEXT.resources.getString(R.string.str_pay_wechat)

    /**
     * 支付
     */
    override fun pay(activity: Activity, payEntity: PayEntity, callback: PayCallback?) {
        payCallback = callback
        val payInfo = payEntity.payInfo
        if (payInfo is PayReq){
            if (mApi?.wxAppSupportAPI?:0 >= Build.PAY_SUPPORTED_SDK_INT){
                mApi?.sendReq(payInfo)
            }else{
                payCallback?.onFail("微信版本过低!")
            }
        }else{
            payCallback?.onFail("支付信息出错!")
        }
    }

    /**
     * 结果由activity处理
     */
    override fun handleIntent(requestCode: Int, resultCode: Int, data: Intent?) {

    }
}