<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="/css/showQuestion.css">

</head>
<body>

<div class="container">
    <aside>
        <div class="top">
            <div class="logo">
                <img src="/client/img/logo.png" alt="">
                <h2 style="color: #ad171c;"> PTIT</h2>
            </div>
        </div>
        <div class="sidebar">
            <a href="/admin/exam">
                <span class="fa-solid fa-laptop-code"></span>
                <h3>Quản lý kì thi</h3>
            </a>

            <a href="/admin/user" class="active">
                <span class="fa-regular fa-user"></span>
                <h3>Quản lý người dùng</h3>
            </a>

            <a href="/admin/thongke/alluser/examresult">
                <span class="fa-solid fa-chart-line"></span>
                <h3>Thống kê</h3>
            </a>
        </div>
    </aside>

    <main>
        <div class="main-head">
            <h1 style="display: inline-block;">Danh sách câu hỏi của bài thi</h1>
            <a href="/admin/exam/question/create_question/${examId}" class="viewquestion">Thêm câu hỏi</a>
        </div>
        <div class="box-body" style="overflow: auto; height: 85vh">
            <% int index = 0; %>
            <c:forEach items="${questions}" var="question">
                <% index++; %>
                <div class="question">
                    <div class="questionContent">
                        <h3 style="display: inline-block;"><strong>Câu <%=index%>:</strong> ${question.questionContent}</h3>
                        <a style="display: inline-block;" href="/admin/exam/question/update_question/${question.id}/${examId}" class="openUpdateModal" data-id="${question.id}" data-modal="modal1"><span style="margin: 0 10px;" class="fa-solid fa-pen-to-square"></span></a>
                        <a style="display: inline-block;" href="/admin/exam/question/delete_question/${question.id}/${examId}" class="openDeleteModal" data-id="${question.id}" data-modal="modal2"><span class="fa-solid fa-xmark"></span></a>
                    </div>

                    <div class="answer">
                        <div>
                            <label for=""><strong>A.</strong> ${question.optionA}</label>
                        </div>
                        <div>
                            <label for=""><strong>B.</strong> ${question.optionB}</label>
                        </div>
                        <div>
                            <label for=""><strong>C.</strong> ${question.optionC}</label>
                        </div>
                        <div>
                            <label for=""><strong>D.</strong> ${question.optionD}</label>
                        </div>                 
                        <div>
                            <strong for="">Đáp án: 
                                <c:choose>
                                    <c:when test="${question.correctOptionIndex eq 1}">A</c:when>
                                    <c:when test="${question.correctOptionIndex eq 2}">B</c:when>
                                    <c:when test="${question.correctOptionIndex eq 3}">C</c:when>
                                    <c:when test="${question.correctOptionIndex eq 4}">D</c:when>
                                    <c:otherwise>Không xác định</c:otherwise>
                                </c:choose>
                            </strong>
                        </div>
                        
                    </div>

                    
                </div>
            </c:forEach>

        </div>
    </main>
    <input type="hidden" id="examId" value="${examId}">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="/js/Question.js"></script>

    <script>
        $(document).ready(function() {
            $(".openUpdateModal").click(function() {
                var questionId = $(this).data("id");
                var examId = document.getElementById("examId").value;
                $.ajax({
                    url: '/admin/exam/question/update_question/' + questionId + '/' + examId,
                    type: 'PUT',
                    success: function(question) {                        
                        $("#questioncontent11").val(question.questionContent);
                        $("#optionA11").val(question.optionA);
                        $("#optionB11").val(question.optionB);
                        $("#optionC11").val(question.optionC);
                        $("#optionD11").val(question.optionD);
                        $("#optionD11").val(question.optionD);
                        $("#correctOptionIndex11").val(question.correctOptionIndex);
                        // Open the modal
                        $("#modal1").show();
                        console.log(exam);
                    }
                });
            });

            $(".openDeleteModal").click(function() {
                var questionId = $(this).data("id");
                var examId = document.getElementById("examId").value;
                $.ajax({
                    url: '/admin/exam/question/delete_question/' + questionId + '/' + examId,
                    type: 'DELETE',
                    success: function(question) {
                        // Open the modal
                        $("#modal2").show();
                        console.log(question);
                    }
                });
            });
        });
    </script>
</body>
</html>

