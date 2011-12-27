<%@page import="java.math.BigDecimal"%>
<%@ page import="aircon.model.Order" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="javax.servlet.http.HttpServletRequest" %>

<%-- <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> --%>

<%
	Order.StateType state = (Order.StateType)request.getAttribute("state");
	boolean newOrder = false;
	int id = -1;
	if (request.getAttribute("id") == null)
		newOrder = true;
	else
		id = (Integer)request.getAttribute("id");
	
	boolean pumpNeeded = (request.getAttribute("pumpNeeded") != null) && (request.getAttribute("pumpNeeded").equals(true));
	BigDecimal fullCost = new BigDecimal("123.45");
%>

<html>
<head>
	<script type="text/javascript">
		function check_additional()
		{
			var selstate = document.getElementById("state");
			var editing_disabled = ((selstate.value == "complete") || (selstate.value == "cancelled")) ? "disabled" : "";
	
			document.getElementById("pipeLineLength_row").style.visibility = ((selstate.value != "new") ? "visible" : "hidden");
			document.getElementById("additionalCoolantAmount_row").style.visibility = ((selstate.value != "new") ? "visible" : "hidden");
			document.getElementById("pumpNeeded_row").style.visibility = ((selstate.value != "new") ? "visible" : "hidden");
			document.getElementById("fullCost_row").style.visibility = ((selstate.value != "new") ? "visible" : "hidden");
			document.getElementById("productManufacturerAndModel").disabled = editing_disabled;
	
			document.getElementById("customerName").disabled = editing_disabled;
			document.getElementById("targetAddress").disabled = editing_disabled;
			document.getElementById("pipeLineLength").disabled = editing_disabled;
			document.getElementById("additionalCoolantAmount").disabled = editing_disabled;
			document.getElementById("pumpNeeded").disabled = editing_disabled;
		}
	</script>
	<style>
		input.incorrect { background: #ffaaaa }
		.incorrect_input_msg { color: #880000 }
	</style>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <title>Редактирование заказа</title>
</head>
<body>
<div>
	<form method="post">
		<table style="width: 700px">
			<tr>
				<td colspan="3">
					<b>Заказ:</b>
				</td>
			</tr>
			<tr>
				<td>Статус: </td>
				<td>
					<select id="state" name="state" onchange="check_additional();" style="width: 250pt" <%= state == Order.StateType.STATE_COMPLETE ? "disabled=\"disabled\"" : "" %>>
						<%
							if (state == Order.StateType.STATE_NEW)
							{
								%><option value="new" selected="selected">Новый</option><%
							}
						%>
						<option value="after_insp" <%= state == Order.StateType.STATE_AFTER_INSPECTION ? "selected=\"selected\"" : "" %>>Осмотрен</option>
						<%
							if (!newOrder)
							{
								if (state == Order.StateType.STATE_AFTER_INSPECTION)
								{
									%><option value="complete" <%= state == Order.StateType.STATE_COMPLETE ? "selected=\"selected\"" : "" %>>Завершен</option><%
								}
								if (state != Order.StateType.STATE_COMPLETE)
								{
									%><option value="cancelled" <%= state == Order.StateType.STATE_CANCELLED ? "selected=\"selected\"" : "" %>>Отменен</option><%
								}
							}
						%>
					</select>
				</td>
			</tr>		

			<tr id="productManufacturerAndModel_row">
				<td style="width: 300pt">Производитель и модель:</td> 
				<td>
					<input style="width: 100%" name="productManufacturerAndModel" id="productManufacturerAndModel" type="text" value="${productManufacturerAndModel}" />
				</td>
			</tr>
			<tr id="customerName_row">
				<td>Наименование заказчика:</td> 
				<td>
					<input style="width: 100%" name="customerName" id="customerName" type="text" value="${customerName}" />
				</td>
			</tr>
			<tr id="targetAddress_row">
				<td>Адрес проведения работ:</td> 
				<td>
					<input style="width: 100%" name="targetAddress" id="targetAddress" type="text" value="${targetAddress}" />
				</td>
			</tr>
			<tr id="pipeLineLength_row">
				<td>Длина магистрали между блоками:</td> 
				<td>
					<input style="width: 100%" name="pipeLineLength" id="pipeLineLength" type="text" value="${pipeLineLength}" />
				</td>
				<td width="30pt">
					, <b>м</b>
				</td>
			</tr>
			<tr id="additionalCoolantAmount_row">
				<td>Количество дозаправленного хладагента:</td> 
				<td>
					<input style="width: 100%" name="additionalCoolantAmount" id="additionalCoolantAmount" type="text" value="${additionalCoolantAmount}" />
				</td>
				<td>
					, <b>кг</b>
				</td>
			</tr>
			<tr id="pumpNeeded_row">
				<td>Необходима установка дренажной помпы:</td> 
				<td>
					<input style="width: 100%" name="pumpNeeded" id="pumpNeeded" type="checkbox" <%= pumpNeeded ? "checked" : "" %>/>
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<input style="margin-top: 10px; width: 100pt; height: 20pt" type="submit" value="Применить" />
				</td>
			</tr>		
			<tr id="fullCost_row" style="visibility: hidden">
				<td style="padding: 10px 0 10px 0">
					<b>Общая стоимость: <%= fullCost.toString() %> р.</b>
				</td>
			</tr>
		</table>
	</form>
</div>
<script type="text/javascript">
	check_additional();
</script>
</body>
</html>
