<template>
  <div id="news-carousel">
    <el-carousel class="news-carousel" height="150px" element-loading-background="#364D79"
                 v-loading="loading">
      <el-carousel-item class="carousel-item" v-for="item in data" :key="item.id">
        <div class="carousel-item-container">

          <div class="carousel-item-title">{{ item.title }}</div>
          <div class="carousel-item-content">{{ item.content }}</div>
          <div class="carousel-item-button">
            <el-button size="mini" @click="showNews(item.id)" round v-show="item.content.length > 35">
              查看更多
            </el-button>
          </div>

        </div>
      </el-carousel-item>
    </el-carousel>
  </div>
</template>

<script>
export default {
  name: 'NewsCarousel',
  data () {
    return {
      loading: true,
      data: [{ id: 1, title: '', content: '' }]
    }
  },
  methods: {
    getLatestNews () {
      this.$ajax.get('/news/getLatestNews').then(res => {
        this.data = res
        this.loading = false
      })
    },
    showNews (id) {
      this.$router.push({ name: 'NewsDetail', params: { id: id } })
    }
  },
  created () {
    this.getLatestNews()
  }
}
</script>

<style scoped>
  .news-carousel {
    background-color: #364D79;
    border-radius: 10px;
  }

  .carousel-item {
    background-color: #364D79;
    color: white;
    text-align: center;
  }

  .carousel-item-container {
    height: 100%;
    width: 85%;
    margin: auto;
  }

  .carousel-item-title {
    font-size: 22px;
    top: 12%;
    position: relative;
  }

  .carousel-item-content {
    top: 14%;
    position: relative;
    line-height: 20px;
    height: 26%;
    overflow: hidden;
  }

  .carousel-item-button {
    top: 16%;
    position: relative;
  }
</style>
