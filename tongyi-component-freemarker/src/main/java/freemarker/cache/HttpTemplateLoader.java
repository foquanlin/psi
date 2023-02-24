package freemarker.cache;

import java.net.MalformedURLException;

import java.net.URL;

/**
 * 远程加载freemarker模版内容
 * @author foquanlin@163.com
 */
public class HttpTemplateLoader extends URLTemplateLoader {
    private String urlPath;
    public HttpTemplateLoader(String urlPath){
        this.urlPath = urlPath;
    }
    @Override
    protected URL getURL(String s) {
        try {
            return new URL(this.urlPath+s);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
