<template>
  <div id="status-pie-chart" v-loading="loading">

  </div>
</template>

<script>
import echarts from 'echarts'

export default {
  name: 'StatusPieChart',
  data () {
    return {
      loading: true
    }
  },
  methods: {
    initPieChart (res) {
      let myChart = echarts.init(document.getElementById('status-pie-chart'))
      let option = {
        color: ['#B22222', '#33CC33', '#67E0E3', '#FEDB5B', '#708090'],
        series: [
          {
            name: '结果统计',
            type: 'pie',
            radius: '50%',
            data: res,
            itemStyle: {
              emphasis: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            }
          }
        ]
      }
      myChart.setOption(option)
    }
  },
  mounted () {
    this.$ajax.get('/status/getStatusPieCount').then(res => {
      this.initPieChart(res)
      this.loading = false
    })
  }
}
</script>

<style scoped>
  #status-pie-chart {
    height: 150px;
    width: 180px;
    margin: auto;
  }
</style>
