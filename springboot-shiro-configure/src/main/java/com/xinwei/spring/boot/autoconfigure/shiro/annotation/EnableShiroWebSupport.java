package com.xinwei.spring.boot.autoconfigure.shiro.annotation;

import com.xinwei.spring.boot.autoconfigure.shiro.ShiroWebMvcConfigurerAdapter;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE })
@Inherited
@Import({ EnableShiroWebSupport.ShiroWebMvcConfigurerAdapterImportSelector.class })
public @interface EnableShiroWebSupport {
    static class ShiroWebMvcConfigurerAdapterImportSelector implements ImportSelector {
        public String[] selectImports(AnnotationMetadata importingClassMetadata) {
            return new String[] { ShiroWebMvcConfigurerAdapter.class.getName() };
        }
    }
}
