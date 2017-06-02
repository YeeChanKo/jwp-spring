package next.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class ProfilingIntercepter extends HandlerInterceptorAdapter {

	private static final Logger log = LoggerFactory
			.getLogger(ProfilingIntercepter.class);

	private static final String START_TIME = "start_time";

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		request.setAttribute(START_TIME, System.currentTimeMillis());
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		long preHandleTime = (long) request.getAttribute(START_TIME);
		long postHandleTime = System.currentTimeMillis();
		long timeElapsed = postHandleTime - preHandleTime;

		log.debug("elapsed time: {}", timeElapsed);
	}

}
