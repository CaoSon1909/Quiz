/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonpc.listeners;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author ACER
 */
public class ContextListener implements ServletContextListener {

    private final String DELIMETER = "=";

    //read file
    public Map<String, String> loadResourceMapping(String fileName) {
        File file = null;
        FileReader fr = null;
        BufferedReader br = null;
        Map<String, String> map = null;
        String line = "";
        try {
            file = new File(fileName);
            fr = new FileReader(file);
            br = new BufferedReader(fr);

            while ((line = br.readLine()) != null) {
                String tmp[] = line.split(DELIMETER);
                String action = tmp[0];
                String servlet = tmp[1];
                if (map == null) {
                    map = new HashMap<>();
                }
                map.put(action, servlet);
            }
            if (br != null) {
                br.close();
            }
            if (fr != null) {
                fr.close();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ContextListener.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ContextListener.class.getName()).log(Level.SEVERE, null, ex);
        }
        return map;
    }

    public List<String> loadListFromFile(String fileName) {
        File file = null;
        FileReader fr = null;
        BufferedReader br = null;
        List<String> list = null;
        String line = "";
        try {
            file = new File(fileName);
            fr = new FileReader(file);
            br = new BufferedReader(fr);

            while ((line = br.readLine()) != null) {
                if (list == null) {
                    list = new Vector<>();
                }
                list.add(line);
            }
            br.close();
            fr.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ContextListener.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ContextListener.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    //
    
//    private String getRealPath(ServletContextEvent sce){
//        ServletContext context = sce.getServletContext();
//        String path = context.getRealPath("/");
//        return path;
//    }
//    
//    private String getResourceFileName(ServletContextEvent sce){
//        String path = getRealPath(sce);
//        ServletContext context = sce.getServletContext();
//        String resourceFileName = path + context.getInitParameter("resourceFileName");
//        return resourceFileName;
//    }
//    
//    private String getAuthenticationFileName(ServletContextEvent sce){
//        ServletContext context = sce.getServletContext();
//        String path = context.getRealPath("/");
//    }
//    
//    private String getAuthorizeFileName(ServletContextEvent sce){
//        
//    }
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //context scope
        ServletContext context = sce.getServletContext();
        //path
        String path = context.getRealPath("/");
        //load resource file
        String fileResourceName = path + context.getInitParameter("resourceFileName");
        Map<String, String> resourceMapping = loadResourceMapping(fileResourceName);
        //load authentication list
        String authenticateFileName = path + context.getInitParameter("authenticateFileName");
        List<String> authenticateList = loadListFromFile(authenticateFileName);
        //load admin resource list
        String adminResourceFileName = path + context.getInitParameter("adminResourceFileName");
        List<String> adminList = loadListFromFile(adminResourceFileName);
        //load student resource list
        String studentResourceFileName = path + context.getInitParameter("studentResourceFileName");
        List<String> studentList = loadListFromFile(studentResourceFileName);
        //set attribute to context scope
        context.setAttribute("SITE_MAP", resourceMapping); //resource attribute
        context.setAttribute("AUTHENTICATE_LIST", authenticateList);
        context.setAttribute("ADMIN_RESOURCE_LIST", adminList);
        context.setAttribute("STUDENT_RESOURCE_LIST", studentList);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
