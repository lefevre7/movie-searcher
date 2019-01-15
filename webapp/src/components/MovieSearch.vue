<template>

  <div class="movie-search">
    <div>
      <h1>TMDB Movie Search</h1>
      <input type="text" v-model="input.searchTerm" placeholder="Search Term" />
      <button v-on:click="search()">Search</button>
      <br />
      <br />
      <p>{{results}}</p>
      <div class="columns">
         <div class="column">
            <movie-component v-for="movie in movies" :movie="movie" :key="movie.movie_id"/>
         </div>
      </div>
    </div>
  </div>
</template>

<script>

import Vue from 'vue'
import axios from 'axios'

Vue.component('movie-component', {
  template: `
    <div class="movie">
      <div class="box">
        <article class="media">
          <div class="media-left">
            <figure class="image is-64x64">
              <img :src="movie.poster_image_url" alt="Image">
            </figure>
          </div>
          <div class="media-content">
            <div class="content">
              <p>
                <strong>{{movie.title}}</strong> <small>{{movie.popularity_summary}}</small>
                <br>
                {{movie.overview}}
              </p>
            </div>
          </div>
        </article>
      </div>
    </div>
  `,
  props: {
    movie: Object
  }
})

export default {
  name: 'movie-search',
  data () {
    return {
      input: {
        searchTerm: ''
      },
      movies: '',
      results: ''
    }
  },
  methods: {
    search () {
      axios({ method: 'GET',
        'url': 'http://localhost:8081/movie',
        params: { search: this.input.searchTerm },
        'headers': { 'content-type': 'application/json',
          'Access-Control-Allow-Origin': '*',
          'Access-Control-Allow-Methods': 'POST, GET, OPTIONS, DELETE, PUT',
          'Access-Control-Allow-Credentials': 'true',
          'Accept': 'application/json',
          'Access-Control-Allow-Headers': 'Origin, X-Requested-With, Content-Type, Accept' }
      }).then(result => {
        this.movies = result.data
        this.results = 'There are ' + result.data.length + ' results'
      }, error => {
        console.error(error)
      })
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style>
h1, h2 {
  font-weight: normal;
}

ul {
  list-style-type: none;
  padding: 0;
}

li {
  display: inline-block;
  margin: 0 10px;
}

a {
  color: #35495E;
}
</style>
