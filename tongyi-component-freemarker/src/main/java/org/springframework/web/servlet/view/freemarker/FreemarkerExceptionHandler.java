package org.springframework.web.servlet.view.freemarker;

import freemarker.core.Environment;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Writer;

public class FreemarkerExceptionHandler implements TemplateExceptionHandler {
	private static final Logger log = LoggerFactory.getLogger(FreemarkerExceptionHandler.class);
	@SuppressWarnings("unused")
	@Override
	public void handleTemplateException(TemplateException te, Environment env,
			Writer out) throws TemplateException {
        String missingVariable = "undefined";
        log.debug("[Freemarker Error: " + te.getMessage() + "]");
        try {
            String[] tmp = te.getMessageWithoutStackTop().split("\n");
            if (tmp.length > 1)
                tmp = tmp[1].split(" ");
            if (tmp.length > 1)
                missingVariable = tmp[1];

            out.write("<span style='color:red'>[freemarker标签异常，请联系网站管理员]</span>");
        } catch (IOException e) {
        	e.printStackTrace();
        }
	}

}
