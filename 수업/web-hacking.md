# 수업 개요

1. 웹어플리케이션 웹 해킹 기법 중 대표적인 몇몇 개의 기법을 case study 하면서, 웹 해킹이 어떠한 식으로 이루어지는지 확인하고, 대책을 고민해보면서, 웹/앱 개발자로서 안전한 프로그램 작성하는 방법을 연구한다.

# 수업 대상

1. 커뮤니케이션즈 해킹 사건(2012) - SQL Injection
2. KT 사이트 해킹 사건(2017) - 파일 업로드 취약점, 웹 애플리케이션의 인증 취약점, 웹 애플리케이션의 인증 취약점(brute force attack)
3. 넥슨 해킹 사건 (2020년) - 크로스 사이트 스크립팅(XSS)
4. 카카오 서비스 해킹 사건 (2019년 - 세션 하이재킹(Session Hijacking)

# SQL Injection

```
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class SqlInjectionExample {
    public static void main(String[] args) {
        // 데이터베이스 연결 정보
        String url = "jdbc:mysql://localhost:3306/mydatabase";
        String username = "root";
        String password = "password";

        // 사용자 입력 받기
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username: ");
        String userInput = scanner.nextLine(); // 사용자가 입력한 값

        // SQL 쿼리문에 사용자 입력값을 직접 삽입
        String query = "SELECT * FROM users WHERE username = '" + userInput + "'";

        try {
            // JDBC 연결
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            // 결과 출력
            while (resultSet.next()) {
                System.out.println("User found: " + resultSet.getString("username"));
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

```

# XSS

```
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class XSSExampleServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 사용자 입력을 받기
        String userInput = request.getParameter("username");

        // 사용자 입력을 그대로 HTML로 출력
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h2>Welcome, " + userInput + "!</h2>");
        out.println("</body></html>");
    }
}

```
