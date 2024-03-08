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
import object.Admin;
import object.Customer;

public class Login {
	public Admin check(String adminId, String password) throws FileNotFoundException {
		// System.out.println(admin_id + " " + password); // 1 199804071517

		// データベースへの接続情報をプロパティファイルから取得
		DbConfig dbInfo = new DbConfig();
		String url  = dbInfo.getDbInfo().get("url");
		String user = dbInfo.getDbInfo().get("user");
		String pass = dbInfo.getDbInfo().get("password");

		// 実行 SQL
		String loginSql = "SELECT * FROM admin_table WHERE admin_id = ? AND password = ?;";

		// 管理者のオブジェクトを作成
		Admin admin = new Admin();

		// データベースへの接続
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		try(Connection connection = DriverManager.getConnection(url, user, pass)) {
			PreparedStatement preparedStatement = connection.prepareStatement(loginSql);

			// loginSql について, 1 番目の ? に admin_id, 2 番目の ? に password をセット
			preparedStatement.setString(1, adminId);
			preparedStatement.setString(2, password);

			// SQL を実行し, 結果を取得
			ResultSet resultSet = preparedStatement.executeQuery();

			// データベースから取得した値を Admin オブジェクトに格納
			// 値がなければ, loginFlag (false) のみを格納
			if(resultSet.next()) {
				admin.setId(resultSet.getInt("admin_id"));
				admin.setName(resultSet.getNString("name"));
				admin.setPassword(resultSet.getString("password"));
				admin.setLoginFlag(true);
			} else {
				admin.setLoginFlag(false);
			}
		} catch(SQLException e) {
			System.out.println("データベースとの接続を切断します");
			e.printStackTrace();
		}

		// データベースから取得した値を返す
		return admin;
	}

	// ログイン成功後に管理者が管理する顧客情報の取得
	public List<Customer> getCustomerInfo(String adminId) throws FileNotFoundException {
		// データベースの接続情報をプロパティファイルから取得
		DbConfig dbInfo = new DbConfig();
		String url  = dbInfo.getDbInfo().get("url");
		String user = dbInfo.getDbInfo().get("user");
		String pass = dbInfo.getDbInfo().get("password");

		// 実行 SQL
		// admin_id で該当する顧客情報を取得する
		String customerSql = "SELECT * FROM customer_table WHERE admin_id = ?;";

		// 顧客情報のデータを格納する List を作成
		List<Customer> customerList = new ArrayList<>();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try(Connection connection = DriverManager.getConnection(url, user, pass)) {
			PreparedStatement preparedStatement = connection.prepareStatement(customerSql);

			preparedStatement.setString(1, adminId);
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
				customerList.add(customerInfo);
			}

		} catch(SQLException e) {
			System.out.println("データベースとの接続を切断します");
			e.printStackTrace();
		}

		return customerList;
	}
}
