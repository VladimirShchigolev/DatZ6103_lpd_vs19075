
var chart_bestScoreDistributionSummaryChart2_c5172 = new Chart(document.getElementById('chart_bestScoreDistributionSummaryChart2_c5172'), {
    type: 'boxplot',
    data: {
        labels: [
            'Problem_0', 'Problem_1', 'Problem_2', 'Problem_3', 'Problem_4'
        ],
        datasets: [
                {
                    label: 'Tabu 5 500',
                        borderWidth: 1
                    ,
                    data: [
                                null
                            , 
                                null
                            , 
                                null
                            , 
                                null
                            , 
                                {
                                    min: 2051,
                                    max: 2051,
                                    q1: 2051,
                                    q3: 2051,
                                    median: 2051,
                                    mean: 2051,
                                    items: [],
                                    outliers: [],
                                }
                            
                    ]
                }, 
                {
                    label: 'Tabu 10 1000',
                        borderWidth: 1
                    ,
                    data: [
                                null
                            , 
                                null
                            , 
                                null
                            , 
                                null
                            , 
                                {
                                    min: 1971,
                                    max: 1971,
                                    q1: 1971,
                                    q3: 1971,
                                    median: 1971,
                                    mean: 1971,
                                    items: [],
                                    outliers: [],
                                }
                            
                    ]
                }, 
                {
                    label: 'LAHC 400 (favorite)',
                        borderWidth: 4
,
                    data: [
                                {
                                    min: 500,
                                    max: 500,
                                    q1: 500,
                                    q3: 500,
                                    median: 500,
                                    mean: 500,
                                    items: [],
                                    outliers: [],
                                }
                            , 
                                {
                                    min: 787,
                                    max: 787,
                                    q1: 787,
                                    q3: 787,
                                    median: 787,
                                    mean: 787,
                                    items: [],
                                    outliers: [],
                                }
                            , 
                                {
                                    min: 1480,
                                    max: 1480,
                                    q1: 1480,
                                    q3: 1480,
                                    median: 1480,
                                    mean: 1480,
                                    items: [],
                                    outliers: [],
                                }
                            , 
                                {
                                    min: 2408,
                                    max: 2408,
                                    q1: 2408,
                                    q3: 2408,
                                    median: 2408,
                                    mean: 2408,
                                    items: [],
                                    outliers: [],
                                }
                            , 
                                {
                                    min: 2031,
                                    max: 2031,
                                    q1: 2031,
                                    q3: 2031,
                                    median: 2031,
                                    mean: 2031,
                                    items: [],
                                    outliers: [],
                                }
                            
                    ]
                }
        ]
    },
    options: {
        animation: false,
        responsive: true,
        maintainAspectRatio: false,
        plugins: {
            title: {
                display: true,
                text: 'Best soft score distribution summary (higher is better)'
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
  chart_bestScoreDistributionSummaryChart2_c5172.resize(1280, 720);
});
window.addEventListener('afterprint', () => {
  chart_bestScoreDistributionSummaryChart2_c5172.resize();
});