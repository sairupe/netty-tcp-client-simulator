package app.client.net.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @author syriana.zh
 * 
 * 消息分发调用函数注解类,用于标记分发协议的函数入口
 * 2016年4月21日 下午2:23:56
 */
@Target( { ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Handler {
    int moduleId();
    int sequenceId();
}
