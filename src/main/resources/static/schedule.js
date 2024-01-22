function getScorePopoverContent(constraint_list) {
    var popover_content = "Hello";
    constraint_list.forEach((constraint) => {
          if (getHardScore(constraint.score) == 0) {
             popover_content = popover_content + constraint.name + " : " + constraint.score + "<br>";
          } else {
             popover_content = popover_content + "<b>" + constraint.name + " : " + constraint.score + "</b><br>";
          }
    })
    return popover_content;
}

function getEntityPopoverContent(entityId, indictmentMap) {
    var popover_content = "";
    const indictment = indictmentMap[entityId];
    if (indictment != null) {
        popover_content = popover_content + "Total score: <b>" + indictment.score + "</b> (" + indictment.matchCount + ")<br>";
        indictment.constraintMatches.forEach((match) => {
                  if (getHardScore(match.score) == 0) {
                     popover_content = popover_content + match.constraintName + " : " + match.score + "<br>";
                  } else {
                     popover_content = popover_content + "<b>" + match.constraintName + " : " + match.score + "</b><br>";
                  }
            })
    }
    return popover_content;
}

function getHardScore(score) {
   return score.slice(0,score.indexOf("hard"))
}

function getMediumScore(score) {
   return score.slice(score.indexOf("hard/")+5,score.indexOf("medium"))
}

function getSoftScore(score) {
   return score.slice(score.indexOf("medium/")+7,score.indexOf("soft"))
}

$(document).ready(function () {
    const urlParams = new URLSearchParams(window.location.search);
    const solutionId = urlParams.get('id');

    $.getJSON("/schedules/score?id=" + solutionId, function(analysis) {
            var badge = "badge bg-danger";
            if (getHardScore(analysis.score)==0) { badge = "badge bg-success"; }
            $("#score_a").attr({"title":"Score Brakedown","data-bs-content":"" + getScorePopoverContent(analysis.constraints) + "","data-bs-html":"true"});
            $("#score_text").text(analysis.score);
            $("#score_text").attr({"class":badge});
    });

    $.getJSON("/schedules/solution?id=" + solutionId, function(solution) {
        $.getJSON("/schedules/indictments?id=" + solutionId, function(indictments) {
                        renderSchedules(solution, indictments);
                        $(function () {
                          $('[data-toggle="popover"]').popover()
                        })
        })
    });

});

function renderSchedules(solution, indictments) {
    var indictmentMap = {};
    indictments.forEach((indictment) => {
         indictmentMap[indictment.indictedObjectID] = indictment;
    })

    solution["testRunsMap"] = solution.testRuns.reduce(function(map, testRun) {
                                  map[testRun.id] = testRun;
                                  return map;
                              }, {});

    solution["testsMap"] = solution.tests.reduce(function(map, test) {
                                      map[test.name] = test;
                                      return map;
                                  }, {});

    console.log(solution["testRunsMap"]);
    console.log(solution["testsMap"]);

    const time_limit_header = $("#time_limit");
    time_limit_header.text("Time Limit: " + formatTime(solution.timeLimit));

    const min_arch_cover_header = $("#min_arch_cover");
    min_arch_cover_header.text("min(architecture cover per test) = " +
                          getMediumScore(indictmentMap["Minimal Architecture Cover"].score) + "%");

    const device_div = $("#device_container");
    solution.devices.forEach((device) => {
        console.log(device.name);
        var runtime = 0;
        device.testRuns.forEach((testRun) => {
            testRun = solution.testRunsMap[testRun];
            runtime = runtime + testRun.runTime;
        });
        var d_badge = "badge bg-danger";
        if (indictmentMap[device.name]==null || getHardScore(indictmentMap[device.name].score)==0) { d_badge = "badge bg-success"; }
        device_div.append($('<a data-toggle="popover" data-bs-html="true" data-bs-content="' +
        'name=' + device.name +
        '<br>compSpeed=' + device.compSpeed +
        '<br>platforms=' + device.platforms +
        '<br>architectures=' + device.architectures +
        '<hr>' +
        getEntityPopoverContent(device.name, indictmentMap) +
        '" data-bs-original-title="'+ device.name + ' (' + formatTime(runtime) + ')"><span class="'+ d_badge +'">'+
        device.name + ' (' + formatTime(runtime) + ')</span></a>'));
        var testRun_nr = 1;
        var finished_at = 0
        device.testRuns.forEach((testRun) => {
            testRun = solution.testRunsMap[testRun];
            if (testRun.test !== null) {
                var testRun_badge = "badge bg-danger";
                if (indictmentMap[testRun.id] == null || getHardScore(indictmentMap[testRun.id].score)==0) { testRun_badge = "badge bg-success"; }
                finished_at = finished_at + testRun.runTime;
                device_div.append($('<a data-toggle="popover" data-bs-html="true" data-bs-content="'+
                'id=' + testRun.id +
                '<br>test=' + testRun.test +
                '<br>device=' + testRun.device +
                '<br>platform=' + testRun.platform +
                '<br>architecture=' + testRun.architecture +
                '<br>runtime=' + formatTime(testRun.runTime) +
                '<br>finishedAt=' + formatTime(finished_at) +
                '<hr>' +
                getEntityPopoverContent(testRun.id, indictmentMap) +
                '" data-bs-original-title="'+
                '#' + testRun_nr + ' ' +testRun.test + ' (' + formatTime(finished_at)+')"><span class="'+testRun_badge+'">'+
                        '#' + testRun_nr + ' ' +testRun.test + ' (' + formatTime(finished_at)+')</span></a>'));

                testRun_nr = testRun_nr + 1;
            }
        })
        device_div.append($('<br>'));
    });

    const test_div = $("#test_container");
    solution.tests.forEach((test) => {
        console.log(test.name);
        var tested_architectures = new Set();
        var tested_platforms = new Set();
        test.testRuns.forEach((testRun) => {
            testRun = solution.testRunsMap[testRun];
            tested_architectures.add(testRun.architecture);
            tested_platforms.add(testRun.platform);
        });
        tested_architectures = Array.from(tested_architectures);
        tested_architectures.sort();
        tested_platforms = Array.from(tested_platforms);
        tested_platforms.sort();
        var t_badge = "badge bg-danger";
        if (indictmentMap[test.name]==null || getHardScore(indictmentMap[test.name].score)==0) { t_badge = "badge bg-success"; }
        test_div.append($('<a data-toggle="popover" data-bs-html="true" data-bs-content="' +
        'name=' + test.name +
        '<br>avgRunTime=' + test.avgRunTime +
        '<br>platforms=' + test.platforms.toSorted() +
        '<br>tested on: ' + tested_platforms +
        '<br>available architectures count=' + test.architectureCount +
        '<br>tested architectures count: ' + tested_architectures.length +
        '<br>tested on: ' + tested_architectures +
        '<hr>' +
        getEntityPopoverContent(test.name, indictmentMap) +
        '" data-bs-original-title="'+ test.name + '"><span class="'+ t_badge +'">'+
        test.name + '</span></a>'));
        test_div.append($('<br>'));
    })
}

function formatTime(timeInSeconds) {
        if (timeInSeconds != null) {
            const HH = Math.floor(timeInSeconds / 3600);
            const MM = Math.floor((timeInSeconds % 3600) / 60);
            const SS = Math.floor(timeInSeconds % 60);
            return HH + ":" + MM + ":" + SS;
        } else return "null";
}
