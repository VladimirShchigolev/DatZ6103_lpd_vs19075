
var chart_bestScoreDistributionSummaryChart2_d86d0 = new Chart(document.getElementById('chart_bestScoreDistributionSummaryChart2_d86d0'), {
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
                                    min: 880,
                                    max: 880,
                                    q1: 880,
                                    q3: 880,
                                    median: 880,
                                    mean: 880,
                                    items: [],
                                    outliers: [],
                                }
                            , 
                                {
                                    min: 1346,
                                    max: 1346,
                                    q1: 1346,
                                    q3: 1346,
                                    median: 1346,
                                    mean: 1346,
                                    items: [],
                                    outliers: [],
                                }
                            , 
                                {
                                    min: 2228,
                                    max: 2228,
                                    q1: 2228,
                                    q3: 2228,
                                    median: 2228,
                                    mean: 2228,
                                    items: [],
                                    outliers: [],
                                }
                            , 
                                {
                                    min: 652,
                                    max: 652,
                                    q1: 652,
                                    q3: 652,
                                    median: 652,
                                    mean: 652,
                                    items: [],
                                    outliers: [],
                                }
                            , 
                                {
                                    min: 160,
                                    max: 160,
                                    q1: 160,
                                    q3: 160,
                                    median: 160,
                                    mean: 160,
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
  chart_bestScoreDistributionSummaryChart2_d86d0.resize(1280, 720);
});
window.addEventListener('afterprint', () => {
  chart_bestScoreDistributionSummaryChart2_d86d0.resize();
});