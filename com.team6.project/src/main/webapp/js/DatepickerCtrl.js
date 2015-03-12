
var app = angular.module('plunker', []);

app.controller('MainCtrl', function($scope) {
  $scope.var1 = '12-07-2013';
});


app.directive('datetimez', function() {
    return {
        restrict: 'A',
        require : 'ngModel',
        link: function(scope, element, attrs, ngModelCtrl) {
          element.datetimepicker({           
           language: 'en',
           pickDate: false,          
          }).on('changeDate', function(e) {
            ngModelCtrl.$setViewValue(e.date);
            scope.$apply();
          });
        }
    };
});