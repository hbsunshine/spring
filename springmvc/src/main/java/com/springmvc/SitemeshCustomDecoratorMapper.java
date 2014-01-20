package com.springmvc;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.opensymphony.module.sitemesh.Config;
import com.opensymphony.module.sitemesh.Decorator;
import com.opensymphony.module.sitemesh.DecoratorMapper;
import com.opensymphony.module.sitemesh.Page;
import com.opensymphony.module.sitemesh.mapper.ConfigDecoratorMapper;

public class SitemeshCustomDecoratorMapper extends ConfigDecoratorMapper {
	private final Logger logger = Logger.getLogger(this.getClass().getName());

	@Override
	public void init(final Config config, final Properties properties,
			final DecoratorMapper parent) throws InstantiationException {
		// TODO Auto-generated method stub
		super.init(config, properties, parent);
	}

	@Override
	public Decorator getDecorator(final HttpServletRequest request, final Page page) {

		Logger.getLogger("SitemeshCustomDecoratorMapper").info(request.getServletPath());

		if (page.isPropertySet("meta.decorator")) {
			if(request.getSession()!=null){
			    final Object user = request.getSession().getAttribute("");
			   /* if (request.getServletPath().startsWith("/merchant") && (user != null)) {
		            if (sessionUser.getCustomerType().equals("BUSINESS")) {
		                final Decorator decorator = getNamedDecorator(request,
		                        "mloggedIn");
		                logger.info(decorator.getPage());
		                return decorator;
		            }
		        }*/
			}
		    
		    final Decorator decorator = getNamedDecorator(request,
					page.getProperty("meta.decorator"));
			return decorator;
		}

		if (request.getServletPath().startsWith("/m/")) {
			final Decorator decorator = getNamedDecorator(request, "mobile");
			logger.info(decorator.getPage());
			return decorator;
		}
		if (null != request.getParameter("_os")) {
			final Decorator decorator = getNamedDecorator(request, "oauth");
			logger.info(decorator.getPage());
			return decorator;
		}

		return super.getDecorator(request, page);
	}
}
