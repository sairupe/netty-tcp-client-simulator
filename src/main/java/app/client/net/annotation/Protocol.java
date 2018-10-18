package app.client.net.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import app.client.net.protocol.ProtocolType;


/**
 * 
 * @author syriana.zh
 *
 * 消息ID注解类,用于标记协议实体类
 * 2016年4月15日 下午3:27:20
 */
@Target( { ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Protocol {
    int moduleId();
    int sequenceId();
	ProtocolType type();
}
