package SpringBootDemo.Entities;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties
//@PropertySource(value="file:E:/MyWorkSpaces/Java/eclipse-workspace/SpringBootDemo/target/config/test.yml", encoding="utf-8")
@PropertySource(value="file:test.yml", encoding="utf-8")
public class MyConfig {
	private String version;
	private String name;
	
	public void setVersion(String version) {
		this.version = version;
	}
	
	public String getVersion() {
		return this.version;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
}
