package org.springframework.web.servlet.view.freemarker;

import freemarker.template.DefaultObjectWrapper;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.Version;

/**
 * DefaultObjectWrapperBuilderFactory
 * 
 * @author: tom
 * @date: 2018年12月27日 上午9:28:56
 * @Copyright: 江西金磊科技发展有限公司 All rights reserved.Notice
 *             仅限于授权后使用，禁止非授权传阅以及私自用于商业目的。
 */
public class DefaultObjectWrapperBuilderFactory {

	private DefaultObjectWrapperBuilderFactory() {
	}

	private static final Version VERSION = new Version(2, 3, 24);
	private static final DefaultObjectWrapperBuilder DEFAULT_OBJECT_WAPPER_BUILDER = 
			new DefaultObjectWrapperBuilder(VERSION);

	/**
	 * 静态工厂方法 获取 DefaultObjectWrapperBuilder
	 * 
	 * @Title: getInstance
	 * @return: DefaultObjectWrapperBuilder
	 */
	public static DefaultObjectWrapperBuilder getInstance() {
		return DEFAULT_OBJECT_WAPPER_BUILDER;
	}

	public static DefaultObjectWrapper getDefaultObjectWrapper() {
		return DEFAULT_OBJECT_WAPPER_BUILDER.build();
	}

}
