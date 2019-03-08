package SpringBootDemo.Controllers;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import SpringBootDemo.Entities.MyConfig;
import SpringBootDemo.Entities.People;
import SpringBootDemo.Services.IHelloService;

@RestController
@RequestMapping("test")
public class HelloController {

	@Value("${codename}")
	String name;
	
	@Autowired
	MyConfig myConfig;
	
	@Resource(name="${helloservice}")
	IHelloService helloSvc;
	
	@GetMapping("/home")
	public String home() {
		String msg = String.format("你好, %s!", this.myConfig.getName());
		return msg;
	}

	@GetMapping("/hello")
	public List<String> Hello() {
		String msg = String.format("你好，%s!", name);
		System.out.println(msg);
		ArrayList<String> list = new ArrayList<String>();
		list.add(msg);
		list.add(this.helloSvc.Hello());
		return list;
	}
	
	@GetMapping(value="/info")
	public People Info() {
		People user = new People("bruce", 18, "aalall@122.com", 0);
		ObjectMapper mapper = new ObjectMapper();
		try {
			String json = mapper.writeValueAsString(user);
			System.out.println(json);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	
	@PostMapping("user")
	public String createUser(@RequestBody People user) {
		System.out.println(user.getName() + ": " + user.getAge());
		return String.format("%s:%d created!", user.getName(), user.getAge());
	}
	
	@RequestMapping("fab")
	public int Fab() {
		return fab(30);
	}
	
	int fab(int n) {
		if (n <= 2) {
			return n;
		}
		return fab(n-1) + fab(n-2);
	}
}
