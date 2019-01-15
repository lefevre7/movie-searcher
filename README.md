# movie-searcher
Searches movies from TMDB

## Usage

1. Clone https://github.com/lefevre7/movie-searcher

2. Open webapp and webserver (with gradle.settings) as different projects.

3. Make sure to have Kotlin (latest is alright) and Gradle (4.x latest not 5.x because that caused me problems) installed.

4. Make sure kotlin is configured (for Intellij it's in the project structure>problems>configure Kotlin and/or structure>facets>configure Kotlin).

5. Run the backend (webserver) which uses port 8081.

6. Run the frontend (webapp) which uses port 8082 (npm install, npm run dev).

7. Go to http://localhost:8082 and search a movie title!
