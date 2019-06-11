<template>
  <div id="news-detail" v-loading="loading">
    <t-zoom-in-top>
      <h2 id="news-detail-title">{{news.id}} - {{news.title}}</h2>

      <el-card shadow="always">
        <div v-html="news.content">{{news.content}}</div>
      </el-card>

      <div id="news-detail-date">
        {{news.date}}
      </div>
    </t-zoom-in-top>
  </div>
</template>

<script>
export default {
  name: 'NewsDetail',
  data () {
    return {
      loading: true,
      news: {
        id: '',
        title: '',
        content: '',
        date: ''
      }
    }
  },
  created () {
    this.$ajax.get('/news/getNews', {
      id: this.$route.params.id
    }).then(res => {
      this.news = res
      this.loading = false
    })
  }
}
</script>

<style scoped>
  #news-detail-title {
    text-align: center;
  }

  #news-detail-date {
    margin-top: 10px;
    text-align: right;
  }
</style>
