package allinone.vi.servlet;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by jiang.j on 2017/2/17.
 */
public class VIFilter implements Filter {
//    VIApiServlet apiServlet;
//    StaticContentServlet staticContentServlet;
    private final String urlPattern = "/@in";
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.printf("filter 前置");
//        apiServlet  = new VIApiServlet();
//        apiServlet.init();
//        staticContentServlet =  new StaticContentServlet();
//        staticContentServlet.init();

    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.printf("filter 处理中");
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String contextPath = httpRequest.getContextPath();
        if (contextPath == null) {
            contextPath = "";
        }


        String path = httpRequest.getRequestURI();
        if (!path.startsWith(contextPath + urlPattern+"/") && !path.equalsIgnoreCase(contextPath + urlPattern)) {
            chain.doFilter(request,response);
        }else{
            if(path.startsWith(contextPath+urlPattern+"/api/")){
//                apiServlet.service(request,response);
            }else{
//                staticContentServlet.service(request,response);
            }
        }
    }

    @Override
    public void destroy() {
        System.out.printf("filter 后置");
//        apiServlet = null;
//        staticContentServlet = null;

    }
}
