<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../resources/css/bootstrap.css" />
<script src="../resources/jquery/jquery-3.6.0.js"></script>
<title>Insert title here</title>
</head>
<body>
	<form action="buyTicketAction.do" method="post" 
		name="ticketFrm">
	<!-- table>tr*3>td*2 -->
	<table class="table table-bordered" 
		style="width:500px;">
		<tr>
			<td>고객아이디</td>
			<td>
				<input type="text" name="customerId" />
			</td>
		</tr>
		<tr>
			<td>티켓구매수</td>
			<td>
				<select name="amount">
				<%
				for(int i=1 ; i<=10 ; i++){
				%>
					<option value="<%=i%>"><%=i%>장</option>
				<%} %>
				</select>				
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<button type="submit" class="btn 
					btn-warning">구매하기</button>
			</td>
		</tr>
	</table>
	</form>
</body>
</html>