import Vue from 'vue'
import MovieSearch from '@/components/MovieSearch'

describe('MovieSearch.vue', () => {
  it('should render correct contents', () => {
    const Constructor = Vue.extend(MovieSearch)
    const vm = new Constructor().$mount()
    expect(vm.$el.querySelector('.movie-search h1').textContent)
      .to.equal('TMDB Movie Search')
  })
})
