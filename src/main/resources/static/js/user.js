let index= {
    init: function(){
        $("#btn-save").on("click",()=>{
            this.save();
        });
    },
    	save: function(){
        /* alert('user의 save function발동'); */
        let data = {
            username:$("#username").val(),
            password:$("#password").val(),
            email:$("#email").val()
        }
        console.log(data);
    }
}
index.init();