package com.eureka.Listener;

import com.netflix.discovery.shared.Applications;
import com.netflix.eureka.EurekaServerContextHolder;
import com.netflix.eureka.registry.PeerAwareInstanceRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceCanceledEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import com.netflix.appinfo.InstanceInfo;
import org.springframework.cloud.netflix.eureka.server.event.*;


/**
 * Created by Administrator on 2018/8/23.
 */
@Component
@Slf4j
public class EurekaStateChangeListener {

    @EventListener
    public void listen(EurekaInstanceCanceledEvent event) {

        PeerAwareInstanceRegistry registry = EurekaServerContextHolder.getInstance().getServerContext()
                .getRegistry();
        Applications applications = registry.getApplications();
        // 遍历获取已注册节点中与当前失效节点ID一致的节点信息
        applications.getRegisteredApplications().forEach((registeredApplication) -> {
            registeredApplication.getInstances().forEach((instance) -> {
                if (instance.getInstanceId().equals(event.getServerId())) {
                    log.info("[EurekaInstanceCanceledListener.onApplicationEvent] [服务：" + instance.getAppName()
                            + " IP :端口："+instance.getIPAddr()+":"+instance.getPort()+" 服务下线！！！]");
                }
            });
        });

    }

    @EventListener
    public void listen(EurekaInstanceRegisteredEvent event) {
        InstanceInfo instanceInfo = event.getInstanceInfo();

        if (event.getInstanceInfo() != null) {
            log.info("[EurekaInstanceCanceledListener.onApplicationEvent] [服务："
                    + event.getInstanceInfo().getAppName() + " IP :端口："+event.getInstanceInfo().getIPAddr()+":"+event.getInstanceInfo().getPort()+" 注册成功！！！]");
        }

    }

    @EventListener
    public void listen(EurekaInstanceRenewedEvent event) {
        InstanceInfo instanceInfo =event.getInstanceInfo();
         if (event.getInstanceInfo() != null) {
            log.info("[EurekaInstanceCanceledListener.onApplicationEvent] [心跳检测服务："
                    + event.getInstanceInfo().getAppName() + " IP :端口：" + event.getInstanceInfo().getIPAddr() + ":" + event.getInstanceInfo().getPort() + "  服务进行续约！！！]");
        }
    }

    @EventListener
    public void listen(EurekaRegistryAvailableEvent event) {
        log.info("注册中心 启动");
    }

    @EventListener
    public void listen(EurekaServerStartedEvent event) {
        log.info("Eureka Server 启动");
    }

}


