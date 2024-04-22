<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Thống kê</title>
  <link
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
          crossorigin="anonymous"
  />
  <link
          rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css"
  />
</head>
<body>
<header>
  <style>
    *{
      box-sizing: border-box;
    }
    h3{
      color: red;
      padding: 20px;
      margin-left: 100px;
    }
    h4, .allexams{
      margin: 20px;
      background-color: #f7f7f7; 
      border-radius: 10px;
      box-shadow:  0 0 5px rgba(128, 128, 128, 0.932);
      padding: 20px;
    }
    .exam{
      display: flex;
      justify-content: space-between;
      
      border-bottom: solid 1px #aaa; 
      padding: 20px;
    }
    .detail{
      width: calc(100% - 200px);
      word-wrap: break-word; 
    }
    .diem{
      width: 100px;
      align-items: center;
      margin: 0px 100px;
    }
  </style>
  <h3>Thống kê</h3>
</header>
<div class="container">
  <div class="my-2">
    <div class="row">
      <div class="col-10 d-flex justify-content-start">
        <div class="input-group mb-3">
          <!-- <input type="search" class="form-control" id="search-input" placeholder="Search.....">
          <button class="btn btn-outline-danger" type="button" id="search-button">Search</button> -->
        </div>
      </div>
      <div class="col-2 d-flex justify-content-end">
        <button class="btn btn-danger mb-2" id="export-data">Export Data</button>
      </div>
    </div>
  </div>
  <div class="accordion" id="thong-ke">
    <h4>Họ tên: ${student.fullName}</h4>
    <div class="allexams">
      <c:forEach items="${student.examDTOS}" var="item">
        <div class="exam">
          <div class="detail">
            <p style="font-size: 25px;"><strong>Kì thi: ${item.name}</strong></p>
            <p><strong>Mã kì thi: </strong>${item.id}</p>
            <p><strong>Trạng thái: </strong>${item.status}</p>
            <p><strong>Thời gian tham gia: </strong>${item.time}</p>
          </div>
          <div class="diem d-flex  ">
            <p class="px-2"><strong>Điểm: </strong></p>
            <p><strong style="font-size:30px; color:red"><fmt:formatNumber value="${item.score}" type="number" /></strong></p>

          </div>
        </div>
      </c:forEach>
    </div>
  </div>
</div>
<script src='https://cdnjs.cloudflare.com/ajax/libs/FileSaver.js/1.3.8/FileSaver.min.js'></script>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"
></script>


</body>
</html>