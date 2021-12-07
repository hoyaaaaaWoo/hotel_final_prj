<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
session.invalidate();
response.sendRedirect("http://localhost/hotel_prj/admin/common/admin_logout_view.jsp");
%>
