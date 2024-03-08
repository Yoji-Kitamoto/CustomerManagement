<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<!-- BootStrap を使用するための CDN -->
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css"
			  rel="stylesheet"
			  integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1"
			  crossorigin="anonymous">
		<title>ログイン画面</title>
	</head>
	<body>
		<div class="mx-auto" style="width: 300px;">
			<!-- ログインボタン押下後, 処理を LoginServlet の doPost に渡す -->
			<h1 class="mb-3" style="text-align: center">管理者としてログイン</h1>
			<form action="/CustomerManagement/LoginServlet" method="post">
				<div class="mb-3">
					<!-- 入力された管理者 ID の値を LoginServlet の doPost に渡す -->
					<label for="adminID" class="form-label">管理者 ID</label>
					<input type="text" class="form-control" id="adminID" name="admin_id">
				</div>
				<div class="mb-3">
					<!-- 入力されたパスワードを LoginServlet の doPost に渡す -->
					<label for="pass" class="form-label">パスワード</label>
					<input type="password" class="form-control" id="pass" name="password">
				</div>
				<button type="submit" class="btn btn-primary">ログイン</button>
			</form>
		</div>
	</body>
</html>