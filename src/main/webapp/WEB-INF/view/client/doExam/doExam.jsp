<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Làm bài kiểm tra</title>
    <!--Bootstrap-->
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
    <link rel="stylesheet" href="/css/clock.css">


</head>

<body class="bg-light">
<input type="hidden" value="${exam.status}" id="examStatus">
<input type="hidden" value="${exam.numberOfMinutes}" id="numberOfMinutes">
<script src="/js/clock.js"></script>
<form:form method="post" action="/doExam/examResult" modelAttribute="questionListWrapper">
    <div class="bg-white w-100 top-0 p-2" style="z-index: 100000;position: sticky;">
        <div class="container d-flex justify-content-between align-items-center">
            <h4 class="h-100 d-flex justify-content-center align-items-center text-center">
                Thí sinh: ${sessionScope.fullName}
            </h4>
            <h4>
                Kì thi: ${exam.name}
            </h4>
            <h4 id="clock1"><i class="fas fa-clock"></i> <span id="countdown"></span></h4>
            <button
                    type="submit"
                    class="btn btn-primary"
                    id="submitExam"
            >
                Nộp bài
            </button>
        </div>
    </div>
    <div class="container">
        <div class="row">
            <div class="col-9">
                <c:forEach items="${questionListWrapper.questions}" var="question" varStatus="status">
                    <div class="card my-2" id="cardCau${status.index + 1}">
                        <div class="card-body">

                            <div style="display: none">
                                <form:input path="questions[${status.index}].exam" value="${examId}"/>
                            </div>

                            <div style="display: none">
                                <form:input path="questions[${status.index}].id"/>
                            </div>

                            <div>
                                <h5>Câu ${status.index +1}: ${question.questionContent}</h5>
                                <div style="display: none">
                                    <form:input path="questions[${status.index}].questionContent"
                                                value="${question.questionContent}"/>
                                </div>
                            </div>

                            <div class="form-check">
                                <input
                                        class="form-check-input"
                                        type="radio"
                                        name="questions[${status.index}].selectedOptionIndex"
                                        id="Cau${status.index + 1}.1"
                                        value="1"
                                />
                                <label class="form-check-label" for="Cau${status.index + 1}">
                                    A. </label> ${question.optionA}
                                <div style="display: none">
                                    <form:input path="questions[${status.index}].optionA" value="${question.optionA}"/>
                                </div>
                            </div>

                            <div class="form-check">
                                <input
                                        class="form-check-input"
                                        type="radio"
                                        name="questions[${status.index}].selectedOptionIndex"
                                        id="Cau${status.index + 1}.2"
                                        value="2"
                                />
                                <label class="form-check-label" for="Cau${status.index + 1}">
                                    B. </label> ${question.optionB}
                                <div style="display: none">
                                    <form:input path="questions[${status.index}].optionB" value="${question.optionB}"/>
                                </div>
                            </div>

                            <div class="form-check">
                                <input
                                        class="form-check-input"
                                        type="radio"
                                        name="questions[${status.index}].selectedOptionIndex"
                                        id="Cau${status.index + 1}.3"
                                        value="3"
                                />
                                <label class="form-check-label" for="Cau${status.index + 1}">
                                    C. </label> ${question.optionC}
                                <div style="display: none">
                                    <form:input path="questions[${status.index}].optionC" value="${question.optionC}"/>
                                </div>
                            </div>

                            <div class="form-check">
                                <input
                                        class="form-check-input"
                                        type="radio"
                                        name="questions[${status.index}].selectedOptionIndex"
                                        id="Cau${status.index + 1}.4"
                                        value="4"
                                />
                                <label class="form-check-label" for="Cau${status.index + 1}">
                                    D. </label> ${question.optionD}
                                <div style="display: none">
                                    <form:input path="questions[${status.index}].optionD" value="${question.optionD}"/>
                                </div>
                            </div>

                            <div class="answer">
                                <input type="hidden" name="Cau${status.index + 1}" id="answer${status.index + 1}"
                                       value="${question.correctOptionIndex}">
                                <div style="display: none">
                                    <form:input path="questions[${status.index}].correctOptionIndex"
                                                value="${question.correctOptionIndex}"/>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <div class="col-3">
                <div class="card p-3 my-2 position-fixed" style="width: 20%; z-index: 1; display: grid; grid: auto / auto auto auto auto auto; grid-gap: 5px;">
                    <c:forEach items="${questionListWrapper.questions}" var="question" varStatus="status">
                        <button
                                id="Cau${status.index + 1}"
                                type="button"
                                class="btn btn-outline-primary"
                                style="width: 100%;"
                        >
                                ${status.index + 1}
                        </button>
                    </c:forEach>
                </div>
            </div>
        </div>

    </div>
