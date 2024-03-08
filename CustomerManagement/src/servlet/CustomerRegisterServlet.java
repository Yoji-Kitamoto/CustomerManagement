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
import sql.Register;

/**
 * Servlet implementation class CustomerRegisterServlet
 */
@WebServlet("/CustomerRegisterServlet")
public class CustomerRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerRegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		// 顧客登録画面を表示
		request.getRequestDispatcher("WEB-INF/jsp/customerRegister.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		// 顧客登録画面に入力された値をもとに, 顧客情報をデータベースへ登録

		//文字コードの設定
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		// 顧客登録画面で入力された値を取得
		String customerName    = request.getParameter("customerName");
		String customerAddress = request.getParameter("address");

		// 管理者のセッションを取得
		HttpSession httpSession = request.getSession(true);
		Admin admin = (Admin)httpSession.getAttribute("admin");

		Register register = new Register();

		// 顧客情報の登録処理を実行
		register.customerRegister(admin.getId(), customerName, customerAddress);

		Login login = new Login();
		List<Customer> customer = null;

		// データベースから取得した顧客情報を格納
		customer = login.getCustomerInfo(String.valueOf(admin.getId()));

		// 格納した顧客情報を遷移先の画面に渡す
		request.setAttribute("customer", customer);

		request.getRequestDispatcher("WEB-INF/jsp/customerList.jsp").forward(request, response);
	}

}
