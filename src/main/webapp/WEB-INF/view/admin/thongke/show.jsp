<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

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
    <input type="hidden" id="numberOfexamResultDTO" value="${examResultDTOS.size()}">
    <% int index = 0; %>
    <c:forEach items="${examResultDTOS}" var="examResultDTO">
      <% index++; %>
      <input type="hidden" id = "ten<%=index%>" value="${examResultDTO.ten}">
      <input type="hidden" id = "soLanThamGia<%=index%>" value="${examResultDTO.soLanThamGia}">
      <input type="hidden" id = "tiLeHoanThanh<%=index%>" value="${examResultDTO.tiLeHoanThanh}">
      <input type="hidden" id = "diem<%=index%>" value="${examResultDTO.diem}">

    </c:forEach>

    <header>
        <style>
            
            h3{
                color: red;  
                padding: 20px;
                margin-left: 100px;
        
            }
        </style>
        <h3>Thống kê</h3>
    </header>
    <div class="container">
      <div class="my-2">
        <div class="row">
          <div class="col-10 d-flex justify-content-start">
            <div class="input-group mb-3">
              <input type="search" class="form-control" id="search-input" placeholder="Search.....">
              <button class="btn btn-outline-danger" type="button" id="search-button">Search</button>
            </div>
          </div>
          <div class="col-2 d-flex justify-content-end">
            <button class="btn btn-danger mb-2" id="export-data">Export Data</button>
          </div>
        </div>
      </div>
      <div class="accordion" id="danh-sach-sinh-vien">

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
    <script src="/js/thongke.js"></script>

  </body>
</html>