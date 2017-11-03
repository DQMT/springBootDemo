
@SpringBootApplication
public class SpringBootDemoApplication {
	@Bean
	public Queue helloQueue() {
		return new Queue("helloQueue");
	}
