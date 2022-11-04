angular.module('market-front').controller('storeController', function ($scope, $http, $location, $localStorage) {


    const contextPath = "http://localhost:8180/core/api/v1";
    let page = 1;

    let totalPage;

    $scope.loadNext = function () {
        if (totalPage !== page) {
            ++page;
            $scope.loadProducts(page)
            document.getElementById("up").scrollIntoView({
                behavior: 'smooth',
                block: 'start'
            })
        }
    }

    $scope.loadPrevious = function () {
        if (page !== 1) {
            --page;
            $scope.loadProducts(page)
            document.getElementById("up").scrollIntoView({
                behavior: 'smooth',
                block: 'start'
            })
        }
    }

    $scope.loadCategory = function () {
        $http.get(contextPath + '/category')
            .then(function (response) {
                $scope.category = response.data;
            })
    };

    $scope.addToCart = function (id) {
        $scope.UserAndProductInfo = {}
        $scope.UserAndProductInfo.productId = id;
        $scope.UserAndProductInfo.productCount = 1;
        $scope.UserAndProductInfo.username = $localStorage.webMarketUser.username;
        $http.post('http://localhost:8180/cart/api/v1/cart', $scope.UserAndProductInfo)
            .then(function successCallback(response) {
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
            document.getElementById("up").scrollIntoView({
                behavior: 'smooth',
                block: 'start'
            })
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

    $scope.sortByCategory = function (category, index = 1) {
        $http({
            url: contextPath + '/product/sort/' + category,
            method: 'GET',
            params: {
                i: index
            }
        })
            .then(function successCallback(response) {
                totalPage = response.data.totalPages;
                $scope.productData = response.data;
                $scope.pagesArray = $scope.createPagesArray(1, totalPage);
                page = index;
            }, function failCallback(response) {
                console.log(response)
            })
    };

    $scope.loadProducts(page);
    $scope.loadCategory();
});
