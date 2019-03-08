package SpringBootDemo.Services;

import org.springframework.stereotype.Service;

@Service("hi")
public class HiService implements IHelloService {

	@Override
	public String Hello() {
		// TODO Auto-generated method stub
		return "Hi...";
	}

}
