angular.module('market-front').controller('storeController', function ($scope, $http, $location, $localStorage) {


    const contextPath = "http://localhost:8180/core/api/v1";
    let page = 1;

    let totalPage;

    $scope.loadNext = function () {
        if (totalPage !== page) {
            ++page;
            $scope.loadProducts(page)
        }
    }

    $scope.loadPrevious = function () {
        if (page !== 1) {
            --page;
            $scope.loadProducts(page)
        }
    }

    $scope.addToCart = function (id) {
        $scope.cartDto = {}
        $scope.cartDto.productId = id;
        $scope.cartDto.username = $localStorage.webMarketUser.username;
        $http.post('http://localhost:8180/cart/api/v1/cart', $scope.cartDto)
            .then(function successCallback(response) {
                console.log($scope.cartDto)
                alert("Товар успешно добавлен.")
            }, function failCallback(response) {
                alert(response.data.message)
            })
    }

    $scope.createPagesArray = function (start, end) {
        let arr = [];
        for (let i = start; i < end + 1; i++) {
            arr.push(i);
        }
        return arr;
    }

    $scope.loadProducts = function (index = 1) {
        $http({
            url: contextPath + '/products',
            method: 'GET',
            params: {
                i: index
            }
        }).then(function (response) {
            totalPage = response.data.totalPages;
            $scope.productData = response.data;
            $scope.pagesArray = $scope.createPagesArray(1, totalPage);
            page = index;
        })
    }

    $scope.deleteProduct = function (id) {
        $http.delete(contextPath + '/products/' + id)
            .then(function successCallback(response) {
                $scope.loadProducts(page);
            }, function failCallback(response) {
                alert(response.data.message)
            })
    };

    $scope.navToEditProductPage = function (productId) {
        $location.path('/edit_product/' + productId);
    }

    $scope.loadProducts(page);
});
