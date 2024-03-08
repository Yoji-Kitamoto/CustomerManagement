<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css"
			  rel="stylesheet"
			  crossorigin="anonymous">
		<title>顧客検索</title>
	</head>
	<body>
		<jsp:include page="header.jsp"/>
		<div class="mx-auto" style="width: 300px;">
			<h1 class="mb-3" style="text-align: center">顧客検索画面</h1>
			<form action="/CustomerManagement/CustomerSearchServlet" method="post">
				<div class="mb-3">
					<label for="requirementContent" class="form-label">項目</label>
					<select class="form-control" id="requirementContent" name="requirementContent" required>
						<option value="" disabled selected>▼選択してください</option>
						<option value="customerName">お客様名</option>
						<option value="address">住所</option>
						<option value="registrationDate">登録日</option>
						<option value="updatedDate">更新日</option>
					</select>
				</div>
				<div class="mb-3">
					<label for="requirementText" class="form-label">条件</label>
					日付は yyyy-mm-dd の形式で入力してください。<br>
					(例: 2010 年 3 月 9 日 -> 2010-03-09)<br>
					<input type="text" class="form-control" id="requirementText" name="requirementText" required>
					<select class="form-control" id="requirementSelect" name="requirementSelect" required>
						<option value="" disabled selected>▼選択してください</option>
						<option value="perfectMatch">完全一致</option>
						<option value="partialMatch">部分一致</option>
						<option value="lessThan">未満 (より前)</option>
						<option value="over">超 (より後)</option>
						<option value="orLess">以下 (以前)</option>
						<option value="orMore">以上 (以後)</option>
					</select>
				</div>
				<button type="submit" class="btn btn-primary" onclick="location.href='<%= request.getContextPath() %>/CustomerSearchResultServlet'">検索</button>
			</form>

			<a href="#" onclick="window.history.back(); return false;" class="btn btn-primary mt-3">顧客一覧へ</a>
		</div>
	</body>

	<script type="text/javascript">
	</script>
</html>