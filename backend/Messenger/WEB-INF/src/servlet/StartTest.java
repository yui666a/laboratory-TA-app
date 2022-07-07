package servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class StartTest implements ServletContextListener {

    public void contextInitialized(ServletContextEvent arg0)  {
    	System.out.println("スタートのテスト");
    }

    public void contextDestroyed(ServletContextEvent arg0)  {
    }
}