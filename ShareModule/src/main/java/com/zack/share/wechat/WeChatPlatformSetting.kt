package com.zack.share.wechat

import com.zack.share.platform.PlatformConfig
import com.zack.share.platform.PlatformSetting

/**
 * author:zack
 * Date:2019/3/26
 * Description:微信分享
 */
abstract class WeChatPlatformSetting(private val appId: String) : PlatformSetting{

  override fun getPlatformConfig(): PlatformConfig {
    return PlatformConfig(appId = appId)
  }

}