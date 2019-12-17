<template>
  <span :style="{backgroundColor: getResultColorById(status.result)}"
        @click="showError(status.id, status.result)"
        class="status-list-result">
    {{getResultNameById(status.result)}}
    <img class="status-list-working" src="@/assets/working.gif" v-if="isRunning(status.result)"/>
  </span>
</template>

<script>
export default {
  name: 'StatusResult',
  props: ['status'],
  methods: {
    showError (id, result) {
      if (result === 2 || result === 3) {
        this.$router.push({ name: 'SolutionError', params: { id: id } })
      }
    },
    getResultColorById (id) {
      switch (id) {
        case -1:
          return 'black'
        case 0:
          return '#5cb85c'
        case 1:
          return '#d9534f'
        case 2:
          return 'dodgerblue'
        case 3:
          return 'dodgerblue'
        case 4:
          return '#f0ad4e'
        case 5:
          return '#f0ad4e'
        case 6:
          return '#f0ad4e'
        case 7:
          return '#d9534f'
        case 10:
          return 'gray'
        case 11:
          return '#f0ad4e'
        default:
          return 'gray'
      }
    },
    getResultNameById (id) {
      switch (id) {
        case -1:
          return '评测失败'
        case 0:
          return '正确'
        case 1:
          return '答案错误'
        case 2:
          return '编译错误'
        case 3:
          return '运行错误'
        case 4:
          return '时间超限'
        case 5:
          return '内存超限'
        case 6:
          return '输出超限'
        case 7:
          return '格式错误'
        case 10:
          return '等待'
        case 11:
          return '评测中'
        default:
          return '未知结果'
      }
    },
    isRunning (result) {
      return result === 10 || result === 11
    }
  }
}
</script>

<style scoped>
  .status-list-result {
    cursor: pointer;
    background-color: #00abff;
    padding: 5px 15px 5px 15px;
    border-radius: 25px;
    color: white;
  }

  .status-list-working {
    width: 15px;
    height: 15px;
    margin-bottom: -1px;
  }
</style>
