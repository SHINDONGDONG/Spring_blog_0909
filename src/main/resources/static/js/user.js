let index= {
    init: function(){
        $("#btn-save").on("click",()=>{ //function()=>{} //사용하지 않는 이유는 this.를 바인딩하기 위하여
            this.save();
        });
        $("#btn-update").on("click",()=>{ //function()=>{} //사용하지 않는 이유는 this.를 바인딩하기 위하여
            this.update();
        });
    },
    	save: function(){
        /* alert('user의 save function발동'); */
        let data = {
            username:$("#username").val(),
            password:$("#password").val(),
            email:$("#email").val()
        }
        // console.log(data);
        //ajax호출시 기본은 비동기호출
        $.ajax({
        //회원가입 수행요청
        type:"POST",
        url:"/auth/joinProc", //api/user으로 가서 회원가입을 시킨다
        data:JSON.stringify(data),
        contentType:"application/json; charset=utf-8", //body타입이 어떤타입인지
        dataType:"json" //요청을 서버로해서 응답이왔을때 기본적으로 String (생긴게 json이라면) =>javascript오브젝트로 변경해준다.
        }).done(function(resp){
        	if(resp.status === 500){ 
			alert('회원가입에 실패하였습니다');        		
        	}else{
            alert('회원가입이 완료 되었습니다.');
            location.href="/";
        	}
        
        }).fail(function(error){
            alert(JSON.stringify(error));
        }); //ajax통신을 통해서 3개의 데이터를 json으로 변경하여 insert요청을 할것임.
    },
        	update: function(){
        /* alert('user의 save function발동'); */
        let data = {
            id:$("#id").val(),
            username:$("#username").val(),
            password:$("#password").val(),
            email:$("#email").val()
        }
        // console.log(data);
        //ajax호출시 기본은 비동기호출
        $.ajax({
        //회원가입 수행요청
        type:"PUT",
        url:"/user", //api/user으로 가서 회원가입을 시킨다
        data:JSON.stringify(data),
        contentType:"application/json; charset=utf-8", //body타입이 어떤타입인지
        dataType:"json" //요청을 서버로해서 응답이왔을때 기본적으로 String (생긴게 json이라면) =>javascript오브젝트로 변경해준다.
        }).done(function(resp){
            console.log(resp);
            alert('회원수정 완료 되었습니다.');
            location.href="/";
        }).fail(function(error){
            alert(JSON.stringify(error));
        }); //ajax통신을 통해서 3개의 데이터를 json으로 변경하여 insert요청을 할것임.
    }
}
index.init();