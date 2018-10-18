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
 * 消息回调Service注解类,用于标记Service实体类
 * 2016年4月21日 上午11:25:01
 */
@Target( { ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Receiver {
	
}
