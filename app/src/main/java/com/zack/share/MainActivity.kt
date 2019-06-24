package com.zack.share

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.zack.pay.PayLib
import com.zack.pay.PayUtil
import com.zack.pay.alipay.AlipayUtil
import com.zack.pay.entity.PayEntity
import com.zack.pay.wechat.WeChatPayUtil
import com.zack.share.qq.QQFriendPlatformSetting
import com.zack.share.qq.QQSpacePlatformSetting
import com.zack.share.wechat.WeChatFriendPlatformSetting
import com.zack.share.wechat.WeChatScenePlatformSetting
import com.zack.share.weibo.WeiBoPlatformSetting

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    ShareLib.initPlatform(application, arrayListOf(WeChatFriendPlatformSetting("你注册的appid"),WeChatScenePlatformSetting("你注册的appid"),
        QQFriendPlatformSetting("你注册的appid"),QQSpacePlatformSetting("你注册的appid"),
        WeiBoPlatformSetting("你注册的appKey","你注册的appRedirect","你注册的scope")))
    PayLib.initPayLib(application,AlipayUtil,WeChatPayUtil)
    PayUtil.showPay(this, PayEntity())
    ShareUtil.share(this, "标题","说明","图片路径","网址")
  }

  override fun onActivityResult(requestCode: Int,resultCode: Int,data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    ShareUtil.handleIntent(requestCode,resultCode,data)
    PayUtil.handleIntent(requestCode,resultCode,data)
  }
}
