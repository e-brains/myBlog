/**
 *  board.js
 */
let index = {
	
	init: function(){
		$("#btn-save").on("click", ()=>{   
			this.save();
		} );
		
		$("#btn-delete").on("click", ()=>{   
			this.del();
		} );
	},
		
    save: function() {
		let data = {
			title: $("#title").val(),
			content: $("#content").val(),
		};
		
		$.ajax({
			type: "POST",
			url: "/api/board",
			data: JSON.stringify(data),   
			contentType: "application/json; charset=utf-8", 
			dataType: "json" 
			
		}).done(function(resp){   
			alert("글쓰기가 완료되었습니다.");
			location.href = "/";
			
		}).fail(function(){  //요청이 실패 시 수행
			alert(JSON.stringify(error));
			
		});  
    },
 
    del: function() {
		var id =$("#id").text();

		$.ajax({
			type: "DELETE",
			url: "/api/board/"+id,
			dataType: "json" 
		}).done(function(resp){   
			alert("삭제가 완료되었습니다.");
			location.href = "/";
		}).fail(function(){  //요청이 실패 시 수행
			alert(JSON.stringify(error));
		});  
    } 
}

index.init();

