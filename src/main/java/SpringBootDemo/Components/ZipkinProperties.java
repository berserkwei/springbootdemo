package SpringBootDemo.Components;

import com.github.kristofa.brave.Brave;
import com.github.kristofa.brave.EmptySpanCollectorMetricsHandler;
import com.github.kristofa.brave.Sampler;
import com.github.kristofa.brave.SpanCollector;
import com.github.kristofa.brave.http.DefaultSpanNameProvider;
import static com.github.kristofa.brave.Brave.Builder;
import static com.github.kristofa.brave.http.HttpSpanCollector.Config;
import static com.github.kristofa.brave.http.HttpSpanCollector.create;
import com.github.kristofa.brave.okhttp.BraveOkHttpRequestResponseInterceptor;
import com.github.kristofa.brave.servlet.BraveServletFilter;
import lombok.Data;
import okhttp3.OkHttpClient;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = ZipkinProperties.ZIPKIN_PREFIX)
public class ZipkinProperties {
	public static final String ZIPKIN_PREFIX = "zipkin";

	private String serviceName;

	private String url;

	private int connectTimeout;

	private int readTimeout;

	private int flushInterval;

	private boolean compressionEnabled;
/*
	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getConnectTimeout() {
		return connectTimeout;
	}

	public void setConnectTimeout(int connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	public int getReadTimeout() {
		return readTimeout;
	}

	public void setReadTimeout(int readTimeout) {
		this.readTimeout = readTimeout;
	}

	public int getFlushInterval() {
		return flushInterval;
	}

	public void setFlushInterval(int flushInterval) {
		this.flushInterval = flushInterval;
	}

	public boolean isCompressionEnabled() {
		return compressionEnabled;
	}

	public void setCompressionEnabled(boolean compressionEnabled) {
		this.compressionEnabled = compressionEnabled;
	}
*/
	@Bean
	public SpanCollector spanCollector() {
		System.out.println("###########Propertyes#############");
		System.out.println("Service name: " + serviceName);
		Config config = Config.builder() // 默认false，span在transport之前是否会被gzipped
				.compressionEnabled(compressionEnabled).connectTimeout(connectTimeout).flushInterval(flushInterval)
				.readTimeout(readTimeout).build();
		return create(url, config, new EmptySpanCollectorMetricsHandler());
	}

	@Bean
	public Brave brave(SpanCollector spanCollector) { // 调用服务的名称
		Builder builder = new Builder(serviceName);
		builder.spanCollector(spanCollector);
		// 采集率
		builder.traceSampler(Sampler.ALWAYS_SAMPLE);
		return builder.build();
	}

	@Bean
	public BraveServletFilter braveServletFilter(Brave brave) {
		BraveServletFilter filter = new BraveServletFilter(brave.serverRequestInterceptor(),
				brave.serverResponseInterceptor(), new DefaultSpanNameProvider());
		return filter;
	}

	@Bean
	public OkHttpClient okHttpClient(Brave brave) {
		BraveOkHttpRequestResponseInterceptor interceptor = new BraveOkHttpRequestResponseInterceptor(brave.clientRequestInterceptor(),
				brave.clientResponseInterceptor(), 
				new DefaultSpanNameProvider());
		OkHttpClient httpClient = new OkHttpClient.Builder().addInterceptor(interceptor).build();
		return httpClient;
	}
}