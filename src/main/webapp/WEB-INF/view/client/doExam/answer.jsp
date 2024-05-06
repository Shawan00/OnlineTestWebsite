<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Kết quả thi</title>
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

    <style>
        .correct {
            color: green;
        }

        .incorrect {
            color: red;
        }
    </style>

</head>

<body class="bg-light">
<div class="bg-white w-100 top-0 p-2" style="z-index: 100000;position: sticky;">
    <div class="container d-flex justify-content-between align-items-center">
        <h4 class="h-100 d-flex justify-content-center align-items-center text-center">
            Thí sinh: ${sessionScope.fullName}
        </h4>
        <h4>Kết quả</h4>
        <a class="btn btn-primary" href="/">Quay về trang chủ</a>
    </div>
</div>
<div class="container">
    <div class="row">
        <div class="col-9">
            <c:forEach items="${questionListWrapper.questions}" var="question" varStatus="status">
                <div class="card my-2" id="cardCau${status.index + 1}">

                    <div class="card-body">

                        <div style="display: none">
                            <input path="questions[${status.index}].exam" value="${examId}"/>
                        </div>

                        <p>
                            <strong>Câu ${status.index +1}:</strong> ${question.questionContent}
                        <div style="display: none">
                            <input value="questions[${status.index}].questionContent"
                                   value="${question.questionContent}"/>
                        </div>
                        </p>

                        <div class="form-check" style="opacity: 1 !important;">
                            <input
                                    class="form-check-input"
                                    type="radio"
                                    name="questions[${status.index}].selectedOptionIndex"
                                    id="Cau${status.index + 1}.1"
                                    value="1"
                                ${question.selectedOptionIndex == 1 || question.correctOptionIndex == 1 ? 'checked' : ''}
                                    disabled
                                    style="opacity: 1 !important"
                            />
                            <label style="opacity: 1 !important"
                                   class="form-check-label ${question.correctOptionIndex == 1 ? 'correct' : (question.selectedOptionIndex == 1 && question.correctOptionIndex != 1 ? 'incorrect' : '')}">
                                A. ${question.optionA}</label>
                        </div>

                        <div class="form-check" style="opacity: 1 !important;">
                            <input
                                    class="form-check-input"
                                    type="radio"
                                    name="questions[${status.index}].selectedOptionIndex"
                                    id="Cau${status.index + 1}.2"
                                    value="1"
                                ${question.selectedOptionIndex == 2 || question.correctOptionIndex == 2 ? 'checked' : ''}
                                    disabled
                                    style="opacity: 1 !important"
                            />
                            <label style="opacity: 1 !important"
                                   class="form-check-label ${question.correctOptionIndex == 2 ? 'correct' : (question.selectedOptionIndex == 2 && question.correctOptionIndex != 2 ? 'incorrect' : '')}">
                                B. ${question.optionB}</label>
                        </div>

                        <div class="form-check" style="opacity: 1 !important;">
                            <input
                                    class="form-check-input"
                                    type="radio"
                                    name="questions[${status.index}].selectedOptionIndex"
                                    id="Cau${status.index + 1}.3"
                                    value="1"
                                ${question.selectedOptionIndex == 3 || question.correctOptionIndex == 3 ? 'checked' : ''}
                                    disabled
                                    style="opacity: 1 !important"
                            />
                            <label style="opacity: 1 !important"
                                   class="form-check-label ${question.correctOptionIndex == 3 ? 'correct' : (question.selectedOptionIndex == 3 && question.correctOptionIndex != 3 ? 'incorrect' : '')}">
                                C. ${question.optionC}</label>
                        </div>

                        <div class="form-check">
                            <input
                                    class="form-check-input"
                                    type="radio"
                                    name="questions[${status.index}].selectedOptionIndex"
                                    id="Cau${status.index + 1}.4"
                                    value="1"
                                ${question.selectedOptionIndex == 4 || question.correctOptionIndex == 4 ? 'checked' : ''}
                                    disabled
                                    style="opacity: 1 !important"
                            />
                            <label style="opacity: 1 !important"
                                   class="form-check-label ${question.correctOptionIndex == 4 ? 'correct' : (question.selectedOptionIndex == 4 && question.correctOptionIndex != 4 ? 'incorrect' : '')}">
                                D. ${question.optionD}</label>
                        </div>

                        <div class="answer">
                            <input type="hidden" name="Cau${status.index + 1}" id="answer${status.index + 1}"
                                   value="${question.correctOptionIndex}">
                            <div style="display: none">
                                <input path="questions[${status.index}].correctOptionIndex"
                                       value="${question.correctOptionIndex}"/>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
        <div class="col-3">
            <div class="card p-3 my-2 position-fixed"
                 style="width: 20%; z-index: 1; display: grid; grid: auto / auto auto auto auto auto; grid-gap: 5px;">
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

<input type="hidden" id="totalQuestions" value="${questionListWrapper.questions.size()}"/>
<input type="hidden" id="examId" value="${examId}"/>
<input type="hidden" id="userId" value="${sessionScope.id}"/>


<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"
></script>
<script>
    var totalQuestions = document.getElementById("totalQuestions").value;
    console.log(totalQuestions)
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
