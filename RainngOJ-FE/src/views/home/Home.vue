<template>
  <div id="home">
    <el-container>
      <el-main>
        <div id="home-main">
          <t-zoom-in-top>
            <div id="home-title">
              <p id="home-title-h">Welcome to <b>R</b>ainng <b>OJ</b></p>
            </div>
          </t-zoom-in-top>
          <t-zoom-in-bottom>
            <news-carousel/>
          </t-zoom-in-bottom>
          <div>
            <status-column-chart/>
          </div>
        </div>
      </el-main>

      <el-aside id="home-aside" width="20%">
        <status-pie-chart/>
        <t-zoom-in-top>
          <user-top/>
        </t-zoom-in-top>
      </el-aside>
    </el-container>
  </div>
</template>

<script>
import NewsCarousel from '@/components/news/NewsCarousel'
import UserTop from '@/components/user/UserTop'
import StatusPieChart from '@/components/status/StatusPieChart'
import StatusColumnChart from '@/components/status/StatusColumnChart'

export default {
  name: 'Home',
  components: { StatusColumnChart, StatusPieChart, UserTop, NewsCarousel },
  data () {
    return {
      asideShow: false
    }
  },
  mounted () {
    this.asideShow = true
  }
}
</script>

<style scoped>
  #home-title {
    height: 50px;
    width: 100%;
    background-color: #6ACC98;
    border-radius: 10px;
    display: flex;
    align-items: center;
  }

  #home-title-h {
    margin: auto;
    color: white;
    font-size: 24px;
  }

  #home-aside {
    overflow: hidden;
  }
</style>
