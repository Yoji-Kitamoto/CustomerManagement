package sql;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import config.DbConfig;

public class Register {
	public void customerRegister(int adminId, String name, String address) throws FileNotFoundException {
		// データベースへの接続情報をプロパティファイルから取得
		DbConfig dbInfo = new DbConfig();
		String url  = dbInfo.getDbInfo().get("url");
		String user = dbInfo.getDbInfo().get("user");
		String pass = dbInfo.getDbInfo().get("password");

		// 実行 SQL
		String registerSql = "INSERT INTO customer_table(admin_id, name, address) VALUES(?, ?, ?);";

		// データベースへの接続
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try(Connection connection = DriverManager.getConnection(url, user, pass)) {
			// オートコミット機能を無効化
			connection.setAutoCommit(false);

			try(PreparedStatement preparedStatement = connection.prepareStatement(registerSql)) {
				// registerSql の各 ? について, adminId, name, address を順に設定
				preparedStatement.setInt(1, adminId);
				preparedStatement.setString(2, name);
				preparedStatement.setString(3, address);

				// SQL の実行
				preparedStatement.executeUpdate();

				// コミット
				connection.commit();
				System.out.println("コミット処理を行いました");
			} catch(SQLException e2) {
				connection.rollback();
				System.out.println("ロールバック処理を行いました");
				e2.printStackTrace();
			}
		} catch(SQLException e1) {
			e1.printStackTrace();
		}

	}
}
