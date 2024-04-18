<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cập nhật câu hỏi</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="/css/manageUser.css">


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
            <h1>Quản lý người dùng</h1>

            <div class="content">
                <div class="box-header">
                    <p style="display: inline-block;">Cập nhật câu hỏi</p>
                </div>

                <div class="box-body" style="overflow: auto; height: 450px;">
                    <div class="modal-main">
                        <form id="updateExamForm" method="post" action="/admin/exam/question/update_question" modelAttribute="newQuestion">
                            <div style="display: none">
                                <label>Mã câu hỏi:</label>
                                <form:input type="text" path="newQuestion.id" readonly="true"/>
                            </div>
                            <div style="display: none">
                                <form:input type="text" path="newQuestion.exam" readonly="true"/>
                            </div>
                            <div>
                                <label>Nội dung câu hỏi:</label>
                                <form:input type="text" id="questioncontent11" path="newQuestion.questionContent" />
                            </div>
                            <div>
                                <label>Phương án A:</label>
                                <form:input type="text" id="optionA11" path="newQuestion.optionA"/>
                            </div>
                            <div>
                                <label>Phương án B:</label>
                                <form:input type="text" id="optionB11" path="newQuestion.optionB"/>
                            </div>
                            <div>
                                <label>Phương án C:</label>
                                <form:input type="text" id="optionC11" path="newQuestion.optionC"/>
                            </div>
                            <div>
                                <label>Phương án D:</label>
                                <form:input type="text" id="optionD11" path="newQuestion.optionD"/>
                            </div>
                            <div>
                                <label>Đáp án đúng:</label>
                                <form:select path="newQuestion.correctOptionIndex" id="correctOptionIndex11">
                                    <form:option value="1">A</form:option>
                                    <form:option value="2">B</form:option>
                                    <form:option value="3">C</form:option>
                                    <form:option value="4">D</form:option>
                                </form:select>
                            </div>
                            <div class="form-bot">
                                <button style="width: 100%;" type="submit">Cập nhật</button>
                            </div>
                        
                        </form>
                    </div>
                </div>
            </div>

            
        </main>
   
    
    

    
    </main>
         
     
    

      
</body>
</html>

