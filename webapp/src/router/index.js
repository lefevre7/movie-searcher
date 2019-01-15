import Vue from 'vue'
import Router from 'vue-router'
import MovieSearch from '@/components/MovieSearch'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Movie Search',
      component: MovieSearch
    }
  ]
})
