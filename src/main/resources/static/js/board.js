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
		
		$("#btn-update").on("click", ()=>{   
			this.update();
		} );

		$("#btn-reply-save").on("click", ()=>{   
			this.replySave();
		} );
	},
		
    save: function() {
		let data = {
			title: $("#title").val(),
			content: $("#content").val()
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
 
     update: function() {
     	let id =$("#id").val();
 
 		let data = {
			title: $("#title").val(),
			content: $("#content").val()
		};
		
		$.ajax({
			type: "PUT",
			url: "/api/board/"+id,
			data: JSON.stringify(data),   
			contentType: "application/json; charset=utf-8", 
			dataType: "json" 
		}).done(function(resp){   
			alert("글수정이 완료되었습니다.");
			location.href = "/";
		}).fail(function(){  //요청이 실패 시 수행
			alert(JSON.stringify(error));
			
		});  
    },
 
    replySave: function() {
		let data = {
			content: $("#reply-content").val()
		};
		
		let boardId = $("#boardId").val();
				
		/* URI에 $를 사용 시 싱글 쿼테이션 사용  url: '/api/board/${boardId}/reply'  */
		$.ajax({
			type: "POST",
			url: "/api/board/" + boardId + "/reply",   
			data: JSON.stringify(data),   
			contentType: "application/json; charset=utf-8", 
			dataType: "json" 
			
		}).done(function(resp){   
			alert("댓글작성이 저장되었습니다.");
			location.href = "/board/"+boardId;
			
		}).fail(function(){  //요청이 실패 시 수행
			alert(JSON.stringify(error));
			
		});  
    },
 
    del: function() {
		let id =$("#id").text();
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

