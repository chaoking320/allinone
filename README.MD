1、Make sure that your main class is in a root package above other classes. public static void main(String[] args) { SpringApplication.run(AllinoneApplication.class, args); }

2、MBassador bus 能够根据发送的消息类型进行监听，比如，发出来String类型的由String类型监听处理，Integer类型的由Integer类型监听处理 主要用到了反射机制，将handler对应的方法（包括方法的参数‘类型’信息）放到内存中，发出了某个消息后反射到该方法去执行

3、自动加载properties文件中的信息：
@ConfigurationProperties(prefix = "connection") 需要引用包：
	<dependency>
			<groupId> org.springframework.boot </groupId>
			<artifactId> spring-boot-configuration-processor </artifactId>
			<optional> true </optional>
		</dependency>

4、Thymeleaf的使用 模板引擎，因为一般spring boot为jar类型，需要该模板引擎来实现
    需要注意Controller和@RestController的区别，后者无法返回到具体页面，而前者可以

5、@EnableWebMvc接管mvc
   WebMvcConfigurerAdapter

 6、LocalResolver