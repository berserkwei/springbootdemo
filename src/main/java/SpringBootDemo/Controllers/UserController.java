package SpringBootDemo.Controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import SpringBootDemo.Dao.UserDao;
import SpringBootDemo.Entities.ApiResult;
import SpringBootDemo.Entities.People;

@RestController
public class UserController {
	
	@Autowired
	UserDao userDao;
	
	@GetMapping("users")
	public ApiResult listUsers() {
		System.out.println("list users...");
		ArrayList<People> list = new ArrayList<People>();
		list.add(new People("bruce", 99, "ssjs", 0));
//		return list;
		try {
			List<People> users = this.userDao.getPeoples();
			return new ApiResult(true, "Succeed", users);
		}catch (Exception ex) {
			return new ApiResult(false, ex.getLocalizedMessage());
		}
	}
	
	@GetMapping("users/{id}")
	public ApiResult getUser(@PathVariable("id") int id) {
		try {
			People user = this.userDao.getPeople(id);
			return new ApiResult(true, "Succeed", user);
		}catch (Exception ex) {
			return new ApiResult(false, ex.getLocalizedMessage());
		}
	}
	
	@PostMapping("users")
	public ApiResult createUser(@RequestBody People people) {
		try {
			boolean succeed = this.userDao.createPeople(people);
			return new ApiResult(succeed, "Succeed");
		}catch (Exception ex) {
			return new ApiResult(false, ex.getLocalizedMessage());
		}
	}
	
	@PutMapping("users/{id}")
	public ApiResult updateUser(@PathVariable("id") int id, @RequestBody People people) {
		System.out.println(id);
		System.out.println(people.getName());
		System.out.println(people.getEmail());

		try {
//			people.setId(id);
			JSONObject json = (JSONObject)JSON.toJSON(people);
			json.put("id", id);
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("name", people.getName());
			map.put("email", people.getEmail());
			map.put("sex", people.getSex());
			map.put("id", id);
			boolean succeed = this.userDao.updatePeople(json);
			return new ApiResult(succeed, "Succeed");
		}catch (Exception ex) {
			return new ApiResult(false, ex.getLocalizedMessage());
		}
	}
	
	@DeleteMapping("users/{id}")
	public ApiResult deleteUser(@PathVariable("id") int id) {
		try {
			boolean succeed = this.userDao.deletePeople(id);
			return new ApiResult(succeed, "Succeed");
		}catch (Exception ex) {
			return new ApiResult(false, ex.getLocalizedMessage());
		}
	}
	
	@GetMapping("users/batchadd")
	public ApiResult batchAdd() {
		long start = System.currentTimeMillis();
		for(int i= 0;i<10000;i++) {
			this.userDao.createPeople(new People("Name"+i, i, "email" + i, i%2));
		}
		long end = System.currentTimeMillis();
		System.out.println("batchadd spent " + (end - start));
		return new ApiResult(true, "Succeed");
	}
	
	@GetMapping("users/trans")
	@Transactional
	public ApiResult transaction() {
		long start = System.currentTimeMillis();
		try {
			for(int i= 0;i<1000;i++) {
				this.userDao.createPeople(new People(String.format("Name%d", i), i, String.format("Email%d", i), i%2));
			}
			return new ApiResult(true, "Succeed");
		}catch (Exception ex) {
			return new ApiResult(false, "failed");
		}finally {
			long end = System.currentTimeMillis();
			System.out.println("transaction spent " + (end - start));
		}
	}
}
