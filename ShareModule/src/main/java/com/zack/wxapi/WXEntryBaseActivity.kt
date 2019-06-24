package com.zack.wxapi

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import com.tencent.mm.opensdk.modelbase.BaseReq
import com.tencent.mm.opensdk.modelbase.BaseResp
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler
import com.zack.share.wechat.WeChatFriendUtil
import com.zack.share.wechat.WeChatSceneUtil
import com.zack.share.wechat.WeChatUtil

/**
 * Created by zack on 2019/4/24.
 * 微信返回处理
 */
open class WXEntryBaseActivity : Activity(), IWXAPIEventHandler {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(FrameLayout(this))
        WeChatUtil.instance.getIWXAPI()?.handleIntent(intent, this)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        setIntent(intent)
        WeChatUtil.instance.getIWXAPI()?.handleIntent(intent, this)
    }

    override fun onReq(p0: BaseReq?) {

    }

    /**
     * 微信支付response回调
     */
    override fun onResp(resp: BaseResp?) {
        Log.d("weChat", "onWeiXinFinish, errCode = " + resp?.errCode)
        if (resp is SendMessageToWX.Resp){
            when(resp.errCode){
                BaseResp.ErrCode.ERR_OK -> {
                    WeChatSceneUtil.shareCallback?.onSuccess(WeChatSceneUtil.instance)
                    WeChatFriendUtil.shareCallback?.onSuccess(WeChatSceneUtil.instance)
                    WeChatSceneUtil.shareCallback = null
                    WeChatFriendUtil.shareCallback = null
                }
                BaseResp.ErrCode.ERR_USER_CANCEL ->{
                    WeChatSceneUtil.shareCallback?.onUserCancel(WeChatSceneUtil.instance)
                    WeChatFriendUtil.shareCallback?.onUserCancel(WeChatSceneUtil.instance)
                    WeChatSceneUtil.shareCallback = null
                    WeChatFriendUtil.shareCallback = null
                }
                else ->{
                    WeChatSceneUtil.shareCallback?.onFail(WeChatSceneUtil.instance)
                    WeChatFriendUtil.shareCallback?.onFail(WeChatSceneUtil.instance)
                    WeChatSceneUtil.shareCallback = null
                    WeChatFriendUtil.shareCallback = null
                }
            }
        }
        finish()
    }

}