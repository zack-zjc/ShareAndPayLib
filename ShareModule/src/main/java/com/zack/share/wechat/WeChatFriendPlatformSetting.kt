package com.zack.share.wechat

import com.zack.share.R
import com.zack.share.platform.SharePlatformInterface
import com.zack.share.config.ApplicationContext

/**
 * author:zack
 * Date:2019/3/26
 * Description:微信好友分享设置
 */
open class WeChatFriendPlatformSetting(appId: String) :WeChatPlatformSetting(appId){

  override fun getPlatformLogo(): Int {
    return R.mipmap.icon_share_wechat
  }

  override fun getPlatformName(): String {
    return ApplicationContext.CONTEXT.resources.getString(R.string.str_share_wechat)
  }

  override fun getSharePlatform(): SharePlatformInterface {
    return WeChatFriendUtil.instance
  }

}