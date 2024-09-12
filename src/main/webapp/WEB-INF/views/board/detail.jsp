<%@ page language = "java" contentType = "text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@include file="../layout/header.jsp"%>

<div class="container">
        <%--     loginProc는 따로 controller(getmapping)을 작성하지 않아도 시큐리티에서 가로채기 때문에 상관없다.   --%>
            <button class="btn btn-secondary" onclick="history.back()">돌아가기</button>
            <c:if test="${board.member.id == principal.member.id}">
                <a href="/board/${board.id}/updateForm"  class="btn btn-warning">수정</a>
                <button id="btn-delete" class="btn btn-danger">삭제</button>
            </c:if>
            <br/><br/>
            <div>
                글 번호 : <span id="id"><i>${board.id}</i></span>
                작성자 : <span ><i>${board.member.username}</i></span>
            </div>
            <br/>

        <div>
            <h3>${board.title}</h3>
        </div>
        <hr>
        <div>
            <div>
                ${board.content}
            </div>
        </div>
        <hr>
</div>
<script src="/js/board.js"></script>

<%@include file="../layout/footer.jsp"%>