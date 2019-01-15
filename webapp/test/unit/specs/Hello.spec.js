import Vue from 'vue'
import MovieSearch from '@/components/MovieSearch'

describe('MovieSearch.vue', () => {
  it('should render correct contents', () => {
    const Constructor = Vue.extend(MovieSearch)
    const vm = new Constructor().$mount()
    expect(vm.$el.querySelector('.hello h1').textContent)
      .to.equal('Welcome to Your Vue.js PWA')
  })
})
