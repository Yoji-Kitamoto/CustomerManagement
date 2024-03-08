<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
	</head>
	<body>
		<nav class="navbar navbar-expand-sm navbar-dark bg-dark mb-5">
			<div class="collapse navbar-collapse justify-content-end" id="navbarNav4">
				<ul class="navbar-nav">
					<li class="nav-item">
						<a class="nav-link" href="<%= request.getContextPath() %>/Logout" onclick="return logoutDialog()">ログアウト</a>
					</li>
				</ul>
			</div>
		</nav>
	</body>
	<script type="text/javascript">
		function logoutDialog() {
			return confirm("ログアウトします。よろしいですか?");
		}
	</script>
</html>