package com.zack.pay

import android.app.Application
import com.zack.pay.config.PayConfig
import com.zack.share.config.ApplicationContext

/**
 * Created by zack on 2019/4/23.
 * 注册支付平台
 */
object PayLib {

    /**
     * 初始化支付平台
     */
    fun initPayLib(application: Application,vararg payInterface: PayInterface){
        ApplicationContext.CONTEXT = application
        PayConfig.payList.clear()
        PayConfig.payList.addAll(payInterface)
    }

}