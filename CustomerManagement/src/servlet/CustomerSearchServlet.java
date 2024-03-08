package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import object.Customer;
import sql.Search;

/**
 * Servlet implementation class CustomerSearch
 */
@WebServlet("/CustomerSearchServlet")
public class CustomerSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.getRequestDispatcher("WEB-INF/jsp/customerSearch.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		// 検索処理の実装

		// 文字コードの設定
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		// 顧客検索画面で入力された値を取得
		String requirementContent = request.getParameter("requirementContent");
		String requirementText    = request.getParameter("requirementText");
		String requirementSelect  = request.getParameter("requirementSelect");

		Search search = new Search();

		// 検索処理を実行
		List<Customer> searchList = null;
		searchList = search.customerSearch(requirementContent, requirementText, requirementSelect);

		// 格納した顧客情報を遷移先の画面に渡す
		request.setAttribute("searchList", searchList);

		request.getRequestDispatcher("WEB-INF/jsp/customerSearchResult.jsp").forward(request, response);
	}

}
