
var chart_worstScoreCalculationSpeedDifferencePercentageSummaryChart_8c51a = new Chart(document.getElementById('chart_worstScoreCalculationSpeedDifferencePercentageSummaryChart_8c51a'), {
    type: 'bar',
    data: {
        labels: [
            'classExample10', 'classExample15', 'classExample25', 'classExample35', 'classExample5'
        ],
        datasets: [
            {
                  label: 'Tabu 5 500',
                  grouped: true,
                    borderWidth: 1
                  ,
                  data: [
                    34.02489212082468, 16.80625823731984, 10.567999065613686, 5.9951609635165255, 28.90641654231507
                  ]
                }, 
{
                  label: 'Tabu 10 1000',
                  grouped: true,
                    borderWidth: 1
                  ,
                  data: [
                    32.82373741409621, 19.659972884750744, 11.457462961465549, 1.9801114805941922, 32.89323681795853
                  ]
                }, 
{
                  label: 'LAHC 400 swap device (favorite)',
                  grouped: true,
                    borderWidth: 4
,
                  data: [
                    22.983758190826276, 13.850034720745999, 12.421048848638401, 2.938781023666782, 21.799838729111247
                  ]
                }, 
{
                  label: 'LAHC 400 swap test',
                  grouped: true,
                    borderWidth: 1
                  ,
                  data: [
                    8.599868147674604, 5.142921927922566, 3.7883075927872567, 1.9856735914947379, 4.644416227188048
                  ]
                }, 
{
                  label: 'LAHC 400 swap test and device',
                  grouped: true,
                    borderWidth: 1
                  ,
                  data: [
                    0, 0, 0, 0, 0
                  ]
                }
        ]
    },
    options: {
        animation: false,
        responsive: true,
        maintainAspectRatio: false,
        resizeDelay: 100,
        spanGaps: true,
        plugins: {
            title: {
                display: true,
                text: 'Worst score calculation speed difference percentage summary (higher is better)'
            }
        },
        scales: {
            x: {
                display: true
            },
            y: {
                title: {
                    display: true,
                    text: 'Worst score calculation speed difference percentage'
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
  chart_worstScoreCalculationSpeedDifferencePercentageSummaryChart_8c51a.resize(1280, 720);
});
window.addEventListener('afterprint', () => {
  chart_worstScoreCalculationSpeedDifferencePercentageSummaryChart_8c51a.resize();
});