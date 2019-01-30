package com.xinwei.shiro;

import com.xinwei.shiro.tag.ShiroTags;
import freemarker.template.TemplateException;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import java.io.IOException;

public class ShiroTagFreeMarkerConfigurer extends FreeMarkerConfigurer{
    public void afterPropertiesSet() throws IOException, TemplateException {
        super.afterPropertiesSet();  
        this.getConfiguration().setSharedVariable("shiro", new ShiroTags());  
    }
}