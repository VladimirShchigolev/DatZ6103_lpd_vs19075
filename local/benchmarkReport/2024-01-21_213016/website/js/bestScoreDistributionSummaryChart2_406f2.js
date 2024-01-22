
var chart_bestScoreDistributionSummaryChart2_406f2 = new Chart(document.getElementById('chart_bestScoreDistributionSummaryChart2_406f2'), {
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
                                {
                                    min: 365,
                                    max: 365,
                                    q1: 365,
                                    q3: 365,
                                    median: 365,
                                    mean: 365,
                                    items: [],
                                    outliers: [],
                                }
                            , 
                                {
                                    min: 941,
                                    max: 941,
                                    q1: 941,
                                    q3: 941,
                                    median: 941,
                                    mean: 941,
                                    items: [],
                                    outliers: [],
                                }
                            , 
                                {
                                    min: 1240,
                                    max: 1240,
                                    q1: 1240,
                                    q3: 1240,
                                    median: 1240,
                                    mean: 1240,
                                    items: [],
                                    outliers: [],
                                }
                            , 
                                {
                                    min: 2421,
                                    max: 2421,
                                    q1: 2421,
                                    q3: 2421,
                                    median: 2421,
                                    mean: 2421,
                                    items: [],
                                    outliers: [],
                                }
                            , 
                                {
                                    min: 2344,
                                    max: 2344,
                                    q1: 2344,
                                    q3: 2344,
                                    median: 2344,
                                    mean: 2344,
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
                                {
                                    min: 331,
                                    max: 331,
                                    q1: 331,
                                    q3: 331,
                                    median: 331,
                                    mean: 331,
                                    items: [],
                                    outliers: [],
                                }
                            , 
                                {
                                    min: 941,
                                    max: 941,
                                    q1: 941,
                                    q3: 941,
                                    median: 941,
                                    mean: 941,
                                    items: [],
                                    outliers: [],
                                }
                            , 
                                {
                                    min: 1225,
                                    max: 1225,
                                    q1: 1225,
                                    q3: 1225,
                                    median: 1225,
                                    mean: 1225,
                                    items: [],
                                    outliers: [],
                                }
                            , 
                                {
                                    min: 2438,
                                    max: 2438,
                                    q1: 2438,
                                    q3: 2438,
                                    median: 2438,
                                    mean: 2438,
                                    items: [],
                                    outliers: [],
                                }
                            , 
                                {
                                    min: 2344,
                                    max: 2344,
                                    q1: 2344,
                                    q3: 2344,
                                    median: 2344,
                                    mean: 2344,
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
                                    min: 365,
                                    max: 365,
                                    q1: 365,
                                    q3: 365,
                                    median: 365,
                                    mean: 365,
                                    items: [],
                                    outliers: [],
                                }
                            , 
                                {
                                    min: 941,
                                    max: 941,
                                    q1: 941,
                                    q3: 941,
                                    median: 941,
                                    mean: 941,
                                    items: [],
                                    outliers: [],
                                }
                            , 
                                {
                                    min: 1265,
                                    max: 1265,
                                    q1: 1265,
                                    q3: 1265,
                                    median: 1265,
                                    mean: 1265,
                                    items: [],
                                    outliers: [],
                                }
                            , 
                                {
                                    min: 2402,
                                    max: 2402,
                                    q1: 2402,
                                    q3: 2402,
                                    median: 2402,
                                    mean: 2402,
                                    items: [],
                                    outliers: [],
                                }
                            , 
                                {
                                    min: 2344,
                                    max: 2344,
                                    q1: 2344,
                                    q3: 2344,
                                    median: 2344,
                                    mean: 2344,
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
  chart_bestScoreDistributionSummaryChart2_406f2.resize(1280, 720);
});
window.addEventListener('afterprint', () => {
  chart_bestScoreDistributionSummaryChart2_406f2.resize();
});