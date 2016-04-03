<!DOCTYPE html>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
	<meta charset="UTF-8">
	<title>Registration</title>
</head>
<body>
	<h3>Registration</h3>

	<s:form action="registerprocess">
		<s:textfield name="user.name" label="First name"/>
		<s:textfield name="user.surname" label="Last name"/>
		<s:textfield name="user.email" label="Email"/>
		<s:textfield name="user.login" label="Login"/>
		<s:password  name="user.password" label="Password"/>
		<s:password  label="Password one more time"/>
		<s:submit/>
	</s:form>

	<hr>

	<s:debug/>
</body>
</html>