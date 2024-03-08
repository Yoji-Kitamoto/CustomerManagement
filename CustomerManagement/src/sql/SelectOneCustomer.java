package sql;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import config.DbConfig;
import object.Customer;

public class SelectOneCustomer {
	public Customer getOneCustomerInfo(int customerId) throws FileNotFoundException {
		// データベースの接続情報をプロパティファイルから取得
		DbConfig dbInfo = new DbConfig();
		String url  = dbInfo.getDbInfo().get("url");
		String user = dbInfo.getDbInfo().get("user");
		String pass = dbInfo.getDbInfo().get("password");

		// 該当する顧客情報のオブジェクトを作成
		Customer oneCustomer = new Customer();

		// 実行 SQL (customerId で該当する顧客情報を取得する)
		String oneCustomerSql = "SELECT * FROM customer_table WHERE customer_id = ?;";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try(Connection connection = DriverManager.getConnection(url, user, pass)) {
			PreparedStatement preparedStatement = connection.prepareStatement(oneCustomerSql);

			preparedStatement.setInt(1, customerId);
			ResultSet resultSet = preparedStatement.executeQuery();

			while(resultSet.next()) {
				// オブジェクトにデータを一時格納
				oneCustomer.setCustomerId(resultSet.getInt("customer_id"));
				oneCustomer.setName(resultSet.getString("name"));
				oneCustomer.setAddress(resultSet.getString("address"));
			}

;		} catch(SQLException e) {
			System.out.println("データベースの接続を切断します");
			e.printStackTrace();
		}

		return oneCustomer;
	}
}
