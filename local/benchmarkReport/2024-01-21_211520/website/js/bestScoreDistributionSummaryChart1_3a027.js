
var chart_bestScoreDistributionSummaryChart1_3a027 = new Chart(document.getElementById('chart_bestScoreDistributionSummaryChart1_3a027'), {
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
                                    min: 50,
                                    max: 50,
                                    q1: 50,
                                    q3: 50,
                                    median: 50,
                                    mean: 50,
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
                                    min: 50,
                                    max: 50,
                                    q1: 50,
                                    q3: 50,
                                    median: 50,
                                    mean: 50,
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
                            , 
                                {
                                    min: 20,
                                    max: 20,
                                    q1: 20,
                                    q3: 20,
                                    median: 20,
                                    mean: 20,
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
  chart_bestScoreDistributionSummaryChart1_3a027.resize(1280, 720);
});
window.addEventListener('afterprint', () => {
  chart_bestScoreDistributionSummaryChart1_3a027.resize();
});