angular.module('market-front').controller('reportController', function ($scope, $http, $location) {

    var stompClient = null;

    const api_stats = "http://localhost:8180/core/api/v1/stats";
    $scope.loadStats = function () {
        $http.get(api_stats + '/all')
            .then(function (response) {
                $scope.stats = response.data;
            })
    };

    $scope.loadStats();

    const api_call = "http://localhost:8180/call/api/v1/requests";
    $scope.content = [];
    $scope.cont = [];
    $scope.apiData = [];
    $scope.loadCall = function () {
        $http.get(api_call)
            .then(function (response) {
                $scope.apiData = response.data;
                $scope.callContent();
            })
    };

    $scope.loadCall();


    $scope.callContent = function () {
        for (let i = 0; i < $scope.apiData.length; i++) {
            $http.get("http://localhost:8180/auth/api/v1/" + $scope.apiData[i].userId)
                .then(function (response) {
                    $scope.content.username = response.data.username;
                    $scope.content.email = response.data.email;
                    $scope.content.cart = $scope.apiData[i].cartItemDtoList
                    $scope.cont.push($scope.content)
                    $scope.content = [];
                });
        }
    };


    $scope.connect = function () {
        var socket = new SockJS("http://localhost:8181/market-core/web-socket")
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function (frame) {
            console.log('Connected:' + frame);
            stompClient.subscribe('/topic/file', function (message) {
                $scope.download(message.body);
            })
        })
    };

    $scope.disconnect = function () {
        if (stompClient != null) {
            stompClient.disconnect();
        }
        console.log("Disconnected");
    };

    $scope.sendMessage = function () {
        stompClient.send("/app/web-socket", {},
            "productInfo.xlsx");
    };

    $scope.download = function (fileUrl) {
        window.location.href = fileUrl;
    };

    window.onload = new function () {
        $scope.connect();
    };

    $scope.viewDivProduct = function () {
        let elements = document.querySelectorAll('div.content-div>div');
        for (const element of elements) {
            document.getElementById(element.id).style.display = "none"
        }
        document.getElementById("productContent").style.display = "block";
        document.getElementById("mainDiv").style.display = "block";
    };

    $scope.viewDivStats = function () {
        let elements = document.querySelectorAll('div.content-div>div');
        for (const element of elements) {
            document.getElementById(element.id).style.display = "none"
        }
        document.getElementById("statsContent").style.display = "block";
        document.getElementById("mainDiv").style.display = "block";
    };
    $scope.viewDivCall = function () {
        let elements = document.querySelectorAll('div.content-div>div');
        for (const element of elements) {
            document.getElementById(element.id).style.display = "none"
        }
        document.getElementById("callContent").style.display = "block";
        document.getElementById("mainDiv").style.display = "block";
    };

});



