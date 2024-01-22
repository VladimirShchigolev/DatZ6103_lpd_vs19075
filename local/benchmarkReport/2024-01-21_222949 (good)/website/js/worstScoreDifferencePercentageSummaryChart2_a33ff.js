
var chart_worstScoreDifferencePercentageSummaryChart2_a33ff = new Chart(document.getElementById('chart_worstScoreDifferencePercentageSummaryChart2_a33ff'), {
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
                    0, 0, 0, 0, 0
                  ]
                }, 
{
                  label: 'Tabu 10 1000',
                  grouped: true,
                    borderWidth: 1
                  ,
                  data: [
                    0, 0, 0.6072874493927125, 0, 0
                  ]
                }, 
{
                  label: 'LAHC 400 swap device (favorite)',
                  grouped: true,
                    borderWidth: 4
,
                  data: [
                    0, 1.694915254237288, -4.65587044534413, 0, 0
                  ]
                }, 
{
                  label: 'LAHC 400 swap test',
                  grouped: true,
                    borderWidth: 1
                  ,
                  data: [
                    0, 1.694915254237288, -5.263157894736842, 0, 0
                  ]
                }, 
{
                  label: 'LAHC 400 swap test and device',
                  grouped: true,
                    borderWidth: 1
                  ,
                  data: [
                    0, 1.694915254237288, -4.8582995951417, 0, 0
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
                text: 'Worst soft score difference percentage summary (higher is better)'
            }
        },
        scales: {
            x: {
                display: true
            },
            y: {
                title: {
                    display: true,
                    text: 'Worst soft score difference percentage'
                },
                ticks: {
                        stepSize: 0.1
                        
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
  chart_worstScoreDifferencePercentageSummaryChart2_a33ff.resize(1280, 720);
});
window.addEventListener('afterprint', () => {
  chart_worstScoreDifferencePercentageSummaryChart2_a33ff.resize();
});