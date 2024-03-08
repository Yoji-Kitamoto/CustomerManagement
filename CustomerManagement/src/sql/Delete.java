package sql;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import config.DbConfig;

public class Delete {
	public void customerDelete(int customerId) throws FileNotFoundException {
		// データベースへの接続情報をプロパティファイルから取得
		DbConfig dbInfo = new DbConfig();
		String url  = dbInfo.getDbInfo().get("url");
		String user = dbInfo.getDbInfo().get("user");
		String pass = dbInfo.getDbInfo().get("password");

		// 実行 SQL
		String deleteSql = "DELETE FROM customer_table WHERE customer_id = ?;";

		// データベースへの接続
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try(Connection connection = DriverManager.getConnection(url, user, pass)) {
			// オートコミットを無効化
			connection.setAutoCommit(false);

			try(PreparedStatement preparedStatement = connection.prepareStatement(deleteSql)) {
				// deleteSql の ? に customerId を設定
				preparedStatement.setInt(1, customerId);

				// SQL の実行
				preparedStatement.executeUpdate();

				// コミット
				connection.commit();

				System.out.println("削除処理が成功しました");
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
