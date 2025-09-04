package org.springframework.web.servlet.view.freemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateNotFoundException;
import org.springframework.beans.BeansException;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.view.AbstractTemplateView;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Locale;
import java.util.Map;

/**
 * 惠州市酷天科技有限公司
 *
 * @author foquanlin@163.com 林佛权
 * 2021-02-25
 */
public class TongyiFreeMarkerView extends AbstractTemplateView {

    private Configuration configuration;


    @Override
    protected void initApplicationContext() throws BeansException {
        super.initApplicationContext();
        Configuration config = this.getApplicationContext().getBean("newConfiguration",Configuration.class);
        this.configuration = config;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    protected void renderMergedTemplateModel(Map model, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        model.putAll(model);
        try{
            Locale locale = Locale.ROOT;//RequestContextUtils.getLocale(request);
//            Locale locale = RequestContextUtils.getLocale(request);
            Template template = configuration.getTemplate(getUrl(),locale);
            response.setCharacterEncoding(configuration.getDefaultEncoding());
            template.process(model, response.getWriter());
        }catch (TemplateNotFoundException e){
            logger.error(e.getMessage());
            String tplErrorUrl = "/";
            if(!StringUtils.isEmpty(request.getContextPath())){
                tplErrorUrl = request.getContextPath() + "404" ;
            }
            response.sendRedirect(tplErrorUrl);
        }
    }
}
