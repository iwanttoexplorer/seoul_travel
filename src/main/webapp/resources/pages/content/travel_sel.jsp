<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>동가홍상</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/SEOUL_TRAVEL/assets/css/bootstrap.css">
<script src="/SEOUL_TRAVEL/assets/js/jquery_3_7_1.js"></script>
<script>
document.addEventListener("DOMContentLoaded", function(){
  console.log('DOMContentLoaded---');
  //목록 버튼
  
  const moveToListBtn = document.querySelector("#moveToList");
  //수정 버튼
  /* const doUpdateBtn = document.querySelector("#doUpdate");
  //삭제 버튼 
  const doDeleteBtn = document.querySelector("#doDelete"); */
  /* console.log('doDeleteBtn:'+doDeleteBtn); */
  
  const workDiv = document.querySelector("#work_div");//작업구분
  const seq = document.querySelector("#seq");//seq
  const title = document.querySelector("#title");//title
  const contents = document.querySelector("#contents");//contents
  const modId = document.querySelector("#modId");//modId
  
  //이벤트 핸들러 등록
  moveToListBtn.addEventListener("click", function(event){
    console.log('moveToListBtn click event'+event);
    moveToList();
  });
  
  doUpdateBtn.addEventListener("click", function(event){
    console.log('doUpdateBtn click event'+event);
    doUpdate();
  });
  
  doDeleteBtn.addEventListener("click", function(event){
    console.log('doDeleteBtn click event'+event);
    //doDelete
    doDelete();
  });
  //--------------------------------------------------------
  function moveToList(){
    console.log('moveToList()');
    alert("게시 목록으로 이동 합니다.");
    window.location.href= "/SEOUL_TRAVEL/content/content.do?work_div=doRetrieve";
  }
  
  
  function doUpdate(){
    console.log('doUpdate()');
    
    if( isEmpty(seq.value) ==true){
        alert('Seq를 확인 하세요.');
        return;
    }
    
    if( isEmpty(title.value) ==true){
        title.focus();
        alert('제목을 확인 하세요.');
        return;
    }    

    if( isEmpty(contents.value) ==true){
        contents.focus();
        alert('내용 확인 하세요.');
        return;
    }    
    
    $.ajax({
        type: "POST", 
        url:"/WEB02/board/board.do",
        asyn:"true",
        dataType:"html",
        data:{
            "work_div":"doUpdate",
            "seq": seq.value,
            "title": title.value,
            "contents": contents.value,
            "modId": modId.value  
        },
        success:function(response){//통신 성공
            console.log("success data:"+response);
        
            //null, undefined처리
            if(response){
              try{
                const messageVO = JSON.parse(response);
                console.log("messageVO.messageId:"+messageVO.messageId);
                console.log("messageVO.msgContents:"+messageVO.msgContents);
                
                if(isEmpty(messageVO) == false &&  "1" === messageVO.messageId){
                  alert(messageVO.msgContents);
                  window.location.href= "/WEB02/board/board.do?work_div=doRetrieve";
                }else{
                  alert(messageVO.msgContents);
                }
                
              }catch(e){    
                console.error("JSON 파싱 에러:",e);
              }
              
              
            }else{
              console.warn("response가 null혹은 undefined.");
              alert("response가 null혹은 undefined.");
            }
        
        },
        error:function(data){//실패시 처리
                console.log("error:"+data);
        }
    });    
    
  }//--doUpdate() end
  
  function doDelete(){
      console.log('doDelete()');
      workDiv.value = 'doDelete';
      
      console.log('seq.value:'+seq.value);
      
      //seq 
      if( isEmpty(seq.value) ==true){
         alert('Seq를 확인 하세요.');
         return;
      }
      
      if(false === confirm('삭제 하시겠습니까?')){
         return;
      }
      
      
      $.ajax({
          type: "GET", 
          url:"/WEB02/board/board.do",
          asyn:"true",
          dataType:"html",
          data:{
              "work_div":"doDelete",
              "seq": seq.value 
          },
          success:function(response){//통신 성공
              console.log("success response:"+response);
              const messageVO = JSON.parse(response);
              console.log("messageVO.messageId:"+messageVO.messageId);
              console.log("messageVO.msgContents:"+messageVO.msgContents);
          
              if(isEmpty(messageVO) == false &&  "1" === messageVO.messageId){
                alert(messageVO.msgContents);
                window.location.href= "/WEB02/board/board.do?work_div=doRetrieve";
              }else{
                alert(messageVO.msgContents);
              }
              
          },
          error:function(data){//실패시 처리
                  console.log("error:"+data);
          }
      });//--ajax end     
  }//--doDelete()
  
});//--DOMContentLoaded end 

