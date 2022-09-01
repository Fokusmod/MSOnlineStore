angular.module('market-front').controller('cartController', function ($scope, $http, $location, $localStorage) {

    const contextPath = "http://localhost:8180/cart/api/v1";


    $scope.loadProductFromCart = function () {
        $http.get(contextPath + '/cart/' + $localStorage.webMarketUser.username)
            .then(function (response) {
                console.log(response);
                $scope.cartProducts = response.data.items;
            })
    }


    $scope.deleteProductFromCart = function (id) {
        $scope.cart = {};
        $scope.cart.productId = id
        $scope.cart.username = $localStorage.webMarketUser.username;
        $http.put(contextPath + '/cart', $scope.cart)
            .then(function successCallback(response) {
                alert("Товар удален.")
                $scope.loadProductFromCart();
            }, function failCallback(response) {
                alert(response.data.message)
            })
    }

    $scope.loadProductFromCart();

});
