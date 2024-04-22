<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Kết quả bài thi</title>
    <link rel="stylesheet" href="/css/result.css">
</head>
<body>
    <div class="container">
        <div class="message">
            <h1>Chúc mừng bạn đã hoàn thành bài thi!</h1>
            <p><strong>Số câu trả lời đúng:</strong> <span id="correctAnswers">${examResult.numberOfCorrectQuestion}</span></p>
            <p><strong>Điểm số:</strong> <span id="score"><fmt:formatNumber value="${examResult.score}" type="number" /></span></p>
            <a href="/answer/${examResult.user.id}/${examResult.exam.id}">Xem lại câu trả lời</a>
        </div>
    </div>

</body>
</html>
