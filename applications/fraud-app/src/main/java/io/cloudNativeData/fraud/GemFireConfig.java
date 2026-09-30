package io.cloudNativeData.fraud;

import io.cloudNativeData.fraud.domains.Alert;
import lombok.extern.slf4j.Slf4j;
import org.apache.geode.cache.DataPolicy;
import org.apache.geode.cache.client.ClientCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.gemfire.client.ClientRegionFactoryBean;
import org.springframework.data.gemfire.config.annotation.ClientCacheApplication;

@Configuration
@ClientCacheApplication(subscriptionEnabled = true, readyForEvents = true)
@Slf4j
public class GemFireConfig {

    @Bean("Alert")
    ClientRegionFactoryBean<String, Alert> alert(ClientCache cache) {
        var regionBean = new ClientRegionFactoryBean<String, Alert>();
        regionBean.setCache(cache);
        regionBean.setName("Alert");
        regionBean.setDataPolicy(DataPolicy.EMPTY);
        return regionBean;
    }

}

