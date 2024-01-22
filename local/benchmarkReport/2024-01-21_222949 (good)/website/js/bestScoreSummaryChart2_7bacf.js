
var chart_bestScoreSummaryChart2_7bacf = new Chart(document.getElementById('chart_bestScoreSummaryChart2_7bacf'), {
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
                    1000, 1475, 2470, 3500, 500
                  ]
                }, 
{
                  label: 'Tabu 10 1000',
                  grouped: true,
                    borderWidth: 1
                  ,
                  data: [
                    1000, 1475, 2485, 3500, 500
                  ]
                }, 
{
                  label: 'LAHC 400 swap device (favorite)',
                  grouped: true,
                    borderWidth: 4
,
                  data: [
                    1000, 1500, 2355, 3500, 500
                  ]
                }, 
{
                  label: 'LAHC 400 swap test',
                  grouped: true,
                    borderWidth: 1
                  ,
                  data: [
                    1000, 1500, 2340, 3500, 500
                  ]
                }, 
{
                  label: 'LAHC 400 swap test and device',
                  grouped: true,
                    borderWidth: 1
                  ,
                  data: [
                    1000, 1500, 2350, 3500, 500
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
                text: 'Best soft score summary (higher is better)'
            }
        },
        scales: {
            x: {
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
  chart_bestScoreSummaryChart2_7bacf.resize(1280, 720);
});
window.addEventListener('afterprint', () => {
  chart_bestScoreSummaryChart2_7bacf.resize();
});