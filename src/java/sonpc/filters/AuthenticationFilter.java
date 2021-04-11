/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonpc.filters;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sonpc.tblUser.TblUserDTO;

/**
 *
 * @author ACER
 */
public class AuthenticationFilter implements Filter {

    private static final boolean debug = true;

    private FilterConfig filterConfig = null;
    private final String LOGIN_PAGE = "login.jsp";
    private final String MAIN_SERVLET = "MainServlet";

    public AuthenticationFilter() {
    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("AuthenticationFilter:DoBeforeProcessing");
        }

    }

    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("AuthenticationFilter:DoAfterProcessing");
        }

    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    /*1:Kiểm tra xem resource có tồn tại ko*/
    private String getResourceExistedSiteMapFile(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String uri = req.getRequestURI();
        int lastIndex = uri.lastIndexOf("/");
        String resource = uri.substring(lastIndex + 1);
        Map<String, String> siteMap = (Map<String, String>) req.getServletContext().getAttribute("SITE_MAP");
        String url = siteMap.get(resource);
        if (url != null) {
            return resource;
        }
        return null;
    }

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        //
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        //kiểm tra resource có tồn tại ko
        String resource = getResourceExistedSiteMapFile(request, response);
        System.out.println("-------------------------------");
        System.out.println("Authenticate Filter:");
        System.out.println("Request resource: " + resource);
        if (resource == null) {
            System.out.println("Is resource valid: " + false);
            res.sendError(404);
        } else {
            System.out.println("Is resource valid: "+ true);
            //kiểm tra resouce đó có cần authenticate ko
            ServletContext context = req.getServletContext();
            List<String> authenticateList = (List<String>) context.getAttribute("AUTHENTICATE_LIST");
            if (authenticateList != null) {
//                boolean needAuthenticate = false;
                boolean isAuthenticated = false;
                // ***** kiểm tra resource có cần authenticate ko <=> resouce đó có nằm trong authenticate list ko
                if (authenticateList.contains(resource)) {
                    //resource user yêu cầu cần authenticate
                    System.out.println("Need authenticated: " + true);
//                    req.setAttribute("NEED_AUTHENTICATE", needAuthenticate); //sẽ check bên Filter Dispatcher
                    //kiểm tra USER_DTO trong session có tồn tại ko
                    HttpSession session = req.getSession(false);
                    if (session != null) {
                        TblUserDTO user = (TblUserDTO) session.getAttribute("USER_DTO");
                        if (user != null) {
                            //nếu userDTO tồn tại thì isAuthenticated = true;
                            isAuthenticated = true;
                            req.setAttribute("IS_AUTHENTICATED", isAuthenticated);
                            System.out.println("Is authenticated: " + isAuthenticated);
                        } else {
                            //nếu userDTO chưa tồn tại thì sendRedirect về lại trang login
                            res.sendRedirect(LOGIN_PAGE);
                        }
                    }
                }
                // Resource đó ko cần authenticate => chain do filter để nó đi tiếp
                System.out.println("Need authenticated: " + isAuthenticated);
                System.out.println("-------------------------------");
                chain.doFilter(request, response);
            }
        }

//        HttpServletRequest httpRequest = (HttpServletRequest) request;
//        HttpServletResponse httpResponse = (HttpServletResponse) response;
//        httpResponse.setHeader("Cache-Control", "no-cache, no-store");
//
//        //get button parameter
//        String action = request.getParameter("btAction");
//
//        //Lấy danh sách những resource cần authenticate từ context listener
//        ServletContext context = httpRequest.getServletContext();
//        List<String> resourceList = (List<String>) context.getAttribute("AUTHENTICATION_RESOURCE_LIST");
//
//        if (resourceList.contains(action)) {
//            boolean isAuthenticated = false;
//            //get and check session
//            HttpSession session = httpRequest.getSession(false);
//            if (session != null) {
//                //isAuthenticated = true
//                TblUserDTO user = (TblUserDTO) session.getAttribute("USER_DTO");
//                if (user != null) {
//                    isAuthenticated = true;
//                }
//            }
//            if (isAuthenticated) {
//                //đã authenticate rồi
//                httpRequest.setAttribute("IS_AUTHENTICATED", Boolean.TRUE);
//                chain.doFilter(request, response);
//            } else {
//                //chưa authenticate
//                //send redirect thẳng về trang login.jsp
//               String url =  httpRequest.getServletContext().getContextPath() + "login.jsp";
//               httpResponse.sendRedirect(url);
//            }
//        }
    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {
                log("AuthenticationFilter:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("AuthenticationFilter()");
        }
        StringBuffer sb = new StringBuffer("AuthenticationFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);

        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
                pw.print(stackTrace);
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }

    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }

}