</form:form>
<input type="hidden" id="totalQuestions" value="${questionListWrapper.questions.size()}"/>
<input type="hidden" id="examId" value="${examId}"/>
<input type="hidden" id="userId" value="${sessionScope.id}"/>


<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"
></script>


<!-- <script>
    var totalQuestions = document.getElementById("totalQuestions").value;
    for(let i = 1; i <= totalQuestions; i++) {
        for(let j = 1; j <= 4; j++) {
            document.getElementById("Cau" + i + "." + j).addEventListener("click", function () {
                let button = document.getElementById("Cau" + i);
                if(button.classList.contains("btn-outline-primary")) {
                    button.classList.remove("btn-outline-primary");
                    button.classList.add("btn-primary");
                }
            });
        }
        console.log("Số câu đúng: " + correctAnswer);
        var totalScore = correctAnswer / totalQuestion * 10;
        totalScore = totalScore.toFixed(2);
        console.log("Điểm: " + totalScore);

        var userId = document.getElementById("userId").value;
        var examId = document.getElementById("examId").value;
        var examResult = {
          numberOfCorrectQuestion: correctAnswer,
          score: totalScore
        }

        console.log("userId: " + userId + "; examId: " + examId);
        console.log(examResult);

        // Gửi yêu cầu POST tới backend
        fetch(`/doexam/examresult/${userId}/${examId}`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(examResult)
        })
        .then(response => {
            if (response.ok) {
                // Nếu yêu cầu thành công, chuyển hướng trang web
                window.location.href = `/examresult/${userId}/${examId}`;
            }
        })
        .catch(error => {
            // Xử lý lỗi nếu có
            console.error('Error:', error);
        });

      }

      $("#submitExam").click(function () {
        calculateScore();
      });
    });
    var examStatus = document.getElementById("examStatus").value;
    console.log(examStatus);
  </script>

    for(let i = 1; i <= totalQuestions; i++) {
        let button = document.getElementById("Cau" + i);
        button.addEventListener("click", function () {
            // scroll to question
            let question = document.getElementById("cardCau" + i);
            question.scrollIntoView({behavior: "smooth", block: "center", inline: "center"});

        });
    }

    var examStatus = document.getElementById("examStatus").value;

</script> -->
<script>
  var totalQuestions = document.getElementById("totalQuestions").value;
    for(let i = 1; i <= totalQuestions; i++) {
        for(let j = 1; j <= 4; j++) {
            document.getElementById("Cau" + i + "." + j).addEventListener("click", function () {
                let button = document.getElementById("Cau" + i);
                if(button.classList.contains("btn-outline-primary")) {
                    button.classList.remove("btn-outline-primary");
                    button.classList.add("btn-primary");
                }
            });
        }
    }

    for(let i = 1; i <= totalQuestions; i++) {
        let button = document.getElementById("Cau" + i);
        button.addEventListener("click", function () {
            // scroll to question
            let question = document.getElementById("cardCau" + i);
            question.scrollIntoView({behavior: "smooth", block: "center", inline: "center"});

        });
    }
</script>
</body>
