package com.eureka.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2018/8/20.
 *  自定义网关过滤器
 *
 */
@Slf4j
@Component
public class AccessFilter extends ZuulFilter {


    @Override
    public String filterType() {
        return "pre";  //枚举值：pre, routing, post, error
    }

    @Override
    public int filterOrder() {
        return 1;    //优先级， 0是最高优先级即最先执行
    }

    @Override
    public boolean shouldFilter() {
        return true;  //写逻辑，是否需要执行过滤。true会执行run函数，false不执行run函数
    }

    @Override
    public Object run() {
        log.info("----------------this is DemoFilter----------");

        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        log.info(String.format("%s %s", request.getMethod(), request.getRequestURL().toString()));
        Object accessToken = request.getParameter("token");

        if(accessToken == null) {    //判断释放有token自动
            log.warn("token is empty");
            //ctx.setSendZuulResponse(false);
            // ctx.setResponseStatusCode(401);
//            try {
//                ctx.getResponse().getWriter().write("token is empty");
//            }catch (Exception e){
//
//            }

           return null;
        }
        return null;
    }
}
