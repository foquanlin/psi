package org.springframework.web.servlet.view.freemarker;

import org.springframework.web.servlet.view.AbstractTemplateViewResolver;
import org.springframework.web.servlet.view.AbstractUrlBasedView;

/**
 * 惠州市酷天科技有限公司
 *
 * @author foquanlin@163.com 林佛权
 * 2021-02-25
 */
public class TongyiFreeMarkerViewResolver extends AbstractTemplateViewResolver {
    static final String SPT = "/";

    public TongyiFreeMarkerViewResolver() {
        this.setSuffix(".ftl");
        this.setViewClass(TongyiFreeMarkerView.class);
    }

    /**
     * if viewName start with / , then ignore prefix.
     */
    @Override
    protected AbstractUrlBasedView buildView(String viewName) throws Exception {
        AbstractUrlBasedView view = super.buildView(viewName);
        // start with / ignore prefix
        if (viewName.startsWith(SPT)) {
            view.setUrl(viewName + getSuffix());
        }
        return view;
    }

}
