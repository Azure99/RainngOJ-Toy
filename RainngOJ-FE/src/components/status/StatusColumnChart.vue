<template>
  <div id="status-column-chart">

  </div>
</template>

<script>
import echarts from 'echarts'

export default {
  name: 'StatusColumnChart',
  methods: {
    initColumnChart: function () {
      let myChart = echarts.init(document.getElementById('status-column-chart'))
      let option = {
        color: ['#37A2DA', '#67E0E3'],
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'shadow'
          }
        },
        legend: {
          data: ['正确', '提交']
        },
        toolbox: {
          show: true,
          feature: {
            magicType: { type: ['line', 'bar'] },
            saveAsImage: {}
          }
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },
        yAxis: {
          type: 'value'
        },
        xAxis: {
          type: 'category',
          data: ['六天', '五天', '四天', '三天', '前天', '昨天', '今天']
        },
        series: [
          {
            name: '正确',
            type: 'bar',
            stack: '总量',
            data: [12, 33, 10, 33, 27, 38, 36]
          },
          {
            name: '提交',
            type: 'bar',
            stack: '总量',
            label: {
              normal: {
                show: true,
                position: 'top'
              }
            },
            data: [33, 56, 23, 44, 37, 61, 55]
          }
        ]
      }
      myChart.setOption(option)

      let resizing = false
      window.onresize = () => {
        if (resizing === false) {
          resizing = true
          setTimeout(() => {
            myChart.resize()
            resizing = false
          }, 500)
        }
      }
    }
  },
  mounted () {
    this.initColumnChart()
  }
}
</script>

<style scoped>
  #status-column-chart {
    padding-top: 10px;
    width: 100%;
    height: 350px;
    margin: auto;
  }
</style>
