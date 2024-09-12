let index = {
    init: function () {
        $("#btn-save").on("click", () => { //function(){} 대신 ()=>{}를 사용하는 이유는 this 를 바인딩하기 위해서
            this.save();
        });
        $("#btn-delete").on("click", () => { //function(){} 대신 ()=>{}를 사용하는 이유는 this 를 바인딩하기 위해서
            this.deleteById();
        });
        $("#btn-update").on("click", () => { //function(){} 대신 ()=>{}를 사용하는 이유는 this 를 바인딩하기 위해서
            this.update();
        });

    },
    save: function () {
        //alert("user 의 save 함수 호출됨");
        let data = {
            title : $("#title").val(),
            content: $("#content").val(),
        };
        $.ajax({
            type: "POST",
            url: "/api/board",
            data: JSON.stringify(data), // http body 데이터
            contentType: "application/json; charset=utf-8", // body 데이터가 어떤 타입인지(MIME)
            dataType: "json" // 요청을 서버로 해서 응답이 왔을 때 기본적으로 모든 것이 문자열 (생긴게 json 이라면) => javascript 오브젝트로 변경

        }).done(function (resp){ //resp는
            alert("글쓰기가 완료되었습니다.");
            location.href = "/";
        }).fail(function (error){
            alert(JSON.stringify(error));
        }); // 통신을 통해서 3개의 파라미터를 json 으로 변경하여 insert 요청
    },

    deleteById: function () {

        let id = $("#id").text();
        //alert("user 의 save 함수 호출됨");
        $.ajax({
            type: "DELETE",
            url: "/api/board/"+id,

        }).done(function (resp){ //resp는
            alert("삭제가 완료되었습니다.");
            location.href = "/";
        }).fail(function (error){
            alert(JSON.stringify(error));
        }); // 통신을 통해서 3개의 파라미터를 json 으로 변경하여 insert 요청
    },

    update: function () {
        let id = $("#id").val();
        
        let data = {
            title : $("#title").val(),
            content: $("#content").val(),
        };
        //alert("user 의 save 함수 호출됨");
        $.ajax({
            type: "PUT",
            url: "/api/board/"+ id,
            data: JSON.stringify(data), // http body 데이터
            contentType: "application/json; charset=utf-8", // body 데이터가 어떤 타입인지(MIME)
            dataType: "json" // 요청을 서버로 해서 응답이 왔을 때 기본적으로 모든 것이 문자열 (생긴게 json 이라면) => javascript 오브젝트로 변경
        }).done(function (resp){ //resp는
            console.log(data);
            console.log(id);
            console.log("why")
            alert("글수정이 완료되었습니다.");
            location.href = "/";
        }).fail(function (error){
            alert(JSON.stringify(error));
        }); // 통신을 통해서 3개의 파라미터를 json 으로 변경하여 insert 요청
    },


}


index.init();
