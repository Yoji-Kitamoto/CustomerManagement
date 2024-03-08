package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import object.Admin;
import object.Customer;
import sql.Login;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		// ログイン画面を表示
		request.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		// ログイン処理の実装

		// 文字コードの設定
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		// ログイン画面で入力された値を取得
		String adminId   = request.getParameter("admin_id");
		String password = request.getParameter("password");

		// ログイン画面で入力された値をもとに, データベースに登録された管理者の値を取得
		// 入力された情報でデータベースから値が取得できない場合, ログイン失敗
		Login login = new Login();
		Admin admin = login.check(adminId, password);

		if(admin.isLogin()) {
			// ログイン成功 -> 次の画面へ遷移
			System.out.println("ログイン成功");

			// ログイン成功時にセッションオブジェクトを作成
			HttpSession adminSession = request.getSession(true);

			// セッションに管理者情報 (オブジェクト) を格納
			adminSession.setAttribute("admin", admin);

			List<Customer> customer = null;

			// データベースから取得した顧客情報を格納
			customer = login.getCustomerInfo(adminId);

			// 格納した顧客情報を遷移先の画面に渡す
			request.setAttribute("customer", customer);

			request.getRequestDispatcher("WEB-INF/jsp/customerList.jsp").forward(request, response);
		} else {
			// ログイン失敗 -> ログイン画面へ遷移
			System.out.println("ログイン失敗");
			request.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(request, response);
		}
	}

}
