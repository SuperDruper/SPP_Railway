<%--
  Created by IntelliJ IDEA.
  User: PC-Alyaksei
  Date: 14.03.2016
  Time: 11:50
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<table>
  <tr>
    <td>Login</td>
    <td>Password</td>
    <td>First Name</td>
    <td>Last Name</td>
    <td>Email</td>
    <td>Role</td>
  </tr>
  <s:iterator value="users">
    <tr>
      <td><s:property value="login"/></td>
      <td><s:property value="password"/></td>
      <td><s:property value="name"/></td>
      <td><s:property value="surname"/></td>
      <td><s:property value="email"/></td>
      <td><s:property value="role.name"/></td>
    </tr>
  </s:iterator>
</table>
</body>
</html>
