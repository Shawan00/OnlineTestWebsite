<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

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
            <p><strong>Điểm số:</strong> <span id="score">${examResult.score}</span></p>
            <a href="/">Trở về</a>
        </div>
    </div>
</body>
</html>
