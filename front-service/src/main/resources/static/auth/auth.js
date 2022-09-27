angular.module('market-front').controller('authController', function ($rootScope, $scope, $http,$localStorage,$location) {

    const contextPath = 'http://localhost:8180/auth/api/v1';

    $rootScope.tryToAuth = function () {
        $http.post(contextPath + '/authenticate', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.webMarketUser = {username: $scope.user.username, token: response.data.token};

                    $scope.user.username = null;
                    $scope.user.password = null;
                    $location.path('/store');
                }
            }, function errorCallback(response) {
            });
    };

    $scope.tryToLogout = function () {
        $scope.clearUser();
        if ($scope.user.username) {
            $scope.user.username = null;
        }
        if ($scope.user.password) {
            $scope.user.password = null;
        }
    };

    $scope.clearUser = function () {
        delete $localStorage.webMarketUser;
        $http.defaults.headers.common.Authorization = '';
    };

    $rootScope.isUserLoggedIn = function () {
        if ($localStorage.webMarketUser) {
            return true;
        } else {
            return false;
        }
    };



    // const registrationUrl = 'http://localhost:8180/auth/api/v1';
    //
    // $scope.regRequest = function () {
    //     $http.post(registrationUrl + '/registration', $scope.new_user)
    //         .then(function successCallback(response) {
    //             $scope.new_user = null;
    //             alert(response.data.messages)
    //             $location.path('/');
    //         }, function failCallback(response) {
    //             alert(response.data.messages)
    //         })
    // };
    //
    // $scope.tryToRegistration = function () {
    //     if ($scope.new_user.username == null || $scope.new_user.password == null || $scope.new_user.email == null) {
    //         alert("Поля обязательны для заполнения")
    //     } else {
    //         $scope.regRequest();
    //     }
    // }


});
