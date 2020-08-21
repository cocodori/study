package com.coco.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] {RootConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
//		return new Class[] {WebConfig.class};  //설정 클래스를 명시;
		return null;
	}
	@Override
	protected String[] getServletMappings() {
//		return new String[] {"/"};  //dispatch servlet mapping;
		return null;
	}
}
