<%@page import="com.open.service.impl.ServiceImpl"%>
<%@page import="com.open.service.IService"%>
<%@page import="com.open.dao.impl.DaoImpl"%>
<%@page import="com.open.dao.IDao"%>
<%@page import="java.util.UUID"%>
<%@page import="com.open.userinfo.UserInfo"%>
<%@page import="java.util.Date"%>
<%@page import="com.o.connection.ConnectionManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.test.TestCase"%>
<%@page import="com.open.spring.SpringContext"%>
<%
IService s = (IService)SpringContext.getInstance().getBean("service");
UserInfo user = new UserInfo();
user = (UserInfo)s.find(UserInfo.class, "1");
//user.setId(UUID.randomUUID().toString());
user.setName("atseae");
s.update(user);
System.out.println(user.getName());

%>