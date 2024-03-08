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
import sql.SelectOneCustomer;
import sql.Update;

/**
 * Servlet implementation class CustomerUpdateServlet
 */
@WebServlet("/CustomerUpdateServlet")
public class CustomerUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		// 「顧客編集画面」への遷移

		// 文字コードの設定
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		// 「編集」リンクから顧客 ID を取得
		int id = Integer.parseInt(request.getParameter("id"));

		SelectOneCustomer oneCustomer = new SelectOneCustomer();

		// リンクで選択された顧客情報を取得する
		Customer customer = oneCustomer.getOneCustomerInfo(id);

		// 遷移先画面に値を渡す
		request.setAttribute("customer", customer);

		request.getRequestDispatcher("WEB-INF/jsp/customerUpdate.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		// 顧客編集処理

		// 文字コードの設定
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String address = request.getParameter("address");

		Update sql = new Update();

		// 顧客情報編集 (更新) 処理を実行
		sql.customerUpdate(name, address, id);

		// 管理者のセッションを取得
		HttpSession session = request.getSession(true);
		Admin admin = (Admin)session.getAttribute("admin");

		Login login = new Login();
		List<Customer> customer = null;

		// データベースから取得した顧客情報を格納
		customer = login.getCustomerInfo(String.valueOf(admin.getId()));

		// 格納した顧客情報を遷移先の画面に渡す
		request.setAttribute("customer", customer);

		request.getRequestDispatcher("WEB-INF/jsp/customerList.jsp").forward(request, response);
	}

}
