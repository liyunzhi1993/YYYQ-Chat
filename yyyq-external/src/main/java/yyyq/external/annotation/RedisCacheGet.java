package yyyq.external.annotation;

import java.lang.annotation.*;

/**
 * RedisCacheGet
 *
 * @author liyunzhi
 * @date 2018/7/29 15:44
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface RedisCacheGet {
    //枚举类型
    enum DataType {
        CLASS, JSON
    }
    /** * key值 * * @return */
    String key();

    /** * 缓存过期时间默认为不过期，过期时间手动去设定，单位为 S * 0:不限制保存时长 * * @return */
    int expire() default 0;

    /** * force是否强制刷新数据 * * @return */
    boolean force() default false;

    /** * 数据类型 * * @return */
    DataType dataType() default DataType.JSON;
}