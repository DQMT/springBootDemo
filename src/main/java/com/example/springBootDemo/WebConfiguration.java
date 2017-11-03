
@Configuration
public class WebConfiguration {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Bean
    public RemoteIpFilter remoteIpFilter() {
        return new RemoteIpFilter();
    }
