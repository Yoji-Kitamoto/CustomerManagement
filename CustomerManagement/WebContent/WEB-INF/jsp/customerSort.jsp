<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css"
			  rel="stylesheet"
			  crossorigin="anonymous">
		<title>顧客情報の並び替え</title>
	</head>
	<body>
		<jsp:include page="header.jsp"/>
		<div class="mx-auto" style="width: 300px;">
			<h1 class="mb-3" style="text-align: center">顧客の並び替え</h1>
			<form action="/CustomerManagement/CustomerSortServlet" method="post">
				<div class="mb-3">
					<label for="sortContent" class="form-label">項目</label>
					<select class="form-control" id="sortContent" name="sortContent" required>
						<option value="" disabled selected>▼選択してください</option>
						<option value="registrationDate">登録日</option>
						<option value="updatedDate">更新日</option>
					</select>
				</div>
				<div class="mb-3">
					<label for="sortRequirement" class="form-label">条件</label>
					<select class="form-control" id="sortRequirement" name="sortRequirement" requiered>
						<option value="">▼選択してください</option>
						<option value="ascending">昇順</option>
						<option value="descending">降順</option>
					</select>
				</div>
				<button type="submit" class="btn btn-primary" onclick="location.href='<%= request.getContextPath() %>/CustomerSortResultServlet'">並び替え</button>
			</form>

			<a href="#" onclick="window.history.back(); return false;" class="btn btn-primary mt-3">顧客一覧へ</a>
		</div>
	</body>
</html>