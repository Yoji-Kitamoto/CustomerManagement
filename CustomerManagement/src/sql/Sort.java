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

public class Sort {
	public String sortContent(String sortContent) {
		switch(sortContent) {
			case "registrationDate":
				return "registered_time";

			case "updatedDate":
				return "updated_time";

			default:
				return "";
		}
	}

	public String sortRequirement(String sortRequirement) {
		switch(sortRequirement) {
			case "ascending":
				return "ASC";

			case "descending":
				return "DESC";

			default:
				return "";
		}
	}

	public List<Customer> customerSort(String sortContent, String sortRequirement) throws FileNotFoundException {
		// データベースへの接続情報をプロパティファイルから取得
		DbConfig dbInfo = new DbConfig();
		String url  = dbInfo.getDbInfo().get("url");
		String user = dbInfo.getDbInfo().get("user");
		String pass = dbInfo.getDbInfo().get("password");

		// 実行 SQL
		String sortSql = "SELECT * FROM customer_table ORDER BY " + sortContent(sortContent) + " " + sortRequirement(sortRequirement) + ";";

		// 並べ替え情報を格納する List を作成
		List<Customer> sortList = new ArrayList<>();

		// データベースへの接続
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}

		try(Connection connection = DriverManager.getConnection(url, user, pass)) {
			PreparedStatement preparedStatement = connection.prepareStatement(sortSql);

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
				sortList.add(customerInfo);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}

		return sortList;
	}
}