</script>
</head>
<body>
<!-- container -->
<div class="container">
  
  <!-- 제목 -->
  <div class="page-header  mb-4">
    <h2>게시-관리</h2>
  </div>
  <!--// 제목 end ------------------------------------------------------------->
  <!-- 버튼 -->
  <div class="mb-2 d-grid gap-2 d-md-flex justify-content-md-end">
      <input type="button" value="목록" class="btn btn-primary" id="moveToList">
      <input type="button" value="수정" class="btn btn-primary" id="doUpdate">
      <input type="button" value="삭제" class="btn btn-primary" id="doDelete">
  </div>
  <!--// 버튼 ----------------------------------------------------------------->
  
  <!-- form -->
  <form action="#" class="form-horizontal">
    <input type="hidden" name="work_div" id="work_div">
    <div class="row mb-3">
        <label for="seq" class="col-sm-2 col-form-label">순번</label>
        <div class="col-sm-10">
          <input disabled type="text" class="form-control" name="seq" id="seq"  required="required" value="${outVO.seq}">
        </div>      
    </div>  

    <div class="row mb-3">
        <label for="readCnt" class="col-sm-2 col-form-label">조회수</label>
        <div class="col-sm-10">
          <input disabled type="text" class="form-control" name="readCnt" id="readCnt"   value="${outVO.readCnt}" >
        </div>      
    </div>  
         
    <div class="row mb-3">
        <label for="regId" class="col-sm-2 col-form-label">등록자</label>
        <div class="col-sm-10">
          <input type="text" disabled="disabled" class="form-control" name="regId" id="regId"   value="${outVO.regId}"  >        
        </div>      
    </div>    
    <div class="row mb-3">
        <label for="regDt" class="col-sm-2 col-form-label">등록일</label>
        <div class="col-sm-10">
          <input type="text" disabled="disabled" class="form-control" name="regDt" id="regDt"    value="${outVO.regDt}" >        
        </div>      
    </div>        
    <div class="row mb-3">
        <label for="modId" class="col-sm-2 col-form-label">수정자</label>
        <div class="col-sm-10">
          <input type="text" disabled="disabled" class="form-control" name="modId" id="modId"   value="${outVO.modId}"  >        
        </div>      
    </div>    
    <div class="row mb-3">
        <label for="modDt" class="col-sm-2 col-form-label">수정일</label>
        <div class="col-sm-10">
          <input type="text" disabled="disabled" class="form-control" name="modDt" id="modDt"   value="${outVO.modDt}"  >        
        </div>      
    </div>    
    <div class="row mb-3">
        <label for="title" class="col-sm-2 col-form-label">제목</label>
        <div class="col-sm-10">
          <input type="text" class="form-control" name="title" id="title"  maxlength="75" required="required"  value="${outVO.title}">
        </div>      
    </div>     
    <div class="row mb-3"">
        <label for="contents" class="col-sm-2 col-form-label">내용</label>
        <div class="col-sm-10">    
         <textarea style="height: 200px"  class="form-control" id="contents" name="contents">${outVO.contents }</textarea>
        </div> 
    </div>    
    
  </form>
  
  <!--// form end -->
</div>
<!--// container end ---------------------------------------------------------->    
<script src="/SEOUL_TRAVEL/assets/js/bootstrap.bundle.min.js"></script>   
</body>
</html>