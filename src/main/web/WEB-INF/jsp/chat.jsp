<%--
  Created by IntelliJ IDEA.
  User: zhaohaifeng
  Date: 15/1/3
  Time: 下午4:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>聊天页面</title>
</head>
<body>
<input id="userId" type="hidden" value="${userId}"/>
每次刷新，都会给当前tab一个新的userId，输入目标用户id即可。
<div id="chat-content">
    你的userId是${userId}
    <ul id="chat-list"></ul>
</div>
<div>
    <textarea id="message"></textarea>
    <input id='submit' type="submit" value="发送"/>
</div>
<div>
    <label>发送给:</label><input id="to-user-id" value=""/>
</div>
</body>
<script src="/resources/js/jquery-1.8.3.min.js"></script>
<script src="/resources/js/sockjs-0.3.4.js"></script>
<script src="/resources/js/stomp.js"></script>
<script src="/resources/js/chat.js"></script>
</html>
