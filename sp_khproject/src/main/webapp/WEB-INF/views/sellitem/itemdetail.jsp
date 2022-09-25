<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>판매정보</title>
	
	<link rel="stylesheet"
	   href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	   integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	   crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	   integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	   crossorigin="anonymous"></script>
	<script
	   src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
	   integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
	   crossorigin="anonymous"></script>
	<script
	   src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
	   integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
	   crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
	
	<!-- 무료아이콘 -->
	<link rel="stylesheet"
	   href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
	
	<style>
	#previewImg {
		max-width: 250px;
		border-radius: 30%;
		height: auto;
	}
	img {
		max-width: 1125px;
		object-fit: contain;
			
		
	}
	.container1 {
		display: block;
	}
	
	.bg-small{
		width: 1100px; height: auto;
	}
	
	div.col-md-12 { margin-bottom: 15px; } 
	.star_on { color: #ffeb00; }
	.buttonA { 
	   background-color: #17B794; 
	   border: none;
	   color: white;
	   text-align: center;
	   text-decoration: none;
	   display: inline-block;
	    font-size: 16px;
	    padding: 10px;
	    border-radius: 5px;
	    cursor: pointer;
	    margin-right: 1px;
	}
	.buttonB { 
	   background-color: #555555;
	    border: none;
	    color: white;
	    text-align: center;
	    text-decoration: none;
	    display: inline-block;
	    font-size: 16px;
	    padding: 10px;
	    border-radius: 5px;
	    cursor: pointer;
	}
	
	</style>
</head>
	<body>
		<%@ include file="../module/head.jsp"%>
		<c:if test="${data.ac_index == 20}">
		<section class="container w-75">
			<div class="mt-5">
				<div class="row mb-5 center">
					<div class="col-10 mb-3">
						<p class="fw-bold fs-2">판매정보</p>
					</div>
				</div>
				<div class="row mt-3">
					<div class="col-3 image-form left">
						<img id="previewImg" class="image-360" alt="프로필 이미지" 
						src="../static/img/profile/${data.ac_email}.png" 
						onerror="this.onerror=null; this.src='../static/img/profile/basic.png'">
					</div>
					<div class="col-4 mt-4">
						<p>이름</p>
						<p>직업</p>
						<p>전문분야</p>
					</div>
					<div class="col-4 mt-4">
						<p>${data.ac_name }</p>
						<p>${data.ac_job }</p>
						<p>${data.ac_field }</p>
					</div>
					<div class="mt-5">
						<button type="button" onclick="location.href='${pageContext.request.contextPath}/chat?itemid=${itemdata.sel_id}&buyer=${loginData.ac_name}&seller=${itemdata.sel_name}'"
						class="btn btn-outline-success">메시지 보내기</button>
						<c:if test="${loginData.ac_index == 10}">
							<button style="margin-left:20px;" type="button" 
							onclick="location.href='${pageContext.request.contextPath}/purchase?itemid=${itemdata.sel_id}'" 
							class="btn btn-outline-success">구매하기</button>
						</c:if>
						<c:if test="${data.ac_name == loginData.ac_name}">
							<div class="mt-5">
								<button type="button" class="btn btn-outline-success" onclick="location.href='modify?id=${itemdata.sel_id}'">수정</button>
								<button type="button" class="btn btn-outline-success" style="margin-left:0.5">삭제</button>
							</div>
						</c:if>
					</div>
					<div class="mt-5" style="background-color: rgb(241, 241, 241); height:10rem;">
						<table class="table table-borderless">
							<thead>
							  <tr>
							      <th class="col-4" style="text-align: center;">고용</th>
							      <th class="col-4" style="text-align: center;">리뷰</th>
							      <th class="col-auto" style="text-align: center;">경력</th>						      
							  </tr>
							</thead>
							<tbody class="mt-5">
							    <tr class="mt-5">
							      <td style="text-align: center;">nn회</td>
							      <td style="text-align: center;">리뷰~</td>
							      <td style="text-align: center;">n년</td>
							    </tr>
							 </tbody>
						</table>
					</div>
				</div>
			</div>
	
			<div class="mt-5">상세내용</div>
			<div>${itemdata.sel_content}</div>

			
			<p class="fw-bold fs-4 mt-5 mb-5">리뷰(${reviewCount})</p>
			<c:forEach items="${reviews}" var="reviews">
				<p>${reviews.review_starCount}</p>
				<p>${reviews.review_writeDay}</p>
				<p>${reviews.review_writer}</p>
				<p>${reviews.review_content}</p>
			</c:forEach>
			
				
				<c:if test="${loginData.ac_index == 10}">
					<div class="mt-5" style="text-align: center; height:10rem;">
					  <button type="button" class="btn" onclick="ajaxLike(${itemdata.sel_id});" style="margin-left:8rem; width:7rem; background-color:rgb(224, 224, 224);">❤</button>
					  <button type="button" class="btn btn-outline-primary" style="margin-left:1rem; width:7rem;">뒤로가기</button>
					  <c:if test="${purchaseCheck == 'Y'}">
					  	<button class="btn btn-outline-primary" data-toggle="modal" data-target="#addnotesmodal" style="margin-left:1rem; width:9rem;">리뷰작성</button>
					  	<!-- Modal Add notes -->
						<div class="modal fade" id="addnotesmodal" tabindex="-1" role="dialog" aria-labelledby="addnotesmodalTitle" style="display: none;" aria-hidden="true">
						    <div class="modal-dialog modal-dialog-centered" role="document">
						        <div class="modal-content border-0">
						            <div class="modal-header bg-info text-white">
						                <h5 class="modal-title text-white">리뷰 작성</h5>
						                <button data-dismiss="modal" type="button" class="close text-white" aria-label="Close">
						                    <span aria-hidden="true"><i class="fa fa-times"></i></span>
						                </button>
						            </div>
						            <div class="modal-body">
						                <div class="notes-box">
						                    <div class="notes-content">
						                    	<c:url var="reviewUrl" value="/sellitem/review" />
						                        <form action="${reviewUrl}" method="post" id="addnotesmodalTitle">
						                        	<input type="hidden" name="itemid" value="${itemdata.sel_id}">
						                        	<input type="hidden" name="sellerName" value="${data.ac_name}">
						                        	<input type="hidden" name="writer" value="${loginData.ac_name}">
						                            <div class="row">
						                                <div class="col-md-12 mb-3">
						                                    <div class="modal-title">
						                                        <label style="font-weight: bold;">구매 상품 : ${itemdata.sel_title}</label><br>
						                                        <div id="note-has-title">
						                                        </div>
						                                    </div>
						                                </div>
						                                <div class="col-md-12 mb-3">
						                                    <div class="modal-star">
						                                        <label>별점</label>
						                                        <div id="mstar">
						                                           <i class="fa fa-star fa-2x" id="1"></i>
						                                           <i class="fa fa-star fa-2x" id="2"></i>
						                                           <i class="fa fa-star fa-2x" id="3"></i>
						                                           <i class="fa fa-star fa-2x" id="4"></i>
						                                           <i class="fa fa-star fa-2x" id="5"></i>
						                                        </div>
						                                        <input type="hidden" id="note-has-star" value="1" name="modal-star" class="form-control" minlength="25" placeholder="Title" />
						                                    </div>
						                                </div>
						
						                                <div class="col-md-12">
						                                    <div class="modal-description">
						                                        <label>내용</label>
						                                        <textarea id="note-has-description" name="modal-desc" class="form-control" minlength="20" rows="5" maxlength="300" 
						                                        placeholder="※ 등록된 후기는 수정이 불가능합니다 .&#13;&#10;※ 최소 20글자, 최대 300글자를 입력하실 수 있습니다."></textarea>
						                                    </div>
						                                </div>
						                                
						                            </div>
						                        </form>
						                    </div>
						                    
						                </div>
						            </div>
						            
						            <div class="modal-footer">
						               <button form="addnotesmodalTitle" type="submit" id="btn-n-add" class="buttonA" onclick="formCheck();">작성</button>
						               <button class="buttonB" data-dismiss="modal">취소</button>
						            </div>
						        </div>
						    </div>
						</div> <!-- end modal -->
					  </c:if>
				</div>	
			</c:if>
		</section>
		</c:if>

		
		
		<%@ include file="../module/footer.jsp" %>



	</body>
	
	<script type="text/javascript">
		<!-- 모달 - 별점 -->
	
		$(document).ready(function(){
			
		});
		
		$('#mstar i').click(function (){
		  /* 별점의 star_on 클래스 전부 제거 */ 
		   $(this).parent().children("i").removeClass("star_on"); 
		   /* 클릭한 별과, 그 앞 까지 별점에 star_on 클래스 추가 */
		   $(this).addClass("star_on").prevAll("i").addClass("star_on"); 
		   
		   var starpoint = this.id;
		   
		   $('#note-has-star').attr("value", starpoint)
		      
		   return false;
			});
		
		function ajaxLike(id) {
			$.ajax({
				type: "post",
				url: "/home/sellitem/like",
				data: {
					id: id
				},
				success: function(data) {
					if(data.code === "success"){
						alert("성공");
					}else if(data.code === "default"){
						alert("실패");
						
					}
					
				}
			});
		}
	</script>
</html>