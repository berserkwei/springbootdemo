package SpringBootDemo.Dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.alibaba.fastjson.JSONObject;

import SpringBootDemo.Entities.People;

@Mapper
public interface UserDao {
	@Select("select * from user limit 0,100")
	List<People> getPeoples();
	
	@Select("select * from user where id = #{id}")
	People getPeople(int id);
	
	@Insert("insert into user(name, email, sex) values(#{name}, #{email}, #{sex})")
	boolean createPeople(People people);
	
	@Update("update user set name=#{name}, email=#{email}, sex=#{sex} where id = #{id}")
	boolean updatePeople(JSONObject p);
	
	@Delete("delete from user where id=#{id}")
	boolean deletePeople(int id);
}
