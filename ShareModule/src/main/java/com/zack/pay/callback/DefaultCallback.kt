package com.zack.pay.callback

import android.widget.Toast
import com.zack.pay.PayInterface
import com.zack.share.R
import com.zack.share.config.ApplicationContext

/**
 * author:zack
 * Date:2019/6/24
 * Description:默认支付回调
 */
open class DefaultCallback : PayCallback{

  override fun onSuccess(payInterface: PayInterface) {
    Toast.makeText(ApplicationContext.CONTEXT, R.string.str_pay_success,Toast.LENGTH_SHORT).show()
  }

  override fun onFail(error: String) {
    Toast.makeText(ApplicationContext.CONTEXT, R.string.str_pay_fail,Toast.LENGTH_SHORT).show()
  }

  override fun onCancel(payInterface: PayInterface) {
    Toast.makeText(ApplicationContext.CONTEXT, R.string.str_pay_cancel,Toast.LENGTH_SHORT).show()
  }

}