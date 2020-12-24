# 开源WebDriverAgent的Java-client

WebDriverAgent（wda）是iOS端一个最著名的UI自动化测试框架，Appium目前使用的xctest-driver也是基于WebDriverAgent来做二次封装和调用的，经常逛testerhome社区发现，其实很多公司也并没有直接用appium来做iOS自动化，而是直接调用WebDriverAgent来实现自己的iOS自动化框架。

最近我也在做iOS的自动化框架，也是基于它来改造的，wda的代码写非常工整，方便阅读，是学习iOS自动化技术的典范，它内部的实现原理，其实比较简单，就是基于xctest，实现一个内部的http服务器，通过http api接口的形式提供外界调用，我用postman整理了一共71个接口，制作了一个文档：[WebDriverAgent Http Api 文档](https://documenter.getpostman.com/view/1837823/TVmMhJNB)，并基于这个api接口，开发了对应的java-client开源调用库，方便大家试用，也欢迎大家试用这个api接口完成Python的调用库，这将非常有价值！

关于对WebDriverAgent的理解和使用，参见我这篇文章：[理解和使用WebDriverAgent](https://testerhome.com/articles/27059)

### maven仓库地址

```xml
todo：maven 仓库 
```

### 用法示例

```java
WDAClient client = new WDAClient("127.0.0.1", 8100);
// 检查wda是否健康
client.health();
client.getSessionApi().healthCheck();
// 创建Session
BaseResponse<CreateSession> res = client.getSessionApi().createSession();
if (res.isSuccess) {
  String sid = res.getValue().getSessionId();
  // 启动应用
	BaseResponse r = this.client.getSessionApi().launchApp(sid, "com.apple.Maps");
  assert r.isSuccess();
}
```

### API列表

假设新建了client对象：

```java
WDAClient client = new WDAClient("127.0.0.1", 8100);
```

**全局Api**

```java
// 获取控件树
String source = client.getPageSource();

// 检查client是否健康
boolean health = client.health();

// 关闭wda client
boolean bl = client.shutdown();
```

**Session会话Api**

```java
// 创建Session
BaseResponse<String> res = client.getSessionApi().createSession();

// 启动应用
BaseResponse res = client.getSessionApi().launchApp("session id", "bundle id");

// 获取应用状态
BaseResponse<Integer> res = client.getSessionApi().getAppState();

// 激活应用（如果引用未启动，则重新启动）
BaseResponse res = client.getSessionApi().activateApp("session id", "bundle id");

// 健康检查（wda会按一次home物理键，来检测手机是否卡主，wda是否正常工作）
BaseResponse res = client.getSessionApi().healthCheck();
```

**Screenshot截图Api**

```java
// 截图（成功的话，返回Base64图片数据）
BaseResponse<String> res = client.getScreenshotApi().screenshot();
```

**Orientation旋转Api**

```java
// 获取当前手机屏幕方向
// 横向 - LANDSCAPE
// 竖向 - PORTRAIT
BaseResponse<String> res = client.getOrientationApi().getOrientation("session id");
```

**Alert对话框Api**

```java
// 获取当前alet对话框的文本，如果失败那就是界面没有alert提示框
BaseResponse<String> res = client.getAlertApi().getAlertText();

// 获取alert对话框的操作按钮的文本列表
BaseResponse<List<String>> res = client.getAlertApi().getAlertButtons("session id");

// 隐藏alert对话框
BaseResponse res = client.getAlertApi().dismiss("session id");

// 点击对话框的某个按钮
BaseResponse res = client.getAlertApi().accept("session id", "alert button name");
```

**FindElement元素查找Api**

```java 
// 查找符合某个条件的所有元素
QueryInfo query = new QueryInfo();
queryInfo.setUsing(QueryUsing.CLASS_NAME);
queryInfo.setValue("XCUIElementTypeStaticText");
BaseResponse<List<Element>> res = client.getFindElementApi().elements("session id", query);

// 查找某个元素下的符合条件的所有子元素
BaseResponse<List<Element>> res = client.getFindElementApi().elements("session id", "target element uuid", query);
```

**Element元素Api**

```java
// 获取手机窗口大小
BaseResponse<WindowSize> res = client.getElementApi().getWindowSize("session id");

// 检查某个元素是否禁用了
BaseResponse<Boolean> res = client.getElementApi().enabled("session id", "element uuid");

// 获取某个元素的大小
BaseResponse<WDARect> res = client.getElementApi().rect("session id", "element uuid");

// 获取某个元素的文本
BaseResponse<String> res = client.getElementApi().text("session id", "element uuid");

// 检查某个元素是否展现了
BaseResponse<Boolean> res = client.getElementApi().displayed("session id", "element uuid");

// 检查某个元素是否被选择了
BaseResponse<Boolean> res = client.getElementApi().selected("session id", "element uuid");

// 获取某个元素的名称
BaseResponse<String> res = client.getElementApi().name("session id", "element uuid");

// 对某个控件截图
BaseResponse<String> res = client.getElementApi().screenshot("session id", "element uuid");

// 控件输入值
BaseResponse res = client.getElementApi().value("session id", "element uuid", "value");

// 点击某个控件
BaseResponse res = client.getElementApi().click("session id", "element uuid");

// 清空输入框
BaseResponse res = client.getElementApi().clear("session id", "element uuid");

// 滑动某个控件
// 将一个控件往某个方向滑动
// 包含两个参数：
// direction表示滚动方向，可选值有：up、down、left、right
// velocity表示滚动速度，只建议从50-100，值越大速度越快
String direction = "down";
int velocity = 50;
BaseResponse res = client.getElementApi().swip("session id", "element uuid", direction, velocity);

// 长按一个控件
// 可以设置时长，时间单位为秒
int duration = 3;
BaseResponse res = client.getElementApi().touchAndHold("session id", "element uuid", duration);

// 坐标长按
// 基于屏幕指定位置长按
// x	坐标x值	100	double
// y	坐标y值	200	double
// duration	长按时间，单位秒	0.5	double
BaseResponse res = client.getElementApi().touchAndHoldInCoordinate("session id", "element uuid", x, y, duration);

// 控件拖动
FromToParam param = new FromToParam();
param.setFromX(100);
param.setFromY(100);
param.setToX(500);
param.setToY(500);
param.setDuration(3);
BaseResponse res = client.getElementApi().dragFromToForDuration("session id", "element uuid", param);

// 屏幕拖动(基于全屏幕的坐标拖动)
BaseResponse res = client.getElementApi().dragFromToForDurationInCoordinate("session id", param);

// 拨动滚轮控件
// 找到一个滚动，向上或向下滚动，滚动幅度取值从0.1到0.5，0.1表示一格，最大5格
// 被选定的控件的类型必须为：XCUIElementTypePickerWheel，否则报错。
// {
//    "order": "next",  // 取值为next或者previous，不区分大小写
//    "offset": 0.1 // 滚动幅度取值从0.1到0.5，0.1表示一格，最大5格
// }
BaseResponse res = client.getElementApi().pickWheel("session id", "uuid", "next or pre", 1);

// 输入文本
// 对有输入焦点的控件输入字符串
// 参数：
// 字段名	含义	示例
// value	要输入的字符串数组	["hello world", "dddd"]
// frequency	输入速度，整形，数字越大速度越快	10
// 备注：
// 如果当前界面没有输入焦点的控件，这个操作会等待一段时间直到超时或者有焦点的控件出现
int frequency = 10;
List<String> values = new ArrayList<>();
values.add("hello world");
values.add("test");
BaseResponse res = client.getElementApi().keys("session id", frequency, values);
// 或者
BaseResponse res = client.getElementApi().keys("session id", frequency, ...keys);

// 长按控件
// 支持设定压力和时延，以及控件内的某个位置
// pressure	压力值	0.5
// duration	按压的时间，单位秒	3
// x	该控件内的某个点的x值
// y	该控件内的某个点的y值
int x = 100;
int y = 100;
int duration = 2;
double pressure = 0.5;
BaseResponse res = client.getElementApi().forceTouch("session id", "element uuid", x, y, duration, pressure);

// 坐标双击
BaseResponse res = client.getElementApi().doubleTap("session id", x, y);
```

**Custom自定义Api**

```java
// 重启当前应用
BaseResponse res = client.getCustomApi().deactiveApp("session id");

// 锁屏
BaseResponse res = client.getCustomApi().lock();

// 解锁
BaseResponse res = client.getCustomApi().unlock();

// 判断手机是否锁屏
BaseResponse<Boolean> res = client.getCustomApi().locked();

// 获取当前应用信息
BaseResponse<ActiveAppInfo> res = client.getCustomApi().getActiveAppInfo();

// 获取当前设备信息
BaseResponse<WDADeviceInfo> res = client.getCustomApi().getDeviceInfo();

// 手机回到屏幕主页
BaseResponse res = client.getCustomApi().homeScreen();
```


