<%@ page language = "java" contentType = "text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<%@include file="../layout/header.jsp"%>

<div class="container">
    <form>
        <%--     loginProc는 따로 controller(getmapping)을 작성하지 않아도 시큐리티에서 가로채기 때문에 상관없다.   --%>
        <input type="hidden" id="id" value="${board.id}">
        <div class="form-group">
            <input value="${board.title}" type="text" name="username" class="form-control" placeholder="제목을 기입해주세요." id="title">
        </div>

        <div class="form-group">
            <textarea class="form-control summernote" rows="5" id="content">${board.content}</textarea>
        </div>
    </form>
    <button id="btn-update" class="btn btn-primary">글수정 완료</button>

</div>
<script>
    $('.summernote').summernote({
        placeholder: '내용을 기입해주세요.',
        tabsize: 2,
        height: 300
    });
</script>
<script src="/js/board.js"></script>

<%@include file="../layout/footer.jsp"%>