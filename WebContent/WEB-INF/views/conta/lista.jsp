<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table style="height: 10px; width: 775px;" border="1">
		<tr>
			<th>Código</th>
			<th>Descrição</th>
			<th>Valor</th>
			<th>Tipo</th>
			<th>Pago</th>
			<th>Data de Pagamento</th>
			<th>Ações</th>
		</tr>

		<c:forEach items="${todasContas}" var="conta">
			<tr>
				<td>${conta.id}</td>
				<td>${conta.descricao}</td>
				<td>${conta.valor}</td>
				<td>${conta.tipo}</td>
				<td><c:if test="${conta.paga eq false}">
						Não Paga!
					</c:if> <c:if test="${conta.paga eq true}">
						Paga!
					</c:if></td>
				<td><fmt:formatDate value="${conta.dataPagamento.time}"	pattern="dd/MM/yyyy" /></td>
				<td>
					<a href="removeConta?id=${conta.id}">Deletar</a>
				</td>
				<td>
					<a href="mostraConta?id=${conta.id}">Alterar</a>
				</td>
						
			</tr>
		</c:forEach>
	</table>
</body>
</html>