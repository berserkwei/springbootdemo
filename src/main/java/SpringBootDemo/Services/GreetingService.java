package SpringBootDemo.Services;

import org.springframework.stereotype.Service;

@Service("greeting")
public class GreetingService implements IHelloService {

	@Override
	public String Hello() {
		// TODO Auto-generated method stub
		return "Greeting...";
	}

}
