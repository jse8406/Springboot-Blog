let index = {
    init: function () {
        $("#btn-save").on("click", () => { //function(){} 대신 ()=>{}를 사용하는 이유는 this 를 바인딩하기 위해서
            this.save();
        });
        $("#btn-update").on("click", () => { //function(){} 대신 ()=>{}를 사용하는 이유는 this 를 바인딩하기 위해서
            this.update();
        });
    },
    save: function () {
        //alert("user 의 save 함수 호출됨");
        let data = {
            username : $("#username").val(),
            password : $("#password").val(),
            email : $("#email").val()
        };
        //console.log(data);
        //ajax 는 default 가 비동기 호출
        $.ajax({
            type: "POST",
            url: "/auth/joinProc",
            data: JSON.stringify(data), // http body 데이터
            contentType: "application/json; charset=utf-8", // body 데이터가 어떤 타입인지(MIME)
            dataType: "json" // 요청을 서버로 해서 응답이 왔을 때 기본적으로 모든 것이 문자열 (생긴게 json 이라면) => javascript 오브젝트로 변경

        }).done(function (resp){ //resp는
            alert("회원가입이 완료되었습니다.");
            location.href = "/";
            console.log(resp);
        }).fail(function (error){
            alert(JSON.stringify(error));
        }); // 통신을 통해서 3개의 파라미터를 json 으로 변경하여 insert 요청
    },

    update: function () {
        //alert("user 의 save 함수 호출됨");
        let data = {
            id : $("#id").val(),
            password : $("#password").val(),
            email : $("#email").val()
        };
        //ajax 는 default 가 비동기 호출
        $.ajax({
            type: "PUT",
            url: "/user",
            data: JSON.stringify(data), // http body 데이터
            contentType: "application/json; charset=utf-8", // body 데이터가 어떤 타입인지(MIME)
            dataType: "json" // 요청을 서버로 해서 응답이 왔을 때 기본적으로 모든 것이 문자열 (생긴게 json 이라면) => javascript 오브젝트로 변경

        }).done(function (resp){ //resp는
            alert("회원수정이 완료되었습니다.");
            location.href = "/";
        }).fail(function (error){
            alert(JSON.stringify(error));
        }); // 통신을 통해서 3개의 파라미터를 json 으로 변경하여 insert 요청
    }
}


index.init();
