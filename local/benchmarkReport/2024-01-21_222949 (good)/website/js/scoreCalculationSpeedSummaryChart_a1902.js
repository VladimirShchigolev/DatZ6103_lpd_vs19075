
var chart_scoreCalculationSpeedSummaryChart_a1902 = new Chart(document.getElementById('chart_scoreCalculationSpeedSummaryChart_a1902'), {
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
                    278162, 268350, 247266, 246131, 266793
                  ]
                }, 
{
                  label: 'Tabu 10 1000',
                    borderWidth: 1
                  ,
                  data: [
                    286765, 265945, 253307, 248111, 256687
                  ]
                }, 
{
                  label: 'LAHC 400 swap device (favorite)',
                    borderWidth: 4
,
                  data: [
                    262827, 246243, 241008, 250256, 259100
                  ]
                }, 
{
                  label: 'LAHC 400 swap test',
                    borderWidth: 1
                  ,
                  data: [
                    225808, 217443, 222576, 231039, 256701
                  ]
                }, 
{
                  label: 'LAHC 400 swap test and device',
                    borderWidth: 1
                  ,
                  data: [
                    215786, 200224, 211689, 222606, 251703
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
                text: 'Score calculation speed summary (higher is better)'
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
                    text: 'Score calculation speed per second'
                },
                ticks: {
                        stepSize: 1000
                        
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
  chart_scoreCalculationSpeedSummaryChart_a1902.resize(1280, 720);
});
window.addEventListener('afterprint', () => {
  chart_scoreCalculationSpeedSummaryChart_a1902.resize();
});
