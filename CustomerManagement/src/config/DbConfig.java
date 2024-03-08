package config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class DbConfig {
	// データベースの接続情報を取得
	public Map<String, String> getDbInfo() throws FileNotFoundException {
		// プロパティファイルのフルパスを設定
		String dbPropertiesFile = "C:\\Users\\Owner\\Desktop\\pleiades\\workspace\\CustomerManagement\\DbConfig.properties";

		Properties dbInfo = new Properties();
		FileInputStream dbFileStream = new FileInputStream(dbPropertiesFile);

		try {
			// プロパティファイルを読み込む
			dbInfo.load(dbFileStream);
		} catch(IOException e) {
			System.out.println("データベース設定ファイルが認識できませんでした");
			e.printStackTrace();
		}

		// DbConfig.properties のキーから値を取得する
		String dbUrl  = dbInfo.getProperty("url");
		String dbUser = dbInfo.getProperty("user");
		String dbPass = dbInfo.getProperty("password");

		// 取得したデータベースの接続情報を Map に格納
		Map<String, String> getDbInfoForMap = new HashMap<>();

		getDbInfoForMap.put("url",      dbUrl);
		getDbInfoForMap.put("user",     dbUser);
		getDbInfoForMap.put("password", dbPass);

		// DbConfig の getDbInfo が呼び出された際に, 「接続情報, ユーザ名, パスワード」の情報を返す
		return getDbInfoForMap;
	}
}
