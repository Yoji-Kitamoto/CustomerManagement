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
import sql.Delete;
import sql.Login;

/**
 * Servlet implementation class CustomerDeleteServlet
 */
@WebServlet("/CustomerDeleteServlet")
public class CustomerDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		// 文字コードの設定
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		// 「削除」リンクから対象の顧客 ID を取得
		int id = Integer.parseInt(request.getParameter("id"));

		Delete sql = new Delete();

		// 顧客削除処理を実行
		sql.customerDelete(id);

		// 管理者のセッションを取得
		HttpSession httpSession = request.getSession(true);
		Admin admin = (Admin)httpSession.getAttribute("admin");

		Login login = new Login();
		List<Customer> customer = null;

		// データベースから取得した顧客情報を格納
		customer = login.getCustomerInfo(String.valueOf(admin.getId()));

		// 格納した顧客情報を遷移先の画面に渡す
		request.setAttribute("customer", customer);

		request.getRequestDispatcher("WEB-INF/jsp/customerList.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
