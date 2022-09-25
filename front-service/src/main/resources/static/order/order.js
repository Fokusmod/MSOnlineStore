angular.module('market-front').controller('orderController', function ($scope, $http, $location, $localStorage) {

    const contextPath = "http://localhost:8180/order/api/v1";

    $scope.isEmpty = false

    $scope.loadOrder = function () {
        $http.get(contextPath + '/order/' + $localStorage.webMarketUser.username)
            .then(function successCallback (response) {
                $scope.isEmpty = true
                $scope.orderId = response.data.id
                $scope.address = response.data.address
                $scope.phoneNumber = response.data.phoneNumber
                $scope.status = response.data.status.title

                if($scope.status==="Оплачено") {
                    document.getElementById("color").style.color = 'green';
                }
                if($scope.status==="Не оплачено") {
                    document.getElementById("color").style.color = 'red';
                }

                $scope.orderItems = response.data.orderItems
                $scope.orderPrice = function () {
                    var total = 0;
                    for (var i = 0; i < response.data.orderItems.length; i++) {
                        $scope.item = response.data.orderItems[i];
                        total += $scope.item.sum
                    }
                    return total
                }
            },function failCallback(response){
                $scope.isEmpty = false
            })
    }
    $scope.loadOrder()

    function loadAsync(url, callback) {
        var s = document.createElement('script');
        s.setAttribute('src', url);
        s.onload = callback;
        document.head.insertBefore(s, document.head.firstElementChild);
    }

    loadAsync('https://www.paypal.com/sdk/js?client-id=test&currency=RUB',
        function () {
            paypal.Buttons({
                createOrder: function (data, actions) {
                    return fetch('http://localhost:8180/order/api/v1/paypal/create/' + $localStorage.webMarketUser.username, {
                        method: 'post',
                        headers: {
                            'content-type': 'application/json'
                        }
                    }).then(function (response) {
                        return response.text();
                    });
                },
                onApprove: function (data, actions) {
                    return fetch('http://localhost:8180/order/api/v1/paypal/capture/' + data.orderID, {
                        method: 'post',
                        headers: {
                            'content-type': 'application/json'
                        }
                    }).then(function (response) {
                        response.text().then(msg => alert(msg));
                        $http.get('http://localhost:8180/order/api/v1/status/' + $localStorage.webMarketUser.username)
                            .then(function (response) {
                                $scope.loadOrder();
                            });
                    });
                }

            }).render('#paypal-buttons');
        });


});

