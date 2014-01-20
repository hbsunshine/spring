package com.springmvc.util;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import org.apache.commons.io.IOUtils;
import org.apache.cxf.common.util.PropertyUtils;

import freemarker.cache.TemplateLoader;

public class HtmlTemplateLoader implements TemplateLoader {

	public static final String ESCAPE_PREFIX = "<#ftl strip_whitespace=true><#escape x as x?html>";
	public static final String ESCAPE_SUFFIX = "</#escape>";

	private final TemplateLoader delegate;

	public HtmlTemplateLoader(final TemplateLoader delegate) {
		this.delegate = delegate;
	}

	@Override
	public Object findTemplateSource(final String name) throws IOException {
		return delegate.findTemplateSource(name);
	}

	@Override
	public long getLastModified(final Object templateSource) {
		return delegate.getLastModified(templateSource);
	}

	@Override
	public Reader getReader(final Object templateSource, final String encoding) throws IOException {

		final Reader reader = delegate.getReader(templateSource, encoding);
		if (PropertyUtils.isTrue("turnOffHTMLEscape")) {
			try {
				final String templateText = IOUtils.toString(reader);
				if (templateText.contains("<#ftl")) {
					return new StringReader(templateText);
				}
				return new StringReader(ESCAPE_PREFIX + templateText
						+ ESCAPE_SUFFIX);
			} finally {
				IOUtils.closeQuietly(reader);
			}
		}
		return reader;
	}

	@Override
	public void closeTemplateSource(final Object templateSource) throws IOException {
		delegate.closeTemplateSource(templateSource);
	}
}