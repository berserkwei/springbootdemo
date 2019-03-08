package SpringBootDemo.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import SpringBootDemo.Entities.People;

@RequestMapping("redis")
@RestController
public class RedisController {

	@Autowired
	private RedisTemplate<String, People> template;
	
	@GetMapping("test")
	public String test() {
		return "Redis test!";
	}
	
	@GetMapping("set")
	public void set(@RequestParam String key, @RequestParam String value) {
		People people = new People("bruce", 25);
		template.opsForValue().set(key, people);
	}
	
	@GetMapping("get/{key}")
	public People get(@PathVariable String key) {
		return template.opsForValue().get(key);
	}
}
