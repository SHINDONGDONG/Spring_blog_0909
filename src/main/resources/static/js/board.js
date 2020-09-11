let index= {
    init: function(){
        $("#btn-save").on("click",()=>{ //function()=>{} 사용하지 않는 이유는 this.를 바인딩하기 위하여
            this.save();
        });
    },
    	save: function(){
        /* alert('user의 save function발동'); */
        let data = {
            title:$("#title").val(),
            content:$("#content").val()
        }
        $.ajax({
        type:"POST",
        url:"/api/board", 
        data:JSON.stringify(data),
        contentType:"application/json; charset=utf-8", 
        dataType:"json" 
        }).done(function(resp){
            console.log(resp);
            alert('글쓰기 완료.');
            location.href="/";
        }).fail(function(error){
            alert(JSON.stringify(error));
        }); 
    }
}
index.init();