package pro10.sec02;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter("/*")	//모든 요청은 해당 필터를 거친다.
public class EncoderFilter implements Filter {
	
	private ServletContext context;
	
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("filter........");
		System.out.println("utf-8 encoding.........");
		context = fConfig.getServletContext();
	}
	
	//실제로 필터 역할을 하는 메서드
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("doFilter()..........");
		request.setCharacterEncoding("utf-8");
		
		//웹 어플리케이션의 컨텍스트 이름
		String context = ((HttpServletRequest)request).getContextPath();
		
		//웹 브라우저에서 요청한 요청 URI
		String pathinfo = ((HttpServletRequest)request).getRequestURI();
		
		//요청 URI의 절대 경로
		String realPath = request.getRealPath(pathinfo);
		
		String msg = "Context : " + context +" \n URI : : " + pathinfo + "\n 절대 경로 : " + realPath;
		
		System.out.println(msg);
		
		long begin = System.currentTimeMillis();
		
		//다음 필터로 넘기는 작업을 수행
		chain.doFilter(request, response);
		
		System.out.println("response filter..........");

		long end = System.currentTimeMillis();
		
		System.out.println("작업 시간 : " + (end - begin)+"ms");
		
	}
	
	public void destroy() {
		System.out.println("destroy().....");
	}
}