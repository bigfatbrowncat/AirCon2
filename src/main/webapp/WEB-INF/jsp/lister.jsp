<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="javax.servlet.http.HttpServletRequest" %>

<%-- <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> --%>

<%!
	String putChecked(HttpServletRequest request, String name)
	{
		return ((Boolean)request.getAttribute(name)) ? "checked=\"checked\"" : "";
	}
%>

<html>
<head>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <title>Список заказов</title>
</head>
<body>
<div> 
	<form method="get">
		<table style="width: 500px">
			<tr>
			<td colspan="2">
				<b>Поиск заказа:</b>
			</td>
			</tr>

			<tr>
				<td>Заказчик: </td><td><input style="width: 250px" name="customerNameSearchRequest" value="${customerNameSearchRequest}"/></td>
			</tr>		
			<tr>		
				<td>Марка и модель товара: </td><td><input style="width: 250px" name="productManufacturerAndModelSearchRequest" value="${productManufacturerAndModelSearchRequest}" /></td>
			</tr>		
			<tr>		
				<td>Адрес установки: </td><td><input style="width: 250px" name="targetAddressSearchRequest" value="${targetAddressSearchRequest}" /></td>
			</tr>		
			<tr>		
				<td>Новый заказ: </td><td><input name="searchNew" style="width: 250px" type="checkbox" <%= putChecked(request, "searchNew") %>/></td>
			</tr>		
			<tr>		
				<td>Осмотренный заказ: </td><td><input name="searchInspected" style="width: 250px" type="checkbox" <%= putChecked(request, "searchInspected") %>/></td>
			</tr>		
			<tr>		
				<td>Выполненный заказ: </td><td><input name="searchCompleted" style="width: 250px" type="checkbox" <%= putChecked(request, "searchCompleted") %>/></td>
			</tr>		
			<tr>		
				<td>Отмененный заказ: </td><td><input name="searchCancelled" style="width: 250px" type="checkbox" <%= putChecked(request, "searchCancelled") %>/></td>
			</tr>		
		</table>
		<input style="padding: 2px; margin: 2px; margin-top: 5px;" type="submit" value="Искать" />		
	</form>
</div>

</body>
</html>
