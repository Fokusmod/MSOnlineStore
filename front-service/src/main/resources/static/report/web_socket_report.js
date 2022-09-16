angular.module('market-front').controller('reportController', function ($scope, $http, $location) {

    var stompClient = null;

    $scope.connect = function () {
        var socket = new SockJS("http://localhost:8181/market-core/web-socket")
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function (frame) {
            console.log('Connected:' + frame);
            stompClient.subscribe('/topic/file', function (message) {
                $scope.download(message.body);
            })
        })
    }
    $scope.disconnect = function () {
        if (stompClient != null) {
            stompClient.disconnect();
        }
        console.log("Disconnected");
    }

    $scope.sendMessage = function () {
        stompClient.send("/app/web-socket", {},
            "productInfo.xlsx");
    }

    $scope.download = function (fileUrl) {
         window.location.href = fileUrl;
    }

    window.onload = new function () {
        $scope.connect();
    }

});



