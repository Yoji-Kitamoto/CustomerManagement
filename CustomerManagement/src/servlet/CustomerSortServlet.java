package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import object.Customer;
import sql.Sort;

/**
 * Servlet implementation class CustomerSortServlet
 */
@WebServlet("/CustomerSortServlet")
public class CustomerSortServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerSortServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		// 顧客情報の並べ替え画面を表示
		request.getRequestDispatcher("WEB-INF/jsp/customerSort.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		// 文字コードの設定
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		// 顧客並べ替え画面で入力された値を取得
		String sortContent     = request.getParameter("sortContent");
		String sortRequirement = request.getParameter("sortRequirement");

		Sort sort = new Sort();

		// 並び替え処理を実行
		List<Customer> sortList = null;
		sortList = sort.customerSort(sortContent, sortRequirement);

		// 格納した顧客情報を遷移先の画面に渡す
		request.setAttribute("sortList", sortList);

		request.getRequestDispatcher("WEB-INF/jsp/customerSortResult.jsp").forward(request, response);
	}

}
