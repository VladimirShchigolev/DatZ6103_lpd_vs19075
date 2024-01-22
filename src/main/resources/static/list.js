$(document).ready(function () {
    $.getJSON("/schedules/list", function(schedules) {
        var listofschedules = $("#listofschedules");
        $.each(schedules, function(idx, value) {
              listofschedules.append($('<li><a href="schedule.html?id='+ value.solutionId + '">' +
               value.score +'</a><a href="schedule_leaflet.html?id='+ value.solutionId + '"> (map) </a>' +
               ' devices: ' + value.devices.length + ', tests:' + value.tests.length + '</li>'));
        });
    });
});