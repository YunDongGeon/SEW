package itc.hoseo.sew;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
	
	@Autowired
	HttpInterceptor httpInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(httpInterceptor)
			.addPathPatterns("/**/*.do", "/")		// 인터셉터 걸리는 패턴
			.excludePathPatterns("/sewChangePw.do", "/logOut.do");		// 인터셉터 안걸리는 패턴	
	}
	
}
