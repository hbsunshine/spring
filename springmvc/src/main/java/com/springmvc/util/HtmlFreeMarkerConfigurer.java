package com.springmvc.util;
import java.util.List;

import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.cache.TemplateLoader;

public class HtmlFreeMarkerConfigurer extends FreeMarkerConfigurer {

	@Override
	protected TemplateLoader getAggregateTemplateLoader(final List<TemplateLoader> templateLoaders) {
		logger.info("Using HtmlTemplateLoader to enforce HTML-safe content");
		return new HtmlTemplateLoader(super.getAggregateTemplateLoader(templateLoaders));
	}
}