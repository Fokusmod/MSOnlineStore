angular.module('market-front').controller('editProductController', function ($scope, $http, $location, $routeParams) {

    const contextPath = 'http://localhost:8180/core/api/v1';

    let page = 1;

    $scope.loadCategory = function () {
        $http.get(contextPath + '/category')
            .then(function (response) {
                $scope.category = response.data;
            })
    };


    $scope.prepareProductForUpdate = function () {
        $http.get(contextPath + '/products/' + $routeParams.productId)
            .then(function successCallback(response) {
                    $scope.updated_product = response.data;
                }, function failCallback(response) {
                    alert(response.data.messages);
                    $location.path('/store');
                }
            );
    }

    $scope.updateProduct = function () {
        $http.put(contextPath + '/products', $scope.updated_product)
            .then(function successCallback(response) {
                    $scope.updated_product = null;
                    alert("Продукт успешно обновлен");
                    $location.path('/store');
                }, function failCallback(response) {
                    alert(response.data.messages);
                }
            );
    }

    $scope.loadCategory();
    $scope.prepareProductForUpdate();
});
