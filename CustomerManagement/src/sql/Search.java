package sql;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import config.DbConfig;
import object.Customer;

public class Search {
	public String requirementContent(String requirementContent) {
		switch(requirementContent) {
			case "customerName":
				return "name";

			case "address":
				return "address";

			case "registrationDate":
				//
				return "DATE_FORMAT(registered_time, '%Y-%m-%d')";

			case "updatedDate":
				//
				return "DATE_FORMAT(updated_time, '%Y-%m-%d')";

			default:
				return "";
		}
	}

	public String requirement(String requirementContent, String requirementText, String requirementSelect) {
		String content = requirementContent(requirementContent);

		if(content.equals("")) return "";

		switch(requirementSelect) {
			case "perfectMatch":
				return " WHERE " + content +" LIKE \"" + requirementText + "\"";

			case "partialMatch":
				return " WHERE " + content + " LIKE \"%" + requirementText + "%\"";

			case "lessThan":
				return " WHERE " + content + " < \"" + requirementText + "\"";

			case "over":
				return " WHERE " + content + " > \"" + requirementText + "\"";

			case "orLess":
				return " WHERE " + content + " <= \"" + requirementText + "\"";

			case "orMore":
				return " WHERE " + content + " >= \"" + requirementText + "\"";

			default:
				return "";
		}
	}

	public List<Customer> customerSearch(String requirementContent, String requirementText, String requirementSelect) throws FileNotFoundException {
		// データベースへの接続情報をプロパティファイルから取得
		DbConfig dbInfo = new DbConfig();
		String url  = dbInfo.getDbInfo().get("url");
		String user = dbInfo.getDbInfo().get("user");
		String pass = dbInfo.getDbInfo().get("password");

		// 実行 SQL
		String searchSql = "SELECT * FROM customer_table" + requirement(requirementContent, requirementText, requirementSelect) + ";";

		// 検索情報を格納する List を作成
		List<Customer> searchList = new ArrayList<>();

		// データベースへの接続
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try(Connection connection = DriverManager.getConnection(url, user, pass)) {
			PreparedStatement preparedStatement = connection.prepareStatement(searchSql);

			ResultSet resultSet = preparedStatement.executeQuery();

			while(resultSet.next()) {
				// 顧客情報用のオブジェクトを作成
				Customer customerInfo = new Customer();

				// オブジェクトにデータを一時格納
				customerInfo.setCustomerId(resultSet.getInt("customer_id"));
				customerInfo.setAdminId(resultSet.getInt("admin_id"));
				customerInfo.setName(resultSet.getString("name"));
				customerInfo.setAddress(resultSet.getString("address"));
				customerInfo.setRegisteredTime(resultSet.getDate("registered_time"));
				customerInfo.setUpdatedTime(resultSet.getDate("updated_time"));

				// オブジェクトに格納された顧客情報用データをリストに加える
				searchList.add(customerInfo);
			}
		} catch(SQLException e) {
			System.out.println("データベースとの接続を切断します");
			e.printStackTrace();
		}

		return searchList;
	}
}
