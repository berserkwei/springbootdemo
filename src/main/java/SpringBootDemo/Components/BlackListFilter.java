package SpringBootDemo.Components;

import java.io.IOException;
import java.util.Random;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.springframework.core.annotation.Order;

// @Component
@WebFilter(urlPatterns="/*", filterName="blackListFilter")
@Order(1)
public class BlackListFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		if (new Random().nextBoolean()) {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write("拒绝访问!");
		}else {
			response.setContentType("application/json;charset=utf-8");
			chain.doFilter(request, response);
		}
//		chain.doFilter(request,  response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}
}
