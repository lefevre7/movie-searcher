<template>
  <div class="movie-search">
    <div>
      <h1>TMDB Movie Search</h1>
      <input id="input" type="text" v-model="input.searchTerm" placeholder="Search Term" />
      <button id="button" type="submit" v-on:click="search()">Search</button>
      <br />
      <br />
      <p>{{results}}</p>
      <div class="loader"></div>
      <div class="rows">
         <div class="rows">
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
            <figure>
              <img :src="movie.poster_image_url" alt="Image" :title="movie.overview">
            </figure>
          </div>
          <div class="media-content">
            <div class="content">
              <p>
                <strong>{{movie.title}}</strong>
                <br>
                <small>{{movie.popularity_summary}}</small>
                <br>
                <br>
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
      document.getElementsByClassName('loader')[0].style.display = 'inline-block'
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
        this.results = 'Displaying ' + result.data.length + ' results'
        var elems = document.getElementsByClassName('loader')
        for (var i = 0; i < elems.length; i += 1) {
          elems[i].style.display = 'none'
        }
      }, error => {
        console.error(error)
        this.results = 'There was an error: ' + error
        var elems = document.getElementsByClassName('loader')
        for (var i = 0; i < elems.length; i += 1) {
          elems[i].style.display = 'none'
        }
      })
    }
  }
}
window.onload = function () {
  var input = document.getElementById('input')
  input.addEventListener('keyup', function (event) {
    event.preventDefault()
    if (event.keyCode === 13) {
      document.getElementById('button').click()
    }
  })
}
</script>

<!-- Add "scoped" attribute to limit CSS to this template only -->
<style>
h1, h2 {
  font-weight: normal;
}

li {
  display: inline-block;
  margin: 0 10px;
}

.rows .movie {
  display: inline-block;
}

input, button {
  font-size: 130%;
  line-height: 1.5;
  padding-left: 5px;
}

.loader {
  display: none;
  border: 5px solid #f3f3f3;
  border-radius: 50%;
  border-top: 5px solid gray;
  width: 30px;
  height: 30px;
  -webkit-animation: spin 1s linear infinite; /* Safari */
  animation: spin 1s linear infinite;
}

/* Safari */
@-webkit-keyframes spin {
  0% { -webkit-transform: rotate(0deg); }
  100% { -webkit-transform: rotate(360deg); }
}

@keyframes spin {
  0% { transform: rotate(0deg); }
  100% { transform: rotate(360deg); }
}
</style>
