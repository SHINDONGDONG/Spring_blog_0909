let index = {
	init: function() {
		$("#btn-save").on("click", () => { //function()=>{} 사용하지 않는 이유는 this.를 바인딩하기 위하여
			this.save();
		});
		$("#btn-delete").on("click", () => { //function()=>{} 사용하지 않는 이유는 this.를 바인딩하기 위하여
			this.deleteById();
		});
		$("#btn-update").on("click", () => { //function()=>{} 사용하지 않는 이유는 this.를 바인딩하기 위하여
			this.update();
		});
	},
	save: function() {
		/* alert('user의 save function발동'); */
		let data = {
			title: $("#title").val(),
			content: $("#content").val()
		}
		$.ajax({
			type: "POST",
			url: "/api/board",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"
		}).done(function(resp) {
			console.log(resp);
			alert('글쓰기 완료.');
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	},
	deleteById: function() {
		let id = $("#id").text();
		$.ajax({
			type: "DELETE",
			url: "/api/board/" + id,
			dataType: "json"
		}).done(function(resp) {
			console.log(resp);
			alert('삭제가 완료 되었습니다..');
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	},
	update: function() {
		let id = $("#id").val();

		let data = {
			title: $("#title").val(),
			content: $("#content").val()
		}
		$.ajax({
			type: "PUT",
			url: "/api/board/" + id,
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"
		}).done(function(resp) {
			console.log(resp);
			alert('글수정 완료.');
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	}
}
index.init();