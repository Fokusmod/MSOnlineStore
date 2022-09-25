angular.module('market-front').controller('cartController', function ($scope, $http, $location, $localStorage) {

    const contextPath = "http://localhost:8180/cart/api/v1";

    $scope.isEmpty = true

    $scope.loadProductFromCart = function () {
        $http.get(contextPath + '/cart/' + $localStorage.webMarketUser.username)
            .then(function successCallback(response) {
                if (response.data === "") {
                    $scope.isEmpty = false
                } else {
                    $scope.cartProducts = response.data.items;
                    $scope.cartPrice = function () {
                        var total = 0;
                        for (var i = 0; i < response.data.items.length; i++) {
                            $scope.item = response.data.items[i];
                            total += $scope.item.sum
                        }
                        return total
                    }
                }

            }, function failCallback(response) {
            })
    }


    $scope.deleteProductFromCart = function (id, count) {
        $scope.UserAndProductInfo = {};
        $scope.UserAndProductInfo.productId = id
        $scope.UserAndProductInfo.productCount = count
        $scope.UserAndProductInfo.username = $localStorage.webMarketUser.username;
        $http.put(contextPath + '/cart', $scope.UserAndProductInfo)
            .then(function successCallback(response) {
                alert("Товар удален.")
                $scope.loadProductFromCart();
            }, function failCallback(response) {
                alert(response.data.message)
            })
    }

    $scope.loadProductFromCart();

    $scope.createOrder = function () {
        $scope.OrderDto = {}
        $scope.OrderDto.address = $scope.cartOrder.address
        $scope.OrderDto.phoneNumber = $scope.cartOrder.phoneNumber
        $scope.OrderDto.list = $scope.cartProducts
        $scope.OrderDto.price = $scope.cartPrice()
        $scope.OrderDto.username = $localStorage.webMarketUser.username;
        $http.post(contextPath + '/order', $scope.OrderDto)
            .then(function successCallback(response) {
                alert("Заказ успешно оформлен")
                $http.delete(contextPath + '/cart/' + $localStorage.webMarketUser.username)
                    .then(function successCallback(response) {
                    }, function failCallback(response) {
                    })
                $location.path('/order');
            }, function failCallback(response) {
                alert(response)
            })


    }

});
