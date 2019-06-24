package com.zack.pay.config

import com.zack.pay.PayInterface
import com.zack.pay.alipay.AlipayUtil
import com.zack.pay.wechat.WeChatPayUtil

/**
 * Created by zack on 2019/4/23.
 * 支付配置
 */
object PayConfig {

    /**
     * 支付平台
     */
    val payList : ArrayList<PayInterface> = arrayListOf(WeChatPayUtil, AlipayUtil)


}