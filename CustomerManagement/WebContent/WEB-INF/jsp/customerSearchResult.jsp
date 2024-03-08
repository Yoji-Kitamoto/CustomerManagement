<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="object.Customer" %>
<% List<Customer> searchList = (List<Customer>)request.getAttribute("searchList"); %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css"
			  rel="stylesheet"
			  crossorigin="anonymous">
		<title>顧客検索結果</title>
	</head>
	<body>
		<jsp:include page="header.jsp"/>
		<h2>検索結果</h2>
		<% if(searchList == null) { %>
			<h3>検索条件を入力してください</h3>
		<% } %>
		<table class="table table-striped">
			<thead>
				<tr>
					<th scope="col">顧客 ID</th>
					<th scope="col">お客様名</th>
					<th scope="col">住所</th>
					<th scope="col">登録日</th>
					<th scope="col">更新日</th>
				</tr>
			</thead>
			<tbody>
				<% if(searchList != null) { %>
					<% for(Customer customer : searchList) { %>
						<tr>
							<td><%= customer.getCustomerId() %></td>
							<td><%= customer.getName() %></td>
							<td><%= customer.getAddress() %></td>
							<td><%= customer.getRegisteredTime() %></td>
							<td><%= customer.getUpdatedTime() %></td>
						</tr>
					<% } %>
				<% } %>
			</tbody>
		</table>

		<a href="#" onclick="window.history.back(); return false;" class="btn btn-primary mt-3">検索画面へ</a>
	</body>
</html>