package yyyq.gateway;

import com.alibaba.fastjson.JSON;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpMethod;
import yyyq.common.constants.SecurityConstants;
import yyyq.common.model.ActionResultModel;
import yyyq.common.model.auth.AccountModel;
import yyyq.common.util.StringUtil;
import yyyq.gateway.feignclient.AuthClient;

import javax.servlet.http.HttpServletRequest;

public class AccessFilter extends ZuulFilter {

    @Autowired
    private AuthClient authClient;

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        //所有POST必须带TOKEN，GET是开放的，对部分页面除外
        if(HttpMethod.POST.matches(request.getMethod())) {
            boolean isAuth=true;
            for (String page : SecurityConstants.NOT_AUTH_PAGE) {
                if (request.getRequestURI().contains(page)) {
                    isAuth=false;
                    break;
                }
            }
            if (!isAuth) {
                return null;
            }
            String token = request.getHeader(SecurityConstants.HEADER_STRING);
            if (StringUtil.isNullOrEmpty(token)) {
                ctx.setSendZuulResponse(false);
                ctx.setResponseStatusCode(401);
                ctx.setResponseBody("401 Unauthorized");
                return null;
            }

            try {
                AccountModel accountModel = authClient.token(token).data;
                ctx.addZuulRequestHeader("accountModel", JSON.toJSONString(accountModel));
            }catch (Exception ex)
            {
                ctx.setSendZuulResponse(false);
                ctx.setResponseStatusCode(401);
                ctx.setResponseBody("401 Unauthorized");
                return null;
            }
            return null;
        }
        return null;
    }
}
