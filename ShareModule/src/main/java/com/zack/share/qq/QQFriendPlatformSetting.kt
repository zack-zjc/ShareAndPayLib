package com.zack.share.qq

import com.zack.share.R
import com.zack.share.platform.SharePlatformInterface
import com.zack.share.config.ApplicationContext

/**
 * author:zack
 * Date:2019/3/26
 * Description:qq好友分享
 */
open class QQFriendPlatformSetting(appId: String) : QQPlatformSetting(appId){

  override fun getPlatformLogo(): Int {
    return R.mipmap.icon_share_qq
  }

  override fun getPlatformName(): String {
    return ApplicationContext.CONTEXT.resources.getString(R.string.str_share_qq)
  }

  override fun getSharePlatform(): SharePlatformInterface {
    return TencentFriendUtil.instance
  }

}