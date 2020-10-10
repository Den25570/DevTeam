<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=utf-8" %>

<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    <c:set var="context" value="${pageContext.request.contextPath}"/>

    <script>
        $(document).ready(function () {
            //LoadProjects

            $.ajax({
                type: "POST",
                url: "${context}/MyProjects",
                success: function (response) {
                    console.log(response);
                    let projectList = response;
                    for(let i = 0 ; i < projectList.length; i++) {
                        AddProject('projects', projectList[i].specification.name, projectList[i].specification.description, projectList[i].id);
                    }
                }
            });

            function AddProject(parentId, name, description, specification_id) {
                let jobTextTemplate = '<div>\n' +
                    '            <div>\n' +
                    '                <h5 class="mb-2 border-bottom p-1">'+ name +'</h5>\n' +
                    '                <p class="mb-2">'+ description +'</p>\n' +
                    '                <button data-id="'+ specification_id +'" class="btn btn-outline-dark">Open</button>\n' +
                    '            </div>\n' +
                    '        </div>'

                let jobTemplate = document.createElement('div');
                jobTemplate.classList.add("card-item");
                jobTemplate.classList.add("bg-white");
                jobTemplate.classList.add("p-3");
                jobTemplate.classList.add("mb-2");
                jobTemplate.innerHTML = jobTextTemplate;

                document.getElementById(parentId).appendChild(jobTemplate);
            }
        });
    </script>

    <style>
        .card-item {
            margin-bottom: 15px;
            background: #f7f7f7;
            box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
            padding: 20px;
        }
    </style>
</head>

<c:if test="${sessionScope.Customer == null}">
    <jsp:forward page="/JSP/Login.jsp"></jsp:forward>
</c:if>
<body>
<%@ include file="../../templates/header.jsp" %>
<div class="container">
    <div id="projects" class="card-item mb-3">
        <div class="card-item bg-white p-3 mb-2">
            <div>
                <h5 class="mb-2 border-bottom p-1">Тест</h5>
                <p class="mb-2">Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the
                    industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type
                    and scrambled it to make a type specimen book. It has survived not only five centuries, but also the
                    leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s
                    with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop
                    publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p>
                <button class="btn btn-outline-dark">Open</button>
            </div>
        </div>
    </div>
    <div>
        <nav aria-label="paging">
            <ul class="pagination justify-content-center">
                <li class="page-item disabled">
                    <a class="page-link" href="#" tabindex="-1">Previous</a>
                </li>
                <li class="page-item"><a class="page-link" href="#">1</a></li>
                <li class="page-item"><a class="page-link" href="#">2</a></li>
                <li class="page-item"><a class="page-link" href="#">3</a></li>
                <li class="page-item">
                    <a class="page-link" href="#">Next</a>
                </li>
            </ul>
        </nav>
    </div>
</div>
</body>
</html>
