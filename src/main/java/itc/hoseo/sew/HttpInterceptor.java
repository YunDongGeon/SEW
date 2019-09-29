package itc.hoseo.sew;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class HttpInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {		
		try {
			HttpSession session = request.getSession(false);
			if(session!=null) {
				String memId = session.getAttribute("memId").toString();
				String memName = session.getAttribute("memName").toString();
				String memStat = session.getAttribute("memStat").toString();
				
				if(memStat=="yes") {
					response.sendRedirect("/sewChangePw");
					return false;
				}else {
					return true;
				}			
			}else {
				return true;
			}
		} catch (NullPointerException e) {
			return true;
		}		
	}
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		super.afterCompletion(request, response, handler, ex);
	}
	
}
