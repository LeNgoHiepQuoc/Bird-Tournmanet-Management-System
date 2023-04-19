<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Font -->
        <link href='https://fonts.googleapis.com/css?family=Baloo Bhaijaan' rel='stylesheet'>
        <link href='https://fonts.googleapis.com/css?family=Montserrat' rel='stylesheet'>
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Ubuntu:wght@700&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css2?family=FAMILY_NAME:wght@WEIGHT_OR_RANGE&display=swap" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
        <!-- CSS -->
        <link rel="stylesheet" href="CSS/dashboardAdmin.css">
        <!-- icon  -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css">
        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
        <script type="text/javascript">
            google.charts.load("current", {packages: ["corechart"]});
            google.charts.setOnLoadCallback(drawChart);
            function drawChart() {
                var data = google.visualization.arrayToDataTable([
                    ["Element", "Density", {role: "style"}],
                    ["Tournament", ${requestScope.count_tournament}, "#b87333"],
                    ["Bird", ${requestScope.count_bird}, "silver"],
                    ["Account", ${requestScope.count_account}, "gold"],
                    ["Blog", ${requestScope.count_blog}, "color: #e5e4e2"]
                ]);

                var view = new google.visualization.DataView(data);
                view.setColumns([0, 1,
                    {
                        calc: "stringify",
                        sourceColumn: 1,
                        type: "string",
                        role: "annotation"
                    },
                    2]);

                var options = {
                    title: "Categories in bird tournament web site, in 1 unit",
                    width: 700,
                    height: 400,
                    bar: {groupWidth: "95%"},
                    legend: {position: "none"},
                };
                var chart = new google.visualization.BarChart(document.getElementById("barchart_values"));
                chart.draw(view, options);
            }
            //   Pie chart
            google.charts.load("current", {packages: ["corechart"]});
            google.charts.setOnLoadCallback(drawChart1);
            function drawChart1() {

                var data = google.visualization.arrayToDataTable([
                    ['Task', 'Hours per Day'],
                    ['On-Going', ${requestScope.count_tournament_onGoing}],
                    ['Finised', ${requestScope.count_tournament_finised}],
                    ['Delay', ${requestScope.count_tournament_delay}],
                ]);

                var options = {
                    title: 'Categories Status Tournament',
                    width: 700,
                    height: 400,
                };

                var chart = new google.visualization.PieChart(document.getElementById('piechart'));

                chart.draw(data, options);
            }
        </script>
        <title>Admin Dashboard</title>
    </head>
    <body>
        <header>
            <%@include file="adminHeader.jsp" %>
        </header>
        <section>
            <!-- container -->
            <div class="container">
                <!-- Total Categories Site -->
                <div class="main-body-top">
                    <div class="main-body-total-categories">
                        <!-- Each categories site -->
                        <div class="each-total-categories-site">
                            <div class="each-total-categories-site-left">
                                <h1>Battle</h1>
                                <!-- Number -->
                                <h3>${requestScope.count_tournament}</h3>
                            </div>
                            <div class="each-total-categories-site-right" style="background-color: brown; color: white;">
                                <span class="material-symbols-outlined">local_fire_department</span>
                            </div>
                        </div>
                        <div class="each-total-categories-site">
                            <div class="each-total-categories-site-left">
                                <h1>Bird</h1>
                                <!-- Number -->
                                <h3>${requestScope.count_bird}</h3>
                            </div>
                            <div class="each-total-categories-site-right" style="background-color: forestgreen; color: white;">
                                <span class="material-symbols-outlined">pets</span>
                            </div>
                        </div>
                        <div class="each-total-categories-site">
                            <div class="each-total-categories-site-left">
                                <h1>Account</h1>
                                <!-- Number -->
                                <h3>${requestScope.count_account}</h3>
                            </div>
                            <div class="each-total-categories-site-right" style="background-color: #1980c5; color: white;">
                                <span class="material-symbols-outlined">person_add</span>
                            </div>
                        </div>
                        <div class="each-total-categories-site">
                            <div class="each-total-categories-site-left">
                                <h1>Blog</h1>
                                <!-- Number -->
                                <h3>${requestScope.count_blog}</h3>
                            </div>
                            <div class="each-total-categories-site-right" style="background-color: #abc41f; color: white;">
                                <span class="material-symbols-outlined">breaking_news_alt_1</span>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- chart -->
                <div class="chart-container-site d-flex justify-content-center">
                    <div id="barchart_values"></div>
                </div>
                <div class="chart-container-site d-flex justify-content-center">
                    <div id="piechart" class="pie-chart"></div>
                </div>
                <!-- List site -->
                <div class="list-tournament-bird-site">
                    <!-- list tournament site -->
                    <div class="list-tournment-site">
                        <div class="list-tournament-heading-site d-flex justify-content-between">
                            <h1 style=" font-family: 'Ubuntu', sans-serif; color: #A87B24;"> <i class="fa-solid fa-star" style="color:yellow;"></i>     All Tournament</h1>
                        </div>
                        <div class="tournament-table">
                            <table class="table table-hover">
                                <thead>
                                    <tr>
                                        <th scope="col">Tournament ID</th>
                                        <th scope="col">Tournament Name</th>
                                        <th scope="col">Number of Player</th>
                                        <th scope="col">Date time</th>
                                        <th scope="col">Price</th>
                                        <th scope="col">Status</th>
                                    </tr>
                                </thead>
                                <c:forEach var="list" items="${t_list}" >
                                    <tbody>
                                        <tr>
                                            <td>#${list.getTournamentID()}</td>
                                            <td>${list.getTournamentName()}</td>
                                            <td>${list.getMaxParticipant()}</td>
                                            <td>${list.getDateTime()}</td>
                                            <td>${list.getFee()}</td>
                                            <c:choose>
                                                <c:when test="${list.tournamentStatus == 1}"><td><h3 class="text-warning">Open Form</h3></td></c:when>
                                                <c:when test="${list.tournamentStatus == 2}"><td <h3 class="text-primary">Close Form</h3></c:when>
                                                <c:when test="${list.tournamentStatus == 3}"><td ><h3 class="text-success">On-Going</h3></td></c:when>
                                                <c:when test="${list.tournamentStatus == 4}"><td><h3 class="text-danger">Finished</h3></td></c:when>
                                                <c:when test="${list.tournamentStatus == 5}"><td><h3 class="text-secondary">Delay</h3></td></c:when>
                                                <c:otherwise>Delay</c:otherwise>
                                            </c:choose>
                                        </tr>
                                    </tbody>
                                </c:forEach>
                            </table>
                        </div>
                    </div>
                </div>
                <!-- End main body -->
            </div>
        </section>
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
    </body>
</html>
