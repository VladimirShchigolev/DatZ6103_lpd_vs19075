
var chart_bestScoreDistributionSummaryChart1_1ee06 = new Chart(document.getElementById('chart_bestScoreDistributionSummaryChart1_1ee06'), {
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
                                    min: 16,
                                    max: 16,
                                    q1: 16,
                                    q3: 16,
                                    median: 16,
                                    mean: 16,
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
                                    min: 16,
                                    max: 16,
                                    q1: 16,
                                    q3: 16,
                                    median: 16,
                                    mean: 16,
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
                                    min: 100,
                                    max: 100,
                                    q1: 100,
                                    q3: 100,
                                    median: 100,
                                    mean: 100,
                                    items: [],
                                    outliers: [],
                                }
                            , 
                                {
                                    min: 66,
                                    max: 66,
                                    q1: 66,
                                    q3: 66,
                                    median: 66,
                                    mean: 66,
                                    items: [],
                                    outliers: [],
                                }
                            , 
                                {
                                    min: 80,
                                    max: 80,
                                    q1: 80,
                                    q3: 80,
                                    median: 80,
                                    mean: 80,
                                    items: [],
                                    outliers: [],
                                }
                            , 
                                {
                                    min: 75,
                                    max: 75,
                                    q1: 75,
                                    q3: 75,
                                    median: 75,
                                    mean: 75,
                                    items: [],
                                    outliers: [],
                                }
                            , 
                                {
                                    min: 16,
                                    max: 16,
                                    q1: 16,
                                    q3: 16,
                                    median: 16,
                                    mean: 16,
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
                text: 'Best medium score distribution summary (higher is better)'
            }
        },
        scales: {
            x: {
                display: true
            },
            y: {
                title: {
                    display: true,
                    text: 'Best medium score'
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
  chart_bestScoreDistributionSummaryChart1_1ee06.resize(1280, 720);
});
window.addEventListener('afterprint', () => {
  chart_bestScoreDistributionSummaryChart1_1ee06.resize();
});