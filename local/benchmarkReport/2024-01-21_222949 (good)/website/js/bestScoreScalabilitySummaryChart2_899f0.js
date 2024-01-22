
var chart_bestScoreScalabilitySummaryChart2_899f0 = new Chart(document.getElementById('chart_bestScoreScalabilitySummaryChart2_899f0'), {
    type: 'line',
    data: {
        labels: [
            11550, 44275, 168560, 1113840, 3234420
        ],
        datasets: [
            {
                  label: 'Tabu 5 500',
                    borderWidth: 1
                  ,
                  data: [
                    500, 1000, 1475, 2470, 3500
                  ]
                }, 
{
                  label: 'Tabu 10 1000',
                    borderWidth: 1
                  ,
                  data: [
                    500, 1000, 1475, 2485, 3500
                  ]
                }, 
{
                  label: 'LAHC 400 swap device (favorite)',
                    borderWidth: 4
,
                  data: [
                    500, 1000, 1500, 2355, 3500
                  ]
                }, 
{
                  label: 'LAHC 400 swap test',
                    borderWidth: 1
                  ,
                  data: [
                    500, 1000, 1500, 2340, 3500
                  ]
                }, 
{
                  label: 'LAHC 400 swap test and device',
                    borderWidth: 1
                  ,
                  data: [
                    500, 1000, 1500, 2350, 3500
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
                text: 'Best soft score scalability summary (higher is better)'
            },
            tooltip: {
                callbacks: {
                }
            }
        },
        scales: {
            x: {
                title: {
                    display: true,
                    text: 'Problem scale (logarithmic)'
                },
                ticks: {
                },
                suggestedMin: 0,
                suggestedMax: 3234420,
                type: 'logarithmic',
                display: true
            },
            y: {
                title: {
                    display: true,
                    text: 'Best soft score'
                },
                ticks: {
                        stepSize: 100
                        
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
  chart_bestScoreScalabilitySummaryChart2_899f0.resize(1280, 720);
});
window.addEventListener('afterprint', () => {
  chart_bestScoreScalabilitySummaryChart2_899f0.resize();
});
