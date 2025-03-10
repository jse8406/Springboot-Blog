<%@ page contentType = "text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@include file="../layout/header.jsp"%>

<div class="container">
    <form action="/auth/loginProc" method="post">
        <%--     loginProc는 따로 controller(getmapping)을 작성하지 않아도 시큐리티에서 가로채기 때문에 상관없다.   --%>
        <div class="form-group">
            <label for="username">Username:</label>
            <input type="text" name="username" class="form-control" placeholder="Enter username" id="username">
        </div>
        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" name="password" class="form-control" placeholder="Enter password" id="password">
        </div>
        <button  id="btn-login" class="btn btn-primary">로그인</button>
            <a href="https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=afd288e39a67101a3d6da66e9b32ecbc&redirect_uri=http://localhost:8080/auth/kakao/callback&prompt=consent"><img height="38px" src="/image/kakao_login_button.png" alt=""></a>
        </a>
    </form>

</div>

<%@include file="../layout/footer.jsp"%>
