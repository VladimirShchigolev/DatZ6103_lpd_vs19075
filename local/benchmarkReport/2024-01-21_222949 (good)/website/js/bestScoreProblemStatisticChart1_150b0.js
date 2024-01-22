
var chart_bestScoreProblemStatisticChart1_150b0 = new Chart(document.getElementById('chart_bestScoreProblemStatisticChart1_150b0'), {
    type: 'line',
    data: {
        labels: [
            3650, 3655, 3665, 3666, 3667, 3671, 3672, 3681, 3692, 3712, 3717, 3718, 3721, 3734, 3746, 3760, 3763, 3777, 3779, 3782, 3797, 3814, 3828, 3886, 3892, 3946, 3951, 3955, 3956, 3965, 3970, 3975, 4002, 4012, 4017, 4048, 4067, 4068, 4104, 4105, 4123, 4129, 4204, 4215, 4260, 4262, 4265, 4268, 4271, 4280, 4282, 4289, 4309, 4411, 4448, 4469, 4526, 4528, 4561, 4583, 4588, 4601, 4705, 4723, 5033, 5045, 5128, 5174, 5702, 5748, 5976, 6105, 6129, 6241, 6423, 6447, 6859, 6870, 8638, 8676, 8878, 8900, 9517, 9602, 10067, 10286, 10534, 16570, 16625, 20000
        ],
        datasets: [
            {
                  label: 'Tabu 5 500',
                        stepped: true,
                        pointRadius: 0,
                    borderWidth: 1
                  ,
                  data: [
                    , , , , , , , , , , , , , , , , , , , , 14, , , , , , , , , , , , , , , , , , , , , , , , 28, 42, 57, 57, 66, 66, 71, 71, 80, , , , , 80, , , , 85, , , , , , , , , , , , , , , , , , , , , , , , , , , , 85
                  ]
                }, 
{
                  label: 'Tabu 10 1000',
                        stepped: true,
                        pointRadius: 0,
                    borderWidth: 1
                  ,
                  data: [
                    , , , 14, , , , , , , , , , , , , , , , , , , , , , 28, 42, 57, , 71, 75, 80, , , , 80, , , , , , , , , , , , , , , , , , 85, , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , 85
                  ]
                }, 
{
                  label: 'LAHC 400 swap device (favorite)',
                        stepped: true,
                        pointRadius: 0,
                    borderWidth: 4
,
                  data: [
                    14, , 14, 42, 57, , , , 20, , , 20, , , 28, 33, , 33, , 20, , 33, , , , , , , , , , , 33, , 40, , , , 40, , 42, , 42, 50, , , , , , , , , , , , , 50, , 57, , , , , , 57, 60, , , , , , , 60, 66, , , 66, 71, , , , , 71, 80, , , , , , 80
                  ]
                }, 
{
                  label: 'LAHC 400 swap test',
                        stepped: true,
                        pointRadius: 0,
                    borderWidth: 1
                  ,
                  data: [
                    , 14, , , 28, 40, 33, , , , 33, , 20, , , 33, , , , , , , , , , , , , 40, , , , , , , , 40, 42, , , , 50, , , , , , , , , , , , , , , , , , 50, 57, , , , , , 57, 60, , , 60, 66, , , 66, 71, , , , , 71, 75, , , , 75, 80, , , 80
                  ]
                }, 
{
                  label: 'LAHC 400 swap test and device',
                        stepped: true,
                        pointRadius: 0,
                    borderWidth: 1
                  ,
                  data: [
                    , , , , , , , 14, , 28, , 40, , 40, , , 20, , 33, , , , 20, 20, 33, , , , , , , , , 33, , , , , , 40, , , , , , , , , , , , , , , 40, 42, , , , , , , 42, 57, , , , , 57, 60, , , , , , , , , 60, 66, , , , , 71, , , 71, 80, 80
                  ]
                }
        ]
    },
    options: {
        animation: false,
        responsive: true,
        maintainAspectRatio: false,
        spanGaps: true,
        plugins: {
            title: {
                display: true,
                text: 'classExample25 best medium score statistic'
            },
            tooltip: {
                callbacks: {
                        title: function(context) {
                            return humanizeTime(context[0].parsed.x);
                        }
                        
                }
            }
        },
        scales: {
            x: {
                title: {
                    display: true,
                    text: 'Time spent'
                },
                ticks: {
                        stepSize: 100
                        ,
                        callback: function(value, index) {
                            return humanizeTime(value);
                        }
                },
                suggestedMin: 0,
                suggestedMax: 20000,
                type: 'linear',
                display: true
            },
            y: {
                title: {
                    display: true,
                    text: 'Best medium score'
                },
                ticks: {
                        stepSize: 1
                        
                },
                type: 'linear',
                display: true
            }
        },
watermark: {
    image: "website/webjars/timefold/img/timefold-logo-stacked-positive.svg",
    x: 15,
    y: 15,
    width: 48,
    height: 50,
    opacity: 0.1,
    alignX: "right",
    alignY: "bottom",
    alignToChartArea: true,
    position: "front",
}    },
plugins: [{ 
    id: 'customPlugin',
    beforeDraw: (chart, args, options) => {
          const ctx = chart.canvas.getContext('2d');
          ctx.save();
          ctx.globalCompositeOperation = 'destination-over';
          ctx.fillStyle = 'white';
          ctx.fillRect(0, 0, chart.canvas.width, chart.canvas.height);
          ctx.restore();
    }
}]
});

window.addEventListener('beforeprint', () => {
  chart_bestScoreProblemStatisticChart1_150b0.resize(1280, 720);
});
window.addEventListener('afterprint', () => {
  chart_bestScoreProblemStatisticChart1_150b0.resize();
});
