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
</head>
<body>
    <div class="container">
        <div class="message">
            <h1>Chưa đến thời gian dự thi!</h1>
            <p>Bạn chưa thể làm bài thi vào lúc này do chưa đến thời gian dự thi!</p>
            <p>Vui lòng làm bài thi khác hoặc liên hệ với giảng viên.</p>
            <p>Kỳ thi bắt đầu vào ${startTime} và kết thúc vào ${endTime}</p>
            <a href="/">Trở về</a>
        </div>
    </div>

</body>
</html>
