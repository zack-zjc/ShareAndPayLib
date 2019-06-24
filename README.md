# ShareAndPayLib

## 功能说明
包含第三方分享和支付的集成代码，支持扩展分享平台和扩展支付平台，分享支持样式为图文模式，必须包含图片(本地路径)和分享网址

分享平台:qq好友,qq空间,微信好友,微信朋友圈,微博

支付平台:微信,支付宝

## Gradle

[![](https://jitpack.io/v/zack-zjc/ShareAndPayLib.svg)](https://jitpack.io/#zack-zjc/ShareAndPayLib)

```groovy
allprojects {
    repositories {
        jcenter()
        maven { url "https://jitpack.io" }
    }
}
```	

Step 2. 在你的module的build.gradle文件中增加RichEditText依赖。

```groovy
implementation 'com.github.zack-zjc:ShareAndPayLib:1.0.0'
```


## 使用说明

分享：

注册使用：
```groovy
	ShareLib.initPlatform(application, arrayListOf(WeChatFriendPlatformSetting("你注册的appid"),WeChatScenePlatformSetting("你注册的appid"),
        QQFriendPlatformSetting("你注册的appid"),QQSpacePlatformSetting("你注册的appid"),
        WeiBoPlatformSetting("你注册的appKey","你注册的appRedirect","你注册的scope")))
```	

原生界面调用：
```groovy
	 ShareUtil.share("activity引用", "标题","说明","图片路径","网址","callback")
```	

原生界面调用Activity：
```groovy
	override fun onActivityResult(requestCode: Int,resultCode: Int,data: Intent?) {
		super.onActivityResult(requestCode, resultCode, data)
		ShareUtil.handleIntent(requestCode,resultCode,data)
	}
```	


支付使用：

注册使用：
```groovy
	PayLib.initPayLib(application,AlipayUtil,WeChatPayUtil)
```	

原生界面调用：
```groovy
	PayUtil.showPay("activity引用", "PayEntity对象","callback")
```	

原生界面调用Activity：
```groovy
	override fun onActivityResult(requestCode: Int,resultCode: Int,data: Intent?) {
		super.onActivityResult(requestCode, resultCode, data)
		PayUtil.handleIntent(requestCode,resultCode,data)
	}
```	

在AndroidManifest.xml中需要注册，继承实现WXEntryBaseActivity，WXPayEntryBaseActivity注册在对应的包名.wxapi下


```groovy

    <!--腾讯分享需要注册 -->
    <activity
        android:name="com.tencent.tauth.AuthActivity"
        android:noHistory="true"
        android:launchMode="singleTask" >
      <intent-filter>
        <action android:name="android.intent.action.VIEW" />
        <category android:name="android.intent.category.DEFAULT" />
        <category android:name="android.intent.category.BROWSABLE" />
        <data android:scheme="${你对应的id}" />
      </intent-filter>
    </activity>

    <!--微信分享支付回调需要注册 -->
    <activity
        android:configChanges="orientation|keyboardHidden|screenSize|smallestScreenSize|screenLayout"
        android:exported="true"
        android:launchMode="singleTop"
        android:name="com.zack.wxapi.WXEntryBaseActivity"/>

    <activity
        android:configChanges="orientation|keyboardHidden|screenSize|smallestScreenSize|screenLayout"
        android:exported="true"
        android:launchMode="singleTop"
        android:name="com.zack.wxapi.WXPayEntryBaseActivity"/>
		
```	


## 扩展说明

分享：实现PlatformSetting接口，并在注册时ShareLib.initPlatform添加该平台即可

支付：实现PayInterface接口，并在注册时PayLib.initPayLib添加该平台即可





