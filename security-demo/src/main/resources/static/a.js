<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登录</title>
</head>
<body>
	<h2>标准登录页面</h2>
	<h3>表单登录</h3>
	<form action="/authentication/form" method="post">
		<table>
			<tr>
				<td>用户名:</td> 
				<td><input type="text" name="username"></td>
			</tr>
			<tr>
				<td>密码:</td>
				<td><input type="password" name="password"></td>
			</tr>
			<tr>
				<td colspan="2"><button type="submit">登录</button></td>
			</tr>
		</table>
	</form>
	
</body>
</html>