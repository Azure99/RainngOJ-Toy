import Vue from 'vue'
import Router from 'vue-router'
import Home from './views/home/Home'
import Problem from './views/problem/Problem'
import ProblemDetail from './views/problem/ProblemDetail'
import ProblemEdit from './views/problem/ProblemEdit'
import Contest from './views/contest/Contest'
import ContestDetail from './views/contest/ContestDetail'
import Rank from './views/rank/Rank'
import Status from './views/status/Status'
import SolutionError from './views/status/SolutionError'
import SolutionDetail from './views/status/SolutionDetail'
import NewsDetail from './views/news/NewsDetail'
import UserRegister from '@/views/user/UserRegister'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Home',
      component: Home
    },
    {
      path: '/user/register',
      name: 'UserRegister',
      component: UserRegister
    },
    {
      path: '/problem',
      name: 'Problem',
      component: Problem
    },
    {
      path: '/problem/detail/:id',
      name: 'ProblemDetail',
      component: ProblemDetail
    },
    {
      path: '/problem/edit/:id',
      name: 'ProblemEdit',
      component: ProblemEdit
    },
    {
      path: '/contest',
      name: 'Contest',
      component: Contest
    },
    {
      path: '/contest/detail/:id',
      name: 'ContestDetail',
      component: ContestDetail
    },
    {
      path: '/rank',
      name: 'Rank',
      component: Rank
    },
    {
      path: '/status',
      name: 'Status',
      component: Status
    },
    {
      path: '/status/error/:id',
      name: 'SolutionError',
      component: SolutionError
    },
    {
      path: '/status/solution/:id',
      name: 'SolutionDetail',
      component: SolutionDetail
    },
    {
      path: '/news/detail/:id',
      name: 'NewsDetail',
      component: NewsDetail
    }
  ]
})
