package com.zack.share

import android.app.Application
import com.zack.share.config.ApplicationContext
import com.zack.share.config.ShareConfig
import com.zack.share.platform.PlatformSetting

/**
 * author:zack
 * Date:2019/3/26
 * Description:分享设置
 */
object ShareLib {

  /**
   * 初始化对应的存在的分享平台
   */
  fun initPlatform(application: Application,configs :List<PlatformSetting>){
    ApplicationContext.CONTEXT = application
    ShareConfig.sharePlatformList.clear()
    ShareConfig.sharePlatformList.addAll(configs)
    for (config in configs){
      config.getSharePlatform().configPlatform(config.getPlatformConfig())
    }
  }

}