package com.zack.wxapi

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import com.tencent.mm.opensdk.constants.ConstantsAPI
import com.tencent.mm.opensdk.modelbase.BaseReq
import com.tencent.mm.opensdk.modelbase.BaseResp
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler
import com.zack.pay.wechat.WeChatPayUtil

/**
 * Created by zack on 2019/4/23.
 * 微信支付回调类
 */
open class WXPayEntryBaseActivity : Activity(), IWXAPIEventHandler {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(FrameLayout(this))
        WeChatPayUtil.mApi?.handleIntent(intent, this)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        setIntent(intent)
        WeChatPayUtil.mApi?.handleIntent(intent, this)
    }

    override fun onReq(p0: BaseReq?) {

    }

    /**
     * 微信支付response回调
     */
    override fun onResp(resp: BaseResp?) {
        Log.d("weChat", "onPayFinish, errCode = " + resp?.errCode)
        if (resp?.type == ConstantsAPI.COMMAND_PAY_BY_WX) {
            when {
              resp.errCode == BaseResp.ErrCode.ERR_USER_CANCEL -> WeChatPayUtil.payCallback?.onCancel(WeChatPayUtil)
              resp.errCode == BaseResp.ErrCode.ERR_OK -> WeChatPayUtil.payCallback?.onSuccess(WeChatPayUtil)
              else -> WeChatPayUtil.payCallback?.onFail("支付失败")
            }
        }
        finish()
    }

}