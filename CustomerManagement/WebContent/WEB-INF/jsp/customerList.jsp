<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.lang.*" %>
<%@ page import="java.util.*" %>
<%@ page import="object.Customer" %>
<% List<Customer> customerList = (List<Customer>)request.getAttribute("customer"); %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>顧客一覧画面</title>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css"
			  rel="stylesheet"
			  integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1"
			  crossorigin="anonymous">
	</head>
	<body>
		<jsp:include page="header.jsp"/>
		<div class="mx-auto text-center" style="width: 70%;">
			<h2 class="text-center mb-3">顧客一覧</h2>
			<a href="#" class="btn btn-primary mt-3" onclick="location.href='<%= request.getContextPath() %>/CustomerSearchServlet'">検索</a>
			<a href="#" class="btn btn-primary mt-3" onclick="location.href='<%= request.getContextPath() %>/CustomerSortServlet'">ソート</a>
			<a href="#" class="btn btn-primary mt-3" onclick="location.href='<%= request.getContextPath() %>/CustomerRegisterServlet'">登録</a>
			<table class="table table-striped">
				<thead>
					<tr>
						<th scope="col">顧客 ID</th>
						<th scope="col">お客様名</th>
						<th scope="col">住所</th>
						<th scope="col">登録日</th>
						<th scope="col">更新日</th>
						<th scope="col">操作</th>
					</tr>
				</thead>
				<tbody>
					<% for(Customer customer : customerList) { %>
						<tr>
							<td><%= customer.getCustomerId() %></td>
							<td><%= customer.getName() %></td>
							<td><%= customer.getAddress() %></td>
							<td><%= customer.getRegisteredTime() %></td>
							<td><%= customer.getUpdatedTime() %></td>
							<!-- JSTL を使用して顧客 ID のデータをリンクのパラメータに設定し, サーブレットで取得 -->
							<c:url var="update" value="/CustomerUpdateServlet">
								<c:param name="id" value="<%= String.valueOf(customer.getCustomerId()) %>"></c:param>
							</c:url>

							<c:url var="delete" value="/CustomerDeleteServlet">
								<c:param name="id" value="<%= String.valueOf(customer.getCustomerId()) %>"></c:param>
							</c:url>
							<td><a href="${update}">編集</a> | <a href="${delete}" onclick="return deleteDialog()">削除</a></td>
						</tr>
					<% } %>
				</tbody>
			</table>
		</div>
	</body>

	<script type="text/javascript">
		function deleteDialog() {
			return confirm("選択した顧客データを削除します。よろしいですか?");
		};
	</script>
</html>