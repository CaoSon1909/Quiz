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
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
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
public class AuthorizationFilter implements Filter {

    private static final boolean debug = true;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;

    public AuthorizationFilter() {
    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("AuthorizationFilter:DoBeforeProcessing");
        }

    }

    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("AuthorizationFilter:DoAfterProcessing");
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
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        //get resource mà client yêu cầu từ URI
        String uri = req.getRequestURI();
        int lastIndex = uri.lastIndexOf("/");
        String resource = uri.substring(lastIndex + 1);
        System.out.println("Authorize Filter:");
        System.out.println("Request resource: " + resource);

        //admin list attribute
        List<String> adminList = (List<String>) req.getServletContext().getAttribute("ADMIN_RESOURCE_LIST");
        //student list attribute
        List<String> studentList = (List<String>) req.getServletContext().getAttribute("STUDENT_RESOURCE_LIST");

        if (adminList == null || studentList == null) {
            System.out.println("admin list hoac student list bi null");
        } else {
            //kiểm tra xem isAuthenticate attribute có tồn tại chưa. Nếu chưa thi chứng tỏ chưa authenticate => do filter
            Boolean isAuthenticated = (Boolean) req.getAttribute("IS_AUTHENTICATED");
            if (isAuthenticated != null) {
                //nếu isAuthenticated = true và user.getRoleId = 1 => phân quyền thành công
                boolean isAuthorized = false;
                //user in session
                HttpSession session = req.getSession(false);
                if (session != null) {
                    TblUserDTO user = (TblUserDTO) session.getAttribute("USER_DTO");
                    if (user != null) {
                        if (isAuthenticated == true && user.getRoleID() == 1) {
                            //request đó là của admin
                            //check xem resource mà admin yêu cầu có tồn tại trong adminList ko. Nếu có thì isAuthorize = true
                            isAuthorized = adminList.contains(resource);
                        } else if (isAuthenticated == true && user.getRoleID() == 2) {
                            //request đó là của student
                            //check xem resource mà student yêu cầu có tồn tại trong studentList ko. Nếu có thì isAuthorize = true
                            isAuthorized = studentList.contains(resource);
                        }
                        if (isAuthorized) {
                            System.out.println("Is authorized: " + true);
                            chain.doFilter(request, response);
                        } else {
                            System.out.println("Is authorized: " + false);
                            res.sendError(403);
                        }
                    } //end if user is not empty
                } //end if session is not empty

            }//end if isauthenticated is not empty
            else {
                System.out.println("-------------------------------");
                chain.doFilter(request, response);
            }
        }

//        HttpServletRequest httpRequest = (HttpServletRequest) request;
        //        HttpServletResponse httpResponse = (HttpServletResponse) response;
        //        //action
        //        String action = httpRequest.getParameter("btAction");
        //        //get student action list
        //        List<String> studentList = (List<String>) httpRequest.getServletContext().getAttribute("STUDENT_RESOURCE_LIST");
        //        //get admin action list
        //        List<String> adminList = (List<String>) httpRequest.getServletContext().getAttribute("ADMIN_RESOURCE_LIST");
        //
        //        //isAuthenticated
        //        Boolean isAuthenticated = (Boolean) httpRequest.getAttribute("IS_AUTHENTICATED");
        //
        //        if (isAuthenticated != null && isAuthenticated == Boolean.TRUE) {
        //            //get and check session
        //            HttpSession session = httpRequest.getSession(false);
        //            if (session != null) {
        //                TblUserDTO user = (TblUserDTO) session.getAttribute("USER_DTO");
        //                boolean isAuthorized = false;
        //                if (user.getRoleID() == 1) {
        //                    isAuthorized = adminList.contains(action);
        //                } else if (user.getRoleID() == 2) {
        //                    isAuthorized = studentList.contains(action);
        //                }
        //
        //                if (isAuthorized) {
        //                    chain.doFilter(request, response);
        //                } else {
        //                    httpResponse.sendError(403);
        //                }
        //            }
        //        } else {
        //            chain.doFilter(request, response);
        //        }
        {

        }
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
                log("AuthorizationFilter:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("AuthorizationFilter()");
        }
        StringBuffer sb = new StringBuffer("AuthorizationFilter(");
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
