package org.commingling.quinlan.framework.mybatis.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.commingling.quinlan.framework.mybatis.handler.DefaultDBFieldHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * MyBaits 配置类
 *
 * @author 芋道源码
 */
@Component
public class YudaoMybatisAutoConfiguration {

    @Bean
    public MetaObjectHandler defaultMetaObjectHandler(){
        return new DefaultDBFieldHandler(); // 自动填充参数类
    }


}
