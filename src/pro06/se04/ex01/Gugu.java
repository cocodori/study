package pro06.se04.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Gugu
 */
@WebServlet("/gugu")
public class Gugu extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		int dan = Integer.parseInt(request.getParameter("dan"));
		
		System.out.println("dan : " + dan);
		
		String data = "";
		
		data += "<table border=1 width=800 align=center>";
		data += "<tr align=center bgcolor='#FFFF66'>";
		data += "<td colspan=2" + dan + "단 출력 </td>";
		data += "</tr>";
		
		for(int i=1; i<10; i++) {
			if(i % 2 == 0 ) {	//곱하는 수가 2의 배수면 초록색
				data += "<tr align=center bgcolor='#ACFA58'>";
			} else { //아니면 파란색
				data += "<tr align=center bgcolor='#81BEF7'>";
			}
			data += "<td width=200>";
			data += "<input type='radio' />" + i;
			data += "</td>";
			data += "<td width=200>";
			data += "<input type='checkbox' />" +i;
			data += "</td>";
			data += "<td width=400>";
			data += dan + " * " + i;
			data += "</td>";
			data += "<td width=400>";
			data += i*dan;
			data += "</td></tr>";
		}
		
		data += "</table>";
		out.print(data);
	}


}
