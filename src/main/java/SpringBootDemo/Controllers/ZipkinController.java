package SpringBootDemo.Controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@RestController()
@RequestMapping("zipkin")
public class ZipkinController {

	@Autowired
	private OkHttpClient client;
	
	@GetMapping("service1")
	public String service1() throws IOException {
		String msg = "This is service1...";
		Request request = new Request.Builder()
	            .url("http://localhost:8080/zipkin/service2")
	            .build();
		Response response = client.newCall(request).execute();
		return msg + "\n" + response.body().string();
	}
	
	@GetMapping("service2")
	public String service2() throws IOException {
		String msg = "This is service2...";
		Request request = new Request.Builder()
	            .url("http://localhost:8080/zipkin/service3")
	            .build();
		Response response = client.newCall(request).execute();
		return msg + "\n" + response.body().string();
	}
	
	@GetMapping("service3")
	public String service3() throws IOException {
		return "This is service3...";
	}
}
