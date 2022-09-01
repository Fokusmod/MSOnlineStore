angular.module('market-front').controller('createController', function ($scope, $http, $location) {

    const contextPath = "http://localhost:8180/core/api/v1";


    $scope.loadCategory = function () {
        $http.get(contextPath + '/category')
            .then(function (response) {
                $scope.category = response.data;
            })
    };

    $scope.createNewProduct = function () {
        let a = document.getElementById('categoryId').value;
        $scope.new_product.categoryTitle = a;
        $http.post(contextPath + '/products', $scope.new_product)
            .then(function successCallback(response) {
                $scope.new_product = null;
                alert("Продукт успешно добавлен.")
                $location.path('/store');
            }, function failCallback(response) {
                alert(response.data.messages)
            })
    };

    $scope.loadCategory();
});
