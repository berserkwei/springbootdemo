package SpringBootDemo.Components;

// import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class BlackListInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
//		response.setContentType("text/html;charset=utf-8");
		System.out.println(request.getLocalAddr());
		System.out.println(request.getRemoteAddr());
		System.out.println(request.getCharacterEncoding());
		System.out.println("preHandle");
//		if (new Random().nextBoolean()) {
//			return true;
//		} else {
//			response.getWriter().write("拒绝访问!");
//			return false;
//		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception { 
		System.out.println("postHandle");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("Interceptor 结束");

	}
}