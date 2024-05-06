<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Không khả dụng</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
  <div class="container">
    <div class="row">
      <div class="col-md-12 text-center">
        <img src="/client/img/badicon.png" alt="" class="img-fluid mb-4">
        <h1>Chưa đến thời gian dự thi!</h1>
        <p>Bạn chưa thể làm bài thi vào lúc này do chưa đến thời gian dự thi! Vui lòng làm bài thi khác hoặc liên hệ với giảng viên.</p>
        <p>Bài thi bắt đầu vào lúc ${startTime} và kết thúc vào lúc ${endTime}</p>
        <a href="/" class="btn btn-lg btn-primary">Trở về</a>
      </div>
    </div>
  </div>
</body>
</html>

