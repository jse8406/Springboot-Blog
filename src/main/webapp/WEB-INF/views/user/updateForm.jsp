<%@ page contentType = "text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@include file="../layout/header.jsp"%>

<div class="container">
    <form>
        <input type="hidden" id="id" value="${principal.member.id}">
        <div class="form-group">
            <label for="username">Username:</label>
            <input type="text" value="${principal.member.username}" class="form-control" placeholder="Enter username" id="username" readonly>
        </div>
        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" class="form-control" placeholder="Enter password" id="password">
        </div>
        <div class="form-group">
            <label for="email">Email address:</label>
            <input type="email" value="${principal.member.email}" class="form-control" placeholder="Enter email" id="email">
        </div>
    </form>
    <button id="btn-update" class="btn btn-primary">회원 수정 완료</button>
</div>
<script src="/js/user.js"></script>
<%--js파일에서 버튼 클릭 이벤트에 auth/joinProc으로 요청보내도록 되어있음--%>
<%@include file="../layout/footer.jsp"%>
