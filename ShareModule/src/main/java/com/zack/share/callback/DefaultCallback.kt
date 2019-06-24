package com.zack.share.callback

import android.widget.Toast
import com.zack.share.R
import com.zack.share.config.ApplicationContext
import com.zack.share.platform.SharePlatformInterface

/**
 * author:zack
 * Date:2019/4/9
 * Description:默认回调
 */
class DefaultCallback : Callback {

  override fun onSuccess(platformInterface: SharePlatformInterface) {
    Toast.makeText(ApplicationContext.CONTEXT, R.string.str_share_success,Toast.LENGTH_SHORT).show()
  }

  override fun onFail(platformInterface: SharePlatformInterface) {
    Toast.makeText(ApplicationContext.CONTEXT, R.string.str_share_fail, Toast.LENGTH_SHORT).show()
  }

  override fun onUserCancel(platformInterface: SharePlatformInterface) {
    Toast.makeText(ApplicationContext.CONTEXT, R.string.str_share_cancel,Toast.LENGTH_SHORT).show()
  }

}