package com.zack.pay.alipay

import android.app.Activity
import android.content.Intent
import android.os.Handler
import android.os.Looper
import com.alipay.sdk.app.PayTask
import com.zack.pay.PayInterface
import com.zack.pay.callback.PayCallback
import com.zack.pay.entity.PayEntity
import com.zack.share.R
import com.zack.share.config.ApplicationContext

/**
 * Created by zack on 2019/4/23.
 * 支付宝支付
 * 支付结果{
    "memo" : "xxxxx",
    "result" : "{
    \"alipay_trade_app_pay_response\":{
    \"code\":\"10000\",
    \"msg\":\"Success\",
    \"app_id\":\"2014072300007148\",
    \"out_trade_no\":\"081622560194853\",
    \"trade_no\":\"2016081621001004400236957647\",
    \"total_amount\":\"0.01\",
    \"seller_id\":\"2088702849871851\",
    \"charset\":\"utf-8\",
    \"timestamp\":\"2016-10-11 17:43:36\"
    },
    \"sign\":\"NGfStJf3i3ooWBuCDIQSumOpaGBcQz+aoAqyGh3W6EqA/gmyPYwLJ2REFijY9XPTApI9YglZyMw+ZMhd3kb0mh4R
    AXMrb6mekX4Zu8Nf6geOwIa9kLOnw0IMCjxi4abDIfXhxrXyj********\",
    \"sign_type\":\"RSA2\"
    }",
    "resultStatus" : "9000"
    }
 *错误码
 *   9000	订单支付成功
 *   8000	正在处理中，支付结果未知（有可能已经支付成功），请查询商户订单列表中订单的支付状态
 *   4000	订单支付失败
 *   5000	重复请求
 *   6001	用户中途取消
 *   6002	网络连接出错
 *   6004	支付结果未知（有可能已经支付成功），请查询商户订单列表中订单的支付状态
其它	其它支付错误
 */
object AlipayUtil : PayInterface {

    private val mHandler = Handler(Looper.getMainLooper())

    override fun getIcon(): Int = R.mipmap.icon_pay_alipay

    override fun getName(): String = ApplicationContext.CONTEXT.resources.getString(R.string.str_share_alipay)

    /**
     * PayTask处理需要在线程中执行
     */
    override fun pay(activity: Activity, payEntity: PayEntity, callback: PayCallback?) {
        val payInfo = payEntity.payInfo.toString()
        val payRunnable = Runnable {
            val payTask = PayTask(activity)
            val result = payTask.payV2(payInfo,true)
            mHandler.post { processResult(result,callback) }
        }
        val payThread = Thread(payRunnable)
        payThread.start()
    }

    /**
     * 处理支付结果
     */
    private fun processResult(result:Map<String, String>,callback: PayCallback?){
        val resultStatus = result["resultStatus"].toString()
        when (resultStatus) {
          "9000" -> callback?.onSuccess(this)
          "4000" -> callback?.onFail("订单支付失败")
          "8000" -> callback?.onFail("正在处理中")
          "5000" -> callback?.onFail("重复请求")
          "6001" -> callback?.onFail("用户中途取消")
          "6002" -> callback?.onFail("网络连接出错")
          "6004" -> callback?.onFail("支付结果未知")
          else -> callback?.onFail("未知错误")
        }
    }

    override fun handleIntent(requestCode: Int, resultCode: Int, data: Intent?) {

    }

}