
var chart_bestScoreDistributionSummaryChart2_65a37 = new Chart(document.getElementById('chart_bestScoreDistributionSummaryChart2_65a37'), {
    type: 'boxplot',
    data: {
        labels: [
            'Problem_0', 'Problem_1', 'Problem_2', 'Problem_3', 'Problem_4'
        ],
        datasets: [
                {
                    label: 'Config_0 (favorite)',
                        borderWidth: 4
,
                    data: [
                                {
                                    min: 348,
                                    max: 348,
                                    q1: 348,
                                    q3: 348,
                                    median: 348,
                                    mean: 348,
                                    items: [],
                                    outliers: [],
                                }
                            , 
                                {
                                    min: 1000,
                                    max: 1000,
                                    q1: 1000,
                                    q3: 1000,
                                    median: 1000,
                                    mean: 1000,
                                    items: [],
                                    outliers: [],
                                }
                            , 
                                {
                                    min: 1336,
                                    max: 1336,
                                    q1: 1336,
                                    q3: 1336,
                                    median: 1336,
                                    mean: 1336,
                                    items: [],
                                    outliers: [],
                                }
                            , 
                                {
                                    min: 2500,
                                    max: 2500,
                                    q1: 2500,
                                    q3: 2500,
                                    median: 2500,
                                    mean: 2500,
                                    items: [],
                                    outliers: [],
                                }
                            , 
                                {
                                    min: 1125,
                                    max: 1125,
                                    q1: 1125,
                                    q3: 1125,
                                    median: 1125,
                                    mean: 1125,
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
  chart_bestScoreDistributionSummaryChart2_65a37.resize(1280, 720);
});
window.addEventListener('afterprint', () => {
  chart_bestScoreDistributionSummaryChart2_65a37.resize();
});