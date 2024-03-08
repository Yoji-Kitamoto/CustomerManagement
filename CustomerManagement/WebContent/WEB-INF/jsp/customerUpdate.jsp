<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="object.Customer" %>
<% Customer customer = (Customer)request.getAttribute("customer"); %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css"
			  rel="stylesheet"
			  integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1"
			  crossorigin="anonymous">
		<title>顧客情報の変更</title>
	</head>
	<body>
		<jsp:include page="header.jsp"/>
		<div class="mx-auto" style="width: 300px;">
			<h1 class="mb-3" style="text-align: center">顧客情報の変更</h1>
			<form action="/CustomerManagement/CustomerUpdateServlet" method="post">
				<input type="hidden" name="id" value="<%= customer.getCustomerId() %>">
				<div class="mb-3">
					<label for="name" class="form-label">お客様名</label>
					<input type="text" class="form-control" id="name" name="name" value="<%= customer.getName() %>" required>
				</div>
				<div class="mb-3">
					<label for="address" class="form-label">住所</label>
					<input type="text" class="form-control" id="address" name="address" value="<%= customer.getAddress() %>" required>
				</div>
				<button type="submit" class="btn btn-primary" onclick="return updateDialog()">決定</button>
			</form>
			<a href="#" onclick="window.history.back(); return false;" class="btn btn-primary mt-3">顧客一覧へ</a>
		</div>
	</body>

	<script>
		function updateDialog() {
			return confirm("顧客情報を更新します。よろしいですか?");
		}
	</script>
</html>