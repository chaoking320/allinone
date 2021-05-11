1. Make sure that your main class is in a root package above other classes. public static void main(String[] args) { SpringApplication.run(AllinoneApplication.class, args); }
2. MBassador bus 能够根据发送的消息类型进行监听，比如，发出来String类型的由String类型监听处理，Integer类型的由Integer类型监听处理 主要用到了反射机制，将handler对应的方法（包括方法的参数‘类型’信息）放到内存中，发出了某个消息后反射到该方法去执行
3. 自动加载properties文件中的信息：
@ConfigurationProperties(prefix = "connection") 需要引用包：
	<dependency>
			<groupId> org.springframework.boot </groupId>
			<artifactId> spring-boot-configuration-processor </artifactId>
			<optional> true </optional>
		</dependency>
4. Thymeleaf的使用 模板引擎，因为一般spring boot为jar类型，需要该模板引擎来实现
    需要注意Controller和@RestController的区别，后者无法返回到具体页面，而前者可以
5. @EnableWebMvc接管mvc
   WebMvcConfigurerAdapter
6. LocalResolver
7. resources中增加META-INF文件夹，其中添加spring.factories文件添加配置以下配置，用以初始化时加载该配置项
    org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
    chaoking.java.allinone.utils.common.JavaBeanConfiguration
8. 演示`Future`的用法
9. 学习Nio（client 以及 server）
10. @GetMapping 如果一个Controller里面想包含两个get方法需要在getmapping中添加value值，否则编译异常
11. BaseController中有httpclient原始的http调用，且有相应的失败和成功回调逻辑
12. Netty实例 （simpleNetty）
13. 函数式接口编程参考 GreetingService

tomcat：C:\Program Files\Java\apache-tomcat-8.5.6\webapps
nginx：C:\Program Files\Java\nginx-1.12.2\html

idea  spring boot  更改默认端口 application.properties 文件，相应配置：server.port=8181
1、刚开始MyHttpClient类放在了utils目录下，加上Component注解也无法识别，原因是目录问题，将其放在跟controller同一目录下即可，
   或者可以在demoapplication中添加注解，具体参见google/baidu
   具体报错信息如下：“Consider defining a bean of type 'xxx' in your configuration”
2、启动nginx的方法：
    1）直接双击nginx.exe，双击后一个黑色的弹窗一闪而过。
    2）打开cmd命令窗口，切换到nginx目录下，输入命令 nginx.exe 或者 start nginx ，回车即可。
   nginx的配置文件：conf/nginx.conf
3、跨域问题终于搞定，采用jsonp的方式处理
    服务端代码：
    @RequestMapping(value = "/callback4jsonp", method = RequestMethod.GET)
    @ResponseBody
    public String callback4Jsonp(HttpServletRequest request) throws IOException {
        String callback = request.getParameter("callback");
        Long orderId = Long.parseLong(request.getParameter("orderId"));
        String msg ="({\"userId\":\"E540365921\"})";
        return callback+"("+msg+")";
    }
    客户端代码：
    function dotest4jsonp() {
        $.ajax({
            type: "GET", //Post传参
            url: "http://localhost:8181/test/callback4jsonp",//服务地址
            data: "x=x&orderId=1",//参数
            dataType: "jsonp",
            jsonp: "callback",//传递给请求处理程序或页面的，用以获得jsonp回调函数名的参数名(一般默认为:callback)
            jsonpCallback:"testcallback",//自定义的jsonp回调函数名称，默认为jQuery自动生成的随机函数名，也可以写"?"，jQuery会自动为你处理数据
            //contentType: "application/json;charset=utf-8",
            success: function (result) {
                successFun(result);
            },
            error: function (jqXHR, error, errorThrown) {
                errorFun(jqXHR, error, errorThrown);
            }
        })
    }
    // callback的方法
    function testcallback(json){
        alert(json.userId);
    }

