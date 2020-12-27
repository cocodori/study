package pro10.sec3;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class LoginImpl implements HttpSessionListener {
	String id;
	String pwd;
	static int totalUser = 0;
	
	public LoginImpl() {}
	
	public LoginImpl(String id, String pwd) {
		this.id = id;
		this.pwd = pwd;
	}	
    public void sessionCreated(HttpSessionEvent se)  { 
    	 System.out.println("세션 생성");
    	 ++totalUser;
    }

    public void sessionDestroyed(HttpSessionEvent se)  { 
    	System.out.println("세션 소멸");
    	--totalUser;
    }
}
