package itc.hoseo.sew;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
	
	@Autowired
	private HttpInterceptor httpInterceptor;
	
	@Autowired
	private Environment env;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(httpInterceptor)
			.addPathPatterns("/**/*.do", "/")		// 인터셉터 걸리는 패턴
			.excludePathPatterns("/sewChangePw.do", "/logOut.do");		// 인터셉터 안걸리는 패턴	
	}
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		String uploadUrl = env.getProperty("laptop-upload-folder");  
		registry.addResourceHandler("/prodThumb/*.jpg", "/prodCont/*.jpg")
			.addResourceLocations("file:///" + uploadUrl);
	}
	
}
