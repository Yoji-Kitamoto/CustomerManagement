<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css"
			  rel="stylesheet"
			  integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1"
			  crossorigin="anonymous">
		<title>顧客登録</title>
	</head>
	<body>
		<jsp:include page="header.jsp"/>
		<div class="mx-auto" style="width: 300px;">
			<h1 class="mb-3" style="text-align: center">顧客登録画面</h1>
			<form action="/CustomerManagement/CustomerRegisterServlet" method="post">
				<div class="mb-3">
					<label for="customerName" class="form-label">お客様名</label>
					<input type="text" class="form-control" id="customerName" name="customerName" required>
				</div>
				<div class="mb-3">
					<label for="address" class="form-label">住所</label>
					<input type="text" class="form-contorl" id="address" name="address" required>
				</div>
				<button type="submit" class="btn btn-primary" onclick="return registerDialog()">登録</button>
			</form>

			<a href="#" onclick="window.history.back(); return false;" class="btn btn-primary mt-3">顧客一覧へ</a>
		</div>
	</body>

	<script type="text/javascript">
		function registerDialog() {
			return confirm("この内容で登録します。よろしいですか?");
		}
	</script>
</html>